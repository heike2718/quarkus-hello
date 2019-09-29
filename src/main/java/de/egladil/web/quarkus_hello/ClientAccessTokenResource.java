// =====================================================
// Project: quarkus-hello
// (c) Heike Winkelvoß
// =====================================================
package de.egladil.web.quarkus_hello;

import java.util.UUID;

import javax.annotation.security.PermitAll;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.json.JsonObject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.slf4j.Logger;

import de.egladil.web.quarkus_hello.exception.AuthException;
import de.egladil.web.quarkus_hello.payload.OAuthClientCredentials;
import de.egladil.web.quarkus_hello.restclient.InitAccessTokenService;
import de.egladil.web.quarkus_hello.utils.LogmessagePrefixes;

/**
 * ClientAccessTokenResource
 */
@RequestScoped
@Path("/accesstoken")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@PermitAll
public class ClientAccessTokenResource {

	@Inject
	Logger log;

	@Inject
	@RestClient
	InitAccessTokenService initAccessTokenService;

	@ConfigProperty(name = "auth.client-id")
	String clientId;

	@ConfigProperty(name = "auth.client-secret")
	String clientSecret;

	@GET
	@Path("/initial")
	public Response getAccessToken() {

		String nonce = UUID.randomUUID().toString();

		OAuthClientCredentials credentials = OAuthClientCredentials.create(clientId, clientSecret, nonce);

		JsonObject auhtResponse = initAccessTokenService.authenticateClient(credentials);

		Response response = evaluateResponse(nonce, auhtResponse);

		return response;

	}

	private Response evaluateResponse(final String nonce, final JsonObject response) {

		JsonObject message = response.getJsonObject("message");
		String level = message.getString("level");

		if ("INFO".equals(level)) {

			String responseNonce = response.getJsonObject("data").getString("nonce");

			if (!nonce.equals(responseNonce)) {

				log.error(LogmessagePrefixes.BOT + "zurückgesendetes nonce stimmt nicht");
				throw new AuthException();
			}
		}

		return Response.ok(response).build();
	}

}
