package de.egladil.web.quarkus_hello;

import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import javax.annotation.security.PermitAll;
import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.CookieParam;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import de.egladil.web.commons_net.time.CommonTimeUtils;
import de.egladil.web.commons_net.utils.CommonHttpUtils;

@ApplicationScoped
@Path("/hello")
@PermitAll
public class Hello {

	@ConfigProperty(name = "quarkus.application.version")
	String version;

	@GET
	@Path("/world")
	@Produces(MediaType.TEXT_PLAIN)
	public Response hello() {

		// @formatter:off
		NewCookie sessionCookie = new NewCookie(CommonHttpUtils.NAME_SESSIONID_COOKIE,
			CommonHttpUtils.createUserIdReference(),
			null, // path
			null, // domain muss null sein, wird vom Browser anhand des restlichen Responses abgeleitet. Sonst wird das Cookie nicht gesetzt.
			1,  // version
			null, // comment
			7200, // expires (minutes)
			null,
			true, // secure
			true  // httpOnly
			);
		//@formatter:on

		return Response.ok("This is quarkus-hello Version " + version).cookie(sessionCookie).build();
	}

	@GET
	@Path("/bye")
	@Produces(MediaType.TEXT_PLAIN)
	public Response by(@CookieParam(value = CommonHttpUtils.NAME_SESSIONID_COOKIE) final String sessionId) {

		if (sessionId != null) {

			long dateInThePast = CommonTimeUtils.now().minus(10, ChronoUnit.YEARS).toEpochSecond(ZoneOffset.UTC);

			// @formatter:off
			NewCookie invalidationCookie = new NewCookie(CommonHttpUtils.NAME_SESSIONID_COOKIE,
				null,
				null,
				null,
				1,
				null,
				0,
				new Date(dateInThePast),
				true,
				true);
//			 @formatter:on
			// NewCookie sessionCookie = new NewCookie("JSESSIONID", userSession.getSessionId());

			return Response.ok("Your sessionId was " + sessionId).cookie(invalidationCookie).build();
		}

		return Response.ok("There was not any session").build();

	}

}
