package by.tareltos.webtask.builder;


import by.tareltos.webtask.entity.Candies;
import by.tareltos.webtask.entity.Candy;
import org.apache.logging.log4j.Level;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Task 1 Chapter A
 * Created by Vitali Tarelko on 21.02.2018.
 * tareltos@gmail.com; skype: tareltos
 */
public class UnMarshalWithXSDBuilder extends AbstractCandiesBuilder {
    private JAXBContext jc = null;
    private String schemaName;

    @Override
    public void buildSetCandies(String fileaNme) {
        Candies st;
        try {
            jc = JAXBContext.newInstance("by.tareltos.webtask.entity");
            Unmarshaller um = jc.createUnmarshaller();
            SchemaFactory factory =
                    SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            File schemaLocation = new File(schemaName);
            Schema schema = factory.newSchema(schemaLocation);
            um.setSchema(schema);
            st = (Candies) um.unmarshal(new File(fileaNme));
            for (int i = 0; i < st.getCandy().size(); i++) {
                candies.add(st.getCandy().get(i).getValue());
            }

        } catch (JAXBException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }

    }

    public void setSchemaName(String schemaName) {
        this.schemaName = schemaName;
    }
}

