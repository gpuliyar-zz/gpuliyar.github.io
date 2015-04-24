package com.metricstream.systemi.utils.ssl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.UnknownHostException;
import java.security.GeneralSecurityException;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

import com.metricstream.log.Logger;

/**
 * The Class ReloadableX509TrustManager.
 *
 * 
 */
public class ReloadableX509TrustManager implements X509TrustManager {
	
	/** The trust store path. */
	private final String trustStorePath;
	
	/** The trust manager. */
	private X509TrustManager trustManager;
	
	/** The temp cert list. */
	private final List<Certificate> tempCertList = new ArrayList<Certificate>();
	
	/** The server. */
	private final String server;
	
	/** The Constant CLASS_ID. */
	private static final String CLASS_ID = "ReloadableX509TrustManager";

	/**
	 * Instantiates a new reloadable x509 trust manager.
	 *
	 * @param tspath the tspath
	 * @param server the server
	 * @throws GeneralSecurityException the general security exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public ReloadableX509TrustManager(String tspath, String server)
			throws GeneralSecurityException, IOException {
		this.trustStorePath = tspath;
		this.server = server;
		Logger.debug(CLASS_ID, "Inside with tspath =  " + tspath
				+ " and server = " + server, null);
		reloadTrustManager();
	}

	/* (non-Javadoc)
	 * @see javax.net.ssl.X509TrustManager#checkClientTrusted(java.security.cert.X509Certificate[], java.lang.String)
	 */
	@Override
	public void checkClientTrusted(X509Certificate[] chain, String authType)
			throws CertificateException {
		trustManager.checkClientTrusted(chain, authType);
	}

	/* (non-Javadoc)
	 * @see javax.net.ssl.X509TrustManager#checkServerTrusted(java.security.cert.X509Certificate[], java.lang.String)
	 */
	@Override
	public void checkServerTrusted(X509Certificate[] chain, String authType)
			throws CertificateException {
		try {
			trustManager.checkServerTrusted(chain, authType);
			Logger.debug(CLASS_ID, "Trusted Server!", null);
		} catch (CertificateException cx) {
			addServerCertAndReload(chain[0], true);
			trustManager.checkServerTrusted(chain, authType);
		}
	}

	/* (non-Javadoc)
	 * @see javax.net.ssl.X509TrustManager#getAcceptedIssuers()
	 */
	@Override
	public X509Certificate[] getAcceptedIssuers() {
		X509Certificate[] issuers = trustManager.getAcceptedIssuers();
		return issuers;
	}

	/**
	 * Reload trust manager.
	 *
	 * @throws GeneralSecurityException the general security exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	private void reloadTrustManager() throws GeneralSecurityException,
			IOException {

		// load keystore from specified cert store (or default)
		KeyStore ts = KeyStore.getInstance(KeyStore.getDefaultType());
		InputStream in = new FileInputStream(trustStorePath);
		try {
			ts.load(in, null);
		} finally {
			in.close();
		}

		// add all temporary certs to KeyStore (ts)
		for (Certificate cert : tempCertList) {
			ts.setCertificateEntry(UUID.randomUUID().toString(), cert);
		}

		// initialize a new TMF with the ts we just loaded
		TrustManagerFactory tmf = TrustManagerFactory
				.getInstance(TrustManagerFactory.getDefaultAlgorithm());
		tmf.init(ts);

		// acquire X509 trust manager from factory
		TrustManager tms[] = tmf.getTrustManagers();
		for (int i = 0; i < tms.length; i++) {
			if (tms[i] instanceof X509TrustManager) {
				trustManager = (X509TrustManager) tms[i];
				return;
			}
		}

		throw new NoSuchAlgorithmException(
				"No X509TrustManager in TrustManagerFactory");
	}

	/**
	 * Adds the server cert and reload.
	 *
	 * @param cert the cert
	 * @param permanent the permanent
	 */
	@SuppressWarnings("deprecation")
	private void addServerCertAndReload(Certificate cert, boolean permanent) {
		try {
			if (permanent) {
				// import the cert into file trust store
				// Google "java keytool source" or just ...

				// Runtime.getRuntime().exec("keytool -import -trustcacerts -keystore ..\\lib\\security\\cacerts -file D:\\YahooCert.cer");//
				instalSSLCert(server);
			} else {
				tempCertList.add(cert);
			}
			Logger.debug(CLASS_ID,
					"calling reloadTrustManager() after cert installation",
					null);
			reloadTrustManager();
		} catch (GeneralSecurityException e) {
			Logger.error(CLASS_ID, "GeneralSecurityException", e);
		} catch (IOException e) {
			Logger.error(CLASS_ID, "IOException", e);
		}

	}

	/**
	 * Instal ssl cert.
	 *
	 * @param server the server
	 * @throws UnknownHostException the unknown host exception
	 */
	@SuppressWarnings("deprecation")
	protected static void instalSSLCert(String server)
			throws UnknownHostException {
		Logger.debug(CLASS_ID, "inside installCert", null);
		char SEP = File.separatorChar;
		File dir = new File(System.getProperty("java.home") + SEP + "lib" + SEP
				+ "security");
		File file = new File(dir, "cacerts");
		try {
			InputStream in = new FileInputStream(file);

			KeyStore ks = KeyStore.getInstance(KeyStore.getDefaultType());
			ks.load(in, "changeit".toCharArray());
			in.close();

			SSLContext context = SSLContext.getInstance("TLS");
			TrustManagerFactory tmf = TrustManagerFactory
					.getInstance(TrustManagerFactory.getDefaultAlgorithm());
			tmf.init(ks);
			X509TrustManager defaultTrustManager = (X509TrustManager) tmf
					.getTrustManagers()[0];
			SavingTrustManager tm = new SavingTrustManager(defaultTrustManager);
			context.init(null, new TrustManager[] { tm }, null);
			SSLSocketFactory factory = context.getSocketFactory();

			SSLSocket socket = (SSLSocket) factory.createSocket(server, 995);
			socket.setSoTimeout(10000);
			try {
				socket.startHandshake();
				socket.close();
			} catch (SSLException e) {
				Logger.error(CLASS_ID,
						"SSLException while checking SSLHandShanke with server "
								+ server, e);
			}
			X509Certificate[] chain = tm.chain;
			if (chain == null) {
				Logger.warning(CLASS_ID,
						"Could not obtain server certificate chain ", null);
				return;
			}

			X509Certificate cert = chain[0];
			String alias = server + "-" + 1;
			ks.setCertificateEntry(alias, cert);

			OutputStream out = new FileOutputStream(file);
			ks.store(out, "changeit".toCharArray());
			out.close();

		} catch (KeyStoreException e) {
			Logger.error(CLASS_ID,
					"KeyStoreException while checking SSLHandShanke with server "
							+ server, e);
		} catch (NoSuchAlgorithmException e) {
			Logger.error(CLASS_ID,
					"NoSuchAlgorithmException while checking SSLHandShanke with server "
							+ server, e);
		} catch (KeyManagementException e) {
			Logger.error(CLASS_ID,
					"KeyManagementException while checking SSLHandShanke with server "
							+ server, e);
		} catch (CertificateException e) {
			Logger.error(CLASS_ID,
					"CertificateException while checking SSLHandShanke with server "
							+ server, e);
		} catch (IOException e) {
			Logger.error(CLASS_ID,
					"IOException while checking SSLHandShanke with server "
							+ server, e);
		}

	}

	/**
	 * The Class SavingTrustManager.
	 */
	private static class SavingTrustManager implements X509TrustManager {

		/** The tm. */
		private final X509TrustManager tm;
		
		/** The chain. */
		private X509Certificate[] chain;

		/**
		 * Instantiates a new saving trust manager.
		 *
		 * @param tm the tm
		 */
		SavingTrustManager(X509TrustManager tm) {
			this.tm = tm;
		}

		/* (non-Javadoc)
		 * @see javax.net.ssl.X509TrustManager#getAcceptedIssuers()
		 */
		@Override
		public X509Certificate[] getAcceptedIssuers() {
			throw new UnsupportedOperationException();
		}

		/* (non-Javadoc)
		 * @see javax.net.ssl.X509TrustManager#checkClientTrusted(java.security.cert.X509Certificate[], java.lang.String)
		 */
		@Override
		public void checkClientTrusted(X509Certificate[] chain, String authType)
				throws CertificateException {
			throw new UnsupportedOperationException();
		}

		/* (non-Javadoc)
		 * @see javax.net.ssl.X509TrustManager#checkServerTrusted(java.security.cert.X509Certificate[], java.lang.String)
		 */
		@Override
		public void checkServerTrusted(X509Certificate[] chain, String authType)
				throws CertificateException {
			this.chain = chain;
			tm.checkServerTrusted(chain, authType);
		}
	}

}