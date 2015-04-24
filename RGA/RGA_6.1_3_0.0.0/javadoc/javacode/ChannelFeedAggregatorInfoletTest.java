package com.metricstream.systemi.ext.infolet;

import java.io.IOException;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.Test;

import com.metricstream.systemi.ext.infolet.util.Utils;

public class ChannelFeedAggregatorInfoletTest {

	String logPath;
	ChannelFeedAggregatorInfolet infolet;

	@BeforeClass
	private void setup() {
		infolet = new ChannelFeedAggregatorInfolet();
	}

	@BeforeGroups({ "Feeds" })
	private void setupGroup() throws IOException {
		logPath = Utils.loadProperties("common").get("LOG_PATH").toString();

	}

	@Test(groups = { "Feeds" })
	public void writeToLog() throws IOException {
		long linesBeforeRun = Utils.getNumberOfLines(logPath
				+ "/aggr-stats.csv");
		List<FeedXtraxnStats> statsList = new LinkedList<FeedXtraxnStats>();
		FeedXtraxnStats stats = new FeedXtraxnStats();
		stats.setFeedName("TestName");
		statsList.add(stats);
		ChannelFeedAggregatorInfolet.writeToLog(statsList, true);
		Assert.assertTrue(Utils.verifyLogs("TestName", 2, linesBeforeRun,
				logPath));
	}

	@Test(groups = { "Util" })
	public void testGetAMonthEarlierDate() {
		long earlierDateinMS = ChannelFeedAggregatorInfolet
				.getAMonthEarlierDate().getTime();
		long todayInMS = Calendar.getInstance().getTimeInMillis();
		long msInADay = 1000 * 60 * 60 * 24;
		Assert.assertEquals((todayInMS - earlierDateinMS) / msInADay, 30);
	}

	// positive case
	@Test(groups = { "Feeds" })
	public void processChannelFeeds() throws IOException {
		long linesBeforeRun = Utils.getNumberOfLines(logPath
				+ "/aggr-stats.csv");
		Object[][] result = infolet.processChannelFeeds();
		Assert.assertNotNull(result);
		// Assert.assertTrue(result.length > 0);
		Assert.assertTrue(Utils.verifyLogs("FAIL", 11, linesBeforeRun, logPath));
	}

	@Test(groups = { "Feeds" })
	public void writeToLogWithEmptyList() throws IOException {
		long linesBeforeRun = Utils.getNumberOfLines(logPath
				+ "/aggr-stats.csv");
		List<FeedXtraxnStats> statsList = new LinkedList<FeedXtraxnStats>();
		ChannelFeedAggregatorInfolet.writeToLog(statsList, true);
		Assert.assertEquals(linesBeforeRun,
				Utils.getNumberOfLines(logPath + "/aggr-stats.csv"));
	}

	@Test(groups = { "Feeds", "Fail" })
	public void writeToLogWithNull() throws IOException {
		long linesBeforeRun = Utils.getNumberOfLines(logPath
				+ "/aggr-stats.csv");
		ChannelFeedAggregatorInfolet.writeToLog(null, true);
		Assert.assertEquals(linesBeforeRun,
				Utils.getNumberOfLines(logPath + "/aggr-stats.csv"));
	}

}
