package com.metricstream.systemi.ext.infolet;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class FeedQueueTest {

	Feed[] feed;

	@BeforeClass
	private void setup() {
		feed = new Feed[5];
		for (int i = 0; i < 5; i++) {
			Feed f = mock(Feed.class);
			when(f.getAddress()).thenReturn("address-" + i);
			feed[i] = f;
		}
	}

	@Test
	public void getNextfeedWithProperValues() {
		FeedQueue fq = new FeedQueue(feed);
		for (int i = 0; i < 5; i++) {
			Feed next = fq.getNextFeed();
			Assert.assertEquals(next.getAddress(), "address-" + i);
		}
	}

	@Test(expectedExceptions = java.lang.NullPointerException.class)
	public void getNextfeedWithNull() {
		FeedQueue fq = new FeedQueue(null);
		Assert.assertNull(fq.getNextFeed());
	}

	@Test
	public void getNextfeedWithProperEmpty() {
		FeedQueue fq = new FeedQueue(new Feed[] {});
		Assert.assertNull(fq.getNextFeed());
	}

}
