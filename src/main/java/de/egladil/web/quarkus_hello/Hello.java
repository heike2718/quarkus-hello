package de.egladil.web.quarkus_hello;

import javax.annotation.security.PermitAll;
import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.config.inject.ConfigProperty;

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

		return Response.ok("This is quarkus-hello Version " + version).build();
	}

}
