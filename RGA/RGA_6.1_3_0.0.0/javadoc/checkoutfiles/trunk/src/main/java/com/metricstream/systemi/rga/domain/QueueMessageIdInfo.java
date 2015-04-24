package com.metricstream.systemi.rga.domain;

/**
 * The Class QueueMessageIdInfo.
 * @author asif.u
 */
public class QueueMessageIdInfo {

	/** The message id. */
	private String messageId;

	/**
	 * Instantiates a new queue message id info.
	 *
	 * @param messageId the message id
	 */
	public QueueMessageIdInfo(String messageId){
		this.messageId=messageId;
	}
	
	/**
	 * Gets the message id.
	 *
	 * @return the messageId
	 */
	public String getMessageId() {
		return messageId;
	}

	/**
	 * Sets the message id.
	 *
	 * @param messageId the messageId to set
	 */
	public void setMessageId(String messageId) {
		this.messageId = messageId;
	}
	
	
}
