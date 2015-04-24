package com.metricstream.systemi.utils;


import java.io.IOException;
import java.net.HttpURLConnection;



import org.apache.commons.codec.binary.Base64;
import org.springframework.http.client.SimpleClientHttpRequestFactory;


/**
 * The class UsernamePasswordAuthClientHttpRequestFactory, implementation of SimpleClientHttpRequestFactory to pass User
 * Name and Password authentication details.
 * 
 * 
 */
public class UsernamePasswordAuthClientHttpRequestFactory extends SimpleClientHttpRequestFactory {

    /** The username. */
    private final transient String username;

    /** The password. */
    private final transient String password;

    /** The x requested with. */
    private final String xRequestedWith;

    /**
     * Public constructor to set the username, password and x_requested_with details to make the REST call.
     * 
     * @param username
     *            the username to set
     * @param password
     *            the password to set
     * @param xRequestedWith
     *            the x requested with to set
     */
    public UsernamePasswordAuthClientHttpRequestFactory(String username, String password, String xRequestedWith) {
        assert username != null;
        assert password != null;
       assert xRequestedWith != null;

        this.username = username;
        this.password = password;
       this.xRequestedWith = xRequestedWith;
    }

    /**
     * {@inheritDoc}
     * 
     * Overriding the prepareConnection to set the username, password and x_requested_with details
     */
    @Override
    protected void prepareConnection(HttpURLConnection connection, String httpMethod) throws IOException {
        super.prepareConnection(connection, httpMethod);

        
        String authorisation = username.trim().concat(":").concat(password.trim());
        byte[] encodedAuthorisation = Base64.encodeBase64(authorisation.getBytes());
        
        System.out.println("authorization :" + authorisation);
        System.out.println("encoded base64 :" +new String(encodedAuthorisation, "UTF-8"));
        
        connection.setRequestProperty("Authorization", "Basic ".concat(new String(encodedAuthorisation, "UTF-8")));
      
        
    }
}

