package com.metricstream.systemi.ext.infolet.mock;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;

import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Part;

public class MockBodyPart {

	public Part getPart() throws MessagingException, IOException {
		Part part = mock(Part.class);
		when(part.getDisposition()).thenReturn(Part.INLINE, Part.ATTACHMENT);
		when(part.getContent()).thenReturn(mock(Multipart.class), "");
		return part;
	}

}
