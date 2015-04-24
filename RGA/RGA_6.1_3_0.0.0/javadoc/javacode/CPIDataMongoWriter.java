package com.metricstream.systemi.rga.cpi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.context.ContextLoader;

import com.msi.grcintelligence.cpi.data.CPICountryDetails;
import com.msi.grcintelligence.cpi.data.CPIData;

/**
 * This class is used to add the CPI Data to mongo
 * 
 * @author martin.simon
 *
 */
public class CPIDataMongoWriter {
	private static final Logger logger = LoggerFactory.getLogger(CPIDataMongoWriter.class);
	private static CPIDataMongoWriter cpiDataMongoWriter;

	private CPIDataMongoWriter() {
	}

	/**
	 * This method is the single point of access to this class
	 * 
	 * @return
	 */
	public static CPIDataMongoWriter getInstance() {
		if (cpiDataMongoWriter == null) {
			synchronized (CPIDataMongoWriter.class) {
				if (cpiDataMongoWriter == null) {
					cpiDataMongoWriter = new CPIDataMongoWriter();
				}
			}
		}
		return cpiDataMongoWriter;
	}

	/**
	 * This method is used to write the CPI data to mongo
	 * 
	 * @param cpiData
	 * @param collectionName
	 */
	public void writeCPIDataToMongo(CPIData cpiData, String collectionName) {
		// Getting the mongo template
		MongoTemplate mongoTemplate = (MongoTemplate) ContextLoader.getCurrentWebApplicationContext().getBean("mongoTemplate");
		for (CPICountryDetails countryDetails : cpiData.getCpiData()) {
			logger.info("Writing CPI data for country " + countryDetails.getCountry() + " ,year " + countryDetails.getYear());
			mongoTemplate.insert(countryDetails);
			logger.info("CPI data successfully for country " + countryDetails.getCountry() + " ,year " + countryDetails.getYear());
		}
	}
}
