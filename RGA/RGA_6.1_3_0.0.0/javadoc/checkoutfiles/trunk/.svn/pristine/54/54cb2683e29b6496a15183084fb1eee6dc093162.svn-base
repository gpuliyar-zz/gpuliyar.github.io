package com.metricstream.systemi.ext.infolet;

import java.io.IOException;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.Test;

import com.metricstream.systemi.ext.infolet.util.Utils;

public class FeedXtranStatsTest {

	long linesBeforeRun;
	private Map<String, Object> properties;
	private String logPath;

	@BeforeGroups(groups = { "Utils" })
	public void setup() throws IOException {
		properties = Utils.loadProperties("common");
		logPath = properties.get("LOG_PATH") + "/aggr-stats.csv";
		linesBeforeRun = Utils.getNumberOfLines(logPath);
	}

	@Test(groups = { "Utils" })
	public void toStringTest() throws IOException {
		FeedXtraxnStats stats = new FeedXtraxnStats();
		Assert.assertNotNull(stats.toString());
	}
}
