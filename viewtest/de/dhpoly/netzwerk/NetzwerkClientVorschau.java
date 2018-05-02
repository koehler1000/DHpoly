package de.dhpoly.netzwerk;

import de.dhpoly.netzwerk.control.NetzwerkClientImpl;
import de.dhpoly.netzwerk.control.NetzwerkServerImpl;

public class NetzwerkClientVorschau
{
	public static void main(String[] args) throws Exception
	{
		NetzwerkServer server = new NetzwerkServerImpl();
		NetzwerkClient client = new NetzwerkClientImpl("127.0.0.1");
		client.addAnsicht(null);
		server.sende("hallo");
	}
}
