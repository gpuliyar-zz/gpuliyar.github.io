package com.metricstream.systemi.rga.domain;

/**
 * Class to represent domain class to populate response time of each source last updated
 * @author asif.u
 *
 */
public class SourceLastUpdate {

	String serverURL;
	String lastResponseTime;
	/**
	 * @return the serverURL
	 */
	public String getServerURL() {
		return serverURL;
	}
	/**
	 * @param serverURL the serverURL to set
	 */
	public void setServerURL(String serverURL) {
		this.serverURL = serverURL;
	}
	/**
	 * @return the lastResponseTime
	 */
	public String getLastResponseTime() {
		return lastResponseTime;
	}
	/**
	 * @param lastResponseTime the lastResponseTime to set
	 */
	public void setLastResponseTime(String lastResponseTime) {
		this.lastResponseTime = lastResponseTime;
	}
	
	
	
}
