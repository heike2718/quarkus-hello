// =====================================================
// Project: quarkus-hello
// (c) Heike Winkelvoß
// =====================================================
package de.egladil.web.quarkus_hello;

import javax.annotation.security.PermitAll;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import de.egladil.web.quarkus_hello.dao.NativeSqlDao;
import de.egladil.web.quarkus_hello.domain.Teilnehmerstatistik;

/**
 * StatisticsResource
 */
@ApplicationScoped
@Path("/statistics")
@PermitAll
public class StatisticsResource {

	@ConfigProperty(name = "secure.cookie")
	boolean secureCookie;

	@Inject
	NativeSqlDao dao;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getStatistics() {

		Teilnehmerstatistik result = dao.getStatistik();

		// NewCookie cookie = new NewCookie("Herbert", "Herr Bert");
		// @formatter:off
		NewCookie cookie = new NewCookie("Herbert",
			"Her Bert",
			null,
			null,
			1,
			null,
			7200,
			null,
			secureCookie,  // nur in dev!!!
			true);
//		 @formatter:on
		return Response.ok().entity(result).cookie(cookie).build();
	}

}
