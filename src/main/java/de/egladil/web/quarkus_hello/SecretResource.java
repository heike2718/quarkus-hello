// =====================================================
// Project: quarkus-extconfig
// (c) Heike Winkelvoß
// =====================================================
package de.egladil.web.quarkus_hello;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import de.egladil.web.quarkus_hello.config.SecretPropertiesSource;

/**
 * SecretResource
 */
@RequestScoped
@Path("/secret")
public class SecretResource {

	@Inject
	SecretPropertiesSource secretPropertiesSource;

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String hello() {

		if (secretPropertiesSource != null) {

			return " The secret is '" + secretPropertiesSource.getValue("secret") + "'";
		}

		return "Could not load the secret :/";
	}

}
