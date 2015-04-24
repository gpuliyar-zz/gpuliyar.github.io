/**
 * Class: RSSFeedData
 * Author: Sumit L
 * Description:
 * This class, when invoked completely reads a given set of URL Connections and returns
 * a set of rss feeds from all of them.
 */
package com.metricstream.systemi.ext.infolet;

import java.util.Date;
import java.util.List;

import com.metricstream.log.Logger;
import com.metricstream.systemi.utils.Utils;
import com.rometools.rome.feed.synd.SyndContentImpl;
import com.rometools.rome.feed.synd.SyndEnclosure;
import com.rometools.rome.feed.synd.SyndEntry;


/**
 * This class implements a bunch of fields to represent an RSS Feed data that was received.
 */
public class RSSFeedData implements ChannelFeedData {
	
	/** The user id. */
	private final String feedUrl, userID;
	
	/** The feed author. */
	private String feedTitle, feedContent = "", feedDescrptn = "", feedLink, feedAuthor;
	
	/** The feed update date. */
	private Date feedPublishDate, feedUpdateDate;
	
	/** The structured content infolet id. */
	private String structuredContentInfoletId;
	
	/** The save attachment. */
	private String saveAttachment;
	
	/** The channel id. */
	private String channelId;
	
	/** The source id. */
	String sourceId;
	
	/** The sent date. */
	private Date sentDate;
	
	/** The attachment i ds. */
	private String attachmentIDs;
	
	/** The sys date. */
	private Date sysDate = new Date();

	/** The Constant CLASS_ID. */
	private static final String CLASS_ID = "RSSFeedData";

	/**
	 * Instantiates a new RSS feed data.
	 *
	 * @param feedUrl the feed url
	 * @param userID the user id
	 * @param feed the feed
	 * @param urlDate the url date
	 * @param structuredContentInfoletId the structured content infolet id
	 * @param saveAttachment the save attachment
	 * @param channelId the channel id
	 * @param sentDate the sent date
	 * @param sourceId the source id
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public RSSFeedData(String feedUrl, String userID, SyndEntry feed,
			Date urlDate, String structuredContentInfoletId,
			String saveAttachment, String channelId, Date sentDate, String sourceId)
					throws java.io.IOException {

		this.feedUrl = feedUrl;
		this.userID = userID;
		this.structuredContentInfoletId = structuredContentInfoletId;
		this.saveAttachment = saveAttachment;
		this.channelId = channelId;
		this.sentDate = sentDate;
		this.sourceId = sourceId;
		loadFeeds(feed, urlDate);
	}

	/**
	 * Load feeds.
	 *
	 * @param feed feed entry
	 * @param urlPublishedDate the url published date
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	private void loadFeeds(SyndEntry feed, Date urlPublishedDate)
			throws java.io.IOException {

		this.feedTitle = feed.getTitle();
		this.feedLink = feed.getLink();
		this.feedAuthor = feed.getAuthor();
		Logger.info( CLASS_ID, "feed.getUpdatedDate() " +feed.getUpdatedDate(), null);
		this.feedUpdateDate = feed.getUpdatedDate();

		this.feedPublishDate = feed.getPublishedDate();
		Logger.info( CLASS_ID, "feed.getPublishedDate() " +feed.getPublishedDate(), null);
		Logger.info( CLASS_ID, "urlPublishedDate " +urlPublishedDate, null);
		if (this.feedPublishDate == null) {
			if (feedUpdateDate != null) {
				feedPublishDate = feedUpdateDate;
			} else if(this.feedUpdateDate == null) {
				this.feedPublishDate = urlPublishedDate; 
			}else{		
				this.feedPublishDate = sysDate;			// worst case we will have this	
			}
		}

		SyndContentImpl fDesc = (SyndContentImpl) feed.getDescription();
		this.feedDescrptn = (fDesc != null) ? fDesc.toString() : "";
		this.feedContent = (fDesc != null) ? fDesc.getValue() : "";

		List<SyndEnclosure> enclosures = feed.getEnclosures();
		if (isSaveAttachement() && enclosures != null && !enclosures.isEmpty()) {
			for (SyndEnclosure encl : enclosures) {
				String fileName = Utils.downloadFileFromUrl(encl.getUrl());
				String id = Utils.saveFile(fileName);
				this.attachmentIDs = ((this.attachmentIDs != null) ? this.attachmentIDs + ",": "")	+ id;
			}
		}

		for(int ival=0; ival<feed.getContents().size(); ival++){
			SyndContentImpl imp = (SyndContentImpl) feed.getContents().get(ival);
			this.feedContent = this.feedContent + imp.getValue() + '\n';
		}

		// Get all the list of modules from the feed

		/*CBModule cbModule = (CBModule)feed.getModule(CBModule.URI);

		Logger.debug( CLASS_ID, "CBModule Obj " +cbModule, null);
		if(cbModule != null){
			CBEvent cbEventObj = cbModule.getEvent();
			if(cbEventObj != null){
				Logger.info( CLASS_ID, "Set CBEvent Data", null);

			}

			CBNews cbNewsObj = cbModule.getNews();
			if(cbNewsObj != null){
				Logger.info( CLASS_ID, "Set CBNews Data", null);
			}

			CBPaper cbPaper = cbModule.getPaper();
			if(cbPaper != null){
				Logger.info( CLASS_ID, "Set CBPaper Data", null);
			}

			CBSpeech cbSpeechObj = cbModule.getSpeech();
			if(cbSpeechObj != null){
				Logger.info( CLASS_ID, "Set CBSpeech Data="+cbSpeechObj.getSimpleTitle(), null);
				this.feedContent = this.feedContent + cbSpeechObj.getSimpleTitle() + '\n';
			}

			CBStatistics cbStatObj = cbModule.getStatistics();
			if(cbStatObj != null){
				Logger.info( CLASS_ID, "Set CBStat Data", null);
			}
		}*/

	}

	/**
	 * Gets the feed url.
	 *
	 * @return the feed url
	 */
	public String getFeedUrl() {
		return this.feedUrl;
	}

	/**
	 * Gets the user name.
	 *
	 * @return the user name
	 */
	public String getUserName() {
		return this.userID;
	}

	/**
	 * Gets the message subject.
	 *
	 * @return the message subject
	 */
	public String getMessageSubject() {
		return this.feedTitle;
	}

	/**
	 * Gets the message body.
	 *
	 * @return the message body
	 */
	public String getMessageBody() {
		return this.feedContent + "<br/>" +"<a target = \"_blank\"  href=\""+ this.feedLink +"\" >"+this.feedLink+" </a>  ";
	}

	/**
	 * Gets the published timestamp.
	 *
	 * @return the published timestamp
	 */
	public long getPublishedTimestamp() {
		if(this.feedPublishDate == null){
			return 0l;
		}else
			return (this.feedPublishDate.getTime());
	}

	@Override
	public String toString() {
		StringBuffer sbuf = new StringBuffer();
		sbuf.append("FeedUrl: ").append(getFeedUrl()).append('\n');
		sbuf.append("UserID: ").append(getUserName()).append('\n');
		sbuf.append("Subject: ").append(getMessageSubject()).append('\n');
		sbuf.append("Body: ").append(getMessageBody()).append('\n');
		sbuf.append("PublishDate: ")
		.append(new Long(getPublishedTimestamp()).toString())
		.append('\n');
		sbuf.append("MsgID: ").append(getMsgUID());
		sbuf.append("SCInfletId: ").append(getStructuredContentInfoletId());
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
	 * gets the recipient
	 * @return null
	 */
	@Override
	public String getRecipient() {
		return null;
	}

	/**
	 * gets the sender
	 * @return null
	 */
	@Override
	public String getSender() {
		return null;
	}

	/**
	 * gets the server url
	 * @return feedUrl
	 */
	@Override
	public String getServer() {
		return getFeedUrl();
	}

	/**
	 * gets the subject
	 * @return feedTitle
	 */
	@Override
	public String getSubject() {
		return ((getMessageSubject() != null && getMessageSubject().length() > 0) ? getMessageSubject()
				.trim() : null);
	}

	/**
	 * gets the attachment IDs
	 * @return attachmentIDs
	 */
	@Override
	public String getAttachmentIDs() {
		return attachmentIDs;
	}

	/**
	 * gets the msg uIDs
	 * @return null
	 */
	@Override
	public String getMsgUID() {
		return null;
	}

	/**
	 * gets the structured Content Infolet Id
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
	 * gets the saveAttachment
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
	 * gets the channel Id
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
	 * gets the source Id
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
	 * gets the sent Date
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
	 * gets the file name
	 * @return null
	 */
	@Override
	public String getFileName() {
		// TODO Auto-generated method stub
		return null;
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
	 * gets the Activation Code
	 * @return null
	 */
	@Override
	public String getActivationCode() {
		return null;
	}

}