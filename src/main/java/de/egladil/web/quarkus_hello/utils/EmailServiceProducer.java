// =====================================================
// Project: quarkus-hello
// (c) Heike Winkelvoß
// =====================================================
package de.egladil.web.quarkus_hello.utils;

import javax.enterprise.inject.Produces;
import javax.inject.Singleton;

import de.egladil.web.commons_mailer.CommonEmailService;
import de.egladil.web.commons_mailer.impl.CommonEmailServiceImpl;

/**
 * EmailServiceProducer
 */
@Singleton
public class EmailServiceProducer {

	@Produces
	public CommonEmailService produceEmailService() {

		return new CommonEmailServiceImpl();
	}

}
