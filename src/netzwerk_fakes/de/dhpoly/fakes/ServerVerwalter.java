package de.dhpoly.fakes;

import de.dhpoly.netzwerk.NetzwerkServer;

public class ServerVerwalter
{
	private static NetzwerkServer server = Server.erzeugeServer();

	private ServerVerwalter()
	{}

	public static NetzwerkServer getServer()
	{
		return server;
	}
}
