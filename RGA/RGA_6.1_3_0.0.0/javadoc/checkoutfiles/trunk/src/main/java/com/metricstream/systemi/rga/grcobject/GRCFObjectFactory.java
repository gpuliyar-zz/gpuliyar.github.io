/**
 * 
 */
package com.metricstream.systemi.rga.grcobject;

import com.metricstream.systemi.rga.grcobject.cfr.GRCFCFRObjectManager;

/**
 * GRCFObjectFactory factory class which returns various
 * content provider details to generate the GRCF Objects.
 * @author munavar.basha
 *
 */
public class GRCFObjectFactory {
	
	/**
	 * generateGrcfObject returns various GRCF Content provider specific classes to generate objects.
	 * @param contType
	 * @return GRCFObjectManager
	 */
	public static GRCFObjectManager generateGrcfObject(GRCFContentTypes contType){
		
		//TO-DO Need to implement switch case when we have more content providers.
		
		if(contType == GRCFContentTypes.GRCF_CFR_CONTENT){
			return new GRCFCFRObjectManager();
		}
		
		return null;
	}

}
