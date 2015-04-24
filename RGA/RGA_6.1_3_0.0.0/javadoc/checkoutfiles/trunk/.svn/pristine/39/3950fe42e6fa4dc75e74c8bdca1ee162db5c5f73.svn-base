package com.metricstream.systemi.ext.task;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import com.metricstream.log.Logger;

/**
 * This class contains the logic for downloading the scorm training file from content server to application
 * 
 * @author martin.simon
 *
 */
public class StreamContentDownloadTask {
	private String fileLocation;
	private String downloadLocation;

	/**
	 * This constructor is used to initialize the source location and target folder for downloading the scorm training file
	 * 
	 * @param fileLocation
	 * @param downloadLocation
	 */
	public StreamContentDownloadTask(String fileLocation, String downloadLocation) {
		this.fileLocation = fileLocation;
		this.downloadLocation = downloadLocation;
	}

	/**
	 * This method contains the logic for downloading the scorm training file from content server to application
	 * 
	 * @return
	 */
	public boolean execute() {
		InputStream inputStream = null;
		FileOutputStream outputStream = null;
		HttpURLConnection urlConnection = null;
		try {
			// Obtaining the connection to http location
			Logger.info("StreamContentDownloadTask", "Obtaining connection", null);
			URL url = new URL(fileLocation);
			urlConnection = (HttpURLConnection) url.openConnection();
			urlConnection.setConnectTimeout(100000);
			urlConnection.connect();
			Logger.info("StreamContentDownloadTask", "Connected", null);
			int responseCode = urlConnection.getResponseCode();
			Logger.info("StreamContentDownloadTask", "Response code is:- " + responseCode, null);
			switch (responseCode) {
			case 200:
				// Reading the content
				inputStream = urlConnection.getInputStream();
				int contentLength = urlConnection.getContentLength();
				byte[] content = new byte[contentLength];
				inputStream.read(content);
				Logger.info("StreamContentDownloadTask", "File read successfully " + downloadLocation, null);
				// Writing the content
				outputStream = new FileOutputStream(downloadLocation);
				outputStream.write(content);
				Logger.info("StreamContentDownloadTask", "File downloaded successfully " + downloadLocation, null);
				break;
			default:
				Logger.error("StreamContentDownloadTask", "No data found", null);
			}
		} catch (Exception e) {
			Logger.error("StreamContentDownloadTask", "Couldnot download file " + downloadLocation, e);
			return false;
		} finally {
			try {
				if (inputStream != null) {
					inputStream.close();
				}
				if (outputStream != null) {
					outputStream.flush();
					outputStream.close();
				}
				if (urlConnection != null) {
					urlConnection.disconnect();
				}
				Logger.info("StreamContentDownloadTask", "Connections closed successfully", null);
			} catch (IOException e) {
				Logger.error("StreamContentDownloadTask", "Couldnot download file " + fileLocation, e);
				return false;
			}
		}
		return true;
	}
}