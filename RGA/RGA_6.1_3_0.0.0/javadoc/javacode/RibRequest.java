//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.11.24 at 03:06:07 PM IST 
//


package com.metricstream.systemi.rga.rdc.pojo.rib;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import org.w3c.dom.Element;


/**
 * <p>Java class for RibRequest complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="RibRequest">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="header" type="{http://www.rdc.com/batch/rdc_rib.xsd}RequestHeader"/>
 *         &lt;element name="inquiries" type="{http://www.rdc.com/batch/rdc_rib.xsd}Inquiries"/>
 *         &lt;any processContents='lax' namespace='http://www.rdc.com/batch/rdc_rib.xsd' maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RibRequest", propOrder = {
    "header",
    "inquiries",
    "any"
})
@XmlRootElement(name = "RibRequest")
public class RibRequest {

    @XmlElement(required = true)
    protected RequestHeader header;
    @XmlElement(required = true)
    protected Inquiries inquiries;
    @XmlAnyElement(lax = true)
    protected List<Object> any;

    public String toString(){
    	return header + "\n" + inquiries;
    }
    
    /**
     * Gets the value of the header property.
     * 
     * @return
     *     possible object is
     *     {@link RequestHeader }
     *     
     */
    public RequestHeader getHeader() {
        return header;
    }

    /**
     * Sets the value of the header property.
     * 
     * @param value
     *     allowed object is
     *     {@link RequestHeader }
     *     
     */
    public void setHeader(RequestHeader value) {
        this.header = value;
    }

    /**
     * Gets the value of the inquiries property.
     * 
     * @return
     *     possible object is
     *     {@link Inquiries }
     *     
     */
    public Inquiries getInquiries() {
        return inquiries;
    }

    /**
     * Sets the value of the inquiries property.
     * 
     * @param value
     *     allowed object is
     *     {@link Inquiries }
     *     
     */
    public void setInquiries(Inquiries value) {
        this.inquiries = value;
    }

    /**
     * Gets the value of the any property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the any property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAny().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Object }
     * {@link Element }
     * 
     * 
     */
    public List<Object> getAny() {
        if (any == null) {
            any = new ArrayList<Object>();
        }
        return this.any;
    }

}
