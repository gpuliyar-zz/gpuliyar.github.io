package com.metricstream.systemi.jpa.entity;

import java.io.Serializable;

import javax.persistence.*;

import com.metricstream.systemi.jpa.util.JPAConstants;


/**
 * The persistent class for the MS_RGA_URL_LATEST_TIMESTAMP database table.
 * 
 */
@Entity
@Table(name=JPAConstants.URL_LATEST_TIMESTAMP)
@NamedQuery(name="MsRgaUrlLatestTimestamp.findAll", query="SELECT m FROM MsRgaUrlLatestTimestamp m")
public class MsRgaUrlLatestTimestamp implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The last response timestamp. */
	@Column(name=JPAConstants.LAST_RESPONSE_TIMESTAMP)
	private String lastResponseTimestamp;

	/** The server add url. */
	@Id
	@Column(name=JPAConstants.SERVER_ADD_URL)
	private String serverAddUrl;

	/**
	 * Instantiates a new ms rga url latest timestamp.
	 */
	public MsRgaUrlLatestTimestamp() {
	}

	/**
	 * Gets the last response timestamp.
	 *
	 * @return the last response timestamp
	 */
	public String getLastResponseTimestamp() {
		return this.lastResponseTimestamp;
	}

	/**
	 * Sets the last response timestamp.
	 *
	 * @param lastResponseTimestamp the new last response timestamp
	 */
	public void setLastResponseTimestamp(String lastResponseTimestamp) {
		this.lastResponseTimestamp = lastResponseTimestamp;
	}

	/**
	 * Gets the server add url.
	 *
	 * @return the server add url
	 */
	public String getServerAddUrl() {
		return this.serverAddUrl;
	}

	/**
	 * Sets the server add url.
	 *
	 * @param serverAddUrl the new server add url
	 */
	public void setServerAddUrl(String serverAddUrl) {
		this.serverAddUrl = serverAddUrl;
	}

}