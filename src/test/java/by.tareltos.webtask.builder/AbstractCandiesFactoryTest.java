package by.tareltos.webtask.builder;


import by.tareltos.webtask.entity.Candies;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class AbstractCandiesFactoryTest {

    final static Logger LOGGER = LogManager.getLogger();
    private AbstractCandiesFactory factory;
    private final String CREATE_DOM_PARSER = "dom";
    private final String CREATE_UNMARSHALLER_PARSER = "marshaller";
    private final String CREATE_SAX_PARSER = "sax";
    private final String CREATE_STAX_PARSER = "stax";
    private final String CREATE_ABSTRACT_PARSER = "exception";

    @BeforeClass
    public void setUp() {
        factory = new AbstractCandiesFactory();
    }

    @AfterClass
    public void tearDown() {
        factory = null;
    }

    @Test(priority = 1, dependsOnGroups = "Parsers")
    public void createDomParserTest() {
        Class expected = CandiesDOMBuilder.class;
        Class result = factory.createCandyBuilder(CREATE_DOM_PARSER).getClass();
        LOGGER.log(Level.INFO, "TEST: createDOMParser, expected: " + expected + ", result: " + result);
        Assert.assertEquals( result,expected);
    }

    @Test(priority = 1, dependsOnGroups = "Parsers")
    public void createSaxParserTest() {
        Class expected = CandiesSAXBuilder.class;
        Class result = factory.createCandyBuilder(CREATE_SAX_PARSER).getClass();
        LOGGER.log(Level.INFO, "TEST: createSAXParser, expected: " + expected + ", result: " + result);
        Assert.assertEquals(result,expected);
    }

    @Test(priority = 1, dependsOnGroups = "Parsers")
    public void createStaxParserTest() {
        Class expected = CandiesStAXBuilder.class;
        Class result = factory.createCandyBuilder(CREATE_STAX_PARSER).getClass();
        LOGGER.log(Level.INFO, "TEST: createStAXParser, expected: " + expected + ", result: " + result);
        Assert.assertEquals(result,expected);
    }

    @Test(priority = 1, dependsOnGroups = "Parsers")
    public void createUnMarshallerTest() {
        Class expected = UnMarshalWithXSDBuilder.class;
        Class result = factory.createCandyBuilder(CREATE_UNMARSHALLER_PARSER).getClass();
        LOGGER.log(Level.INFO, "TEST: createUnMarshaller, expected: " + expected + ", result: " + result);
        Assert.assertEquals(result,expected);
    }

    @Test(priority = 1, expectedExceptions = IllegalArgumentException.class, dependsOnGroups = "Parsers")
    public void createParserFailTest() {
        factory.createCandyBuilder(CREATE_ABSTRACT_PARSER).getClass();

    }
}
