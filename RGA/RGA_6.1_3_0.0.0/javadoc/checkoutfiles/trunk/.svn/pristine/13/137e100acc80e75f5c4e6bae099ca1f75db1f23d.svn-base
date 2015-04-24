package com.metricstream.systemi.rga.grcobject.cfr;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.xml.bind.JAXBException;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.context.ContextLoader;

import com.metricstream.appstudio.cif.Message;
import com.metricstream.appstudio.cif.Response;
import com.metricstream.appstudio.cif.exceptions.CifException;
import com.metricstream.log.Logger;
import com.metricstream.systemi.constant.Property;
import com.metricstream.systemi.rga.grcobject.GRCFContentDetails;
import com.metricstream.systemi.rga.grcobject.GRCFMandatoryFields;
import com.metricstream.systemi.rga.grcobject.GRCFObjectDetails;
import com.metricstream.systemi.rga.grcobject.GRCFObjectManager;
import com.metricstream.systemi.rga.grcobject.jaxb.requirement.DataLinesType;
import com.metricstream.systemi.rga.grcobject.jaxb.requirement.Datapacket;
import com.metricstream.systemi.rga.grcobject.jaxb.requirement.ListOfValues;
import com.metricstream.systemi.rga.grcobject.jaxb.requirement.RQMTRelatedType;
import com.metricstream.systemi.rga.grcobject.jaxb.requirement.RQTType;
import com.metricstream.systemi.rga.grcobject.jaxb.requirement.RelationshipType;
import com.metricstream.systemi.server.common.Controller;
import com.msi.grcintelligence.utility.mongodb.CFRAnnualContent;
import com.msi.grcintelligence.utility.mongodb.CFRAnnualUtil;
import com.msi.grcintelligence.utility.mongodb.ContentWrapperObj;
import com.msi.grcintelligence.utility.xml.JAXBObjectToXML;

/**
 * GRCFCFRObjectManager implements GRCFObjectManager and handles the creation of GRCF Objects
 * for CFR Content.
 * @author munavar.basha
 *
 */
public class GRCFCFRObjectManager implements GRCFObjectManager {
	
	private MongoTemplate mongoTemplate;
	private String strMasterColName;
	
	/**
	 * This method retrieves the details from client mongo
	 */
	@Override
	public GRCFContentDetails retrive(GRCFObjectDetails grcfObj){
		Logger.info("GRCFCFRObjectManager", "Inside retrive", null);
		// TODO get the details from the mongo db -GRCFContentDetails-
		// this will be called by infolet
		GRCFContentDetails grcfContent = null;
		grcfContent = new GRCFContentDetails();
		grcfContent.setAocDetailsMandatory(grcfObj.getAocDetails());
		grcfContent.setReqDetailsMandatory(grcfObj.getReqDetails());

		this.mongoTemplate = (MongoTemplate)ContextLoader.
				getCurrentWebApplicationContext().getBean("mongoTemplate");

		this.strMasterColName = CFRAnnualUtil.getMasterCollectionName(grcfObj.getContentProviderName(),
				grcfObj.getContentCategoryName(), mongoTemplate);
		Logger.debug("GRCFCFRObjectManager", "Master Collection Name:"+strMasterColName, null);

		Set<CFRAnnualContent> annualContentSet = new HashSet<CFRAnnualContent>();
		Logger.debug("GRCFCFRObjectManager", "ContentWrapperObj List size:"+grcfObj.getSelectedObj(), null);
		for(ContentWrapperObj obj : grcfObj.getSelectedObj()){
			String strObjetName = obj.getText();
			if(strObjetName != null){
				updateSet(annualContentSet, strObjetName, grcfObj.getContentVersion());
			}
		}

		Query query = new Query();
		query.addCriteria(Criteria.where("grcfobjectName").is("Area of Compliance"));
		Logger.debug("GRCFCFRObjectManager", "query:"+query, null);

		CFRAnnualContent annualData = this.mongoTemplate.findOne(query, CFRAnnualContent.class, strMasterColName);
		annualContentSet.add(annualData);

		List<CFRAnnualContent> lAnnualContent = new ArrayList<CFRAnnualContent>(annualContentSet);
		Logger.info("GRCFCFRObjectManager", "Annual Content List size:"+lAnnualContent.size(), null);
		grcfContent.setGrcfObjectDetails(lAnnualContent);

		return grcfContent;
	}
	
	/**
	 * This is the recursive method to update the content set with annual data
	 * @param annualContentSet 
	 * @param strName Object Name
	 * @param strVersion Content Version
	 */
	private void updateSet(Set<CFRAnnualContent> annualContentSet, String strName, String strVersion){
		Logger.info("GRCFCFRObjectManager", "Inside updateSet", null);
		Query query = new Query();
		query.addCriteria(Criteria.where("version").is(strVersion).
				andOperator(Criteria.where("objectName").is(strName)));
		Logger.debug("GRCFCFRObjectManager", "query:"+query, null);

		CFRAnnualContent annualData = this.mongoTemplate.findOne(query, CFRAnnualContent.class, strMasterColName);
		annualContentSet.add(annualData);
		
		String strObjetName = annualData.getObjectParent();
		if(strObjetName != null){
			updateSet(annualContentSet, strObjetName, strVersion);
		}
	}

	/**
	 * This method returns the success/fail message for GRCF object 
	 */
	@Override
	public void generateObject(GRCFContentDetails grcfDetails) {
		Logger.info("GRCFCFRObjectManager", "Inside generateObject", null);
		Datapacket reqDataPacket = null;
		com.metricstream.systemi.rga.grcobject.jaxb.aoc.Datapacket aocDataPacket = null;
		try {
			reqDataPacket = generateReqObject(grcfDetails);
			callCIFApi(reqDataPacket);
			
			aocDataPacket = generateAocObject(grcfDetails);
			callCIFApi(aocDataPacket);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * This methods call the CIF API for GRCF object creationR
	 * @param objType AOC/REQ object
	 * @throws CifException
	 */
	private void callCIFApi(Object objType) throws CifException {
		Response response = new Message.Builder()
			.toEndPoint("ms.grcf.createupdate.object")
			.setBody(objType)
			.setSuccessCallBackClass(SuccessCallBackImpl.class)
			.setErrorCallBackClass(ErrorCallBackImpl.class)
			.sendNow();
		Logger.info("GRCFCFRObjectManager", "Called the GRCF API for object creation "+response.getStatus(), null);
	}

	/**
	 * This method generates the CIF input xml for requirement 
	 * @param annualContentList CFR Annual Content List
	 * @param manFields mandatory fields
	 * @return Data packet object
	 */
	private Datapacket generateReqObject(GRCFContentDetails grcfDetails) {
		Logger.info("GRCFCFRObjectManager", "Inside grcfObjectxml", null);
		Datapacket dataPacket = null;
		try {
			List<CFRAnnualContent> annualContentList = grcfDetails.getGrcfObjectDetails();
			GRCFMandatoryFields reqManFields = grcfDetails.getReqDetailsMandatory();
			
			com.metricstream.systemi.rga.grcobject.jaxb.requirement.MessageHeaderType messageHeaderType = 
					new com.metricstream.systemi.rga.grcobject.jaxb.requirement.MessageHeaderType();
			messageHeaderType.setOperation("CREATE");
			messageHeaderType.setObjectType("MS_GRC_REQUIREMENT");
			messageHeaderType.setPurpose("MS_GRC_REQUIREMENT PUBLISH");

			DataLinesType dataLinesType = new DataLinesType();
			Logger.info("GRCFCFRObjectManager", "annualContentList size: "+annualContentList.size(), null);
			
			for(CFRAnnualContent annualContent : annualContentList){
				if("REQUIREMENT".equalsIgnoreCase(annualContent.getGrcfobjectName())) { //TODO need to replace with constant.
					RQTType reqObj = new RQTType();
					reqObj.setObjectLevel(annualContent.getObjectLevel());
					reqObj.setKeyRequirement("yes");
					reqObj.setFrequency(annualContent.getFrequency());
					reqObj.setSectionCitation(annualContent.getSectionNo());
					reqObj.getCategories().setCategoriesType("3");
					reqObj.setTypeOfCompliance("ISO");
					reqObj.setName(annualContent.getObjectName());
					reqObj.setDescription(annualContent.getDescription());
					
					ListOfValues ns = new ListOfValues();
					ns.getValue().addAll(reqManFields.getOwnerOrgName());
					reqObj.setOwnerOrganizations(ns);
					
					ListOfValues ul = new ListOfValues();
					ul.getValue().addAll(reqManFields.getOwnerName());
					reqObj.setOwners(ul);
					
					reqObj.setRestrictAccessTo(reqManFields.getRestricted());
					reqObj.setAction("S");

					RQMTRelatedType relType = new RQMTRelatedType();
					relType.setObjectType(annualContent.getTgtObjType());
					relType.setObjectName(annualContent.getTgtObjName());

					RelationshipType relshp = new RelationshipType();
					relshp.getRelatedObjects().add(relType);
					
					dataLinesType.getRequirement().add(reqObj);
				}
			}
			
			dataPacket = new Datapacket();
			dataPacket.setMessageHeader(messageHeaderType);
			dataPacket.setDataLines(dataLinesType);

			createXML(dataPacket, dataLinesType.getRequirement().size(), "REQ");
			
		} catch (Exception e) {
			e.printStackTrace();
			Logger.error("GRCFCFRObjectManager", e.getMessage(), null);
		}
		return dataPacket;
	}
	
	/**
	 * This method generates the CIF input xml for AOC
	 * @param annualContentList CFR Annual Content List
	 * @param manFields mandatory fields
	 * @return Data packet object
	 */
	private com.metricstream.systemi.rga.grcobject.jaxb.aoc.Datapacket generateAocObject(
			GRCFContentDetails grcfDetails) {
		Logger.info("GRCFCFRObjectManager", "Inside grcfObjectxml", null);
		com.metricstream.systemi.rga.grcobject.jaxb.aoc.Datapacket dataPacket = null;
		try {
			List<CFRAnnualContent> annualContentList = grcfDetails.getGrcfObjectDetails();
			GRCFMandatoryFields aocManFields = grcfDetails.getAocDetailsMandatory();
			
			com.metricstream.systemi.rga.grcobject.jaxb.aoc.MessageHeaderType messageHeaderType = 
					new com.metricstream.systemi.rga.grcobject.jaxb.aoc.MessageHeaderType();
			messageHeaderType.setOperation("CREATE");
			messageHeaderType.setObjectType("MS_GRC_AREA_OF_COMPLIANCE");
			messageHeaderType.setPurpose("MS_GRC_AREA_OF_COMPLIANCE PUBLISH");

			com.metricstream.systemi.rga.grcobject.jaxb.aoc.DataLinesType dataLinesType = 
					new com.metricstream.systemi.rga.grcobject.jaxb.aoc.DataLinesType();
			Logger.info("GRCFCFRObjectManager", "annualContentList size: "+annualContentList.size(), null);
			
			for(CFRAnnualContent annualContent : annualContentList){
				if("Area of Compliance".equalsIgnoreCase(annualContent.getGrcfobjectName())) { //TODO need to replace with constant.
					com.metricstream.systemi.rga.grcobject.jaxb.aoc.AOCType reqObj = 
							new com.metricstream.systemi.rga.grcobject.jaxb.aoc.AOCType();
					reqObj.setName(annualContent.getObjectName());
					reqObj.setDescription(annualContent.getDescription());
					
					com.metricstream.systemi.rga.grcobject.jaxb.aoc.ListOfValues lovClass = 
							new com.metricstream.systemi.rga.grcobject.jaxb.aoc.ListOfValues();
					lovClass.getValue().addAll(aocManFields.getOwnerOrgName());
					reqObj.setOwnerOrganizations(lovClass);
					
					lovClass = new com.metricstream.systemi.rga.grcobject.jaxb.aoc.ListOfValues();
					lovClass.getValue().addAll(aocManFields.getOwnerName());
					reqObj.setOwners(lovClass);
					
					reqObj.setRestrictAccessTo(aocManFields.getRestricted());
					reqObj.setAction("S");

					com.metricstream.systemi.rga.grcobject.jaxb.aoc.AOCRelatedType relType = 
							new com.metricstream.systemi.rga.grcobject.jaxb.aoc.AOCRelatedType();
					relType.setObjectType(annualContent.getTgtObjType());
					relType.setObjectName(annualContent.getTgtObjName());

					com.metricstream.systemi.rga.grcobject.jaxb.aoc.RelationshipType relshp = 
							new com.metricstream.systemi.rga.grcobject.jaxb.aoc.RelationshipType();
					relshp.getRelatedObjects().add(relType);
					
					reqObj.setRelationships(relshp);
					dataLinesType.getAreaOfCompliance().add(reqObj);
				}
			}
			
			dataPacket = new com.metricstream.systemi.rga.grcobject.jaxb.aoc.Datapacket();
			dataPacket.setMessageHeader(messageHeaderType);
			dataPacket.setDataLines(dataLinesType);
			
			createXML(dataPacket, dataLinesType.getAreaOfCompliance().size(), "AOC");
			Logger.info("GRCFCFRObjectManager", "XML generated at the specified location", null);
		} catch (Exception e) {
			e.printStackTrace();
			Logger.error("GRCFCFRObjectManager", e.getMessage(), null);
		}
		return dataPacket;
	}

	/**
	 * This method creates the xml in the attachment folder
	 * @param dataPacket GRCF packet object
	 * @param iSize size of the packet
	 * @throws JAXBException parsing exception
	 */
	private void createXML(Object dataPacket, int iSize, String strType) throws JAXBException {
		Logger.info("GRCFCFRObjectManager", "Inside createXML method", null);
		if(iSize>0){
			String filePath = Controller.getProperties().getProperty(Property.CUSTOM_TEMPLATE_ROOT,"");
			filePath = filePath.replaceAll("/custom_templates", "/Systemi/attachments/") + "Content_Browser_AOC.xml";
			Logger.debug("GRCFCFRObjectManager", "File Path:"+filePath, null);
			
			File file = new File(filePath);
			if(file.exists()){
				file.delete();
			}
			
			JAXBObjectToXML objectToXML = null;
			if("AOC".equalsIgnoreCase(strType)){
				ClassLoader classLoader = com.metricstream.systemi.rga.grcobject.jaxb.aoc.Datapacket.class.getClassLoader();
				objectToXML = new JAXBObjectToXML("com.metricstream.systemi.rga.grcobject.jaxb.aoc", classLoader);
			} else {
				ClassLoader classLoader = com.metricstream.systemi.rga.grcobject.jaxb.requirement.Datapacket.class.getClassLoader();
				objectToXML = new JAXBObjectToXML("com.metricstream.systemi.rga.grcobject.jaxb.requirement", classLoader);
			}
			
			objectToXML.generateXML(file, dataPacket);
		}
	}
}