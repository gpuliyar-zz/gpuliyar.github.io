package com.metricstream.systemi.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigInteger;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.metricstream.log.Logger;
import com.metricstream.systemi.client.servlet.servant.Newdownloadsingle;
import com.metricstream.systemi.constant.Name;
import com.metricstream.systemi.constant.Property;
import com.metricstream.systemi.ext.infolet.ChannelFeedAggregatorInfolet;
import com.metricstream.systemi.ext.infolet.FeedXtraxnStats;
import com.metricstream.systemi.interfaces.DocRepository;
import com.metricstream.systemi.server.common.Controller;
import com.metricstream.systemi.server.push.Connector;

/**
 * Utility class for RGA module
 */
public class Utils {

	/** The Constant CLASS_ID. */
	private static final String CLASS_ID = "Utils";
	
	/** The msi home. */
	public static String MSI_HOME = null;

	/**
	 * Instantiates a new utils.
	 */
	private Utils() {
	}

	/**
	 * Method to convert object value to long fomrat
	 *
	 * @param infoletId the infolet id
	 * @return the long value
	 */
	public static long getLongValue(Object infoletId) {
		if (infoletId == null) {
			return -1;
		}
		try {
			return Long.parseLong(infoletId.toString());
		} catch (NumberFormatException e) {
			return -1;
		}
	}
	
	
	/**
	 * Method to convert object value to int fomrat
	 *
	 * @param infoletId the infolet id
	 * @return the int value
	 */
	public static int getIntValue(Object infoletId) {
		
		try {
			return Integer.parseInt(infoletId.toString());
		} catch (Exception e) {
			return -1;
		}
	}

	// This and the below saveFile to be refactored into a single api in next
	// release. have modularised the api but need to experiment with
	// part.inputsream() from emal aggr code for completeion

	/**
	 * Method to save a file.
	 *
	 * @param fileName the file name
	 * @param input the input
	 * @return the string
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static String saveFile(String fileName, InputStream input)
			throws IOException {

		Connection connection = null;
		InputStream inStream = null;
		FileOutputStream fos = null;
		BufferedOutputStream bos = null;
		BufferedInputStream bis = null;
		try {
			connection = getConnection();

			// Do not overwite an existing file
			File file = new File(fileName);
			for (int i = 0; file.exists(); i++) {
				file = new File(fileName + i);
			}

			fos = new FileOutputStream(file);
			bos = new BufferedOutputStream(fos);
			bis = new BufferedInputStream(input);

			int aByte;
			while ((aByte = bis.read()) != -1) {
				bos.write(aByte);
			}
			bos.flush();

			DocRepository docRepository = getDocRepository();
			inStream = new FileInputStream(file);
			Map<String, Object[]> formParams = getFormParams();
			String uniqueFileId = getAttachmentId(connection, inStream, file,
					docRepository, formParams);
			try {
				boolean fileDeleted = file.delete();
				if (!fileDeleted) {
					Logger.warning(CLASS_ID,
							"Failed to delete file " + file.getAbsolutePath()
									+ " from TEMP directory", null);
				} else {
					Logger.debug(
							CLASS_ID,
							"Successfully deleted file "
									+ file.getAbsolutePath(), null);
				}
			} catch (Exception ex) {
				Logger.error(CLASS_ID,
						"Failed to delete file " + file.getAbsolutePath()
								+ " from TEMP directory", ex);
			}
			return uniqueFileId;

		} catch (Exception e) {
			Logger.error(CLASS_ID, "Exception in getting a connection ", e);
			return null;
		} finally {
			if (connection != null) {
				try {
					Controller.getConnectionPool().returnConnection(connection);
				} catch (Exception e) {
				}
			}
			closeInputStream(inStream);
			closeInputStream(bis);
			closeOutputStream(fos);
			closeOutputStream(bos);
		}
	}

	/**
	 * Method to close output stream.
	 *
	 * @param fos the fos
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	private static void closeOutputStream(OutputStream fos) throws IOException {
		if (fos != null) {
			fos.close();
		}
	}

	/**
	 * Method to close input stream.
	 *
	 * @param inStream the in stream
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	private static void closeInputStream(InputStream inStream)
			throws IOException {
		if (inStream != null) {
			inStream.close();
		}

	}

	/**
	 * Method to download file from url.
	 *
	 * @param url the url
	 * @return the string
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static String downloadFileFromUrl(String url) throws IOException {
		FileOutputStream fos = null;
		String fileName = getGrciWorkDirectory() + getFileNameFromUrl(url);
		try {
			URL website = new URL(url);
			ReadableByteChannel rbc = Channels.newChannel(website.openStream());
			fos = new FileOutputStream(fileName);
			fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
			return fileName;
		} finally {
			if (fos != null) {
				fos.close();
			}
		}
	}

	// keeping it simple now. expectation is a static path is given in url
	/**
	 * Method to get the file name from url.
	 *
	 * @param url the url
	 * @return the file name from url
	 */
	private static String getFileNameFromUrl(String url) {
		return url.substring(url.lastIndexOf("/"));
	}

	/**
	 * Method to get the msi home location
	 *
	 * @return the msi home
	 */
	public static String getMsiHome() {
		if (MSI_HOME == null) {
			MSI_HOME = System.getProperty("METRICSTREAM.HOME");
		}
		return MSI_HOME;
	}

	/**
	 * Method to get the grci work directory.
	 *
	 * @return the grci work directory
	 */
	public static String getGrciWorkDirectory() {
		String grci = new StringBuilder(getMsiHome()).append("/GRCI/")
				.toString();
		File dir = new File(grci);
		if (!dir.exists()) {
			dir.mkdirs();
		}
		return grci;

	}

	/**
	 *  Method to save a file.
	 *
	 * @param fileName the file name
	 * @return the string
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static String saveFile(String fileName) throws IOException {

		Connection connection = null;
		InputStream inStream = null;
		try {
			File file = new File(fileName);
			connection = getConnection();
			DocRepository docRepository = getDocRepository();
			inStream = new FileInputStream(fileName);
			Map<String, Object[]> formParams = getFormParams();
			String uniqueFileId = getAttachmentId(connection, inStream, file,
					docRepository, formParams);
			try {
				boolean fileDeleted = file.delete();
				if (!fileDeleted) {
					Logger.warning(CLASS_ID,
							"Failed to delete file " + file.getAbsolutePath()
									+ " from TEMP directory", null);
				} else {
					Logger.debug(
							CLASS_ID,
							"Successfully deleted file "
									+ file.getAbsolutePath(), null);
				}
			} catch (Exception ex) {
				Logger.error(CLASS_ID,
						"Failed to delete file " + file.getAbsolutePath()
								+ " from TEMP directory", ex);
			}
			return uniqueFileId;

		} catch (Exception e) {
			Logger.error(CLASS_ID, "Exception in getting a connection ", e);
			return null;
		} finally {
			if (connection != null) {
				try {
					Controller.getConnectionPool().returnConnection(connection);
				} catch (Exception e) {
				}
			}
			closeInputStream(inStream);
		}

	}

	/**
	 * Method to get the attachment id.
	 *
	 * @param connection the connection
	 * @param inStream the in stream
	 * @param file the file
	 * @param docRepository the doc repository
	 * @param formParams the form params
	 * @return the attachment id
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	private static String getAttachmentId(Connection connection,
			InputStream inStream, File file, DocRepository docRepository,
			Map<String, Object[]> formParams) throws IOException {
		String uniqueFileId = docRepository.newId(connection, null, -1, -1, -1,
				"attachment", formParams, 0);
		uniqueFileId = docRepository
				.put(inStream, uniqueFileId, file.getName());
		uniqueFileId = uniqueFileId + "?" + Newdownloadsingle.getCRC(file);
		uniqueFileId = file.getName() + Connector.ATTACHMENT_DELIMITER
				+ uniqueFileId;
		Logger.debug(CLASS_ID, "uniqueFileId = " + uniqueFileId, null);
		return uniqueFileId;
	}

	/**
	 * Method to get the parameter names of form
	 *
	 * @return the form params
	 */
	private static Map<String, Object[]> getFormParams() {
		Map<String, Object[]> formParams = new HashMap<String, Object[]>();
		formParams.put("attachment_dir", new Object[] { "/" });
		return formParams;
	}

	/**
	 * Method to get the doc repository.
	 *
	 * @return the doc repository
	 */
	private static DocRepository getDocRepository() {
		return (DocRepository) (Controller
				.getComponent(Controller
						.getThis()
						.getPrivateProperties()
						.getProperty(Property.REPOSITORY_MANAGER,
								Name.FILE_REPOSITORY)));
	}

	/**
	 * Method to gets the connection.
	 *
	 * @return the connection
	 * @throws SQLException the SQL exception
	 */
	private static Connection getConnection() throws SQLException {
		Connection connection;
		Controller controller;
		controller = Controller.getThis();
		connection = controller.getPrivateConnectionPool().getConnection(
				CLASS_ID);
		return connection;
	}
	
	/**
	 * Method to Write to log of stats to csv file
	 *
	 * @param stats the stats
	 * @param printSeperatorLine the print seperator line
	 */
	public static void writeToLog(List<FeedXtraxnStats> stats,
			boolean printSeperatorLine) {
		String grciLogFolderName = ".";
		String reportFileName = "./run-"
				+ ChannelFeedAggregatorInfolet.ft1.format(new Date()) + ".csv";
		String METRICSTREAM_HOME = System.getProperty("METRICSTREAM.HOME");
		BufferedWriter writer = null;

		boolean justCreated = false;

		try {
			if (METRICSTREAM_HOME == null || METRICSTREAM_HOME.equals("")) {
				File directory = new File(".");
				METRICSTREAM_HOME = directory.getCanonicalPath();
				Logger.debug(CLASS_ID,
						"METRICSTREAM_HOME not found setting to => "
								+ METRICSTREAM_HOME, null);
			}

			grciLogFolderName = METRICSTREAM_HOME + "/log/grci/";
			// Create log directory
			File logDir = new File(grciLogFolderName);
			if (!logDir.exists()) {
				Logger.debug(CLASS_ID, "Creating folder => "
						+ grciLogFolderName, null);
				logDir.mkdirs();
			}

			reportFileName = grciLogFolderName + "aggr-stats.csv";
			Logger.debug(CLASS_ID, "Appending stats to => " + reportFileName,
					null);

			File reportFile = new File(reportFileName);
			if (!reportFile.exists()) {
				Logger.debug(
						CLASS_ID,
						"Creating stats file : "
								+ reportFile.getCanonicalPath(), null);
				reportFile.createNewFile();
				justCreated = true;
			}

		} catch (Exception ex) {
			Logger.error(CLASS_ID,
					"Error creating or opening Stats logfolder => "
							+ grciLogFolderName + " OR logfile => "
							+ reportFileName + ex.getMessage(), ex);
		}

		try {
			writer = new BufferedWriter(new FileWriter(reportFileName, true));
			if (justCreated) {
				writer.write(FeedXtraxnStats.getHeaderEntry());
			}

			for (FeedXtraxnStats stat : stats) {
				if (stat != null) {
					writer.write(stat.toString());
				}
			}

		} catch (Exception e) {
			Logger.error(CLASS_ID, "Exception Occured while writing stats", e);
		} finally {
			try {
				if (writer != null) {
					writer.flush();
					writer.close();
				}
			} catch (IOException e) {
				Logger.error(CLASS_ID, "Exception Occured while closing writer",
						e);
			}
		}
	}
	
	  /**
  	 * Method to get the String value
  	 *
  	 * @param str the str
  	 * @return the value
  	 */
  	public static String getValue(String str)
	  {
	    if (str == null) {
	      return "";
	    }
	    return str;
	  }
  	
  	public static String getValue(BigInteger str){
  		if(null== str){
  			return "";
  		}
  		return String.valueOf(str);
  	}
  		
}
