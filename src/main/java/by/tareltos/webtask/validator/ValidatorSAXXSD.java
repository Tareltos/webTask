package by.tareltos.webtask.validator;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;

public class ValidatorSAXXSD {
    final static Logger LOGGER = LogManager.getLogger(ValidatorSAXXSD.class);

    public static void validateXml(String fileName, String schemaName) {

        String language = XMLConstants.W3C_XML_SCHEMA_NS_URI;
        SchemaFactory factory = SchemaFactory.newInstance(language);
        File schemaLocation = new File(schemaName);
        try {
            // создание схемы
            Schema schema = factory.newSchema(schemaLocation);
            // создание валидатора на основе схемы
            Validator validator = schema.newValidator();
            // проверка документа
            Source source = new StreamSource(fileName);
            validator.validate(source);
            LOGGER.log(Level.INFO,fileName + " is valid.");
        } catch (SAXException e) {
            LOGGER.log(Level.WARN, "validation " + fileName + " is not valid because "
                    + e.getMessage());
        } catch (IOException e) {
            LOGGER.log(Level.WARN,fileName + " is not valid because "
                    + e.getMessage());
        }
    }
}
