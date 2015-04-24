package com.metricstream.systemi.rga.cif;

import com.metricstream.appstudio.cif.AuthorizeResponse;
import com.metricstream.appstudio.cif.Message;
import com.metricstream.appstudio.cif.Response;
import com.metricstream.appstudio.cif.ResponseStatus;
import com.metricstream.appstudio.cif.UserContext;
import com.metricstream.appstudio.cif.ValidateResponse;
import com.metricstream.appstudio.cif.annotations.Authorize;
import com.metricstream.appstudio.cif.annotations.CifService;
import com.metricstream.appstudio.cif.annotations.Processor;
import com.metricstream.appstudio.cif.annotations.Validate;
import com.metricstream.appstudio.cif.exceptions.CifException;
import com.metricstream.log.Logger;
import com.metricstream.systemi.rga.cpi.CPIContentMongoReader;
import com.msi.grcintelligence.cpi.data.CPIData;
import com.msi.grcintelligence.util.GRCIConstants;

/**
 * This class is used to return the CPI data requested for a particular year
 * 
 * @author martin.simon
 *
 */
@CifService(name = "CPIContentRetrievalService", concurrentMessages = 5)
public class CPIContentRetriever {
	/**
	 * This method is for authorizing the request
	 * 
	 * @param context
	 * @return
	 */
	@Authorize
	public AuthorizeResponse authorize(UserContext context) {
		Logger.debug("CPIContentRetriever", "authorization called for service:{}" + context, null);
		return new AuthorizeResponse.Builder().setResponseStatus(ResponseStatus.SUCCESS).build();
	}

	/**
	 * This method is used to validate the request
	 * 
	 * @param message
	 * @return
	 */
	@Validate
	public ValidateResponse validate(Message message) {
		Logger.debug("CPIContentRetriever", "validate called :{}" + message, null);
		return new ValidateResponse.Builder().setResponseStatus(ResponseStatus.SUCCESS).build();
	}

	/**
	 * This method retrieves the CPI Data for a particular year
	 * 
	 * @param message
	 * @return
	 * @throws CifException
	 */
	@Processor
	public Response process(Message message) throws CifException {
		Logger.debug("CPIContentRetriever", "Processing message[ID: {}]" + message.getRequestId(), null);
		Logger.debug("CPIContentRetriever", "Received the message with body: {}" + message.getBody(), null);
		int year = getYear((String) message.getBody());
		Logger.debug("CPIContentRetriever", "Retrieving requested data for year '{}'" + year, null);
		CPIData cpiData = CPIContentMongoReader.getInstance().readCPIContentFromMongo(year, GRCIConstants.CPI_CONTENT_CLIENT_COLLECTION_NAME);
		Logger.debug("CPIContentRetriever::process", "[service]: Processing message[ID: {}]" + message.getRequestId(), null);
		return new Response.Builder(message).setStatus(ResponseStatus.SUCCESS).setBody(cpiData).build();
	}

	/**
	 * This method converts the incoming year information from a string to integer
	 * 
	 * @param strYear
	 * @return
	 */
	private int getYear(String strYear) {
		int year = 0;
		try {
			Logger.debug("CPIContentRetriever", "Year is " + strYear, null);
			year = Integer.parseInt(strYear);
		} catch (NumberFormatException e) {
			Logger.error("CPIContentRetriever", "Couldn't parse string:-" + strYear, e);
		}
		return year;
	}
}
