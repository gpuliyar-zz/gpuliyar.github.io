package com.metricstream.systemi.ext.infolet;

import com.metricstream.systemi.ext.infolet.Feed.FeedType;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * The Feed Transaction Statistics class.
 */
public class FeedXtraxnStats {

	/** The Constant UNALLOCATED. */
	public static final String UNALLOCATED = "Unallocated";

	/**
	 * The Enum Return Code.
	 */
	enum ReturnCode {

		/** The invalid address. */
		INVALID_ADDRESS, 
		/** The get update failed. */
		GET_UPDATE_FAILED, 
		/** The get subject failed. */
		GET_SUBJECT_FAILED, 
		/** The connection failed. */
		CONNECTION_FAILED; // ...etc

		/** The code. */
		private int code;

		/**
		 * Instantiates a new return code.
		 */
		ReturnCode() {
		}

		/**
		 * Instantiates a new return code.
		 *
		 * @param code the code
		 */
		ReturnCode(int code) {
			this.code = code;
		}
	}

	/**
	 * The Enum Extraction Status.
	 */
	enum ExtractionStatus {

		/** The pass. */
		PASS, 
		/** The partial. */
		PARTIAL, 
		/** The fail. */
		FAIL;

		/** The return code. */
		ReturnCode returnCode;
	}

	/** The thread name. */
	private String threadName = UNALLOCATED; // 1

	/** The feed type. */
	private FeedType feedType; // 2

	/** The feed name. */
	private String feedName; // 3

	/** The last extrxn time. */
	private String lastExtrxnTime; // 4

	/** The start time. */
	private Date startTime; // 5

	/** The end time. */
	private Date endTime; // 6

	/** The total found. */
	private int totalFound; // 7

	/** The accepted. */
	private int accepted; // 8

	/** The erroneously missed. */
	private int erroneouslyMissed;

	/** The valid rejections. */
	private int validRejections; // 9

	/** The errors. */
	private List<String> errors;

	// Assign FAIL by default assuming A finished thread will set it as SUCCESS
	/** The status. */
	private ExtractionStatus status = ExtractionStatus.FAIL;

	/** The sc infolet id. */
	private String scInfoletId;

	/** The save attachments. */
	private String saveAttachments;

	/** The channel id. */
	private String channelId;

	/** The source id. */
	private String sourceId;

	/**
	 * Gets the header entry.
	 *
	 * @return the header entry
	 */
	public static String getHeaderEntry() {
		return "THREAD, TYPE, SERVER/URL, PREV-RUN, START, END, ELAPSE Time(sec), Updates Found, Acceptd, Rejectd, Missed, Status, Reason, Errors\n";
	}

	/**
	 * Gets the blank entry.
	 *
	 * @return the blank entry
	 */
	public static String getBlankEntry() {
		return "-,-,-,-,-,-,-,-,-,-,-,-,-,-\n";
	}

	@Override
	public String toString() {
		final SimpleDateFormat ft1 = new SimpleDateFormat("dd-MMM-yy k:mm:ss");

		StringBuffer statsObj = new StringBuffer();
		statsObj.append(threadName + " , ");
		statsObj.append(feedType + " , ");
		statsObj.append(feedName + " , ");

		String lastXtrnDateStr = null;
		try {
			Long lastXtrnDateTime = Long.parseLong(lastExtrxnTime);
			Date prevXtrnDate = new Date(lastXtrnDateTime);
			lastXtrnDateStr = ft1.format(prevXtrnDate);
		} catch (Exception t) {
			// We weren't able to format the date! Print the date as long
		}

		statsObj.append(lastXtrnDateStr + " , ");

		statsObj.append(((startTime != null) ? ft1.format(startTime) : "")
				+ " , ");
		statsObj.append(((endTime != null) ? ft1.format(endTime) : "") + " , ");

		double elapsed = 0;
		if (endTime != null && startTime != null) {
			elapsed = (endTime.getTime() - startTime.getTime() + 0.00) / 1000; // endTime
		}

		statsObj.append(elapsed + " , ");
		statsObj.append(totalFound + " , ");
		statsObj.append(accepted + " , ");
		statsObj.append(validRejections + " , ");
		statsObj.append(erroneouslyMissed + " , ");
		statsObj.append(status.toString() + " ,");
		statsObj.append((errors != null ? errors.toString() : "") + " , ");
		statsObj.append((status.returnCode != null) ? (status.returnCode
				.toString()) : "");
		statsObj.append("\n");

		return statsObj.toString();
	}

	/**
	 * Sets the thread name.
	 *
	 * @param threadName the new thread name
	 */
	public void setThreadName(String threadName) {
		this.threadName = threadName;
	}

	/**
	 * Sets the feed type.
	 *
	 * @param feedType the new feed type
	 */
	public void setFeedType(FeedType feedType) {
		this.feedType = feedType;
	}

	/**
	 * Gets the feed type.
	 *
	 * @return the feed type
	 */
	public FeedType getFeedType() {
		return feedType;
	}

	/**
	 * Sets the feed name.
	 *
	 * @param feedName the new feed name
	 */
	public void setFeedName(String feedName) {
		this.feedName = feedName;
	}

	/**
	 * Sets the last extrxn time.
	 *
	 * @param lastExtrxnTime the new last extrxn time
	 */
	public void setLastExtrxnTime(String lastExtrxnTime) {
		this.lastExtrxnTime = lastExtrxnTime;
	}

	/**
	 * Sets the start time.
	 *
	 * @param startTime the new start time
	 */
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	/**
	 * Sets the end time.
	 *
	 * @param endTime the new end time
	 */
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	/**
	 * Sets the total found.
	 *
	 * @param totalFound the new total found
	 */
	public void setTotalFound(int totalFound) {
		this.totalFound = totalFound;
	}

	/**
	 * Sets the accepted.
	 *
	 * @param accepted the new accepted
	 */
	public void setAccepted(int accepted) {
		this.accepted = accepted;
	}

	/**
	 * Sets the erroneously missed.
	 *
	 * @param erroneouslyMissed the new erroneously missed
	 */
	public void setErroneouslyMissed(int erroneouslyMissed) {
		this.erroneouslyMissed = erroneouslyMissed;
	}

	/**
	 * Sets the valid rejections.
	 *
	 * @param validRejections the new valid rejections
	 */
	public void setValidRejections(int validRejections) {
		this.validRejections = validRejections;
	}

	/**
	 * Adds the error.
	 *
	 * @param error the error
	 */
	public void addError(String error) {
		if (errors == null) {
			errors = new LinkedList<String>();
		}
		errors.add(error);
	}

	/**
	 * Sets the status.
	 *
	 * @param status the new status
	 */
	public void setStatus(ExtractionStatus status) {
		this.status = status;
	}

	/**
	 * Gets the status.
	 *
	 * @return the status
	 */
	public ExtractionStatus getStatus() {
		return status;
	}

	/**
	 * Sets the sc infolet id.
	 *
	 * @param scInfoletId the new sc infolet id
	 */
	public void setScInfoletId(String scInfoletId) {
		this.scInfoletId = scInfoletId;
	}

	/**
	 * Sets the save attachments.
	 *
	 * @param saveAttachments the new save attachments
	 */
	public void setSaveAttachments(String saveAttachments) {
		this.saveAttachments = saveAttachments;
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
	 * Incremented accepted.
	 */
	public void incrementedAccepted() {
		accepted++;

	}

	/**
	 * Incremented erroneously missed.
	 */
	public void incrementedErroneouslyMissed() {
		erroneouslyMissed++;

	}

	/**
	 * Incremented valid rejections.
	 */
	public void incrementedValidRejections() {
		validRejections++;

	}

	/**
	 * Gets the feed name.
	 *
	 * @return the feed name
	 */
	public String getFeedName() {
		return feedName;
	}

	/**
	 * Gets the sc infolet id.
	 *
	 * @return the sc infolet id
	 */
	public String getScInfoletId() {
		return scInfoletId;
	}

	/**
	 * Gets the channel id.
	 *
	 * @return the channel id
	 */
	public String getChannelId() {
		return channelId;
	}

	/**
	 * Gets the source id.
	 *
	 * @return the source id
	 */
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

}
