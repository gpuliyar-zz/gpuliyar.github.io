package com.metricstream.systemi.rga.integration.pdms;

import java.sql.SQLException;

import com.metricstream.log.Logger;
import com.metricstream.systemi.ext.infolet.Fileseed;

/**
 * The Class BulkSeed.
 *
 * @author rajesh.mohanan
 * 
 *         BulkSeed class takes care of automating the manual bulkseeding job
 *         for PDMS. This can be used by other modules to integrate with PDMS
 *         
 */
public class BulkSeed {

	// private static final Logger logger = Log.logger;
	/** The Constant CLASS_ID. */
	private static final String CLASS_ID = "BulkSeed";

	/**
	 * Adds the to pdms.
	 *
	 * @param document the document
	 * @throws IllegalArgumentException             Can be used to load a List of documents
	 * 
	 *             Throws IllegalArgumentException if validation of any field
	 *             fails. Please refer to Bulkseeding document from PDMS team on
	 *             the list of arguments and validation
	 * @throws SQLException the SQL exception
	 */
	public void addToPdms(PDMSDocument document)
			throws IllegalArgumentException, SQLException {
		validateDocument(document);
		// logger.error("Document validated");
		// logger.info("adding document " + document);
		long batchNumber = PDMSDao.getInstance().addDocument(document);
		// This is an api defined by PDMS.
		String returnValue = Fileseed.seedFiles_form(batchNumber + "");
		Logger.error(CLASS_ID, "seedFiles_form returned " + returnValue, null);
	}

	/**
	 * Validate document.
	 *
	 * @param document the document
	 * @throws IllegalArgumentException the illegal argument exception
	 */
	private void validateDocument(PDMSDocument document)
			throws IllegalArgumentException {
		if (document == null) {
			throw new IllegalArgumentException("Document is null ");
		}
		if (document.getEnterpriseName() == null
				|| document.getEnterpriseName().isEmpty()) {
			throw new IllegalArgumentException(
					"Enterprise Name is null or empty for " + document);
		}
		if (document.getDocumentName() == null
				|| document.getDocumentName().isEmpty()) {
			throw new IllegalArgumentException(
					"Document Name is null or empty for " + document);
		}
		if (document.getLifecycleName() == null
				|| document.getLifecycleName().isEmpty()) {
			throw new IllegalArgumentException(
					"Lifecycle Name is null or empty for " + document);
		}
		if (document.getCategory() == null || document.getCategory().isEmpty()) {
			throw new IllegalArgumentException("Category is null or empty for "
					+ document);
		}
		if (document.getAuthor() == null || document.getAuthor().isEmpty()) {
			throw new IllegalArgumentException("Author is null or empty for "
					+ document);
		}
		if (document.getOwner() == null || document.getOwner().isEmpty()) {
			throw new IllegalArgumentException("Owner is null or empty for "
					+ document);
		}
		if (document.getPurpose() == null || document.getPurpose().isEmpty()) {
			throw new IllegalArgumentException("Purpose is null or empty for "
					+ document);
		}
		if (document.getDescription() == null
				|| document.getDescription().isEmpty()) {
			throw new IllegalArgumentException(
					"Description is null or empty for " + document);
		}
		if (document.getReviewScheduleType() == null
				|| document.getReviewScheduleType().isEmpty()) {
			throw new IllegalArgumentException(
					"review Schedule Type is null or empty for " + document);
		}
		if (document.getReviewScheduleDays() < 0) {
			throw new IllegalArgumentException(
					"Review Schedule Days is null or empty for " + document);
		}
		if (document.getUserName() == null || document.getUserName().isEmpty()) {
			throw new IllegalArgumentException(
					"User Name is null or empty for " + document);
		}
		if (document.getAttachmentFileName() == null
				|| document.getAttachmentFileName().isEmpty()) {
			throw new IllegalArgumentException(
					"Attachment file name is null or emty for " + document);
		}
		if (document.getAttachmentFilePath() == null
				|| document.getAttachmentFilePath().isEmpty()) {
			throw new IllegalArgumentException(
					"Attachment path is null or emty for " + document);
		}
		if (document.getObseleteSeq() == null
				|| document.getObseleteSeq().isEmpty()) {
			throw new IllegalArgumentException(
					"Obselete Sequence is null or emty for " + document);
		}
		if (document.getNumberOfUpversions() < 0) {
			throw new IllegalArgumentException(
					"Number of upversions is null or emty for " + document);
		}
		if (document.getSeededFlag() == null
				|| document.getSeededFlag().isEmpty()) {
			throw new IllegalArgumentException(
					"Seeded Flag is null or emty for " + document);
		}
		if (document.getProcessType() == null
				|| document.getProcessType().isEmpty()) {
			throw new IllegalArgumentException(
					"Process Type is null or emty for " + document);
		}

	}
}
