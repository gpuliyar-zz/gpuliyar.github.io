package com.metricstream.systemi.rga.rdc.route;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;

import com.metricstream.log.Logger;
import com.metricstream.systemi.rga.rdc.route.processor.SFTPReadInputProcessor;
import com.metricstream.systemi.rga.rdc.route.processor.SFTPReadOutputProcessor;



/**
 * Class to represent RDC Read route
 * @author Asif U
 */
public class RDCSFTPReadRoute extends RouteBuilder{

	/* (non-Javadoc)
	 * @see org.apache.camel.builder.RouteBuilder#configure()
	 */
	@Override
	public void configure() throws Exception {
		System.out.println(" In route -->");
		//TO-DO need to remove the hardcodings.
		//from("sftp://grc-content@securexfer.metricstream.com?password=Gr5132").process(new SFTPReadInputProcessor()).to("file:/home/oracle/GrcIntelligenceDev/").end().onCompletion().process(new SFTPReadOutputProcessor());
		try{
			Logger.debug("RDCSFTPReadRoute", "Insdie the class", null);
			onException(Exception.class).process(new Processor() {
				
				@Override
				public void process(Exchange exchange) throws Exception {
					// TODO Auto-generated method stub
					System.out.println(exchange.getException());
				}
			});
			///upload/regular
			//from("sftp://grc-content@securexfer.metricstream.com?password=Gr5132").process(new SFTPReadInputProcessor()).to("file:/home/oracle/GrcIntelligenceDev/").end().onCompletion().process(new SFTPReadOutputProcessor());
			from("http://msi-vmdevgrcintlap.metricstream.com:9001/2014/sample.xml").setHeader(Exchange.FILE_NAME,constant("sample.xml")).process(new SFTPReadInputProcessor()).to("sftp://mr03000t@ftp.rdc.com/upload/regular?password=dbLW38$").end().onCompletion().process(new SFTPReadOutputProcessor());
			
		}catch(Exception ex){
			Logger.error("RDCSFTPReadRoute","Error while putting data to sftp ",ex);
		}
	}

}
