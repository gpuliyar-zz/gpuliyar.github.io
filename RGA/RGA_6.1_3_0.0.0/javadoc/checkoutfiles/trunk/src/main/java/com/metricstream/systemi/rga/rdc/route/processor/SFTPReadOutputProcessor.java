package com.metricstream.systemi.rga.rdc.route.processor;

import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;

// TODO: Auto-generated Javadoc
/**
 *  Class to represent RDC Read ouput processor
 *  @author Asif U
 */
public class SFTPReadOutputProcessor implements Processor{

	
	/* (non-Javadoc)
	 * @see org.apache.camel.Processor#process(org.apache.camel.Exchange)
	 */
	@Override
	public void process(Exchange arg0) throws Exception {
		System.out.println("SFTPOutputProcessor ===>");
		CamelContext cc = arg0.getContext();
		cc.stopRoute(arg0.getFromRouteId());
		cc.removeRoute(arg0.getFromRouteId());
		System.out.println(" parsing xml data");
		parseXMLData();
		
	}
	
	
	/**
	 * Method to Parses the xml data.
	 */
	public void parseXMLData(){
/*
		try {
//			ClassLoader cl = com.metricstream.rdc.pojo.rib.ObjectFactory.class.getClassLoader();
//			XMLToJAXBObject cParser = new XMLToJAXBObject("com.metricstream.rdc.pojo.rib", cl);
//			RibResponse ribr = (RibResponse)cParser.parseData("D:\\testcontent\\RIB_IRX_RD01000P_20090330_142814.xml");
			//System.out.println("rib details -->" + ribr.getHeader().getVersion());
		} catch (JAXBException e) {
			System.out.println("print error -- " + e.toString());

           // logger.error("Exception : {}",e.getMessage(), e);
		}
*/
	}

}
