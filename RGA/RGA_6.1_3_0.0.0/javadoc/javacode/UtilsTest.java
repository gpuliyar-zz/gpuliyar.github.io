package com.metricstream.systemi.utils;

import org.testng.Assert;
import org.testng.annotations.Test;

public class UtilsTest {

	@Test
	public void getLongValueForEmptyObject() {
		Assert.assertEquals(Utils.getLongValue(new Object()), -1);
	}

	@Test
	public void getLongValueForNull() {
		Assert.assertEquals(Utils.getLongValue(null), -1);
	}

	@Test
	public void getLongValueForInt() {
		Assert.assertEquals(Utils.getLongValue(1), 1);
	}

	@Test
	public void getLongValueForLong() {
		Assert.assertEquals(Utils.getLongValue(2L), 2);
	}

	@Test
	public void getLongValueForString() {
		Assert.assertEquals(Utils.getLongValue("100"), 100);
	}

}
