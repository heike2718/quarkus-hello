// =====================================================
// Project: quarkus-hello
// (c) Heike Winkelvoß
// =====================================================
package de.egladil.web.quarkus_hello.exception;

import java.net.ConnectException;

import javax.inject.Inject;
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

		Throwable theRealException = exception;

		if (exception instanceof RuntimeExceptionDecorator) {

			theRealException = exception.getCause();

		}

		if (exception instanceof RuntimeException && exception.getCause() != null) {

			theRealException = exception.getCause();
		}

		return handleTheRealException(theRealException);
	}

	private Response handleTheRealException(final Throwable exception) {

		if (exception instanceof ConnectException) {

			return Response.status(502).entity("Der authprovider ist nicht erreichbar").build();
		}

		log.error(exception.getMessage(), exception);
		return Response.serverError().entity("Da ist wohl irgend etwas schiefgelaufen").build();

	}

}
