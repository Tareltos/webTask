package by.tareltos.webtask.builder;

import by.tareltos.webtask.entity.*;
import by.tareltos.webtask.xmlparser.CandyEnum;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class CandiesStAXBuilder extends AbstractCandiesBuilder {
    final static Logger LOGGER = LogManager.getLogger();

    private XMLInputFactory inputFactory;

    public CandiesStAXBuilder() {
        candies = new HashSet<>();
        inputFactory = XMLInputFactory.newInstance();
    }

    public void buildSetCandies(String fileName) {
        FileInputStream inputStream = null;
        XMLStreamReader reader;
        String name;
        try {
            inputStream = new FileInputStream(new File(fileName));
            reader = inputFactory.createXMLStreamReader(inputStream);
            while (reader.hasNext()) {
                int type = reader.next();
                if (type == XMLStreamConstants.START_ELEMENT) {
                    name = reader.getLocalName();
                    if (CandyEnum.valueOf(name.toUpperCase()) == CandyEnum.CHOCOLATE || CandyEnum.valueOf(name.toUpperCase()) == CandyEnum.CARAMEL) {
                        Candy candy = buildCandy(reader);
                        candies.add(candy);
                    }
                }
            }
        } catch (XMLStreamException ex) {
            LOGGER.log(Level.ERROR, "StAX parsing error! " + ex.getMessage());
        } catch (FileNotFoundException ex) {
            LOGGER.log(Level.WARN, "File " + fileName + " not found! " + ex);
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                LOGGER.log(Level.WARN, "Impossible close file " + fileName + " : " + e);
            }
        }
    }

    private Candy buildCandy(XMLStreamReader reader) throws XMLStreamException {
        Candy candy;
        if (reader.getAttributeValue(null, CandyEnum.TYPE.getValue()) != "Caramel" & reader.getAttributeValue(null, CandyEnum.TYPE.getValue()) != null) {
            candy = new Chocolate();
            ((Chocolate) candy).setType(reader.getAttributeValue(null, CandyEnum.TYPE.getValue()));
        } else {
            candy = new Caramel();
            String type=reader.getAttributeValue(null, CandyEnum.TYPE.getValue());
            if(type==null){
                type = ((Caramel)candy).getType();
            }

            ((Caramel) candy).setType(type);
        }
        candy.setName(reader.getAttributeValue(null, CandyEnum.NAME.getValue()));
        if (reader.getAttributeValue(null, CandyEnum.PRODUCTION.getValue()) != null) {
            candy.setProduction(reader.getAttributeValue(null, CandyEnum.PRODUCTION.getValue()));
        }

        String name;
        while (reader.hasNext()) {
            int type = reader.next();
            switch (type) {
                case XMLStreamConstants.START_ELEMENT:
                    name = reader.getLocalName();
                    switch (CandyEnum.valueOf(name.toUpperCase())) {
                        case DATE:
                            XMLGregorianCalendar xmlGregorianCalendar = null;
                            try {
                                xmlGregorianCalendar = DatatypeFactory.newInstance().newXMLGregorianCalendar(getXMLText(reader));
                                LOGGER.log(Level.DEBUG, "Date: " + xmlGregorianCalendar);
                            } catch (DatatypeConfigurationException e) {
                                e.printStackTrace();
                            }
                            candy.setDate(xmlGregorianCalendar);
                            break;
                        case ENERGY:
                            String result = getXMLText(reader);
                            if (result=="") {
                                candy.setDefaultEnergy();
                            } else {
                                candy.setEnergy(Double.parseDouble(result));
                            }
                            LOGGER.log(Level.DEBUG, "Energy: " + candy.getEnergy());
                            break;
                        case DESCRIPTION:
                            candy.setDescription(getXMLText(reader));
                            LOGGER.log(Level.DEBUG, "Descr: " + candy.getDescription());
                            break;
                        case INGREDIENTS:
                            candy.setIngredients(getXMLIngredients(reader));
                            break;
                        case ENERGYVALUE:
                            candy.setEnergyvalue(getXMLEnergyvalue(reader));
                            break;
                    }
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    name = reader.getLocalName();
                    if (CandyEnum.valueOf(name.toUpperCase()) == CandyEnum.CARAMEL || CandyEnum.valueOf(name.toUpperCase()) == CandyEnum.CHOCOLATE) {
                        return candy;
                    }

                    break;
            }
        }
        throw new XMLStreamException("Unknown element in tag Caramel or Chocolate");
    }

    private Ingredients getXMLIngredients(XMLStreamReader reader) throws XMLStreamException {

        Ingredients ingredients = new Ingredients();
        int type;
        String name;
        if (reader.getAttributeValue(null, CandyEnum.CHOCOLATETYPE.getValue()) != null) {
            ingredients.setChocolatetype(reader.getAttributeValue(null, CandyEnum.CHOCOLATETYPE.getValue()));
            LOGGER.log(Level.DEBUG, "ING Choco: " + ingredients.getChocolatetype());
        }
        while (reader.hasNext()) {
            type = reader.next();
            switch (type) {
                case XMLStreamConstants.START_ELEMENT:
                    name = reader.getLocalName();
                    switch (CandyEnum.valueOf(name.toUpperCase())) {
                        case WATER:
                            ingredients.setWater(Boolean.parseBoolean(getXMLText(reader)));
                            LOGGER.log(Level.DEBUG, "ING Water: " + ingredients.isWater());
                            break;
                        case SUGAR:
                            ingredients.setSugar(Double.parseDouble(getXMLText(reader)));
                            LOGGER.log(Level.DEBUG, "ING Sugar: " + ingredients.getSugar());
                            break;
                        case VANILLIN:
                            ingredients.setVanillin(Double.parseDouble(getXMLText(reader)));
                            LOGGER.log(Level.DEBUG, "ING VAN: " + ingredients.getVanillin());
                            break;
                        case FRUCTOSE:
                            ingredients.setFructose(Double.parseDouble(getXMLText(reader)));
                            LOGGER.log(Level.DEBUG, "ING Fru: " + ingredients.getFructose());
                            break;
                    }
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    name = reader.getLocalName();
                    if (CandyEnum.valueOf(name.toUpperCase()) == CandyEnum.INGREDIENTS) {
                        return ingredients;
                    }
                    break;
            }
        }
        throw new XMLStreamException("Unknown element in tag ingredients");

    }

    private Energyvalue getXMLEnergyvalue(XMLStreamReader reader) throws XMLStreamException {

        String name;
        Energyvalue ev = new Energyvalue();
        int type;
        while (reader.hasNext()) {
            type = reader.next();
            switch (type) {
                case XMLStreamConstants.START_ELEMENT:
                    name = reader.getLocalName();
                    switch (CandyEnum.valueOf(name.toUpperCase())) {
                        case PROTEINS:
                            ev.setProteins(Double.parseDouble(getXMLText(reader)));
                            LOGGER.log(Level.DEBUG, "EV proteins: " + ev.getProteins());
                            break;
                        case CARBOHYDRATES:
                            ev.setCarbohydrates(Double.parseDouble(getXMLText(reader)));
                            LOGGER.log(Level.DEBUG, "EV ch: " + ev.getCarbohydrates());
                            break;
                        case FATS:
                            ev.setFats(Double.parseDouble(getXMLText(reader)));
                            LOGGER.log(Level.DEBUG, "EV FATS: " + ev.getFats());
                            break;
                    }
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    name = reader.getLocalName();
                    if (CandyEnum.valueOf(name.toUpperCase()) == CandyEnum.ENERGYVALUE) {

                        return ev;
                    }

                    break;
            }
        }
        throw new XMLStreamException("Unknown element in tag energyvalue");

    }

    private String getXMLText(XMLStreamReader reader) throws XMLStreamException {
        String text = null;
        if (reader.hasNext()) {
            reader.next();
            try {
                text = reader.getText();
            } catch (IllegalStateException e) {
                LOGGER.log(Level.DEBUG, "Element: " + reader.getLocalName() + " is Empty");
                text = "";
            }
        }
        return text;
    }
}