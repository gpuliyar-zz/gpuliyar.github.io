//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.03.19 at 02:21:49 PM IST 
//


package com.metricstream.systemi.rga.grcobject.jaxb.aoc;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for CommonFieldsType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CommonFieldsType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="description" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ownerOrganizations" type="{}ListOfValues" minOccurs="0"/>
 *         &lt;element name="owners" type="{}ListOfValues" minOccurs="0"/>
 *         &lt;element name="approvers" type="{}ApproverType" maxOccurs="2" minOccurs="0"/>
 *         &lt;element name="restrictAccessTo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="validFrom" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="validUntil" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="action" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="actionComments" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CommonFieldsType", propOrder = {
    "name",
    "description",
    "ownerOrganizations",
    "owners",
    "approvers",
    "restrictAccessTo",
    "validFrom",
    "validUntil",
    "action",
    "actionComments"
})
@XmlSeeAlso({
    AOCType.class
})
public class CommonFieldsType {

    @XmlElement(required = true)
    protected String name;
    protected String description;
    protected ListOfValues ownerOrganizations;
    protected ListOfValues owners;
    protected List<ApproverType> approvers;
    protected String restrictAccessTo;
    protected String validFrom;
    protected String validUntil;
    protected String action;
    protected String actionComments;

    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Gets the value of the description property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the value of the description property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescription(String value) {
        this.description = value;
    }

    /**
     * Gets the value of the ownerOrganizations property.
     * 
     * @return
     *     possible object is
     *     {@link ListOfValues }
     *     
     */
    public ListOfValues getOwnerOrganizations() {
        return ownerOrganizations;
    }

    /**
     * Sets the value of the ownerOrganizations property.
     * 
     * @param value
     *     allowed object is
     *     {@link ListOfValues }
     *     
     */
    public void setOwnerOrganizations(ListOfValues value) {
        this.ownerOrganizations = value;
    }

    /**
     * Gets the value of the owners property.
     * 
     * @return
     *     possible object is
     *     {@link ListOfValues }
     *     
     */
    public ListOfValues getOwners() {
        return owners;
    }

    /**
     * Sets the value of the owners property.
     * 
     * @param value
     *     allowed object is
     *     {@link ListOfValues }
     *     
     */
    public void setOwners(ListOfValues value) {
        this.owners = value;
    }

    /**
     * Gets the value of the approvers property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the approvers property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getApprovers().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ApproverType }
     * 
     * 
     */
    public List<ApproverType> getApprovers() {
        if (approvers == null) {
            approvers = new ArrayList<ApproverType>();
        }
        return this.approvers;
    }

    /**
     * Gets the value of the restrictAccessTo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRestrictAccessTo() {
        return restrictAccessTo;
    }

    /**
     * Sets the value of the restrictAccessTo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRestrictAccessTo(String value) {
        this.restrictAccessTo = value;
    }

    /**
     * Gets the value of the validFrom property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getValidFrom() {
        return validFrom;
    }

    /**
     * Sets the value of the validFrom property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setValidFrom(String value) {
        this.validFrom = value;
    }

    /**
     * Gets the value of the validUntil property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getValidUntil() {
        return validUntil;
    }

    /**
     * Sets the value of the validUntil property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setValidUntil(String value) {
        this.validUntil = value;
    }

    /**
     * Gets the value of the action property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAction() {
        return action;
    }

    /**
     * Sets the value of the action property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAction(String value) {
        this.action = value;
    }

    /**
     * Gets the value of the actionComments property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getActionComments() {
        return actionComments;
    }

    /**
     * Sets the value of the actionComments property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setActionComments(String value) {
        this.actionComments = value;
    }

}
