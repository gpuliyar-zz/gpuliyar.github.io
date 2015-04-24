package com.metricstream.systemi.rga.sc;

import static org.apache.commons.lang.StringUtils.containsIgnoreCase;

import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.metricstream.log.Logger;
import com.metricstream.systemi.rga.integration.pdms.BulkSeed;
import com.metricstream.systemi.rga.integration.pdms.PDMSDocument;

//com.metricstream.systemi.rga.sc.GSECMailHandler
/**
 * The Class GSECMailHandler.
 */
public class GSECMailHandler {

	/** The Constant CLASS_ID. */
	private static final String CLASS_ID = "GSECMailHandler";
	
	/** The Constant FERC. */
	private static final String FERC = "FERC";
	
	/** The Constant FERC_CORRESPONDENCE. */
	private static final String FERC_CORRESPONDENCE = "FERC correspondence";
	
	/** The Constant LINKED_MAIL. */
	private static final String LINKED_MAIL = "linked emails";
	
	/** The Constant SPREADSHEET_FILE. */
	private static final String SPREADSHEET_FILE = "spreadsheet file";
	
	/** The Constant MUSTANG. */
	private static final String MUSTANG = "Mustang station";
	
	/** The Constant GSEC_DOC_INITIATOR. */
	private static final String GSEC_DOC_INITIATOR = "DMS_Object_Owner";
	
	/** The Constant GSEC_DOC_APPROVER. */
	private static final String GSEC_DOC_APPROVER = "DMS_Object_Owner";
	
	/** The Constant GSEC_DOC_OWNER. */
	private static final String GSEC_DOC_OWNER = "DMS_Object_Owner";
	
	/** The Constant ENTERPRISENAME. */
	private static final String ENTERPRISENAME = "GRCINT_PGSTG";
	
	/** The Constant GSEC_LIFECYCLE. */
	private static final String GSEC_LIFECYCLE = "GSEC Mails";

	/**
	 * Instantiates a new GSEC mail handler.
	 */
	public GSECMailHandler() {
		Logger.error(CLASS_ID, "inside GSECMailHandler constructor", null);
	}

	/**
	 * Process.
	 *
	 * @param server the server
	 * @param sender the sender
	 * @param recipient the recipient
	 * @param subject the subject
	 * @param body the body
	 * @param attachmentId the attachment id
	 * @param saveAttachments the save attachments
	 * @param infoletId the infolet id
	 * @param instanceId the instance id
	 * @param fileName the file name
	 * @return the object
	 */
	public Object process(String server, String sender, String recipient,
			String subject, String body, String attachmentId,
			String saveAttachments, String infoletId, String instanceId,
			String fileName) {
		Logger.error(CLASS_ID, "inside process", null);

		String returnValue = "FAILURE";
		try {
			returnValue = pushToPDMS(fileName, subject, body, attachmentId);
		} catch (IllegalArgumentException e) {
			Logger.error(CLASS_ID, "", e);
		} catch (SQLException e) {
			Logger.error(CLASS_ID, "", e);
		}
		deleteFile(fileName);
		return returnValue;
	}

	/**
	 * Method to Delete a file.
	 *
	 * @param fileName the file name
	 */
	private void deleteFile(String fileName) {
		new File(fileName).delete();
	}

	/**
	 * Method to push to pdms.
	 *
	 * @param fileName the file name
	 * @param subject the subject
	 * @param body the body
	 * @param attachmentId the attachment id
	 * @return the string
	 * @throws IllegalArgumentException the illegal argument exception
	 * @throws SQLException the SQL exception
	 */
	private String pushToPDMS(String fileName, String subject, String body,
			String attachmentId) throws IllegalArgumentException, SQLException {
		Logger.error(CLASS_ID, "Inside", null);
		BulkSeed seed = new BulkSeed();
		List<String> categories = getCategories(subject, body, attachmentId);
		for (String category : categories) {
			Logger.error(CLASS_ID, "category:" + category, null);
			PDMSDocument document = createPDMSDocument(fileName, category,
					subject);
			seed.addToPdms(document);
		}
		return "SUCCESS";
	}

	/**
	 * Method to get the categories.
	 *
	 * @param subject the subject
	 * @param body the body
	 * @param attachmentId the attachment id
	 * @return the categories
	 */
	private List<String> getCategories(String subject, String body,
			String attachmentId) {
		List<String> categories = new ArrayList<String>();
		Logger.error(CLASS_ID, "\nsubject:" + subject, null);
		Logger.error(CLASS_ID, "\nbody:" + body, null);
		Logger.error(CLASS_ID, "\nattachmentId:" + attachmentId, null);
		if (subject.contains(FERC) || body.contains(FERC)) {
			categories.add(FERC_CORRESPONDENCE);
		}
		if (isWebLinkMail(body)) {
			categories.add(LINKED_MAIL);
		}
		if (hasSpreadSheetAttachment(attachmentId)) {
			categories.add(SPREADSHEET_FILE);
		}

		if (mailContains(body)) {
			categories.add(MUSTANG);
		}
		Logger.error(CLASS_ID, "returning categories" + categories.toString(),
				null);
		return categories;
	}

	/**
	 * Method to check mail contains.
	 *
	 * @param body the body
	 * @return true, if successful
	 */
	private boolean mailContains(String body) {
		String[] keywords = new String[] { "mustang", "contract" };
		return textContains(keywords, body);
	}

	/**
	 * Checks for spread sheet attachment.
	 *
	 * @param attachmentId the attachment id
	 * @return true, if successful
	 */
	private boolean hasSpreadSheetAttachment(String attachmentId) {
		// String[] extensions = new String[] { ".xls#ms_attach_delimiter#",
		// ".xlsx#ms_attach_delimiter#" };
		String[] extensions = new String[] { "xls", "xlsx" };
		if (attachmentId != null) {
			attachmentId = attachmentId.replaceAll("#ms_attach_delimiter#", "");
			return textContains(extensions, attachmentId);
		}
		return false;
	}

	/**
	 * Method to check the Text contains.
	 *
	 * @param keywords the keywords
	 * @param body the body
	 * @return true, if successful
	 */
	private boolean textContains(String[] keywords, String body) {
		for (String word : keywords) {
			if (!containsIgnoreCase(body, word)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Checks if is web link mail.
	 *
	 * @param body the body
	 * @return true, if is web link mail
	 */
	private boolean isWebLinkMail(String body) {
		Pattern pattern = Pattern
				.compile(
						"\\b(?:(?:https?|ftp|file)://|www\\.|ftp\\.)[-A-Z0-9+&@#/%=~_|$?!:,.]*[A-Z0-9+&@#/%=~_|$]",
						Pattern.CASE_INSENSITIVE);
		String headEndTag = "</head>";
		if (body.indexOf(headEndTag) > -1) {
			body = body.substring(
					body.indexOf(headEndTag) + headEndTag.length(),
					body.length());
			Logger.error(CLASS_ID, "body is now :" + body, null);

		}
		Matcher matcher = pattern.matcher(body);
		if (matcher.find()) {
			return true;
		}
		return false;
	}

	/**
	 * Method to create the pdms document.
	 *
	 * @param fileName the file name
	 * @param category the category
	 * @param subject the subject
	 * @return the PDMS document
	 */
	private PDMSDocument createPDMSDocument(String fileName, String category,
			String subject) {
		PDMSDocument document = new PDMSDocument();
		String name = fileName.substring(fileName.lastIndexOf('/') + 1,
				fileName.length());
		String filePath = fileName.substring(0, fileName.lastIndexOf('/'));
		document.setAttachmentFileName(name);
		document.setAttachmentFilePath(filePath);
		// hard code
		document.setAuthor(GSEC_DOC_INITIATOR);
		document.setCategory(category);
		document.setDescription(subject);
		document.setDocumentName(subject + " (" + category + ")");
		document.setEnterpriseName(ENTERPRISENAME);
		// subject or any searchable words
		document.setKeywords(subject);
		// use default
		document.setLifecycleName(GSEC_LIFECYCLE);
		// 1
		document.setNumberOfUpversions(1);
		// to check
		document.setObseleteSeq("N");
		// hard code
		document.setOwner(GSEC_DOC_OWNER);
		// “Create”
		document.setProcessType("Create");
		// what purpose?
		document.setPurpose("Auto saved GSEC Mails");
		// 0
		document.setReviewScheduleDays(365);
		// to check
		document.setReviewScheduleType("Days");
		// N
		document.setSeededFlag("N");
		// hard code
		document.setUserName(GSEC_DOC_OWNER);
		return document;
	}

	
}
