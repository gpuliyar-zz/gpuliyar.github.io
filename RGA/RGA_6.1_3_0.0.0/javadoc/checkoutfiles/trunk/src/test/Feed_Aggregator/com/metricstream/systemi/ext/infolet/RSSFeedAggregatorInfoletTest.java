package com.metricstream.systemi.ext.infolet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.Test;

import com.metricstream.systemi.ext.infolet.util.Utils;

public class RSSFeedAggregatorInfoletTest {

	RSSFeedAggregatorInfolet agg;
	ArrayList<String[]> params = null;
	Feed[] channelData;
	private Object NO_OF_EXPECTED_FEEDS;
	Map<String, Object> properties;
	private ArrayList<String[]> wrongParamList;

	@BeforeGroups(groups = { "Feeds", "RSS", "Fail" })
	public void beforeClass() throws IOException {
		agg = new RSSFeedAggregatorInfolet(
				Collections.synchronizedMap(new HashMap<String, String>()));
		properties = Utils.loadProperties("rss");
		params = (ArrayList<String[]>) properties.get("SERVER_PARAMS");
		wrongParamList = (ArrayList<String[]>) properties
				.get("FAIL_SERVER_PARAMS");
	}

	// The infolet api does not return anything to indicate that the address was
	// wrong. The exception is digested. Refactor if required.
	@Test(groups = { "Feeds", "RSS" })
	public void getFeedsData() throws Exception {
		System.out.println("param size=" + params.size());
		channelData = agg.getFeeds(params);
		Assert.assertEquals(channelData.length, params.size());
		Assert.assertTrue(channelData.length > 0);
	}

	@Test(groups = { "Fail" })
	public void wrongServerWithNull() throws Exception {
		channelData = agg.getFeeds(null);
		Assert.assertNull(channelData);
	}

	/**
	 * The case should have been handled but it is not.Need refactoring
	 * 
	 * @throws Exception
	 */
	@Test(groups = { "Fail" })
	public void wrongServerWithEmptyList() throws Exception {
		channelData = agg.getFeeds(new ArrayList<String[]>());
		Assert.assertEquals(channelData.length, 0);
	}

	/**
	 * The case should have been handled but it is not.Need refactoring
	 * 
	 * @throws Exception
	 */
	@Test(groups = { "Fail" })
	public void wrongServerWithMoreParams() throws Exception {
		channelData = agg.getFeeds(new ArrayList<String[]>());
		Assert.assertEquals(channelData.length, 0);
	}

}
