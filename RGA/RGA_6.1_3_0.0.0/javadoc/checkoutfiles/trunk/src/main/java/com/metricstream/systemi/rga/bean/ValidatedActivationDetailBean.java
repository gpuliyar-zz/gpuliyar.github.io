package com.metricstream.systemi.rga.bean;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * ValidatedActivationDetailBean is an POJO object which will be returned as XML as an response to validateActivation REST API call.
 * Created by munavar.basha on 6/9/2014.
 */
@XmlRootElement(name = "ValidatedActivationDetail")
public class ValidatedActivationDetailBean {

    /** The content type. */
    private String	contentType;
    
    /** The delivary schedule. */
    private String	delivarySchedule;
    
    /** The expiry date. */
    private String	expiryDate;
    
    /** The status. */
    private String  status;
    
    /** The content category. */
    private String  contentCategory;
    
    /** The content delivery format. */
    private String  contentDeliveryFormat;
    
    /**
     * Gets the status.
     *
     * @return String
     */
    public String getStatus() {
        return status;
    }

    /**
     * Sets the status.
     *
     * @param status the new status
     */
    @XmlElement(name = "Status")
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * Gets the content type.
     *
     * @return String
     */
    public String getContentType() {
        return contentType;
    }

    /**
     * Sets the content type.
     *
     * @param contentType the new content type
     */
    @XmlElement(name = "ContentType")
    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    /**
     * Gets the delivary schedule.
     *
     * @return String
     */
    public String getDelivarySchedule() {
        return delivarySchedule;
    }

    /**
     * Sets the delivary schedule.
     *
     * @param delivarySchedule the new delivary schedule
     */
    @XmlElement(name = "DelivarySchedule")
    public void setDelivarySchedule(String delivarySchedule) {
        this.delivarySchedule = delivarySchedule;
    }

    /**
     * Gets the expiry date.
     *
     * @return String
     */
    public String getExpiryDate() {
        return expiryDate;
    }

    /**
     * Sets the expiry date.
     *
     * @param expiryDate the new expiry date
     */
    @XmlElement(name = "ExpiryDate")
    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }
    
    /**
     * Gets the content category.
     *
     * @return String
     */

	public String getContentCategory() {
		return contentCategory;
	}

	/**
	 * Sets the content category.
	 *
	 * @param contentCategory the new content category
	 */
	 @XmlElement(name = "ContentCategory")
	public void setContentCategory(String contentCategory) {
		this.contentCategory = contentCategory;
	}

	/**
	 * Gets the content delivery format.
	 *
	 * @return String
	 */
	public String getContentDeliveryFormat() {
		return contentDeliveryFormat;
	}
	
	/**
	 * Sets the content delivery format.
	 *
	 * @param contentDeliveryFormat the new content delivery format
	 */
	 @XmlElement(name = "ContentDeliveryFormat")
	public void setContentDeliveryFormat(String contentDeliveryFormat) {
		this.contentDeliveryFormat = contentDeliveryFormat;
	}
}
