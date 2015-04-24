package com.metricstream.systemi.logger;

import com.metricstream.systemi.rga.dao.DBManager;
import org.apache.log4j.FileAppender;
import org.apache.log4j.Level;
import org.apache.log4j.PatternLayout;
import org.apache.log4j.RollingFileAppender;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * The Logger class
 */
public class Log {
	
	/** The logger. */
	public static org.apache.log4j.Logger logger;
	
	/** The metricstream home. */
	public static String METRICSTREAM_HOME = System.getProperty("METRICSTREAM.HOME");
	
	/** The Constant CONVERSION_PATTERN. */
	public static final String CONVERSION_PATTERN = "log4j.appender.layout.ConversionPattern";
	
	/** The Constant LOG_LEVEL. */
	public static final String LOG_LEVEL = "LOG_LEVEL";

	/** The Constant LOG_MAX_SIZE. */
	public static final String LOG_MAX_SIZE = "LOG_MAX_SIZE";
	
	/** The Constant LOG_MAX_FILES. */
	public static final String LOG_MAX_FILES = "LOG_MAX_FILES";

	/**
	 * Instantiates a new log.
	 */
	public Log() {
	}

	static {
		logger = org.apache.log4j.Logger.getLogger(Log.class);

		Properties prop = DBManager.getInstance().getAllProperties();
		FileAppender as_appender = null;
		String grciLogFolderName = ".";
		String fileName = "";
		try {
			if (METRICSTREAM_HOME == null || METRICSTREAM_HOME.equals("")) {
				File directory = new File(".");
				METRICSTREAM_HOME = directory.getCanonicalPath();
			}
			grciLogFolderName = METRICSTREAM_HOME + "/log/grci/";
			// Create log directory
			File logDir = new File(grciLogFolderName);
			if (!logDir.exists()) {
				logDir.mkdirs();
			}

			fileName = grciLogFolderName + "grci.log";

		} catch (FileNotFoundException e1) {
			com.metricstream.log.Logger.error("Log.class","FileNotFoundException occurred ", e1);
		} catch (IOException e1) {
			com.metricstream.log.Logger.error("Log.class","IOException occurred ", e1);
		}
		// reading conversion pastern and Log level from Property file.
		String conversionPattern = "%d{MM/dd/yy HH:mm:ss.SSS z} %p [%C{1}.%M] - %m%n";
		String logLevel = prop.getProperty(LOG_LEVEL);
		String maxFileSize = prop.getProperty(LOG_MAX_SIZE);
		String maxBackupFiles = prop.getProperty(LOG_MAX_FILES);

		try {
			as_appender = new RollingFileAppender(new PatternLayout(
					conversionPattern), fileName);
		} catch (IOException e) {
			com.metricstream.log.Logger.error("Log.class",
					"IOException occurred ", e);
		}

		// Setting the Log level read from Property file
		logger.setLevel(Level.toLevel(logLevel));
		((org.apache.log4j.RollingFileAppender) as_appender)
				.setMaxFileSize(maxFileSize);
		((org.apache.log4j.RollingFileAppender) as_appender)
				.setMaxBackupIndex(Integer.parseInt(maxBackupFiles));
		logger.addAppender(as_appender);

	}

}
