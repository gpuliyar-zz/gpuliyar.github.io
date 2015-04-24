package com.metricstream.systemi.ext.infolet.mock;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.Date;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Part;
import javax.mail.internet.InternetAddress;

public class MockMessage {

	private MockMultipart mockMulti = new MockMultipart();
	Message message = mock(Message.class);

	public MockMessage() {
		try {

			when(message.getContentType()).thenReturn("text/html");
			when(message.getFrom()).thenReturn(
					new Address[] { new InternetAddress("from@gmail.com") });
			when(message.getRecipients(Message.RecipientType.TO)).thenReturn(
					new Address[] { new InternetAddress("to@gmail.com") });
			when(message.getRecipients(Message.RecipientType.CC)).thenReturn(
					new Address[] { new InternetAddress("cc@gmail.com") });
			when(message.getSubject()).thenReturn("sample subject");
			when(message.getReceivedDate()).thenReturn(new Date());

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Message getMessageWithMultipartContentInlineHtml()
			throws MessagingException, IOException {
		Multipart multiPart = mockMulti.getMultipart(Part.INLINE, "text/html");
		when(message.getContent()).thenReturn(multiPart);
		return message;
	}

	public Message getMessageWithStringContent() throws MessagingException,
			IOException {
		when(message.getContent()).thenReturn("some email body");
		return message;
	}

	public Message getMessageWithMultipartContentAttachmentHtml()
			throws MessagingException, IOException {
		Multipart multiPart = mockMulti.getMultipart(Part.ATTACHMENT,
				"text/html");
		when(message.getContent()).thenReturn(multiPart);
		return message;
	}
}
