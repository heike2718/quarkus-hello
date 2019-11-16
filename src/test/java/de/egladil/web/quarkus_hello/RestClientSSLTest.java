/**
 *
 */
package de.egladil.web.quarkus_hello;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.io.File;
import java.io.FileInputStream;
import java.security.KeyStore;
import java.security.KeyStoreException;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

import org.junit.jupiter.api.Test;

/**
 * RestClientSSLTest <br>
 * Vorbereitung: das mathe-jung-alt.de-Zertifikat muss in den lokalen
 * Java-Keystore importiert werden.
 * <ul>
 * <li><strong>1.</strong> Über den Browser das DEV2-Zertifikat als
 * Datei Speichern (am besten Base64-encoded). Dateiname sei
 * mathe-jung-alt.crt</li>
 * <li><strong>2.</strong> CommandLine im Verzeichnis mit dem Zertifikat
 * öffnen</li>
 * <li><strong>3.</strong> keytool -import -alias foo -file DEV2.cer
 * -keystore %JAVA_HOME%\lib\security\cacerts</li>
 * <li><strong>4.</strong> Man wird nach dem Keystore-Passwort gefragt.
 * Das ist 'changeit'</li>
 * <li><strong>5</strong> Man wird gefragt, ob man dem Zertifikat
 * vertraut: Ja/Yes</li>
 * </ul>
 */
public class RestClientSSLTest {

	private static final String BASE_URI = "https://mathe-jung-alt.de/quarkus-hello";

	@Test
	void testGetStatistics() throws KeyStoreException {

		// Könnte sein, dass es hinter einem Proxy nicht geht
		Client client = createSSLCient();

		WebTarget target = client.target(BASE_URI + "/statistics");
		Response response = target.request().get();

		assertEquals(200, response.getStatus());

		String responsePayload = response.readEntity(String.class);
		assertFalse(responsePayload.isEmpty());

	}

	private Client createSSLCient() throws KeyStoreException {

		KeyStore trustStore = KeyStore.getInstance(KeyStore.getDefaultType());
		String trustStorePasswd = "changeit";

		String osName = System.getProperty("os.name");
		String pathTruststoreFile = getTruststoreFileFromSystemEnv();

		if (pathTruststoreFile == null) {

			if ("Linux".equals(osName)) {

				pathTruststoreFile = "/usr/lib/jvm/jdk-11.0.2/lib/security/cacerts";

			} else {

				pathTruststoreFile = "d:/projekte/_SOFTWARE_/JAVA/java-11-openjdk-11.0.3.7-1.windows.redhat.x86_64/lib/security/cacerts";
			}
		}

		System.out.println("Loading trustStore from " + pathTruststoreFile);

		try (FileInputStream fis = new FileInputStream(pathTruststoreFile)) {

			trustStore.load(fis, trustStorePasswd.toCharArray());
			// Default trust manager is PKIX (No SunX509)
			TrustManagerFactory tmf = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
			tmf.init(trustStore);
			SSLContext sslContext = SSLContext.getInstance("TLS");
			sslContext.init(null, tmf.getTrustManagers(), null);

			return ClientBuilder.newBuilder().sslContext(sslContext) // just assign the context to the builder
				.build();
		} catch (Exception e) {

			throw new RuntimeException("Test nicht möglich wegen " + e.getMessage(), e);
		}
	}

	private String getTruststoreFileFromSystemEnv() {

		String javahome = System.getenv("JAVA_HOME");

		if (javahome == null) {

			return null;
		}

		String pathTruststoreFile = null;

		pathTruststoreFile = javahome + "/jre/lib/security/cacerts";

		File truststoreFile = new File(pathTruststoreFile);

		if (truststoreFile.exists()) {

			return pathTruststoreFile;
		}

		pathTruststoreFile = javahome + "/lib/security/cacerts";

		truststoreFile = new File(pathTruststoreFile);

		if (truststoreFile.exists()) {

			return pathTruststoreFile;
		}

		return null;
	}
}
