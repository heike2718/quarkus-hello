// =====================================================
// Project: quarkus-hello
// (c) Heike Winkelvoß
// =====================================================
package de.egladil.web.quarkus_hello.domain;

/**
 * Klassenstufe
 */
public enum Klassenstufe {

	EINS("Klasse 1"),
	ZWEI("Klasse 2"),
	IKID("Inklusion");

	private final String label;

	/**
	 * @param label
	 */
	private Klassenstufe(final String label) {

		this.label = label;
	}

	public String getLabel() {

		return label;
	}

}
