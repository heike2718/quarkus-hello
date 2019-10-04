// =====================================================
// Project: quarkus-hello
// (c) Heike Winkelvoß
// =====================================================
package de.egladil.web.quarkus_hello.utils;

import javax.enterprise.inject.Produces;
import javax.inject.Singleton;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import de.egladil.web.commons_mailer.EmailServiceCredentials;

/**
 * EmailCredentialsProducer
 */
@Singleton
public class EmailCredentialsProducer {

	@ConfigProperty(name = "common.email.host")
	String host;

	@ConfigProperty(name = "common.email.port")
	int port;

	@ConfigProperty(name = "common.email.user")
	String user;

	@ConfigProperty(name = "common.email.password")
	String password;

	@Produces
	public EmailServiceCredentials produceEmailServiceCredentials() {

		return EmailServiceCredentials.createInstance(host, port, user, password.toCharArray(), "noreply@egladil.de");
	}

}
