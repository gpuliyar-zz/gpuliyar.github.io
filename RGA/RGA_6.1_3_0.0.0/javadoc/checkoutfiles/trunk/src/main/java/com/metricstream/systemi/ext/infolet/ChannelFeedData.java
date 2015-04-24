/**
 * Class: EmailFeedAggregator
 * Author: B.Vasant
 * Description:
 * This class, when invoked completely reads a given set of POP3 server and returns
 * a set of mail records from all of them.
 */
package com.metricstream.systemi.ext.infolet;

import java.util.Date;

// Generic imports

/**
 * This class implements a bunch of fields to represent channel feed data that was received.
 */
public interface ChannelFeedData {
	
	/**
	 * Gets the server.
	 *
	 * @return the server
	 */
	public String getServer();

	/**
	 * Gets the sender.
	 *
	 * @return the sender
	 */
	public String getSender();

	/**
	 * Gets the recipient.
	 *
	 * @return the recipient
	 */
	public String getRecipient();

	/**
	 * Gets the subject.
	 *
	 * @return the subject
	 */
	public String getSubject();

	/**
	 * Gets the body.
	 *
	 * @return the body
	 */
	public String getBody();

	/**
	 * Gets the attachment i ds.
	 *
	 * @return the attachment i ds
	 */
	public String getAttachmentIDs();

	/**
	 * Gets the msg uid.
	 *
	 * @return the msg uid
	 */
	public String getMsgUID();

	/**
	 * Gets the structured content infolet id.
	 *
	 * @return the structured content infolet id
	 */
	public String getStructuredContentInfoletId();

	/**
	 * Gets the save attachment.
	 *
	 * @return the save attachment
	 */
	public String getSaveAttachment();

	/**
	 * Gets the channel id.
	 *
	 * @return the channel id
	 */
	public String getChannelId();

	/**
	 * Gets the sent date.
	 *
	 * @return the sent date
	 */
	public Date getSentDate();

	/**
	 * Gets the file name.
	 *
	 * @return the file name
	 */
	public String getFileName();
	
	/**
	 * Gets the activation code.
	 *
	 * @return the activation code
	 */
	public String getActivationCode();
	
	/**
	 * Gets the source id.
	 *
	 * @return the source id
	 */
	public String getSourceId();
}