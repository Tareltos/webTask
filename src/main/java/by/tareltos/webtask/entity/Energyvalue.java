//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2018.03.08 at 08:58:25 PM MSK 
//


package by.tareltos.webtask.entity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;
import java.util.Objects;


/**
 * <p>Java class for energyvalue complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="energyvalue">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="proteins" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="fats" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="carbohydrates" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "energyvalue", propOrder = {
    "proteins",
    "fats",
    "carbohydrates"
})
public class Energyvalue {

    protected double proteins;
    protected double fats;
    protected double carbohydrates;

    /**
     * Gets the value of the proteins property.
     * 
     */
    public double getProteins() {
        return proteins;
    }

    /**
     * Sets the value of the proteins property.
     * 
     */
    public void setProteins(double value) {
        this.proteins = value;
    }

    /**
     * Gets the value of the fats property.
     * 
     */
    public double getFats() {
        return fats;
    }

    /**
     * Sets the value of the fats property.
     * 
     */
    public void setFats(double value) {
        this.fats = value;
    }

    /**
     * Gets the value of the carbohydrates property.
     * 
     */
    public double getCarbohydrates() {
        return carbohydrates;
    }

    /**
     * Sets the value of the carbohydrates property.
     * 
     */
    public void setCarbohydrates(double value) {
        this.carbohydrates = value;
    }


}
