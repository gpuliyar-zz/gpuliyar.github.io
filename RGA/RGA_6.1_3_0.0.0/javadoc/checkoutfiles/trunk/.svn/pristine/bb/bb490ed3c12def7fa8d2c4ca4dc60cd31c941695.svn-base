package com.metricstream.systemi.rga.grcobject.cfr;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.metricstream.appstudio.cif.handlers.ErrorCallBack;

/**
 * Error callback class for error scenario
 * @author manish.svk
 */
public class ErrorCallBackImpl implements ErrorCallBack {

	static Logger logger = LoggerFactory.getLogger(ErrorCallBack.class);
	
	@Override
	public void onError(Throwable arg0, String arg1) {
		logger.info(" Error while invoking CIF API " + arg1);
		logger.error(" arg0 {}" , arg0);
	}
}