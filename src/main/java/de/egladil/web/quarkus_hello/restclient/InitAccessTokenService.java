// =====================================================
// Project: quarkus-hello
// (c) Heike Winkelvoß
// =====================================================
package de.egladil.web.quarkus_hello.restclient;

import javax.json.JsonObject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import de.egladil.web.quarkus_hello.payload.OAuthClientCredentials;

/**
 * InitAccessTokenService
 */
@RegisterRestClient
@Path("/clients")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public interface InitAccessTokenService {

	@POST
	@Path("/client/accesstoken")
	JsonObject authenticateClient(OAuthClientCredentials clientSecrets);

}
