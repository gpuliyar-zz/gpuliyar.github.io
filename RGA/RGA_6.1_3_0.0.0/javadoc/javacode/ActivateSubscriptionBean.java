
package com.metricstream.systemi.rga.bean;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * The Activate Subscription Bean.
 *
 * @author manish.svk
 */
@XmlRootElement(name = "ActivateSubscription")
public class ActivateSubscriptionBean {

	/** The activation codes. */
	private List<String> activationCodes;

	/**
	 * Gets the activation codes.
	 *
	 * @return the activationCodes
	 */
	public List<String> getActivationCodes() {
		return activationCodes;
	}

	/**
	 * Sets the activation codes.
	 *
	 * @param activationCodes the activationCodes to set
	 */
	@XmlElementWrapper(name = "ActivationCodeList")
	@XmlElement(name = "ActivationCode")
	public void setActivationCodes(List<String> activationCodes) {
		this.activationCodes = activationCodes;
	}
}

