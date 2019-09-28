// =====================================================
// Project: quarkus-hello
// (c) Heike Winkelvoß
// =====================================================
package de.egladil.web.quarkus_hello.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

/**
 * Loesungszettel
 */
@Entity
@Table(name = "loesungszettel")
public class Loesungszettel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;

	@Column(name = "KLASSENSTUFE")
	@Enumerated(EnumType.STRING)
	private Klassenstufe klassenstufe;

	@Column(name = "JAHR")
	private String jahr;

	@Version
	@Column(name = "VERSION")
	private int version;
}
