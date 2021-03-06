package by.tareltos.webtask.validator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

public class ValidatorTest {

    private final String XML_FILE_PATH = "src/test/resources/candies.xml";
    private final String XSD_FILE_PATH = "src/test/resources/candiesdscr.xsd";
    private final String WRONG_XSD_FILE_PATH = "src/test/resources/candies2dscr.xsd";


    @Test
    public void validatorXMLTest() {
        ValidatorSAXXSD.validateXml(XML_FILE_PATH, XSD_FILE_PATH);
    }

    @Test
    public void validatorXMLTestFail() {
        ValidatorSAXXSD.validateXml(XML_FILE_PATH, WRONG_XSD_FILE_PATH);
    }


}
