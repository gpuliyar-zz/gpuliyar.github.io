package com.metricstream.systemi.ext.infolet;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.Test;

import com.metricstream.systemi.ext.infolet.EmailFeedAggregatorInfolet.EmailPopServer;
import com.metricstream.systemi.ext.infolet.RSSFeedAggregatorInfolet.RSSUrlConn;
import com.metricstream.systemi.ext.infolet.util.Utils;

public class FeedAggregatorTest {

	FeedAggregator agg;
	ArrayList<String[]> params = null;
	Feed[] data;
	private Object NO_OF_EXPECTED_FEEDS;
	Map<String, Object> properties;
	private ArrayList<String[]> wrongParamList;

	@BeforeGroups(groups = { "Email" })
	public void setupEmail() throws Exception {
		properties = Utils.loadProperties("mail");
		params = (ArrayList<String[]>) properties.get("SERVER_PARAMS");
		wrongParamList = (ArrayList<String[]>) properties
				.get("FAIL_SERVER_PARAMS");
		NO_OF_EXPECTED_FEEDS = properties.get("NO_OF_EXPECTED_FEEDS");
		System.out.println(NO_OF_EXPECTED_FEEDS);
		agg = new EmailFeedAggregatorInfolet(
				Collections.synchronizedMap(new HashMap<String, String>()));
		data = agg.getFeeds(params);

	}

	@BeforeGroups(groups = { "RSS" })
	public void setupRSS() throws Exception {
		properties = Utils.loadProperties("rss");
		params = (ArrayList<String[]>) properties.get("SERVER_PARAMS");
		NO_OF_EXPECTED_FEEDS = properties.get("NO_OF_EXPECTED_FEEDS");
		agg = new RSSFeedAggregatorInfolet(
				Collections.synchronizedMap(new HashMap<String, String>()));
		data = agg.getFeeds(params);
	}

	@Test(groups = { "Email" }, enabled = true)
	public void processEmailFeeds() throws Exception {
		List<FeedXtraxnStats> feedStats = new LinkedList<FeedXtraxnStats>();

		Object[][] aggResult = agg.processFeeds(getValidGmailfeed(), feedStats);
		Assert.assertEquals(aggResult.length, 2);
	}

	private Feed[] getValidGmailfeed() throws Exception {
		EmailPopServer pservers = new EmailFeedAggregatorInfolet(
				new HashMap<String, String>()).new EmailPopServer("pop.gmail.com", "feeds.msi@gmail.com", "welcome*123", "mail.pop3.ssl.enable=true", null, null, null, null);
		EmailPopServer[] server = new EmailPopServer[1];
		server[0] = pservers;
		return server;
	}

	@Test(groups = { "RSS" }, enabled = true)
	public void processRSSFeeds() throws Exception {
		List<FeedXtraxnStats> feedStats = new LinkedList<FeedXtraxnStats>();
		Object[][] aggResult = agg.processFeeds(getValidRSSFeed(), feedStats);
		System.out.println(aggResult.length);
		Assert.assertEquals(aggResult.length, 25);
	}

	private Feed[] getValidRSSFeed() throws Exception {
		RSSUrlConn pservers = new RSSFeedAggregatorInfolet(
				new HashMap<String, String>()).new RSSUrlConn("http://www.dzone.com/feed/frontpage/rss.xml", null, "", "", null, null, null, null);
		RSSUrlConn[] server = new RSSUrlConn[1];
		server[0] = pservers;
		return server;
	}

	/**
	 * When you pass null, ideally it should not pass. Need refactoring
	 * 
	 * @throws Exception
	 */
	@Test(groups = { "Email", "Fail" })
	public void mailWrongServerCredentialsNull() throws Exception {
		data = agg.getFeeds(null);
		Assert.assertNull(data);
	}

	@Test(groups = { "Email", "Fail" }, expectedExceptions = { Exception.class })
	public void mailWrongServerCredentialsLessThan3() throws Exception {
		ArrayList<String[]> wrongParamList = new ArrayList<String[]>();
		String[] par = new String[2];
		par[0] = "";
		par[1] = "";
		wrongParamList.add(par);
		data = agg.getFeeds(wrongParamList);
	}

	/**
	 * This should ideally fail.Need refactoring
	 * 
	 * @throws Exception
	 */
	@Test(groups = { "Email", "Fail" }, expectedExceptions = Exception.class)
	public void mailWrongServerCredentialsMoreThan8() throws Exception {
		data = agg.getFeeds(getMoreParams());
	}

	private List<String[]> getMoreParams() {
		ArrayList<String[]> wrongParamList = new ArrayList<String[]>();
		String[] par = new String[10];
		for (int i = 0; i < 10; i++) {
			par[0] = "";
		}
		wrongParamList.add(par);
		return wrongParamList;
	}

}
