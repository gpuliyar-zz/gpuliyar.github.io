/**
 * Class: EmailFeedAggregator
 * Author: B.Vasant
 * Description:
 * This class, when invoked completely reads a given set of POP3 server and returns
 * a set of mail records from all of them.
 */
package com.metricstream.systemi.ext.infolet;

// Generic imports

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import com.metricstream.log.Logger;
import com.metricstream.systemi.rga.domain.FeedInfo;

/**
 * This class reads one or more email channels aggregates a set of POP3 server
 * feeds to read email records. You can mark the email records are marked as
 * 'Read' or 'Deleted' after this has returned the records. This uses a pool of
 * multiple threads to spawn off multiple POP3 connections & retrieve results.
 * This speeds up overall response times for the aggregator.
 * 
 * This is how the classes in here fit together EmailFeedAggregator: Overall
 * class. Takes a list of pop servers as input and returns a list of message
 * data. It basically spawns a number of EmailFeedReader threads that pull from
 * the common queue of pop servers to process. As the thread is exhausted by
 * this parallel set of threads, the job is done & all messages have been
 * retrieved. EmailFeedServersQ: Internal data object to store a list of pop
 * servers that must be processed. The concurrent threads of the feed aggregator
 * pull new pop servers to process from this common queue.
 * EmailFeedReaderThread: This is the body of each thread EmailPopServer:
 * Represents an email pop server and its message store EmailMessageData: This
 * is the message data retrieved from the email store
 * <p>
 */
public class EmailFeedAggregatorInfolet extends FeedAggregator {

	/** The Constant CLASS_ID. */
	private static final String CLASS_ID = "EmailFeedAggregatorInfolet";

	/**
	 * Instantiates a new email feed aggregator infolet.
	 *
	 * @param timeStamps the time stamps
	 */
	EmailFeedAggregatorInfolet(Map<String, String> timeStamps) {
		super(timeStamps);
	}

	/**
	 * Retrieve the feed from a specific set of POP servers separated by commas.
	 * 
	 * @param popServers
	 *            This is expected as a list of String[] objects. The list
	 *            contains all the pop servers. The 2nd level array is a
	 *            4-element string array with server, username, password & other
	 *            server properties "prop1=val1;prop2=val2" (no spaces)
	 * @return the feeds
	 * @throws Exception the exception
	 */
	@Override
	public List<Feed> getFeeds(List<FeedInfo> popServers) throws Exception {
		if (popServers == null) {
			return null; // Sanity check
		}

		List<Feed> pservers=new ArrayList<Feed>();
		for(FeedInfo pserver:popServers){
			Logger.debug(CLASS_ID, "Pop Server = " + pserver.getUrl(), null);
			Logger.debug(CLASS_ID, "getEmailType " + pserver.getEmailType(), null);
			if(pserver.getEmailType().equalsIgnoreCase("IMAP")){
				EmailIMAPServer	pserversTemp = new EmailIMAPServer(pserver.getUrl(), pserver.getUserName(),
							pserver.getPassword(), pserver.getParameter(), pserver.getUpdatedFeedTimeStamp(), pserver.getSCInfoletID(),
							pserver.getSaveAttachment(), pserver.getChannelId(), pserver.getSourceId(), pserver.getEmailType());
				pservers.add(pserversTemp);
			}else if(pserver.getEmailType().equalsIgnoreCase("POP3")){
				EmailPopServer	pserversTemp = new EmailPopServer(pserver.getUrl(), pserver.getUserName(),
						pserver.getPassword(), pserver.getParameter(), pserver.getUpdatedFeedTimeStamp(), pserver.getSCInfoletID(),
						pserver.getSaveAttachment(), pserver.getChannelId(), pserver.getSourceId(),pserver.getEmailType());
			pservers.add(pserversTemp);
			}
		}

		Logger.debug(CLASS_ID, "pservers count = " + pservers.size(), null);
		return pservers;
	}

}
