package com.metricstream.systemi.rga.grcobject.cfr;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.metricstream.appstudio.cif.Response;
import com.metricstream.appstudio.cif.handlers.SuccessCallBack;

/**
 * Success Call back class on success of CIF api
 * @author manish.svk
 */
public class SuccessCallBackImpl implements SuccessCallBack{

	static Logger logger = LoggerFactory.getLogger(SuccessCallBackImpl.class);
	
	/*
	 * This method is called on on success event
	 * @param response response object
	 */
	@Override
	public void onSuccess(Response response) {
		logger.info("Received success response for CifRequestId: {}"+ response.getRequestId());
		logger.info("Received success response for CifRequestId: {}"+ response.getStatus());
		logger.info("Received success response for CifRequestId: {}"+ response.getBody());
	}

}