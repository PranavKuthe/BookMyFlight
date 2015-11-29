
package com.x.ws.generated;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="fromLoc" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="destLoc" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "fromLoc",
    "destLoc"
})
@XmlRootElement(name = "RequestFlight")
public class RequestFlight {

    @XmlElement(required = true)
    protected String fromLoc;
    @XmlElement(required = true)
    protected String destLoc;

    /**
     * Gets the value of the fromLoc property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFromLoc() {
        return fromLoc;
    }

    /**
     * Sets the value of the fromLoc property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFromLoc(String value) {
        this.fromLoc = value;
    }

    /**
     * Gets the value of the destLoc property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDestLoc() {
        return destLoc;
    }

    /**
     * Sets the value of the destLoc property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDestLoc(String value) {
        this.destLoc = value;
    }

}
