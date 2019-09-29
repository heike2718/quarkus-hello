// =====================================================
// Project: quarkus-hello
// (c) Heike Winkelvoß
// =====================================================
package de.egladil.web.quarkus_hello.exception;

/**
 * AuthException
 */
public class AuthException extends RuntimeException {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	/**
	 *
	 */
	public AuthException() {

	}

	/**
	 * @param message
	 */
	public AuthException(final String message) {

		super(message);
	}

}
