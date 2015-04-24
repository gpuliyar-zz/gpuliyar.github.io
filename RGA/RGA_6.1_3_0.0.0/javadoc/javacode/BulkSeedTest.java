package com.metricstream.systemi.rga.integration.pdms;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.sql.SQLException;
import java.util.Date;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class BulkSeedTest {

	BulkSeed seed;

	@BeforeClass
	private void setup() {
		seed = new BulkSeed();
	}

	@Test
	public void loadToPdmsTest() throws IllegalArgumentException, SQLException {
		PDMSDocument document = getMockDocument();
		seed.addToPdms(document);
	}

	@Test(expectedExceptions = { IllegalArgumentException.class })
	public void loadToPdmsTestWithNullArg() throws IllegalArgumentException,
			SQLException {
		seed.addToPdms(null);
	}

	@Test(expectedExceptions = { IllegalArgumentException.class })
	public void loadToPdmsTestWithNoEnterpriseName()
			throws IllegalArgumentException, SQLException {
		PDMSDocument document = getMockDocument();
		when(document.getEnterpriseName()).thenReturn(null);
		seed.addToPdms(document);
	}

	@Test(expectedExceptions = { IllegalArgumentException.class })
	public void loadToPdmsTestWithNoDocumentName()
			throws IllegalArgumentException, SQLException {
		PDMSDocument document = getMockDocument();
		when(document.getDocumentName()).thenReturn(null);
		seed.addToPdms(document);
	}

	@Test(expectedExceptions = { IllegalArgumentException.class })
	public void loadToPdmsTestWithNoLifecycleName()
			throws IllegalArgumentException, SQLException {
		PDMSDocument document = getMockDocument();
		when(document.getLifecycleName()).thenReturn(null);
		seed.addToPdms(document);
	}

	@Test(expectedExceptions = { IllegalArgumentException.class })
	public void loadToPdmsTestWithNoCategory() throws IllegalArgumentException,
			SQLException {
		PDMSDocument document = getMockDocument();
		when(document.getCategory()).thenReturn(null);
		seed.addToPdms(document);
	}

	@Test(expectedExceptions = { IllegalArgumentException.class })
	public void loadToPdmsTestWithNoAuthor() throws IllegalArgumentException,
			SQLException {
		PDMSDocument document = getMockDocument();
		when(document.getAuthor()).thenReturn(null);
		seed.addToPdms(document);
	}

	@Test(expectedExceptions = { IllegalArgumentException.class })
	public void loadToPdmsTestWithNoOwner() throws IllegalArgumentException,
			SQLException {
		PDMSDocument document = getMockDocument();
		when(document.getOwner()).thenReturn(null);
		seed.addToPdms(document);
	}

	@Test(expectedExceptions = { IllegalArgumentException.class })
	public void loadToPdmsTestWithNoPurpose() throws IllegalArgumentException,
			SQLException {
		PDMSDocument document = getMockDocument();
		when(document.getPurpose()).thenReturn(null);
		seed.addToPdms(document);
	}

	@Test(expectedExceptions = { IllegalArgumentException.class })
	public void loadToPdmsTestWithNoDescription()
			throws IllegalArgumentException, SQLException {
		PDMSDocument document = getMockDocument();
		when(document.getDescription()).thenReturn(null);
		seed.addToPdms(document);
	}

	@Test(expectedExceptions = { IllegalArgumentException.class })
	public void loadToPdmsTestWithNoReviewScheduleType()
			throws IllegalArgumentException, SQLException {
		PDMSDocument document = getMockDocument();
		when(document.getReviewScheduleType()).thenReturn(null);
		seed.addToPdms(document);
	}

	@Test(expectedExceptions = { IllegalArgumentException.class })
	public void loadToPdmsTestWithNoReviewScheduleDays()
			throws IllegalArgumentException, SQLException {
		PDMSDocument document = getMockDocument();
		when(document.getReviewScheduleDays()).thenReturn(-1);
		seed.addToPdms(document);
	}

	@Test(expectedExceptions = { IllegalArgumentException.class })
	public void loadToPdmsTestWithNoUserName() throws IllegalArgumentException,
			SQLException {
		PDMSDocument document = getMockDocument();
		when(document.getUserName()).thenReturn(null);
		seed.addToPdms(document);
	}

	@Test(expectedExceptions = { IllegalArgumentException.class })
	public void loadToPdmsTestWithNoAttachmentFileName()
			throws IllegalArgumentException, SQLException {
		PDMSDocument document = getMockDocument();
		when(document.getAttachmentFileName()).thenReturn(null);
		seed.addToPdms(document);
	}

	@Test(expectedExceptions = { IllegalArgumentException.class })
	public void loadToPdmsTestWithNoAttachmentPath()
			throws IllegalArgumentException, SQLException {
		PDMSDocument document = getMockDocument();
		when(document.getAttachmentFilePath()).thenReturn(null);
		seed.addToPdms(document);
	}

	@Test(expectedExceptions = { IllegalArgumentException.class })
	public void loadToPdmsTestWithNoObseleteSeq()
			throws IllegalArgumentException, SQLException {
		PDMSDocument document = getMockDocument();
		when(document.getObseleteSeq()).thenReturn(null);
		seed.addToPdms(document);
	}

	@Test(expectedExceptions = { IllegalArgumentException.class })
	public void loadToPdmsTestWithNoNumberOfUpversions()
			throws IllegalArgumentException, SQLException {
		PDMSDocument document = getMockDocument();
		when(document.getNumberOfUpversions()).thenReturn(-1);
		seed.addToPdms(document);
	}

	@Test(expectedExceptions = { IllegalArgumentException.class })
	public void loadToPdmsTestWithNoSeededFlag()
			throws IllegalArgumentException, SQLException {
		PDMSDocument document = getMockDocument();
		when(document.getSeededFlag()).thenReturn(null);
		seed.addToPdms(document);
	}

	@Test(expectedExceptions = { IllegalArgumentException.class })
	public void loadToPdmsTestWithNoProcessType()
			throws IllegalArgumentException, SQLException {
		PDMSDocument document = getMockDocument();
		when(document.getProcessType()).thenReturn(null);
		seed.addToPdms(document);
	}

	/**
	 * @return
	 */
	private PDMSDocument getMockDocument() {
		PDMSDocument document = mock(PDMSDocument.class);
		when(document.getAttachmentFileName()).thenReturn("fileName");
		when(document.getAttachmentFilePath()).thenReturn("path");
		when(document.getAuthor()).thenReturn("author");
		when(document.getCategory()).thenReturn("category");
		when(document.getDescription()).thenReturn("desc");
		when(document.getDocumentName()).thenReturn("docname");
		when(document.getEffectiveDate()).thenReturn(new Date());
		when(document.getEnterpriseName()).thenReturn("enterprisename");
		when(document.getKeywords()).thenReturn("keyword");
		when(document.getLifecycleName()).thenReturn("lifecycle");
		when(document.getNumberOfUpversions()).thenReturn(1);
		when(document.getObseleteSeq()).thenReturn("obs");
		when(document.getOwner()).thenReturn("owner");
		when(document.getProcessType()).thenReturn("create");
		when(document.getPurpose()).thenReturn("purpose");
		when(document.getReviewScheduleDays()).thenReturn(2);
		when(document.getReviewScheduleType()).thenReturn("schtype");
		when(document.getSeededFlag()).thenReturn("N");
		when(document.getSubCategory1()).thenReturn("cat1");
		when(document.getSubCategory2()).thenReturn("cat2");
		when(document.getSubCategory3()).thenReturn("cat3");
		when(document.getSubCategory4()).thenReturn("cat4");
		when(document.getUserName()).thenReturn("uname");
		return document;
	}
}
