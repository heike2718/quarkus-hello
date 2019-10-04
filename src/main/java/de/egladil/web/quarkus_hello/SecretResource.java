// =====================================================
// Project: quarkus-extconfig
// (c) Heike Winkelvoß
// =====================================================
package de.egladil.web.quarkus_hello;

import javax.enterprise.context.RequestScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.config.inject.ConfigProperty;

/**
 * SecretResource
 */
@RequestScoped
@Path("/secret")
public class SecretResource {

	@ConfigProperty(name = "secret")
	String secret;

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String hello() {

		return " The secret is '" + secret + "'";
	}

}
