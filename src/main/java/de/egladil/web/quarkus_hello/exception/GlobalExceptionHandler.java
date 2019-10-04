// =====================================================
// Project: quarkus-hello
// (c) Heike Winkelvoß
// =====================================================
package de.egladil.web.quarkus_hello.exception;

import java.net.ConnectException;

import javax.inject.Inject;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.slf4j.Logger;

/**
 * GlobalExceptionHandler
 */
@Provider
public class GlobalExceptionHandler implements ExceptionMapper<Throwable> {

	@Inject
	Logger log;

	@Override
	public Response toResponse(final Throwable exception) {

		if (exception instanceof ConnectException
			|| exception.getCause() != null && exception.getCause() instanceof ConnectException) {

			return Response.status(502).entity("Der authprovider ist nicht erreichbar").build();
		}

		if (exception instanceof NotFoundException
			|| exception.getCause() != null && exception.getCause() instanceof NotFoundException) {

			return Response.status(404).entity("Diese URL kennen wir nicht (HEX)").build();
		}

		log.error(exception.getMessage(), exception);
		return Response.serverError().entity("OMG!!! Da ist wohl irgend etwas schiefgelaufen :/").build();
	}

}
