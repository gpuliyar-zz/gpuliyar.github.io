package com.metricstream.systemi.rga.bean;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * The Activation Bean POJO.
 *
 * @author manish.svk
 */
@XmlRootElement(name = "ActivationCodeList")
public class ActivationBeanList {
	
	/** The activation code. */
	private List<ActivationBean> activationCode;

	/**
	 * Gets the activation code.
	 *
	 * @return the activationCode
	 */
	public List<ActivationBean> getActivationCode() {
		return activationCode;
	}

	/**
	 * Sets the activation code.
	 *
	 * @param activationCode the activationCode to set
	 */
    @XmlElementWrapper(name = "ActivationList")
	@XmlElement(name = "ActivationCode")
	public void setActivationCode(List<ActivationBean> activationCode) {
		this.activationCode = activationCode;
	}
}
