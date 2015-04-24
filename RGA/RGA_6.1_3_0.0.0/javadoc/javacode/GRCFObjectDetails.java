package com.metricstream.systemi.rga.grcobject;

import java.util.List;

import com.msi.grcintelligence.utility.mongodb.ContentWrapperObj;

/**
 * GRCFObjectDetails gets the details what user has selected in the content browser for operationalize.
 * @author munavar.basha
 *
 */
public class GRCFObjectDetails {
	
	private String contentProviderName;
	private String contentCategoryName;
	private String contentVersion;
	private GRCFMandatoryFields aocDetails;
	private GRCFMandatoryFields reqDetails;
	private List<ContentWrapperObj> selectedObj;
	
	
	/**
	 * @return the aocDetails
	 */
	public GRCFMandatoryFields getAocDetails() {
		return this.aocDetails;
	}


	/**
	 * @param aocDetails the aocDetails to set
	 */
	public void setAocDetails(GRCFMandatoryFields aocDetails) {
		this.aocDetails = aocDetails;
	}


	/**
	 * @return the reqDetails
	 */
	public GRCFMandatoryFields getReqDetails() {
		return this.reqDetails;
	}


	/**
	 * @param reqDetails the reqDetails to set
	 */
	public void setReqDetails(GRCFMandatoryFields reqDetails) {
		this.reqDetails = reqDetails;
	}

	/**
	 * @return the contentProviderName
	 */
	public String getContentProviderName() {
		return this.contentProviderName;
	}
	
	
	/**
	 * @param contentProviderName the contentProviderName to set
	 */
	public void setContentProviderName(String contentProviderName) {
		this.contentProviderName = contentProviderName;
	}
	
	
	/**
	 * @return the contentCategoryName
	 */
	public String getContentCategoryName() {
		return this.contentCategoryName;
	}
	
	
	/**
	 * @param contentCategoryName the contentCategoryName to set
	 */
	public void setContentCategoryName(String contentCategoryName) {
		this.contentCategoryName = contentCategoryName;
	}
	
	
	/**
	 * @return the contentVersion
	 */
	public String getContentVersion() {
		return this.contentVersion;
	}
	
	
	/**
	 * @param contentVersion the contentVersion to set
	 */
	public void setContentVersion(String contentVersion) {
		this.contentVersion = contentVersion;
	}
	
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "GRCFObjectDetails [contentProviderName="
				+ this.contentProviderName + ", contentCategoryName="
				+ this.contentCategoryName + ", contentVersion="
				+ this.contentVersion + ", selectedObj=" + this.selectedObj
				+ "]";
	}


	/**
	 * @return the selectedObj
	 */
	public List<ContentWrapperObj> getSelectedObj() {
		return selectedObj;
	}


	/**
	 * @param selectedObj the selectedObj to set
	 */
	public void setSelectedObj(List<ContentWrapperObj> selectedObj) {
		this.selectedObj = selectedObj;
	}

}
