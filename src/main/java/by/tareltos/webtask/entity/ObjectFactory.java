//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2018.03.08 at 08:58:25 PM MSK 
//


package by.tareltos.webtask.entity;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.example.candies package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _Chocolate_QNAME = new QName("http://www.example.com/candies", "chocolate");
    private final static QName _Caramel_QNAME = new QName("http://www.example.com/candies", "caramel");
    private final static QName _Candy_QNAME = new QName("http://www.example.com/candies", "candy");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.example.candies
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Caramel }
     * 
     */
    public Caramel createCaramel() {
        return new Caramel();
    }

    /**
     * Create an instance of {@link Candy }
     * 
     */
    public Candy createCandy() {
        return new Candy();
    }

    /**
     * Create an instance of {@link Candies }
     * 
     */
    public Candies createCandies() {
        return new Candies();
    }

    /**
     * Create an instance of {@link Chocolate }
     * 
     */
    public Chocolate createChocolate() {
        return new Chocolate();
    }

    /**
     * Create an instance of {@link Ingredients }
     * 
     */
    public Ingredients createIngredients() {
        return new Ingredients();
    }

    /**
     * Create an instance of {@link Energyvalue }
     * 
     */
    public Energyvalue createEnergyvalue() {
        return new Energyvalue();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Chocolate }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.example.com/candies", name = "chocolate", substitutionHeadNamespace = "http://www.example.com/candies", substitutionHeadName = "candy")
    public JAXBElement<Chocolate> createChocolate(Chocolate value) {
        return new JAXBElement<Chocolate>(_Chocolate_QNAME, Chocolate.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Caramel }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.example.com/candies", name = "caramel", substitutionHeadNamespace = "http://www.example.com/candies", substitutionHeadName = "candy")
    public JAXBElement<Caramel> createCaramel(Caramel value) {
        return new JAXBElement<Caramel>(_Caramel_QNAME, Caramel.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Candy }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.example.com/candies", name = "candy")
    public JAXBElement<Candy> createCandy(Candy value) {
        return new JAXBElement<Candy>(_Candy_QNAME, Candy.class, null, value);
    }

}
