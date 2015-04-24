package com.metricstream.systemi.rga.domain;

import java.util.List;


/**
 * The Class QueueInfoDomain.
 * @author asif.u
 */
public class QueueInfoDomain {

	/** The list. */
	List<Object> list;
	
	/** The message id list. */
	List<QueueMessageIdInfo> messageIdList;
	
	/**
	 * Gets the list.
	 *
	 * @return the list
	 */
	public List<Object> getList() {
		return list;
	}
	
	/**
	 * Sets the list.
	 *
	 * @param list the list to set
	 */
	public void setList(List<Object> list) {
		this.list = list;
	}

	/**
	 * @return the messageIdList
	 */
	public List<QueueMessageIdInfo> getMessageIdList() {
		return messageIdList;
	}

	/**
	 * @param messageIdList the messageIdList to set
	 */
	public void setMessageIdList(List<QueueMessageIdInfo> messageIdList) {
		this.messageIdList = messageIdList;
	}
	

	
	
}
