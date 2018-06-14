package de.dhpoly.fakes;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import de.dhpoly.spieler.model.Spieler;

public class ClientVerwalter
{
	private static Map<Spieler, Client> clients = new HashMap<>();

	private ClientVerwalter()
	{}

	public static Client erzeugeClient(Spieler spieler)
	{
		Client client = new Client();
		clients.put(spieler, client);
		return client;
	}

	public static Collection<Client> getClients()
	{
		return clients.values();
	}

	public static Client getClient(Spieler s)
	{
		return clients.get(s);
	}
}
