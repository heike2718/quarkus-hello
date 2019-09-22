package de.egladil.web.quarkus_hello;

import io.quarkus.test.junit.SubstrateTest;

@SubstrateTest
public class NativeHelloIT extends HelloTest {

    // Execute the same tests but in native mode.
}