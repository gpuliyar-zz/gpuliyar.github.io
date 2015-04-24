package com.metricstream.systemi.ext.infolet;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ContentFeed complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ContentFeed">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ContentProviderName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ContentFeedTitle" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ContentFeedID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ContentFeedRelationShipID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ContentFeedRelationShipName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Title" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Link" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Description" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Date" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ContentFeedName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ContentFeed", propOrder = {
    "contentProviderName",
    "contentFeedTitle",
    "contentFeedID",
    "contentFeedRelationShipID",
    "contentFeedRelationShipName",
    "title",
    "link",
    "description",
    "date",
    "contentFeedName"
})

@XmlRootElement(name = "ContentFeed")
public class ContentFeed {

    @XmlElement(name = "ContentProviderName", required = true)
    protected String contentProviderName;
    @XmlElement(name = "ContentFeedTitle", required = true)
    protected String contentFeedTitle;
    @XmlElement(name = "ContentFeedID", required = true)
    protected String contentFeedID;
    @XmlElement(name = "ContentFeedRelationShipID", required = true)
    protected String contentFeedRelationShipID;
    @XmlElement(name = "ContentFeedRelationShipName", required = true)
    protected String contentFeedRelationShipName;
    @XmlElement(name = "Title", required = true)
    protected String title;
    @XmlElement(name = "Link", required = true)
    protected String link;
    @XmlElement(name = "Description", required = true)
    protected String description;
    @XmlElement(name = "Date", required = true)
    protected String date;
    @XmlElement(name = "ContentFeedName", required = true)
    protected String contentFeedName;

    /**
     * Gets the value of the contentProviderName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContentProviderName() {
        return contentProviderName;
    }

    /**
     * Sets the value of the contentProviderName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContentProviderName(String value) {
        this.contentProviderName = value;
    }

    /**
     * Gets the value of the contentFeedTitle property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContentFeedTitle() {
        return contentFeedTitle;
    }

    /**
     * Sets the value of the contentFeedTitle property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContentFeedTitle(String value) {
        this.contentFeedTitle = value;
    }

    /**
     * Gets the value of the contentFeedID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContentFeedID() {
        return contentFeedID;
    }

    /**
     * Sets the value of the contentFeedID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContentFeedID(String value) {
        this.contentFeedID = value;
    }

    /**
     * Gets the value of the contentFeedRelationShipID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContentFeedRelationShipID() {
        return contentFeedRelationShipID;
    }

    /**
     * Sets the value of the contentFeedRelationShipID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContentFeedRelationShipID(String value) {
        this.contentFeedRelationShipID = value;
    }

    /**
     * Gets the value of the contentFeedRelationShipName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContentFeedRelationShipName() {
        return contentFeedRelationShipName;
    }

    /**
     * Sets the value of the contentFeedRelationShipName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContentFeedRelationShipName(String value) {
        this.contentFeedRelationShipName = value;
    }

    /**
     * Gets the value of the title property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the value of the title property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTitle(String value) {
        this.title = value;
    }

    /**
     * Gets the value of the link property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLink() {
        return link;
    }

    /**
     * Sets the value of the link property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLink(String value) {
        this.link = value;
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
     * Gets the value of the date property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDate() {
        return date;
    }

    /**
     * Sets the value of the date property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDate(String value) {
        this.date = value;
    }

    /**
     * Gets the value of the contentFeedName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContentFeedName() {
        return contentFeedName;
    }

    /**
     * Sets the value of the contentFeedName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContentFeedName(String value) {
        this.contentFeedName = value;
    }

}