package by.tareltos.webtask.builder;

import by.tareltos.webtask.entity.*;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Set;

public class CandiesDOMBuilder extends AbstractCandiesBuilder {

    final static Logger LOGGER = LogManager.getLogger();

    private DocumentBuilder docBuilder;

    public CandiesDOMBuilder() {
        this.candies = new HashSet<>();
        //создание анализатора
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            docBuilder = factory.newDocumentBuilder();

        } catch (ParserConfigurationException e) {
            LOGGER.catching(Level.FATAL, e);
        }
    }
    public void buildSetCandies(String fileName) {

        Document doc = null;
        try {
            // parsing XML-документа и создание древовидной структуры
            doc = docBuilder.parse(fileName);
            Element root = doc.getDocumentElement();
            // получение списка дочерних элементов <chocolate>
            NodeList chocolateList = root.getElementsByTagName("chocolate");
            for (int i = 0; i < chocolateList.getLength(); i++) {
                Element chocolateElement = (Element) chocolateList.item(i);
                Chocolate chocolate = buildChocolate(chocolateElement);
                candies.add(chocolate);
            }
            NodeList caramelList = root.getElementsByTagName("caramel");
            for (int i = 0; i < caramelList.getLength(); i++) {
                Element caramelElement = (Element) caramelList.item(i);
                Caramel caramel = buildCaramel(caramelElement);
                candies.add(caramel);
            }

        } catch (IOException e) {
            System.err.println("File error or I/O error: " + e);
        } catch (SAXException e) {
            System.err.println("Parsing failure: " + e);
        } catch (DatatypeConfigurationException e) {
            e.printStackTrace();
        }
    }

    private Caramel buildCaramel(Element caramelElement) throws DatatypeConfigurationException {
        Caramel caramel = new Caramel();
        // заполнение объекта chocolate
        caramel.setName(caramelElement.getAttribute("name"));
        Element val = (Element) caramelElement.getElementsByTagName("energy").item(0);
        caramel.setEnergy(Double.parseDouble(val.getTextContent()));
        caramel.setType(caramelElement.getAttribute("type"));
        if (caramelElement.getAttribute("production") != null) {
            caramel.setProduction(caramelElement.getAttribute("production"));
        }
        Ingredients ingredients = new Ingredients();
        Element ingrids = (Element) caramelElement.getElementsByTagName("ingredients").item(0);
        ingredients.setWater(Boolean.parseBoolean(getElementTextContent(ingrids, "water")));
        ingredients.setSugar(Double.parseDouble(getElementTextContent(ingrids, "sugar")));
        ingredients.setFructose(Double.parseDouble(getElementTextContent(ingrids, "fructose")));
        ingredients.setVanillin(Double.parseDouble(getElementTextContent(ingrids, "vanillin")));
        ingredients.setChocolatetype(ingrids.getAttribute("chocolatetype"));
        caramel.setIngredients(ingredients);

        Energyvalue energyvalue = new Energyvalue();
        Element enV = (Element) caramelElement.getElementsByTagName("energyvalue").item(0);
        energyvalue.setProteins(Double.parseDouble(getElementTextContent(enV, "proteins")));
        energyvalue.setFats(Double.parseDouble(getElementTextContent(enV, "fats")));
        energyvalue.setCarbohydrates(Double.parseDouble(getElementTextContent(enV, "carbohydrates")));
        caramel.setEnergyvalue(energyvalue);

        Element discr = (Element) caramelElement.getElementsByTagName("description").item(0);
        caramel.setDescription(discr.getTextContent());

        XMLGregorianCalendar xmlGregorianCalendar = DatatypeFactory.newInstance().newXMLGregorianCalendar(caramelElement.getElementsByTagName("date").item(0).getTextContent());

        caramel.setDate(xmlGregorianCalendar);

        return caramel;

    }

    private Chocolate buildChocolate(Element chocolateElement) throws DatatypeConfigurationException {

        Chocolate chocolate = new Chocolate();
        // заполнение объекта chocolate
        chocolate.setName(chocolateElement.getAttribute("name"));
        Element val = (Element) chocolateElement.getElementsByTagName("energy").item(0);
        chocolate.setEnergy(Double.parseDouble(val.getTextContent()));
        chocolate.setType(chocolateElement.getAttribute("type"));
        if (chocolateElement.getAttribute("production") != null) {
            chocolate.setProduction(chocolateElement.getAttribute("production"));
        }
        Ingredients ingredients = new Ingredients();
        Element ingrids = (Element) chocolateElement.getElementsByTagName("ingredients").item(0);
        ingredients.setWater(Boolean.parseBoolean(getElementTextContent(ingrids, "water")));
        ingredients.setSugar(Double.parseDouble(getElementTextContent(ingrids, "sugar")));
        ingredients.setFructose(Double.parseDouble(getElementTextContent(ingrids, "fructose")));
        ingredients.setVanillin(Double.parseDouble(getElementTextContent(ingrids, "vanillin")));
        ingredients.setChocolatetype(ingrids.getAttribute("chocolatetype"));
        chocolate.setIngredients(ingredients);

        Energyvalue energyvalue = new Energyvalue();
        Element enV = (Element) chocolateElement.getElementsByTagName("energyvalue").item(0);
        energyvalue.setProteins(Double.parseDouble(getElementTextContent(enV, "proteins")));
        energyvalue.setFats(Double.parseDouble(getElementTextContent(enV, "fats")));
        energyvalue.setCarbohydrates(Double.parseDouble(getElementTextContent(enV, "carbohydrates")));
        chocolate.setEnergyvalue(energyvalue);

        Element discr = (Element) chocolateElement.getElementsByTagName("description").item(0);
        chocolate.setDescription(discr.getTextContent());

        XMLGregorianCalendar xmlGregorianCalendar = DatatypeFactory.newInstance().newXMLGregorianCalendar(chocolateElement.getElementsByTagName("date").item(0).getTextContent());

        chocolate.setDate(xmlGregorianCalendar);

        return chocolate;
    }

    // получение текстового содержимого тега
    private static String getElementTextContent(Element element, String elementName) {
        NodeList nList = element.getElementsByTagName(elementName);
        Node node = nList.item(0);
        String text = node.getTextContent();
        return text;
    }
}