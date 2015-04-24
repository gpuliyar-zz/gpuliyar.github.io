package com.metricstream.systemi.ext.infolet.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class Utils {

	public static Map<String, Object> loadProperties(String feedType)
			throws IOException {
		Map<String, Object> properties = new HashMap<String, Object>();
		System.out.println("inside loadProperties");
		ArrayList<String[]> params = new ArrayList<String[]>();
		ArrayList<String[]> wrongParams = new ArrayList<String[]>();
		String propFileName = "config/" + feedType + ".properties";
		Properties p = new Properties();
		p.load(new FileInputStream(propFileName));

		for (String prop : p.stringPropertyNames()) {
			String value = p.getProperty(prop);
			if (value != null) {
				if (prop.toUpperCase().startsWith("FEED")) {
					String[] feedParams = value.split(";");
					params.add(feedParams);
				} else if (prop.equalsIgnoreCase("NO_OF_EXPECTED_FEEDS")) {
					properties.put("NO_OF_EXPECTED_FEEDS",
							Integer.parseInt(value));
				} else if (prop.equalsIgnoreCase("LOG_PATH")) {
					properties.put("LOG_PATH", value);
				} else if (prop.toUpperCase().startsWith("XFEED")) {
					String[] feedParams = value.split(";");
					wrongParams.add(feedParams);
				}

			}
		}
		if (!params.isEmpty()) {
			properties.put("SERVER_PARAMS", params);
		}
		if (!wrongParams.isEmpty()) {
			properties.put("FAIL_SERVER_PARAMS", wrongParams);
		}
		return properties;
	}

	/**
	 * @param entryToCheck
	 * @param checkIndex
	 * @return
	 * @throws IOException
	 * 
	 *             Will skip to the line upto which the file was written before
	 *             the test invocation and will check for entryToCheck at
	 *             checkIndex
	 */
	public static boolean verifyLogs(String entryToCheck, int checkIndex,
			long linesBeforeRun, String logPath) throws IOException {
		BufferedReader rdr = null;
		try {
			rdr = new BufferedReader(new FileReader(new File(logPath
					+ "/aggr-stats.csv")));

			String line;
			int count = 0;
			while ((line = rdr.readLine()) != null) {
				++count;
				if (count <= linesBeforeRun) {
					continue;
				}
				System.out.println(line);
				String[] entries = line.split(",");
				if (!entries[checkIndex].trim().equals(entryToCheck)) {
					return false;
				}
			}
		} finally {
			if (rdr != null) {
				rdr.close();
			}
		}

		return true;
	}

	// will return total lines. use of skip() will fail if there are characters
	// more than Long.MAX_VALUE in the file
	public static long getNumberOfLines(String file) throws IOException {
		LineNumberReader lnr = new LineNumberReader(new FileReader(new File(
				file)));
		lnr.skip(Long.MAX_VALUE);
		lnr.close();
		return lnr.getLineNumber();
	}
}
