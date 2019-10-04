// =====================================================
// Project: quarkus-hello
// (c) Heike Winkelvoß
// =====================================================
package de.egladil.web.quarkus_hello.exception;

/**
 * RuntimeExceptionDecorator
 */
public class RuntimeExceptionDecorator extends RuntimeException {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * @param message
	 * @param cause
	 */
	public RuntimeExceptionDecorator(final String message, final Throwable cause) {

		super(message, cause);
	}

}
