package com.metricstream.systemi.ext.infolet;

import java.io.IOException;
import java.util.Date;

import javax.mail.Message;
import javax.mail.MessagingException;

import org.testng.annotations.Test;

import com.metricstream.systemi.ext.infolet.mock.MockMessage;

public class EmailMessageDataTest {

	private MockMessage mockMessage = new MockMessage();

	@Test
	public void loadMultipartMessageInlineHtml() throws IOException,
			MessagingException {
		Message msg = mockMessage.getMessageWithMultipartContentInlineHtml();
		EmailMessageData data = new EmailMessageData("server", "userId", msg,
				"uid", "100000", "yes", "chnid", new Date());
	}

	@Test()
	public void loadStringMessage() throws IOException, MessagingException {
		Message msg = mockMessage.getMessageWithStringContent();
		new EmailMessageData("server", "userId", msg, "uid", "100000", "yes",
				"chnid", new Date());
	}

	@Test
	public void loadMultipartContentAttachmentHtml() throws IOException,
			MessagingException {
		Message msg = mockMessage
				.getMessageWithMultipartContentAttachmentHtml();
		EmailMessageData.tempDir = "D:\\workspace\\branch_6-0_SP1\\log";
		new EmailMessageData("server", "userId", msg, "uid", "100000", "yes",
				"chnid", new Date());
	}

}
