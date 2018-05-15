package de.dhpoly.netzwerk.control;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

public class NetzwerkImplTest {

	@Test
	public void ClientTest() {
		NetzwerkClientImpl client = new NetzwerkClientImpl("Test");
		try {
			client.verbinden("", 1234);
			fail("Not yet implemented");
		} catch (IOException e) {
			assertTrue(true);
			e.printStackTrace();
		}
	}

}
