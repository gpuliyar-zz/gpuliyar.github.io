package com.metricstream.systemi.rga.cpi;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.context.ContextLoader;

import com.msi.grcintelligence.cpi.data.CPICountryDetails;
import com.msi.grcintelligence.cpi.data.CPIData;
import com.msi.grcintelligence.util.GRCIConstants;

/**
 * This class is used to read data from the content store and construct the CPI data object
 * 
 * @author martin.simon
 *
 */
public class CPIContentMongoReader {
	private static CPIContentMongoReader cpiContentMongoReader;
	private static final Logger logger = LoggerFactory.getLogger(CPIContentMongoReader.class);

	private CPIContentMongoReader() {
	}

	/**
	 * This method is the single point of access to this class
	 * 
	 * @return
	 */
	public static CPIContentMongoReader getInstance() {
		if (cpiContentMongoReader == null) {
			synchronized (CPIContentMongoReader.class) {
				if (cpiContentMongoReader == null) {
					cpiContentMongoReader = new CPIContentMongoReader();
				}
			}
		}
		return cpiContentMongoReader;
	}

	/**
	 * This method reads the CPI content from mongo. It constructs an instance of CPIData object with data whose aggregation date is greater than the last delivered date
	 * 
	 * @param year
	 * @param collectionName
	 * @return
	 */
	public CPIData readCPIContentFromMongo(int year, String collectionName) {
		CPIData cpiData = new CPIData();
		// Query the mongo store amd constructing the CPIData instance
		logger.info("Querying the CPIData for aggregated year greater than " + year);
		MongoTemplate template = (MongoTemplate) ContextLoader.getCurrentWebApplicationContext().getBean("mongoTemplate");
		List<CPICountryDetails> countryDetailsList = template.find(Query.query(Criteria.where(GRCIConstants.CPI_YEAR_DOCUMENT_COLUMN).is(year)), CPICountryDetails.class);
		for (CPICountryDetails countryDetails : countryDetailsList) {
			if (countryDetails != null) {
				cpiData.addCountryInfo(countryDetails);
			}
		}
		logger.info("Completed the construction of the CPIData object instance");
		return cpiData;
	}
}
