// =====================================================
// Project: quarkus-hello
// (c) Heike Winkelvoß
// =====================================================
package de.egladil.web.quarkus_hello;

import javax.annotation.security.PermitAll;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.slf4j.Logger;

import de.egladil.web.commons_mailer.CommonEmailService;
import de.egladil.web.commons_mailer.EmailDaten;
import de.egladil.web.commons_mailer.EmailDatenBuilder;
import de.egladil.web.commons_mailer.EmailServiceCredentials;

/**
 * MailResource
 */
@RequestScoped
@Path("/mail")
@PermitAll
public class MailResource {

	@ConfigProperty(name = "secret")
	String secret;

	@ConfigProperty(name = "mail.url.queryparam")
	String expectedQueryParam;

	@ConfigProperty(name = "common.email.bcc")
	String bcc;

	@Inject
	Logger log;

	@Inject
	CommonEmailService emailService;

	@Inject
	EmailServiceCredentials emailServiceCredentials;

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public Response sendTestmail(@QueryParam(value = "whitch") final String queryParam) {

		if (!expectedQueryParam.equals(queryParam)) {

			log.error("invalid query parameter: excpected '{}' but was '{}'", expectedQueryParam, queryParam);

			return Response.status(403).entity("Das hat mangels Wissen nicht geklappt").build();
		}

		if (emailServiceCredentials != null) {

			emailService.sendMail(getEmailDaten(), emailServiceCredentials);

			return Response.ok().entity("mail sent successfully").build();
		}

		return Response.serverError()
			.entity("Können keine Mail versenden wegen Konfigurationsfehlers: emailServiceCredentials null").build();
	}

	private EmailDaten getEmailDaten() {

		String text = "Das secret ist '" + secret + "'\nLG";

		return new EmailDatenBuilder("hdwinkel@egladil.de", "Mail mit secret vom Quarkus")
			.withText(text).addHidden(bcc).build();
	}

}
