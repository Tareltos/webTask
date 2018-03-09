package by.tareltos.webtask.xmlparser;


import by.tareltos.webtask.entity.*;

import com.sun.org.apache.bcel.internal.generic.SWITCH;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;

public class CandiesSAXParser extends DefaultHandler {

    final static Logger LOGGER = LogManager.getLogger();
    private Set<Candy> candies;
    private Candy currentCh = null;
    private CandyEnum currentEnum = null;
    private EnumSet<CandyEnum> withTextIngr;
    private EnumSet<CandyEnum> withTextVal;
    private EnumSet<CandyEnum> simpleEl;

    public CandiesSAXParser() {
        candies = new HashSet<>();
        withTextIngr = EnumSet.range(CandyEnum.CHOCOLATETYPE, CandyEnum.VANILLIN);
        withTextVal = EnumSet.range(CandyEnum.PROTEINS, CandyEnum.CARBOHYDRATES);
        simpleEl = EnumSet.range(CandyEnum.DATE, CandyEnum.ENERGY);
    }

    @Override
    public void startDocument() throws SAXException {
        LOGGER.log(Level.INFO, "Parsing started");

    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {

        switch (localName) {
            case "chocolate":
                currentCh = new Chocolate();
                setAttributes(attributes);
                for (int i = 0; i < attributes.getLength(); i++) {
                    if ("type".equals(attributes.getLocalName(i))) {
                        LOGGER.log(Level.DEBUG, "Type Ch: " + attributes.getValue(i));
                        ((Chocolate) currentCh).setType(attributes.getValue(i));
                        break;
                    }
                }
                return;
            case "caramel":
                currentCh = new Caramel();
                setAttributes(attributes);
                return;
            default:
                CandyEnum temp = CandyEnum.valueOf(localName.toUpperCase());
                if (temp == CandyEnum.INGREDIENTS) {
                    LOGGER.log(Level.DEBUG, "Cocolate type:" + attributes.getValue(0));
                    currentCh.getIngredients().setChocolatetype(attributes.getValue(0));
                }
                if (withTextIngr.contains(temp)) {
                    currentEnum = temp;
                }
                if (withTextVal.contains(temp)) {
                    currentEnum = temp;
                }
                if (simpleEl.contains(temp)) {

                    currentEnum = temp;
                }

        }

    }

    private void setAttributes(Attributes attributes) {

        if (currentCh.getIngredients() == null) {
            currentCh.setIngredients(new Ingredients());
        }
        if (currentCh.getEnergyvalue() == null) {
            currentCh.setEnergyvalue(new Energyvalue());
        }
        for (int i = 0; i < attributes.getLength(); i++) {
            switch (attributes.getLocalName(i)) {
                case "name":
                    LOGGER.log(Level.DEBUG, "Name: " + attributes.getValue(i));
                    currentCh.setName(attributes.getValue(i));
                    break;
                case "production":
                    LOGGER.log(Level.DEBUG, "Production: " + attributes.getValue(i));
                    currentCh.setProduction(attributes.getValue(i));
                    break;
            }
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        String s = new String(ch, start, length).trim();

        if (currentEnum != null) {
            switch (currentEnum) {
                case DATE:
                    XMLGregorianCalendar xmlGregorianCalendar = null;
                    try {
                        xmlGregorianCalendar = DatatypeFactory.newInstance().newXMLGregorianCalendar(s);
                        LOGGER.log(Level.DEBUG, "Date: " + s);
                    } catch (DatatypeConfigurationException e) {
                        e.printStackTrace();
                    }
                    currentCh.setDate(xmlGregorianCalendar);
                    break;
                case ENERGY:
                    if (s.isEmpty()) {
                        currentCh.setDefaultEnergy();
                    } else {
                        currentCh.setEnergy(Double.parseDouble(s));
                        LOGGER.log(Level.DEBUG, "Energy " + s);
                    }
                    break;
                case DESCRIPTION:
                    currentCh.setDescription(s);
                    LOGGER.log(Level.DEBUG, s);
                    break;
                case PROTEINS:
                    currentCh.getEnergyvalue().setProteins(Double.parseDouble(s));
                    LOGGER.log(Level.DEBUG, "Proteins : " + s);
                    break;
                case FATS:
                    currentCh.getEnergyvalue().setFats(Double.parseDouble(s));
                    LOGGER.log(Level.DEBUG, "Fats : " + s);
                    break;
                case CARBOHYDRATES:
                    currentCh.getEnergyvalue().setCarbohydrates(Double.parseDouble(s));
                    LOGGER.log(Level.DEBUG, "HR : " + s);
                    break;
                case CHOCOLATETYPE:
                    currentCh.getIngredients().setChocolatetype(s);
                    LOGGER.log(Level.DEBUG, "CH Type : " + s);
                    break;
                case WATER:
                    LOGGER.log(Level.DEBUG, "water : " + s);
                    currentCh.getIngredients().setWater(Boolean.parseBoolean(s));

                    break;
                case SUGAR:
                    currentCh.getIngredients().setSugar(Double.parseDouble(s));
                    LOGGER.log(Level.DEBUG, "sugar : " + s);
                    break;
                case VANILLIN:
                    currentCh.getIngredients().setVanillin(Double.parseDouble(s));
                    LOGGER.log(Level.DEBUG, "Vanillin : " + s);
                    break;
                case FRUCTOSE:
                    currentCh.getIngredients().setFructose(Double.parseDouble(s));
                    LOGGER.log(Level.DEBUG, "Fruct : " + s);
                    break;
                default:
                    throw new EnumConstantNotPresentException(
                            currentEnum.getDeclaringClass(), currentEnum.name());
            }
        }
        currentEnum = null;
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {

        if ("chocolate".equals(localName) || "caramel".equals(localName)) {
            candies.add(currentCh);
        }
    }

    @Override
    public void endDocument() throws SAXException {
        LOGGER.log(Level.INFO, "Parsing ended");
    }

    public Set<Candy> getCandies() {
        return candies;
    }
}
