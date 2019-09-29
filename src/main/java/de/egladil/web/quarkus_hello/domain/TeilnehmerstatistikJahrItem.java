// =====================================================
// Project: quarkus-hello
// (c) Heike Winkelvoß
// =====================================================
package de.egladil.web.quarkus_hello.domain;

import java.util.List;

/**
 * TeilnehmerstatistikJahrItem
 */
public class TeilnehmerstatistikJahrItem {
	
	private String jahr;

	private List<TeilnehmerstatistikItem> items;

	public List<TeilnehmerstatistikItem> getItems() {

		return items;
	}

	public void setItems(final List<TeilnehmerstatistikItem> items) {

		this.items = items;
	}

	public String getJahr() {
	
		return jahr;
	}

	public void setJahr(String jahr) {
	
		this.jahr = jahr;
	}

}
