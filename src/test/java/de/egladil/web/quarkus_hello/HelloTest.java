package de.egladil.web.quarkus_hello;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
public class HelloTest {

	@Test
	public void testHelloEndpoint() {

		given()
			.when().get("/hello/world")
			.then()
			.statusCode(200)
			.body(is(
				"Hello this is quarkus-hello Version 0.0.1"));
	}

	@Test
	public void testStatisticsEndpoint() {

		given()
			.when().get("/hello/statistics")
			.then()
			.statusCode(200)
			.body(is(
				"{\"items\":[{\"items\":[{\"anzahl\":1,\"klassenstufe\":\"Klasse 1\"}],\"jahr\":\"2008\"},{\"items\":[{\"anzahl\":142,\"klassenstufe\":\"Klasse 2\"}],\"jahr\":\"2010\"},{\"items\":[{\"anzahl\":10,\"klassenstufe\":\"Klasse 1\"},{\"anzahl\":40,\"klassenstufe\":\"Klasse 2\"}],\"jahr\":\"2014\"},{\"items\":[{\"anzahl\":260,\"klassenstufe\":\"Klasse 1\"},{\"anzahl\":686,\"klassenstufe\":\"Klasse 2\"}],\"jahr\":\"2017\"},{\"items\":[{\"anzahl\":125,\"klassenstufe\":\"Klasse 1\"},{\"anzahl\":9,\"klassenstufe\":\"Inklusion\"},{\"anzahl\":96,\"klassenstufe\":\"Klasse 2\"}],\"jahr\":\"2018\"},{\"items\":[{\"anzahl\":35,\"klassenstufe\":\"Klasse 1\"},{\"anzahl\":1,\"klassenstufe\":\"Inklusion\"},{\"anzahl\":19,\"klassenstufe\":\"Klasse 2\"}],\"jahr\":\"2019\"}]}"));
	}

}
