
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
 *         &lt;element name="flPathSug" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="errorMsg" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
    "flPathSug",
    "errorMsg"
})
@XmlRootElement(name = "ResponseFlight", namespace = "http://com.x.service/ResponseFlight")
public class ResponseFlight {

    @XmlElement(namespace = "http://com.x.service/ResponseFlight", required = true)
    protected String flPathSug;
    @XmlElement(namespace = "http://com.x.service/ResponseFlight", required = true)
    protected String errorMsg;

    /**
     * Gets the value of the flPathSug property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFlPathSug() {
        return flPathSug;
    }

    /**
     * Sets the value of the flPathSug property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFlPathSug(String value) {
        this.flPathSug = value;
    }

    /**
     * Gets the value of the errorMsg property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getErrorMsg() {
        return errorMsg;
    }

    /**
     * Sets the value of the errorMsg property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setErrorMsg(String value) {
        this.errorMsg = value;
    }

}
