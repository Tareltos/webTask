package by.tareltos.webtask.xmlparser;


import by.tareltos.webtask.entity.Candies;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.File;

/**
 * Task 1 Chapter A
 * Created by Vitali Tarelko on 21.02.2018.
 * tareltos@gmail.com; skype: tareltos
 */
public class UnMarshalWithXSD {

    public static Candies unmarshall(String schemaName, String xmlName) {
        JAXBContext jc = null;
        Candies st = null;
        try {
            jc = JAXBContext.newInstance("by.tareltos.webtask.entity");
            Unmarshaller um = jc.createUnmarshaller();
            SchemaFactory factory =
                    SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            File schemaLocation = new File(schemaName);
            Schema schema = factory.newSchema(schemaLocation);
            um.setSchema(schema);
            st = (Candies) um.unmarshal(new File(xmlName));
        } catch (JAXBException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
        return st;
    }
}

