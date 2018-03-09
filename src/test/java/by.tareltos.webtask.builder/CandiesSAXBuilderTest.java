package by.tareltos.webtask.builder;

import by.tareltos.webtask.entity.*;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.*;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import java.util.HashSet;
import java.util.Set;

public class CandiesSAXBuilderTest {

    final static Logger LOGGER = LogManager.getLogger();
    private AbstractCandiesBuilder builder;
    private final String XML_FILE_PATH = "src/test/resources/candies.xml";

    @BeforeClass
    public void setUp() {
        builder = new CandiesSAXBuilder();
        builder.buildSetCandies(XML_FILE_PATH);
    }

    @AfterClass
    public void tearDown() {
        builder = null;
    }

    @Test(groups = "Parsers")
    public void candiesSAXBuilderCountTest() {
        int expected = 3;
        int result = builder.getCandies().size();
        LOGGER.log(Level.INFO, "TEST: candiesSAXBuilderCount, expected: " + expected + ", result: " + result);
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

}
