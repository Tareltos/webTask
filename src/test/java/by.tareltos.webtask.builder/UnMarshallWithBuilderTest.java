package by.tareltos.webtask.builder;

import by.tareltos.webtask.entity.*;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import java.util.HashSet;
import java.util.Set;

public class UnMarshallWithBuilderTest {

    final static Logger LOGGER = LogManager.getLogger();
    private UnMarshalWithXSDBuilder builder;
    private final String XML_FILE_PATH = "src/test/resources/candies.xml";
    private final String XSD_FILE_PATH = "src/test/resources/candiesdscr.xsd";
    private final Caramel EXPECTED_CARAMEL_ENTITY = initCaramel();

    public UnMarshallWithBuilderTest() throws DatatypeConfigurationException {
    }

    @BeforeClass

    public void setUp() {
        builder = new UnMarshalWithXSDBuilder();
        builder.setSchemaName(XSD_FILE_PATH);
        builder.buildSetCandies(XML_FILE_PATH);

    }

    @AfterClass
    public void tearDown() {
        builder = null;
    }

    @Test(groups = "Parsers")
    public void unMarshalWithXSDCountTest() {
        int expected = 3;
        int result = builder.getCandies().size();
        LOGGER.log(Level.INFO, "TEST: unMarshalWithXSD, expected: " + expected + ", result: " + result);
        Assert.assertEquals(result, expected);
    }

    @Test(groups = "Parsers", priority = 1)
    public void caramelCountTet() {
        int expected = 1;
        Set<Candy> set = builder.getCandies();
        Set<Caramel> chocolates = getCaramels(set);
        int result = chocolates.size();
        LOGGER.log(Level.INFO, "TEST: caramelCount, expected: " + expected + ", result: " + result);
        Assert.assertEquals(result, expected);
    }

    @Test(groups = "Parsers", priority = 3)
    public void chocolateCountTet() {
        int expected = 2;
        Set<Candy> set = builder.getCandies();
        Set<Chocolate> chocolates = getChocolate(set);
        int result = chocolates.size();
        LOGGER.log(Level.INFO, "TEST: chocolateCount, expected: " + expected + ", result: " + result);
        Assert.assertEquals(result, expected);
    }

    @Test(groups = "Parsers", suiteName = "atributesTest")
    public void entityFieldTest() {
        Set<Candy> set = builder.getCandies();
        Caramel caramel = getFirstCaramelElement(set);
        boolean result = caramel.equals(EXPECTED_CARAMEL_ENTITY);
        LOGGER.log(Level.INFO, "TEST: entityField, expected: " + true + ", result: " + result);
        Assert.assertEquals(result, true);
    }

    private Caramel getFirstCaramelElement(Set<Candy> set) {
        Caramel caramel = new Caramel();
        for (Candy candy : set) {
            if (candy instanceof Caramel) {
                caramel = (Caramel) candy;
                break;
            }
        }
        return caramel;
    }

    private Set<Caramel> getCaramels(Set<Candy> set) {
        Set<Caramel> caramels = new HashSet<>();
        for (Candy candy : set) {
            if (candy instanceof Caramel) {
                caramels.add((Caramel) candy);
            }
        }
        return caramels;
    }

    private Set<Chocolate> getChocolate(Set<Candy> set) {

        Set<Chocolate> choco = new HashSet<>();
        for (Candy candy : set) {
            if (candy instanceof Chocolate) {
                choco.add((Chocolate) candy);
            }
        }
        return choco;
    }

    private Caramel initCaramel() throws DatatypeConfigurationException {
        Caramel cr = new Caramel();
        cr.setName("Caramel01");
        cr.setProduction("CARAMEL OOO");
        cr.setEnergy(433);
        cr.setDate(DatatypeFactory.newInstance().newXMLGregorianCalendar("2018-02"));
        Ingredients ing = new Ingredients();
        ing.setFructose(0.4);
        ing.setVanillin(0.45);
        ing.setSugar(0.5);
        ing.setWater(true);
        cr.setIngredients(ing);
        Energyvalue v = new Energyvalue();
        v.setFats(145);
        v.setCarbohydrates(142);
        v.setProteins(0.01);
        cr.setEnergyvalue(v);
        cr.setDescription("Good");

        return cr;
    }


}
