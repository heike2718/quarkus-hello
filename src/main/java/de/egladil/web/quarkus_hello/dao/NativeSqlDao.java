// =====================================================
// Project: quarkus-hello
// (c) Heike Winkelvoß
// =====================================================
package de.egladil.web.quarkus_hello.dao;

import de.egladil.web.quarkus_hello.domain.Teilnehmerstatistik;

/**
 * NativeSqlDao
 */
public interface NativeSqlDao {

	/**
	 * @return Teilnehmerstatistik
	 */
	Teilnehmerstatistik getStatistik();

}
