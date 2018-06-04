package de.dhpoly.netzwerk.control;

import java.io.IOException;

import org.junit.Test;

public class NetzwerkImplTest
{
	@Test(expected = IOException.class)
	public void ClientTest() throws IOException
	{
		NetzwerkClientImpl client = new NetzwerkClientImpl("Test");
		client.verbinden("", 1234);
	}
}
