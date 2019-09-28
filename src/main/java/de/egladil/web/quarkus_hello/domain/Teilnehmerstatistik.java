// =====================================================
// Project: quarkus-hello
// (c) Heike Winkelvoß
// =====================================================
package de.egladil.web.quarkus_hello.domain;

import java.util.List;

/**
 * Teilnehmerstatistik
 */
public class Teilnehmerstatistik {

	private List<TeilnehmerstatistikJahrItem> items;

	public List<TeilnehmerstatistikJahrItem> getItems() {

		return items;
	}

	public void setItems(final List<TeilnehmerstatistikJahrItem> items) {

		this.items = items;
	}

}
