// =====================================================
// Project: quarkus-hello
// (c) Heike Winkelvoß
// =====================================================
package de.egladil.web.quarkus_hello.domain;

/**
 * TeilnehmerstatistikItem
 */
public class TeilnehmerstatistikItem {

	private String klassenstufe;

	private int anzahl;

	/**
	 *
	 */
	public TeilnehmerstatistikItem() {

	}

	/**
	 * @param klassenstufe
	 * @param anzahl
	 */
	public TeilnehmerstatistikItem(final String klassenstufe, final int anzahl) {

		this.klassenstufe = klassenstufe;
		this.anzahl = anzahl;
	}

	public String getKlassenstufe() {

		return klassenstufe;
	}

	public void setKlassenstufe(final String klassenstufe) {

		this.klassenstufe = klassenstufe;
	}

	public int getAnzahl() {

		return anzahl;
	}

	public void setAnzahl(final int anzahl) {

		this.anzahl = anzahl;
	}

}
