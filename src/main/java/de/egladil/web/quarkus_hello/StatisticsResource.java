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
import javax.ws.rs.core.Response;

import de.egladil.web.quarkus_hello.dao.NativeSqlDao;
import de.egladil.web.quarkus_hello.domain.Teilnehmerstatistik;

/**
 * StatisticsResource
 */
@ApplicationScoped
@Path("/statistics")
@PermitAll
public class StatisticsResource {

	@Inject
	NativeSqlDao dao;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getStatistics() {

		Teilnehmerstatistik result = dao.getStatistik();

		return Response.ok().entity(result).build();
	}

}
