package de.egladil.web.quarkus_hello;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import de.egladil.web.quarkus_hello.dao.NativeSqlDao;
import de.egladil.web.quarkus_hello.domain.Teilnehmerstatistik;

@ApplicationScoped
@Path("/hello")
public class Hello {

	@Inject
	NativeSqlDao dao;

	@ConfigProperty(name = "quarkus-hello.version")
	String version;

	@GET
	@Path("/world")
	@Produces(MediaType.TEXT_PLAIN)
	public Response hello() {

		return Response.ok("Hello this is quarkus-hello Version " + version).build();
	}

	@GET
	@Path("/statistics")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getStatistics() {

		Teilnehmerstatistik result = dao.getStatistik();

		return Response.ok().entity(result).build();
	}

}
