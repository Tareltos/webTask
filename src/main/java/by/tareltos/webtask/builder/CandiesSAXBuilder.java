package by.tareltos.webtask.builder;

import by.tareltos.webtask.entity.Candy;
import by.tareltos.webtask.entity.Chocolate;
import by.tareltos.webtask.xmlparser.CandiesSAXParser;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import java.io.IOException;
import java.util.Set;

public class CandiesSAXBuilder extends AbstractCandiesBuilder {

    final static Logger LOGGER = LogManager.getLogger();

    private CandiesSAXParser cp;
    private XMLReader reader;

    public CandiesSAXBuilder() {
        cp = new CandiesSAXParser();
        try {
            reader = XMLReaderFactory.createXMLReader();
            reader.setContentHandler(cp);
        } catch (SAXException e) {
            LOGGER.log(Level.ERROR, "Error in parser: " + e);
        }
    }

    public void buildSetCandies(String fileName) {
        try {
            reader.parse(fileName);
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.candies = cp.getCandies();
    }


}
