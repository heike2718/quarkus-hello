// =====================================================
// Project: quarkus-extconfig
// (c) Heike Winkelvoß
// =====================================================
package de.egladil.web.quarkus_hello.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.slf4j.Logger;

/**
 * SecretPropertiesSource is connected with a properties file in the file system.<br>
 * <br>
 * The location of this file must be configured by setting the property 'secret.config.path'. This property is resolved
 * by the MP configuration mechanism.<br>
 * <br>
 * The purpose of redirecting to an external file is to avoid secret properties being checked into version control.<br>
 * <br>
 * This class das not implement the MP ConfigSource because this leads to errors of type 'java.lang.RuntimeException: property
 * '%dev.ManagedExecutor/maxAsync' is missing in /usr/local/bin/quarkus-hello/config/secret.properties' on bootstrapping the
 * application
 */
@Singleton
public class SecretPropertiesSource {

	@Inject
	Logger log;

	@ConfigProperty(name = "secret.config.path")
	String pathSecretProperties;

	private Map<String, String> properties = new HashMap<>();

	public String getValue(final String propertyName) {

		log.debug("=== (SPS-1): propertyName=" + propertyName);

		if (properties.isEmpty()) {

			loadProperties();
		}

		String val = properties.get(propertyName);

		if (val == null) {

			throw new RuntimeException("property '" + propertyName + "' is missing in " + pathSecretProperties);
		}
		return val;
	}

	private void loadProperties() {

		try (InputStream in = new FileInputStream(new File(pathSecretProperties))) {

			final Properties props = new Properties();

			props.load(in);

			props.keySet().stream().forEach(k -> {

				String key = (String) k;
				String value = props.getProperty((String) key);
				properties.put((String) key, value);
			});

			log.debug("=== (SPS-2) ===");

		} catch (IOException e) {

			log.error("error reading external configuration: {}", e.getMessage(), e);

		}
	}
}
