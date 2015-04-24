/**
 * Class: RSSFeedAggregatorInfolet
 * Author: Sumit L
 * Description:
 * This class, when invoked completely reads a given set of URLs and returns
 * a set of RSS feed records from all of them.
 */
package com.metricstream.systemi.ext.infolet;

// Generic imports

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.metricstream.log.Logger;
import com.metricstream.systemi.rga.domain.FeedInfo;

/**
 * The RSS Feed Aggregator Infolet.
 */
public class RSSFeedAggregatorInfolet extends FeedAggregator {

	/** The Constant CLASS_ID. */
	private static final String CLASS_ID = "RSSFeedAggregatorInfolet";
	
	/** The Constant dateFormatter. */
	static final SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");

	/**
	 * Instantiates a new RSS feed aggregator infolet.
	 *
	 * @param timeStamps the time stamps
	 */
	RSSFeedAggregatorInfolet(Map<String, String> timeStamps) {
		super(timeStamps);
	}

	/**
	 * Method gets the rss feeds
	 * @param feedUrlConns rss feed url list
	 */
	@Override
	public List<Feed> getFeeds(List<FeedInfo> feedUrlConns) throws Exception {
		if (feedUrlConns == null) {
			return null;
		}

		List<Feed> urlConns = new ArrayList<Feed>();
		for(FeedInfo urlConn:feedUrlConns){
			if (urlConn.getUrl()==null) {
				throw new Exception("Feed URL not specified properly.");
			}

			Logger.debug(CLASS_ID, "URL connection = " + urlConn.getUrl(), null);
			RSSUrlConn rss = new RSSUrlConn(urlConn.getUrl(), urlConn.getUpdatedFeedTimeStamp(),
					urlConn.getUserName(), urlConn.getPassword(), null, urlConn.getSCInfoletID(), urlConn.getSaveAttachment(),
					urlConn.getChannelId(), urlConn.getSourceId());
			urlConns.add(rss);
		}
		return urlConns;
	}
}
