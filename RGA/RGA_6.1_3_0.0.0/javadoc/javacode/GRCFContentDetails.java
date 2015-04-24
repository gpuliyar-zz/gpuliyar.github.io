/**
 * 
 */
package com.metricstream.systemi.rga.grcobject;

import java.util.List;

import com.msi.grcintelligence.utility.mongodb.CFRAnnualContent;

/**
 * GRCFContentDetails holds the complete content details which need to be operationalized.
 * @author munavar.basha
 *
 */
public class GRCFContentDetails {
	
	private List<CFRAnnualContent> grcfObjectDetails;
	private GRCFMandatoryFields aocDetailsMandatory;
	private GRCFMandatoryFields reqDetailsMandatory;
	
	
	/**
	 * @return the grcfObjectDetails
	 */
	public List<CFRAnnualContent> getGrcfObjectDetails() {
		return this.grcfObjectDetails;
	}
	
	
	/**
	 * @param grcfObjectDetails the grcfObjectDetails to set
	 */
	public void setGrcfObjectDetails(List<CFRAnnualContent> grcfObjectDetails) {
		this.grcfObjectDetails = grcfObjectDetails;
	}


	/**
	 * @return the aocDetailsMandatory
	 */
	public GRCFMandatoryFields getAocDetailsMandatory() {
		return aocDetailsMandatory;
	}


	/**
	 * @param aocDetailsMandatory the aocDetailsMandatory to set
	 */
	public void setAocDetailsMandatory(GRCFMandatoryFields aocDetailsMandatory) {
		this.aocDetailsMandatory = aocDetailsMandatory;
	}


	/**
	 * @return the reqDetailsMandatory
	 */
	public GRCFMandatoryFields getReqDetailsMandatory() {
		return reqDetailsMandatory;
	}


	/**
	 * @param reqDetailsMandatory the reqDetailsMandatory to set
	 */
	public void setReqDetailsMandatory(GRCFMandatoryFields reqDetailsMandatory) {
		this.reqDetailsMandatory = reqDetailsMandatory;
	}
}
