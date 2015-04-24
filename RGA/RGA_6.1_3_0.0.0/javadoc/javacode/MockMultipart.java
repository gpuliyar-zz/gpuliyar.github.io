package com.metricstream.systemi.ext.infolet.mock;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;

import javax.mail.BodyPart;
import javax.mail.MessagingException;
import javax.mail.Multipart;

public class MockMultipart {

	public Multipart getMultipart(String disposition, Object content)
			throws MessagingException, IOException {
		Multipart multipart = mock(Multipart.class);
		BodyPart part = mock(BodyPart.class);
		when(part.getDisposition()).thenReturn(disposition);
		when(part.getContent()).thenReturn(content);
		when(part.getContentType()).thenReturn("text/html");
		when(multipart.getBodyPart(0)).thenReturn(part);
		when(multipart.getCount()).thenReturn(1);
		return multipart;
	}

}
