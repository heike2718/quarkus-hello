// =====================================================
// Project: quarkus-hello
// (c) Heike Winkelvoß
// =====================================================
package de.egladil.web.quarkus_hello.dao.impl;

import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import de.egladil.web.quarkus_hello.dao.NativeSqlDao;
import de.egladil.web.quarkus_hello.domain.Klassenstufe;
import de.egladil.web.quarkus_hello.domain.Teilnehmerstatistik;
import de.egladil.web.quarkus_hello.domain.TeilnehmerstatistikItem;
import de.egladil.web.quarkus_hello.domain.TeilnehmerstatistikJahrItem;

/**
 * NativeSqlDaoImpl
 */
@ApplicationScoped
public class NativeSqlDaoImpl implements NativeSqlDao {

	@Inject
	EntityManager entityManager;

	@Override
	public Teilnehmerstatistik getStatistik() {

		List<String> jahre = getJahre();

		List<TeilnehmerstatistikJahrItem> jahrItems = jahre.stream().map(jahr -> getStatistikItem(jahr))
			.collect(Collectors.toList());

		Teilnehmerstatistik result = new Teilnehmerstatistik();
		result.setItems(jahrItems);

		return result;

	}

	private TeilnehmerstatistikJahrItem getStatistikItem(final String jahr) {

		TeilnehmerstatistikJahrItem result = new TeilnehmerstatistikJahrItem();
		result.setJahr(jahr);

		String stmt = "select klassenstufe, count(*) from loesungszettel where jahr = :jahr group by klassenstufe";
		Query query = entityManager.createNativeQuery(stmt);
		query.setParameter("jahr", jahr);

		@SuppressWarnings("unchecked")
		List<Object[]> resultList = query.getResultList();

		List<TeilnehmerstatistikItem> items = resultList.stream()
			.map(
				(final Object[] object) -> new TeilnehmerstatistikItem(Klassenstufe.valueOf(object[0].toString()).getLabel(),
					(((BigInteger) object[1]).intValue())))
			.collect(Collectors.toList());

		result.setItems(items);

		return result;
	}

	@SuppressWarnings("unchecked")
	private List<String> getJahre() {

		String stmt = "select distinct(jahr) from loesungszettel order by jahr";
		Query query = entityManager.createNativeQuery(stmt);

		List<Object> trefferliste = query.getResultList();

		return trefferliste.stream().map(o -> o.toString()).collect(Collectors.toList());

	}

}
