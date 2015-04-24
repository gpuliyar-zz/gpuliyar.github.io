/**
 * Class: EmailFeedAggregator
 * Author: B.Vasant
 * Description:
 * This class, when invoked completely reads a given set of POP3 server and returns
 * a set of mail records from all of them.
 */
package com.metricstream.systemi.ext.infolet;

// Generic imports

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Flags;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Part;

import com.metricstream.log.Logger;
import com.metricstream.systemi.constant.Property;
import com.metricstream.systemi.server.common.Controller;
import com.metricstream.systemi.utils.Utils;
// Javax.Mail API imports
// DB
// ECP imports

/**
 * This class implements a bunch of fields to represent an email message data that was received.
 */
public class EmailMessageData implements ChannelFeedData {
	/**
	 * This class holds an email object's data. This is invoked on a message
	 * object and then proceeds to 'suck it all up'
	 */
	private static final String CLASS_ID = "EmailMessageData";

	// Various message fields
	/** The user id. */
	private String server, userID;

	/** The msg cc. */
	private Address msgFrom[], msgTo[], msgCC[];

	/** The msg body. */
	private String msgSubject, msgBody;

	/** The msg received on. */
	private java.util.Date msgReceivedOn;

	/** The attachment i ds. */
	private String attachmentIDs;

	// File Attachments (in-memory? Hmm... no. This class should store the file
	// attachment to disk use the MSI approach, and get an ID to work with. The ID
	// generation is TBD for now.
	/** The msg file attachments. */
	private EmailMessageAttachmentImpl msgFileAttachments[];

	// Mime type
	/** The msg mime type. */
	private String msgMimeType;

	/** The msg uid. */
	private String msgUID;

	/** The structured content infolet id. */
	private String structuredContentInfoletId;

	/** The save attachment. */
	private String saveAttachment;

	/** The channel id. */
	private String channelId;

	/** The sent date. */
	private Date sentDate;

	/** The file name. */
	private String fileName;

	/** The source id. */
	private String sourceId;

	// Temp Directory to save files
	/** The temp dir. */
	public static String tempDir;

	static {
		try {
			if (Controller.getContext() != null) {
				Properties properties = Controller.getProperties();
				tempDir = properties.getProperty(Property.TEMP_DIRECTORY, "");

				if (tempDir == null || tempDir.length() == 0) {
					Logger.error(CLASS_ID, "Temp Directory not defined to create attachments", null);
				}
			}
		} catch (Exception e) {
			Logger.error(CLASS_ID, e.getMessage(), e);
		}
	}

	/**
	 * Initialize and load up the message data into the object.
	 *
	 * @param server            the POP3 server
	 * @param userID            the user ID whose inbox we got this from
	 * @param msg            the message object
	 * @param uid the uid
	 * @param structuredContentInfoletId the structured content infolet id
	 * @param saveAttachment the save attachment
	 * @param channelId the channel id
	 * @param sentDate the sent date
	 * @param fileName the file name
	 * @param sourceId the source id
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws MessagingException the messaging exception
	 */
	public EmailMessageData(String server, String userID, Message msg,
			String uid, String structuredContentInfoletId,
			String saveAttachment, String channelId, Date sentDate,
			String fileName, String sourceId) throws java.io.IOException, MessagingException {
		Logger.debug(CLASS_ID, "inside", null);
		long start = System.currentTimeMillis();
		this.server = server;
		this.userID = userID;
		this.msgUID = uid;
		this.structuredContentInfoletId = structuredContentInfoletId;
		this.saveAttachment = saveAttachment;
		this.channelId = channelId;
		this.sentDate = sentDate;
		this.fileName = fileName;
		this.sourceId = sourceId;
		Logger.debug(CLASS_ID, "******************inside with " + server + ","
				+ userID + "," + msg + "," + uid + ","
				+ structuredContentInfoletId + "," + saveAttachment + ","
				+ channelId + "," + sentDate + "," + sourceId, null);
		// Load up the message
		Logger.debug(CLASS_ID, "load msg start", null);
		loadMessage(msg);
		Logger.debug(CLASS_ID, "load msg end:"
				+ (System.currentTimeMillis() - start), null);
		Logger.debug(CLASS_ID," message body is " + this.msgBody,null);
	}

	/**
	 * This is the actual method that loads the message.
	 * @param msg the message to load
	 * @throws IOException if error in getting message data
	 * @throws MessagingException the messaging exception
	 */
	private void loadMessage(Message msg) throws java.io.IOException,
	MessagingException {
		// Load message fields
		this.msgMimeType = msg.getContentType();
		this.msgFrom = msg.getFrom();
		this.msgTo = msg.getRecipients(Message.RecipientType.TO);
		this.msgCC = msg.getRecipients(Message.RecipientType.CC);
		this.msgSubject = msg.getSubject();
		this.msgReceivedOn = msg.getReceivedDate();

		// OK - now go through the email content. This is the tricky part.
		Object content = msg.getContent();

		Logger.debug(CLASS_ID, "Subj::: " + this.msgSubject
				+ " Content instanceOf: " + content.getClass()
				+ "Content Type = " + msg.getContentType(), null);

		if (content instanceof String) { 
			// if we are able to read the body directly, set it as the message body
			Logger.debug(CLASS_ID, "Handling String content.....", null);
			this.msgBody = (String) content;
		} else if (content instanceof Multipart) {
			Multipart mPart = (Multipart) content;
			Logger.debug(CLASS_ID, "Handling Multipart content.....", null);
			handleMultipart(mPart);
		} else if (content instanceof InputStream) {
			Logger.debug(CLASS_ID, "Handling InputStream .....", null);
			handleInputStream((InputStream) content);
		} else {
			Logger.debug(CLASS_ID, "Handling Plain Message.....", null);
			handlePart((Part) content);
		}

		// Mark the message as read
		Logger.debug(CLASS_ID, "Marking the message as read......", null);
		msg.setFlag(Flags.Flag.SEEN, true);
	}

	/**
	 * Handle multipart.
	 * @param multipart the multipart
	 * @throws MessagingException the messaging exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	private void handleMultipart(Multipart multipart)
			throws MessagingException, IOException {
		int count = multipart.getCount();
		for (int i = 0, n = count; i < n; i++) {
			Logger.debug(CLASS_ID, "calling handlePart", null);
			handlePart(multipart.getBodyPart(i));
		}
	}

	/**
	 * Handle part.
	 * @param part the part
	 * @throws MessagingException the messaging exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	private void handlePart(Part part) throws MessagingException, IOException {
		Logger.debug(CLASS_ID, "handlePart inside", null);
		long start = System.currentTimeMillis();
		String disposition = part.getDisposition();
		String contentType = part.getContentType();
		if (disposition == null || disposition.equalsIgnoreCase(Part.INLINE)) { // Just
			// body
			Logger.debug(CLASS_ID,
					"handlePart::CONTENT TYPE = " + contentType,
					null);

			Object content1 = part.getContent();
			if (content1 instanceof Multipart) {
				Logger.debug(CLASS_ID,
						"handlePart::Multipart within Multipart !!!!!", null);
				handleMultipart((Multipart) content1);
			} else if ((contentType.toLowerCase().contains("text/plain"))
					|| contentType.toLowerCase().contains("text/html")) {

				if (content1 instanceof String) {
					Logger.debug(CLASS_ID, "handlePart::text/plain:String "
							+ part.getContent().getClass(), null);
					Logger.debug(CLASS_ID, "message body at place 1 "+ (String) part.getContent(), null);
					this.msgBody = (String) part.getContent();
				}
			} else {
				Logger.debug(CLASS_ID, "Other.....", null);
			}
		} else if (disposition != null
				&& disposition.equalsIgnoreCase(Part.ATTACHMENT)) {
			if (isSaveAttachement()) {
				if (tempDir == null || tempDir.length() == 0) {
					throw new IOException(
							"TempDir not defined. Unable to save file : "
									+ part.getFileName());
				}
				String fileName = part.getFileName();
				String filePath = tempDir;
				if (!(filePath.endsWith("/") || filePath.endsWith("\\"))) {
					filePath = filePath + "/";
				}
				if (fileName == null) {
					fileName = File.createTempFile("noname", ".out").getName();
				}
				filePath = filePath + fileName;
				String attachId = Utils.saveFile(fileName,
						part.getInputStream());

				/*
				 * Commented below line of code as a fix for Bug #21197 The
				 * value in the attachments field in DB is getting prefixed with
				 * �null"
				 */
				this.attachmentIDs = ((this.attachmentIDs != null) ? this.attachmentIDs + "," : "") + attachId;
			}
		} else {
			Logger.error(CLASS_ID, "Error handling multipart", null);
		}
	}

	/**
	 * Checks if is save attachement.
	 *
	 * @return true, if is save attachement
	 */
	private boolean isSaveAttachement() {
		if (saveAttachment == null || saveAttachment.equalsIgnoreCase("yes")) {
			return true;
		}
		return false;
	}

	/**
	 * Handle input stream.
	 *
	 * @param stream the stream
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	private void handleInputStream(InputStream stream) throws IOException {
		Logger.debug(CLASS_ID, "Handling InputStream...", null);
		BufferedReader br = new BufferedReader(new InputStreamReader(stream));

		String line = "";
		// Reject the header part
		while ((line = br.readLine()) != null) {
			if (line.contains("Content-Type: text/plain")) {
				break;
			}
		}

		do {
			if ((line = br.readLine()) == null || line.contains("Content-Type")) {
				break;
			}

			this.msgBody = ((this.msgBody == null) ? "" : this.msgBody) + line
					+ "\n";
			Logger.debug(CLASS_ID, "message body at place in loop "+ this.msgBody, null);
		} while (true);

	}

	/**
	 * Gets the pop server.
	 *
	 * @return the pop server
	 */
	public String getPopServer() {
		return server;
	}

	/**
	 * Gets the user name.
	 *
	 * @return the user name
	 */
	public String getUserName() {
		return userID;
	}

	/**
	 * Gets the from.
	 *
	 * @return the from
	 */
	public Address[] getFrom() {
		return msgFrom;
	}

	/**
	 * Gets the to.
	 *
	 * @return the to
	 */
	public Address[] getTo() {
		return msgTo;
	}

	/**
	 * Gets the cc.
	 *
	 * @return the cc
	 */
	public Address[] getCC() {
		return msgCC;
	}

	/**
	 * Gets the message subject.
	 *
	 * @return the message subject
	 */
	public String getMessageSubject() {
		return msgSubject;
	}

	/**
	 * Gets the message body.
	 *
	 * @return the message body
	 */
	public String getMessageBody() {
		return msgBody;
	}

	/**
	 * Gets the mime type.
	 *
	 * @return the mime type
	 */
	public String getMimeType() {
		return msgMimeType;
	}

	/**
	 * Gets the attachments.
	 *
	 * @return the attachments
	 */
	public ChannelFeedAttachment[] getAttachments() {
		return msgFileAttachments;
	}

	/**
	 * Gets the msgUID.
	 *
	 * @return the msgUID
	 */
	@Override
	public String getMsgUID() {
		return msgUID;
	}

	/**
	 * Write out a string.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		StringBuffer sbuf = new StringBuffer();

		sbuf.append("Server: ").append(server).append('\n');
		sbuf.append("UserID: ").append(userID).append('\n');
		if(null!=msgFrom)
			sbuf.append("From: ").append(msgFrom.toString()).append('\n');
		if(null!=msgTo)
			sbuf.append("To: ").append(msgTo.toString()).append('\n');
		if (msgCC != null) {
			sbuf.append("CC: ").append(msgCC.toString()).append('\n');
		}
		sbuf.append("Subject: ").append(msgSubject).append('\n');
		sbuf.append("Body: ").append(msgBody).append('\n');

		return sbuf.toString();
	}

	/**
	 * Implement the interface fields.
	 *
	 * @return the body
	 */
	@Override
	public String getBody() {
		return getMessageBody();
	}

	/**
	 * get the recipient.
	 *
	 * @return userID
	 */
	@Override
	public String getRecipient() {
		return ((userID.contains('@' + "")) ? userID : userID + '@' + server);
	}

	/**
	 * get the sender.
	 *
	 * @return msgFrom
	 */
	@Override
	public String getSender() {
		return ((msgFrom != null) && (msgFrom.length > 0) ? msgFrom[0]
				.toString() : null);
	}

	/**
	 * gets the server.
	 *
	 * @return server
	 */
	@Override
	public String getServer() {
		return getPopServer();
	}

	/**
	 * gets the subject.
	 *
	 * @return msgSubject
	 */
	@Override
	public String getSubject() {
		return ((getMessageSubject() != null && getMessageSubject().length() > 0) ? getMessageSubject()
				.trim() : null);
	}

	/**
	 * gets the attachment IDs.
	 *
	 * @return attachmentIDs
	 */
	@Override
	public String getAttachmentIDs() {
		return attachmentIDs;
	}

	/**
	 * gets the structured Content Infolet IDs.
	 *
	 * @return structuredContentInfoletId
	 */
	@Override
	public String getStructuredContentInfoletId() {
		return structuredContentInfoletId;
	}

	/**
	 * Sets the structured content infolet id.
	 *
	 * @param structuredContentInfoletId the new structured content infolet id
	 */
	public void setStructuredContentInfoletId(String structuredContentInfoletId) {
		this.structuredContentInfoletId = structuredContentInfoletId;
	}

	/**
	 * gets the saved Attachment.
	 *
	 * @return saveAttachment
	 */
	@Override
	public String getSaveAttachment() {
		return saveAttachment;
	}

	/**
	 * Sets the save attachment.
	 *
	 * @param saveAttachment the new save attachment
	 */
	public void setSaveAttachment(String saveAttachment) {
		this.saveAttachment = saveAttachment;
	}

	/**
	 * gets the channel Id.
	 *
	 * @return channelId
	 */
	@Override
	public String getChannelId() {
		return channelId;
	}

	/**
	 * Sets the channel id.
	 *
	 * @param channelId the new channel id
	 */
	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}

	/**
	 * gets the source Id.
	 *
	 * @return sourceId
	 */
	@Override
	public String getSourceId() {
		return sourceId;
	}

	/**
	 * Sets the source id.
	 *
	 * @param sourceId the new source id
	 */
	public void setSourceId(String sourceId) {
		this.sourceId = sourceId;
	}

	/**
	 * gets the sent Date.
	 *
	 * @return sentDate
	 */
	@Override
	public Date getSentDate() {
		return sentDate;
	}

	/**
	 * Sets the sent date.
	 *
	 * @param sentDate the new sent date
	 */
	public void setSentDate(Date sentDate) {
		this.sentDate = sentDate;
	}

	/**
	 * gets the file Name.
	 *
	 * @return fileName
	 */
	@Override
	public String getFileName() {
		return fileName;
	}

	/**
	 * Sets the file name.
	 *
	 * @param fileName the new file name
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	/**
	 * Email Attachment. TBD.
	 */
	class EmailMessageAttachmentImpl implements ChannelFeedAttachment {

		@Override
		public String getAttachmentName() {
			return null;
		}

		@Override
		public String getAttachmentFile() {
			return null;
		}
	}

	@Override
	public String getActivationCode() {
		return null;
	}

}
