package com.metricstream.systemi.ext.infolet;

/**
 * Created with IntelliJ IDEA.
 * User: praveen.gb
 * Date: 6/20/14
 * Time: 3:05 PM
 * To change this template use File | Settings | File Templates.
 */

import com.metricstream.log.Logger;
import com.metricstream.systemi.utils.Utils;
import com.metricstream.systemi.utils.ssl.ReloadableX509TrustManager;

import javax.mail.*;
import javax.mail.search.DateTerm;
import javax.mail.search.SearchTerm;
import javax.mail.search.SentDateTerm;
import javax.net.ssl.X509TrustManager;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.*;

/**
 * This private class specifies an email POP3 server.
 */
public class EmailPopServer extends Feed {

    /** The Constant CLASS_ID. */
    private static final String CLASS_ID = "EmailPopServer";

    /** The inbox folder. */
    private Folder inboxFolder = null;
    
    /** The pop store. */
    private Store popStore = null;

    // Create the server will all info
    /**
     * Instantiates a new email pop server.
     *
     * @param server the server
     * @param userID the user id
     * @param password the password
     * @param propStr the prop str
     * @param lastAccessedTimeStamp the last accessed time stamp
     * @param structuredContentInfoletId the structured content infolet id
     * @param saveAttachments the save attachments
     * @param channelId the channel id
     * @param sourceId the source id
     * @param emailType the email type
     * @throws Exception the exception
     */
    EmailPopServer(String server, String userID, String password,
                   String propStr, String lastAccessedTimeStamp,
                   String structuredContentInfoletId, String saveAttachments,
                   String channelId, String sourceId, String emailType) throws Exception {
        super(server, lastAccessedTimeStamp, userID, password, propStr,
                structuredContentInfoletId, saveAttachments, channelId, sourceId,emailType);

        Logger.debug(CLASS_ID, "inside with " + server + "," + userID + ","
                + password + "," + propStr + "," + lastAccessedTimeStamp
                + "," + structuredContentInfoletId + "," + saveAttachments
                + "," + channelId + "," + sourceId, null);

        if ((userID == null) || (userID.length() == 0)) {
            throw new Exception(
                    "EmailPopServer: Missing Server Pop3 User ID");
        }

        if ((password == null) || (password.length() == 0)) {
            throw new Exception(
                    "EmailPopServer: Missing Server Pop3 Password");
        }
        feedType = FeedType.EMAIL;
        validateCertificate(server);
    }

    /**
     * Validate certificate.
     *
     * @param server the server
     * @throws GeneralSecurityException the general security exception
     * @throws IOException Signals that an I/O exception has occurred.
     */
    private void validateCertificate(String server)
            throws GeneralSecurityException, IOException {
        char SEP = File.separatorChar;
        String cert = System.getProperty("java.home") + SEP + "lib" + SEP
                + "security" + SEP + "cacerts";
        @SuppressWarnings("unused")
        X509TrustManager trustManager = new ReloadableX509TrustManager(
                cert, server);

    }

    /*
     * Method to Validate Channel Users
     */
    /* (non-Javadoc)
     * @see com.metricstream.systemi.ext.infolet.Feed#validate(com.metricstream.systemi.ext.infolet.FeedXtraxnStats)
     */
    @Override
    boolean validate(FeedXtraxnStats stats) {
    	Logger.debug(CLASS_ID, "validate(FeedXtraxnStats stats)  " + password, null);
        String response = validate(address, userID, password, propStr);
        if(!response.equals(SUCCESS))
        {
            stats.addError(response);
            return false;
        }
        return true;
    }

    /**
     * Validate.
     *
     * @param address the address
     * @param userID the user id
     * @param password the password
     * @param serverParams the server params
     * @return the string
     */
    public static String validate(String address, String userID, String password, String serverParams) {
        Store store = null;
        String responseMsg = FAILURE;
        boolean resp = false;
        Session session = null;
        Properties props = new Properties();

        Logger.debug(CLASS_ID, "Validating Username :  " + userID + "\tOn Server : " + address, null);
        Logger.debug(CLASS_ID, "serverParams :  " + serverParams, null);

        props = setSSLProperty(props);
        
	    props = EmailPopServer.setSSLProperty(props);
	    session = Session.getInstance(props, null);
        
        try {
            store = session.getStore("pop3");
            if (store != null) {
                store.connect(address, userID, password);
                if (store.isConnected()) {
                    Logger.debug(CLASS_ID, "Validated User : " + userID + "@" + address, null);
                    resp = true;
                }
            }
        } catch (NoSuchProviderException e) {
            Logger.error(CLASS_ID, "Rejected Invalid server OR credentials : " + userID + "@" + address, e);
            responseMsg = e.getMessage();
        } catch (MessagingException e) {
            Logger.error(CLASS_ID, "Rejected Invalid server OR credentials : "+ userID + "@" + address, e);
            responseMsg = e.getMessage();
        }
        finally {
            try {
                if (store != null) {
                    store.close();
                }
            } catch (MessagingException e) {
                Logger.error(CLASS_ID, "Exception while closing email connection ", e);
            }
        }

        if (resp) {
            responseMsg = SUCCESS;
        }

        return responseMsg;
    }

    /**
     * Get the inbox folder for this server.
     *
     * @return folder the inbox folder to read from for this POP server
     * @throws Exception the exception
     */
    Folder getInboxFolder() throws Exception {
        // See if we already created one. Else create one.
    	Session popSession = null;
    	String propStr = "mail.pop3.ssl.enable=true";
        if (inboxFolder != null) {
            return inboxFolder;
        }

        String userName = "";
       /* if (address != null && address.equals("pop.gmail.com")) {
            userName = "" + userID;
        } else {*/
            userName = userID;
       // }
        // Set up the session
        Properties props = new Properties();

        if (propStr != null) {
            String[] propVals = propStr.split("[=;]");
            for (int i = 0; i + 1 < propVals.length; i += 2) {
            	// check value
            	   Logger.debug(CLASS_ID, "---- Parameters in propStr : "
                           + propVals[i] + ", " + propVals[i + 1] + "", null);
                props.put(propVals[i], propVals[i + 1]);
            }
        }
        String SSL_Property = props.getProperty("mail.pop3.ssl.enable");
        if (SSL_Property != null && SSL_Property.equals("true")) {
            props = EmailPopServer.setSSLProperty(props);
        }
        // Get the instance
        popSession = Session.getInstance(props, null);

        // Get the store
        popStore = popSession.getStore("pop3");
        Logger.debug(CLASS_ID, "---- Connecting to email server : "
                + address + ", " + userID + ", " + password, null);
        popStore.connect(address, userName, password);
        
        // Get the inbox folder
  
        inboxFolder = popStore.getFolder("Inbox");
     
        // Excellente - now we are ready to start processing messages
        return inboxFolder;
    }

    /* (non-Javadoc)
     * @see com.metricstream.systemi.ext.infolet.Feed#getUpdates(java.util.List, java.util.Map)
     */
    @Override
    public FeedXtraxnStats getUpdates(List<ChannelFeedData> msgs, Map<String, String> feedTimeStamps)
            throws Exception {
        return getUnreadMessages(msgs, feedTimeStamps);
    }

    /**
     * Process all the unread messages in the folder and return a set of
     * EmailMessageData objects.
     *
     * @param messages            a list to store all the retrieved EmailMessageData objects
     * @param feedTimeStamps the feed time stamps
     * @return the # of new messages processed. May return -1 if error
     *         opening inbox
     * @throws Exception the exception
     */
    FeedXtraxnStats getUnreadMessages(List<ChannelFeedData> messages, Map<String, String> feedTimeStamps)
            throws Exception {
        return getMessages(messages, feedTimeStamps);
    }

    /**
     * Process all the messages in the folder and return a set of
     * EmailMessageData objects.
     *
     * @param messages            a list to store all the retrieved EmailMessageData objects
     * @param feedTimeStamps the feed time stamps
     * @return the # of new messages processed. May return -1 if error
     *         opening inbox
     * @throws Exception the exception
     */
    FeedXtraxnStats getMessages(List<ChannelFeedData> messages, Map<String, String> feedTimeStamps)
            throws Exception {

        FeedXtraxnStats stats = new FeedXtraxnStats();
        stats.setThreadName(Thread.currentThread().getName());
        stats.setLastExtrxnTime(lastAccessedTimeStamp);
        stats.setStartTime(new Date());
        stats.setFeedType(FeedType.EMAIL);
        stats.setFeedName(FeedAggregator.getOnlyUserName(userID) + "@" + address);
        Folder folder = getInboxFolder();
        if (!inboxFolder.isOpen()) {
            folder.open(Folder.READ_ONLY); // added by SumitL
        }
      
        
        // Sanity check
        if (inboxFolder != null) {
            Logger.debug(CLASS_ID, "Inbox is valid ", null);

            String strLastTimestamp = getLastAccessedTimestamp();
            long lastFeedTimestamp = 0;
            Long maxFeedTimestamp = new Long(0);
            if (strLastTimestamp != null) {
                lastFeedTimestamp = Long.valueOf(strLastTimestamp);
            }
            Logger.info(CLASS_ID, "lastFeedTimestamp for " + userID + " "
                    + lastFeedTimestamp, null);
            // Get the messages
            Message[] msgs = null;
            try {

                if (inboxFolder.isOpen()) { // Ensure an open folder
                    SearchTerm newer = new SentDateTerm(DateTerm.GE,
                            getLastSentDate(lastFeedTimestamp));
                   
                    msgs = inboxFolder.search(newer);

                    // Then go through them
                    if ((msgs != null) && (msgs.length != 0)) {

                        Logger.debug(CLASS_ID, "Detected messages = "
                                + msgs.length, null);
                        long feedTimestamp;
                        Date sentDate = new Date();
                        for (int i = 0; i < msgs.length; i++) {
                            long start = System.currentTimeMillis();
                            Logger.debug(CLASS_ID, "starting iter:" + i,
                                    null);
                            try {
                                String uid = null;
                                sentDate = msgs[i].getSentDate();
                                feedTimestamp = sentDate.getTime();
                                if (feedTimestamp > lastFeedTimestamp) {
                                    Message email = msgs[i];
                                    String emailFileName = writeEmail(email);
                                    messages.add(new EmailMessageData(
                                            address, userID, email, uid,
                                            structuredContentInfoletId,
                                            saveAttachment, channelID,
                                            sentDate, emailFileName, sourceID));
                                    stats.incrementedAccepted();
                                    if (maxFeedTimestamp < feedTimestamp) {
                                        maxFeedTimestamp = feedTimestamp;
                                    }
                                    Logger.debug(
                                            CLASS_ID,
                                            "iter:"
                                                    + i
                                                    + "; time taken for processing a valid feed="
                                                    + (System
                                                    .currentTimeMillis() - start),
                                            null);
                                } else {
                                    stats.incrementedValidRejections();
                                }

                            } catch (Exception e) {
                                Logger.error(
                                        CLASS_ID,
                                        "Error reading msg with Subj : '"
                                                + msgs[i].getSubject()
                                                + "' \nError Msg :: "
                                                + e.getMessage(), e);
                                stats.setStatus(FeedXtraxnStats.ExtractionStatus.PARTIAL);
                                stats.incrementedErroneouslyMissed();
                                stats.addError(e.getMessage());
                                continue; // get other messages
                            }

                        }

                        String userName = FeedAggregator.getOnlyUserName(userID) + "@"
                                + address;
                        if (maxFeedTimestamp > 0) {
                            feedTimeStamps.put(userName, maxFeedTimestamp.toString());
                        }
                        Logger.debug(CLASS_ID, "maxFeedTimestamp for "
                                + userName
                                + " = "
                                + (maxFeedTimestamp > 0 ? maxFeedTimestamp
                                : lastFeedTimestamp), null);

                    } else {
                        Logger.debug(CLASS_ID, "no msgs to process - "
                                + (msgs == null ? msgs : msgs.length), null);
                    }

                }
                if (stats.getStatus() != FeedXtraxnStats.ExtractionStatus.PARTIAL) {
                    stats.setStatus(FeedXtraxnStats.ExtractionStatus.PASS); // Set success
                    // code
                }

            } catch (Exception e) {
                Logger.error(CLASS_ID, "Error While Getting Email Messages"
                        + e.getMessage() + " MSG" + e.getStackTrace(), e);

            } finally {
                stats.setEndTime(new Date());
                stats.setTotalFound(msgs == null ? 0 : msgs.length);
                stats.setAccepted(messages.size());
            }
        }

        stats.setSaveAttachments(saveAttachment);
        stats.setScInfoletId(structuredContentInfoletId);
        stats.setChannelId(channelID);
        stats.setSourceId(sourceID);
        // Done retrieving messages
        return stats;
    }

    /**
     * Write email.
     *
     * @param email the email
     * @return the string
     */
    private String writeEmail(Message email) {
        StringBuilder folder = new StringBuilder(
                Utils.getGrciWorkDirectory()).append(UUID.randomUUID());
        File grciDir = new File(folder.toString());
        if (!grciDir.exists()) {
            grciDir.mkdirs();
        }
        String fileName = folder.append(".eml").toString();
        try {
            email.writeTo(new FileOutputStream(new File(fileName)));
        } catch (FileNotFoundException e) {
            Logger.error(CLASS_ID, "", e);
        } catch (IOException e) {
            Logger.error(CLASS_ID, "", e);
        } catch (MessagingException e) {
            Logger.error(CLASS_ID, "", e);
        }
        return fileName;
    }

    /**
     * Gets the last sent date.
     *
     * @param lastFeedTimestamp the last feed timestamp
     * @return the last sent date
     */
    private Date getLastSentDate(long lastFeedTimestamp) {
        Calendar lastSentDate = Calendar.getInstance();
        lastSentDate.setTimeInMillis(lastFeedTimestamp);
        Logger.debug(CLASS_ID, "lastSentDate = " + lastSentDate, null);
        lastSentDate.set(Calendar.HOUR, 0);
        lastSentDate.set(Calendar.MINUTE, 0);
        lastSentDate.set(Calendar.SECOND, 0);
        lastSentDate.set(Calendar.MILLISECOND, 0);
        return lastSentDate.getTime();
    }

    /**
     * Close out this server connection.
     *
     * @throws MessagingException the messaging exception
     */
    @Override
    void close() throws MessagingException {
        if (inboxFolder != null && inboxFolder.isOpen()) {
            inboxFolder.close(true);
            inboxFolder = null;
        }
        if (popStore != null) {
            popStore.close();
            popStore = null;
        }
    }
    
	/*
	 * Method to Set SSL Property for SSL POP server connectivity
	 */
	/**
	 * Sets the ssl property.
	 *
	 * @param props the props
	 * @return the properties
	 */
	public static Properties setSSLProperty(Properties props) {
		props.put("mail.pop3.socketFactory.class",
				"javax.net.ssl.SSLSocketFactory");
		props.put("mail.pop3.socketFactory.fallback", "false");
		props.put("mail.pop3.port", "995");
		props.put("mail.pop3.socketFactory.port", "995");
		return props;
	}
}

