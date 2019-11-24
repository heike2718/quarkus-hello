// =====================================================
// Project: quarkus-hello
// (c) Heike Winkelvoß
// =====================================================
package de.egladil.web.quarkus_hello;

import java.util.Map;
import java.util.UUID;

import javax.annotation.security.PermitAll;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.slf4j.Logger;

import de.egladil.web.commons_validation.payload.MessagePayload;
import de.egladil.web.commons_validation.payload.OAuthClientCredentials;
import de.egladil.web.commons_validation.payload.ResponsePayload;
import de.egladil.web.quarkus_hello.exception.AuthException;
import de.egladil.web.quarkus_hello.exception.LogmessagePrefixes;
import de.egladil.web.quarkus_hello.exception.RuntimeExceptionDecorator;
import de.egladil.web.quarkus_hello.restclient.InitAccessTokenService;

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

		try {

			Response authResponse = initAccessTokenService.authenticateClient(credentials);

			ResponsePayload responsePayload = authResponse.readEntity(ResponsePayload.class);

			String accessToken = evaluateResponse(nonce, responsePayload);

			return Response.ok(new ResponsePayload(responsePayload.getMessage(), accessToken)).build();
		} catch (WebApplicationException e) {

			throw new RuntimeExceptionDecorator(e.getMessage(), e);
		}

	}

	private String evaluateResponse(final String nonce, final ResponsePayload responsePayload) throws AuthException {

		MessagePayload messagePayload = responsePayload.getMessage();

		if (messagePayload.isOk()) {

			@SuppressWarnings("unchecked")
			Map<String, String> dataMap = (Map<String, String>) responsePayload.getData();
			String responseNonce = dataMap.get("nonce");

			if (!nonce.equals(responseNonce)) {

				log.warn(LogmessagePrefixes.BOT + "zurückgesendetes nonce stimmt nicht");
				throw new AuthException();
			}
			String accessToken = dataMap.get("accessToken");

			return accessToken;

		} else {

			log.error("Authentisierung des Clients hat nicht geklappt: {} - {}", messagePayload.getLevel(),
				messagePayload.getMessage());
			throw new AuthException();
		}
	}
}
