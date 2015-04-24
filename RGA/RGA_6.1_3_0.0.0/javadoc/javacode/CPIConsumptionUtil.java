package com.metricstream.systemi.rga.cpi;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.context.ContextLoader;

import com.metricstream.log.Logger;
import com.msi.grcintelligence.cpi.data.CPIData;
import com.msi.grcintelligence.cpi.data.CPIDistributionDetails;
import com.msi.grcintelligence.util.GRCIConstants;
import com.msi.grcintelligence.utility.mongodb.MasterCollectionContent;

/**
 * This class contains the logic for consuming CPI data
 * 
 * @author martin.simon
 *
 */
public class CPIConsumptionUtil {
	private static CPIConsumptionUtil cpiConsumptionUtil;

	private CPIConsumptionUtil() {
	}

	/**
	 * This method is the single point of access to this class
	 * 
	 * @return
	 */
	public static CPIConsumptionUtil getInstance() {
		if (cpiConsumptionUtil == null) {
			synchronized (CPIConsumptionUtil.class) {
				if (cpiConsumptionUtil == null) {
					cpiConsumptionUtil = new CPIConsumptionUtil();
				}
			}
		}
		return cpiConsumptionUtil;
	}

	/**
	 * This method has the consumption logic for CPI data. The following steps are executed 
	 * a) Write the CPI data to client mongo
	 * b) Insert entry in master collection
	 * 
	 * @param distributionDetails
	 */
	public void consume(CPIDistributionDetails distributionDetails) {
		// Writing the cpi data to client mongo
		Logger.info("CPIConsumptionUtil", "Writing the cpi data to client mongo", null);
		CPIData cpiData = distributionDetails.getCpiData();
		CPIDataMongoWriter.getInstance().writeCPIDataToMongo(cpiData, GRCIConstants.CPI_CONTENT_CLIENT_COLLECTION_NAME);
		// Inserting master record
		Logger.info("CPIConsumptionUtil", "Inserting master record", null);
		insertMasterRecord(distributionDetails);
	}

	/**
	 * This method adds an entry in the master collection
	 * 
	 * @param distributionDetails
	 * @param colName
	 */
	public void insertMasterRecord(CPIDistributionDetails distributionDetails) {
		Logger.info("CPIConsumptionUtil", "Inserting CPI Consumption info to master collection", null);
		try {
			MongoTemplate mongoTemplate = (MongoTemplate) ContextLoader.getCurrentWebApplicationContext().getBean("mongoTemplate");
			String contentProvider = distributionDetails.getContentProvider();
			String contentCategory = distributionDetails.getContentCategory();
			String year = String.valueOf(distributionDetails.getYear());
			MasterCollectionContent masterObj = new MasterCollectionContent();
			masterObj.setContentProviderName(contentProvider);
			masterObj.setContentType(distributionDetails.getContentType());
			masterObj.setCollectionName(GRCIConstants.CPI_CONTENT_CLIENT_COLLECTION_NAME);
			masterObj.setContentCategory(contentCategory);
			masterObj.setDate(new SimpleDateFormat(GRCIConstants.YYYY_MM_DD).format(new Date()));
			masterObj.setYear(year);
			masterObj.setContentUniqueKey(contentProvider + "_" + contentCategory + "_" + year);
			mongoTemplate.save(masterObj);
		} catch (Exception e) {
			Logger.error("CPIConsumptionUtil", e.getMessage(), e);
		}
		Logger.info("CPIConsumptionUtil", "Inserted CPI Consumption info to master collection", null);
	}
}
