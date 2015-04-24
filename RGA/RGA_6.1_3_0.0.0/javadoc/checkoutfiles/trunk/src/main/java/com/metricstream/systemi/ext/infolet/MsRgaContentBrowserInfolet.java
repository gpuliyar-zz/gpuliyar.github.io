package com.metricstream.systemi.ext.infolet;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.context.ContextLoader;

import com.metricstream.log.Logger;
import com.metricstream.systemi.jpa.util.JPAManager;
import com.metricstream.systemi.rga.dao.DBInstance;
import com.metricstream.systemi.rga.dao.DBManager;
import com.metricstream.systemi.rga.dao.IDBHelper;
import com.metricstream.systemi.rga.grcobject.GRCFContentDetails;
import com.metricstream.systemi.rga.grcobject.GRCFObjectDetails;
import com.metricstream.systemi.rga.grcobject.cfr.GRCFCFRObjectManager;
import com.msi.grcintelligence.utility.mongodb.CFRAnnualContent;
import com.msi.grcintelligence.utility.mongodb.CFRAnnualUtil;
import com.msi.grcintelligence.utility.mongodb.MasterCollectionContent;

/**
 * This infolet exposes the various methods to fetch content details form client mongo db .
 * @author manish.svk
 */
public class MsRgaContentBrowserInfolet {

	/**
	 * This method returns all the content categories for a content provider.
	 * @param strConteneProvider : Content Provider Name
	 * @return Array of Content Categories
	 */
	public String[][] getContentCategories(String strConteneProvider){
		Logger.info("MsRgaContentBrowserInfolet", "Inside getContentCategories", null);
		List<String[]> alCategoryList = null;
		try {
			MongoTemplate mongoTemplate = (MongoTemplate)ContextLoader.
					getCurrentWebApplicationContext().getBean("mongoTemplate");
			alCategoryList = new ArrayList<String[]>();
			Query query = new Query();
			query.addCriteria(Criteria.where("contentProviderName").is(strConteneProvider));
			Logger.debug("MsRgaContentBrowserInfolet", "Query:"+query.toString(), null);
			Logger.debug("MsRgaContentBrowserInfolet", "mongoTemplate:"+mongoTemplate, null);

			List<MasterCollectionContent> lMasterColList = mongoTemplate.find(
					query, MasterCollectionContent.class, "MasterCollectionDetails");
			Logger.debug("MsRgaContentBrowserInfolet", "Result Size:"+lMasterColList.size(), null);

			List<String> categoryList = new ArrayList<String>();
			for(MasterCollectionContent masterRec : lMasterColList){
				String strCategory = masterRec.getContentCategory();
				if(!categoryList.contains(strCategory)){
					String[] row=new String[2];
					row[0]=strCategory;
					row[1]=strCategory;
					alCategoryList.add(row);
					categoryList.add(strCategory);
				}

			}
		} catch (Exception e) {
			Logger.error("MsRgaContentBrowserInfolet", "Error in getContentCategories:"+e, null);
		}
		return alCategoryList.toArray(new String[0][0]);
	}

	/**
	 * This method returns all the content version for the given provider and category.
	 * @param strConteneProvider Content Provider Name
	 * @param strContentCategory Content Category Name
	 * @return Array of Content Versions
	 */
	public String[][] getContentVersion(String strConteneProvider, String strContentCategory){
		Logger.info("MsRgaContentBrowserInfolet", "Inside getContentVersion", null);
		List<String[]> alVersionList = null;
		try {
			MongoTemplate mongoTemplate = (MongoTemplate)ContextLoader.
					getCurrentWebApplicationContext().getBean("mongoTemplate");
			alVersionList = new ArrayList<String[]>();
			Query query = new Query();
			query.addCriteria(Criteria.where("contentProviderName").is(strConteneProvider).
					andOperator(Criteria.where("contentCategory").is(strContentCategory)));
			Logger.debug("MsRgaContentBrowserInfolet", "Query:"+query.toString(), null);
			Logger.debug("MsRgaContentBrowserInfolet", "mongoTemplate:"+mongoTemplate, null);

			List<MasterCollectionContent> lMasterColList = mongoTemplate.find(query, 
					MasterCollectionContent.class, "MasterCollectionDetails");
			Logger.debug("MsRgaContentBrowserInfolet", "Result Size:"+lMasterColList.size(), null);
			for(MasterCollectionContent masterRec : lMasterColList){
				String[] row=new String[2];
				row[0]=masterRec.getYear();
				row[1]=masterRec.getYear();
				alVersionList.add(row);
			}
		} catch (Exception e) {
			Logger.error("MsRgaContentBrowserInfolet", "Error in getContentVersion:"+e, null);
		}
		return alVersionList.toArray(new String[0][0]);
	}

	/**
	 * This method returns the description of the content details selected.
	 * @param strConteneProvider Content Provider Name
	 * @param strContentCategory Content Category Name
	 * @param strVersion Content Version
	 * @param strObjectID Content Id
	 * @return Description of the content selected
	 */
	public String getContentDescription(String strConteneProvider, String strContentCategory,
			String strVersion, String strObjectID){
		Logger.info("MsRgaContentBrowserInfolet", "Inside getContentDescription", null);
		String strContentDescr = "";
		try {
			MongoTemplate mongoTemplate = (MongoTemplate)ContextLoader.
					getCurrentWebApplicationContext().getBean("mongoTemplate");
			String strMasterColName = CFRAnnualUtil.getMasterCollectionName(strConteneProvider, strContentCategory, mongoTemplate);
			Logger.debug("MsRgaContentBrowserInfolet", "Master Collection Name"+strMasterColName, null);

			Query query = new Query();
			query.addCriteria(Criteria.where("version").is(strVersion).
					andOperator(Criteria.where("objectName").is(strObjectID)));
			Logger.debug("MsRgaContentBrowserInfolet", "Query:"+query.toString(), null);

			CFRAnnualContent annualData = mongoTemplate.findOne(query, 
					CFRAnnualContent.class, strMasterColName);
			if(annualData != null){
				strContentDescr = annualData.getDescription();
			}
		} catch (Exception e) {
			strContentDescr = e.getMessage();
			Logger.error("MsRgaContentBrowserInfolet", "Error in getContentDescription:"+e, null);
		}
		return strContentDescr;
	}

	/**
	 * This method is called to operationalize the content.
	 * @param pn_pid the pn_pid
	 * @param pc_flow_code the pc_flow_code
	 * @param pc_metric_name the pc_metric_name
	 * @return Success/Failure Message
	 */
	public void opeationalizeContent(int pn_pid, String pc_flow_code, String pc_metric_name){
		Logger.info("MsRgaContentBrowserInfolet", "Inside opeationalizeContent", null);
		try {
			 IDBHelper dbInstance = DBInstance.getInstance().getDbInstance();
			Logger.info("MsRgaContentBrowserInfolet", "Call getContentBrowserDetails", null);
			GRCFObjectDetails grcfObjDtls = dbInstance.getContentBrowserDetails(Integer.toString(pn_pid));

			Logger.info("MsRgaContentBrowserInfolet", "Call retrive", null);
			GRCFCFRObjectManager grcfCfrObjMgr = new GRCFCFRObjectManager();
			GRCFContentDetails grcfContentsDtls = grcfCfrObjMgr.retrive(grcfObjDtls);

			if(grcfContentsDtls != null) {
				Logger.info("MsRgaContentBrowserInfolet", "Call transform", null);
				grcfCfrObjMgr.generateObject(grcfContentsDtls);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		Logger.info("MsRgaContentBrowserInfolet", "Exiting the opeationalizeContent methid", null);
	}
}
