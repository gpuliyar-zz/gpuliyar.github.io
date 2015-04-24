package com.metricstream.systemi.ext.infolet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.testng.Assert;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.Test;

import com.metricstream.systemi.ext.infolet.EmailFeedAggregatorInfolet.EmailPopServer;
import com.metricstream.systemi.ext.infolet.util.Utils;

public class EmailFeedAggregatorInfoletTest {

	EmailFeedAggregatorInfolet agg;
	ArrayList<String[]> paramList;
	ArrayList<String[]> wrongParamList;
	Feed[] data;
	Map<String, Object> properties;

	@BeforeGroups(groups = { "Feeds", "Email", "Fail" })
	public void setup() throws IOException {
		System.out.println("inside before class");
		agg = new EmailFeedAggregatorInfolet(
				Collections.synchronizedMap(new HashMap<String, String>()));
		properties = Utils.loadProperties("mail");
		paramList = (ArrayList<String[]>) properties.get("SERVER_PARAMS");
		wrongParamList = (ArrayList<String[]>) properties
				.get("FAIL_SERVER_PARAMS");
	}

	@Test(groups = { "Feeds", "Email" })
	public void getFeedsData() throws Exception {
		// List<FeedXtraxnStats> feedStats = new LinkedList<FeedXtraxnStats>();
		data = agg.getFeeds(paramList);
		Assert.assertEquals(data.length, paramList.size());
	}

	@Test(groups = { "Email", "Utils" })
	public void validateSetSSLProperties() {
		Properties props = new Properties();
		EmailFeedAggregatorInfolet.setSSLProperty(props);
		Assert.assertEquals(props.get("mail.pop3.socketFactory.class"),
				"javax.net.ssl.SSLSocketFactory");
		Assert.assertEquals(props.get("mail.pop3.socketFactory.fallback"),
				"false");
		Assert.assertEquals(props.get("mail.pop3.port"), "995");
		Assert.assertEquals(props.get("mail.pop3.socketFactory.port"), "995");
	}

	@Test(groups = { "Fail" }, expectedExceptions = { java.lang.Exception.class })
	public void wrongServerLessThan3() throws Exception {
		data = agg.getFeeds(wrongParamList);
	}

	/**
	 * This test has to ideally expect an exception. The case is not handled in
	 * the code. Need refactoring
	 * 
	 * @throws Exception
	 */
	@Test(groups = { "Fail" }, expectedExceptions = { java.lang.Exception.class })
	public void wrongServerMoreThan5() throws Exception {
		data = agg.getFeeds(wrongParamList);
	}

	@Test(groups = { "Feeds", "Email" })
	public void checkEmail() throws Exception {
		long start = System.currentTimeMillis();
		EmailFeedAggregatorInfolet agg = new EmailFeedAggregatorInfolet(
				Collections.synchronizedMap(new HashMap<String, String>()));
		// EmailPopServer pop = agg.new EmailPopServer("pop.gmail.com",
		// "feeds.msi@gmail.com", "welcome*123", "mail.pop3.ssl.enable=true",
		// null, null, "yes", "dummy channel id");
		// EmailPopServer pop = agg.new EmailPopServer("pop.gmail.com",
		// "grci1523@gmail.com", "welcome*12", "mail.pop3.ssl.enable=true",
		// null, null, "yes", "dummy channel id");
		// EmailPopServer pop = agg.new
		// EmailPopServer("plus.pop.mail.yahoo.com", "metricstream.alerts1",
		// "welcome*123", "mail.pop3.ssl.enable=true", null, null, "yes",
		// "dummy channel id");
		// vishnu.annam@msi-jamessrv-01.metricstream.com
		// EmailPopServer pop = agg.new
		// EmailPopServer("msi-jamessrv-01.metricstream.com", "vishnu.annam",
		// "welcome*123", "", null, null, "yes", "dummy channel id");
		EmailPopServer pop = agg.new EmailPopServer("pop3.live.com", "metricstream.alerts1@hotmail.com", "welcome*123", "mail.pop3.ssl.enable=true", null, null, "yes", "dummy channel id");

		List<ChannelFeedData> msgs = new ArrayList<ChannelFeedData>();
		// SearchTerm UNREAD_ITEMS_SEARCH_TERM = new FlagTerm(new Flags(
		// Flags.Flag.SEEN), false);
		// pop.getMessages(msgs, UNREAD_ITEMS_SEARCH_TERM);

		pop.getMessages(msgs);
		System.out.println("total time = "
				+ (System.currentTimeMillis() - start) + " millisecs");
		Assert.assertNotNull(msgs);
		System.out.println(msgs.size());
		Assert.assertTrue(msgs.size() > 0);

	}

}
