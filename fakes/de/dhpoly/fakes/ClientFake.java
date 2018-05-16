package de.dhpoly.fakes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import de.dhpoly.datenobjekt.Datenobjekt;
import de.dhpoly.netzwerk.Datenobjektverwalter;
import de.dhpoly.netzwerk.NetzwerkClient;

public class ClientFake implements NetzwerkClient
{
	public static final ClientFake CLIENT_FAKE = new ClientFake();
	private static final Logger LOGGER = Logger.getLogger(ServerFake.class.getName());

	private List<Datenobjektverwalter> verwalter = new ArrayList<>();

	@Override
	public void setDatenobjektverwalter(Datenobjektverwalter verwalter)
	{
		this.verwalter.add(verwalter);
	}

	@Override
	public void sendeAnServer(Datenobjekt obj)
	{
		LOGGER.log(Level.INFO, obj.getClassName());
		ServerFake.SERVER_FAKE.empfange(obj);
	}

	public void empfange(Datenobjekt obj)
	{
		LOGGER.log(Level.INFO, obj.getClassName());
		verwalter.forEach(v -> v.empfange(obj));
	}

	@Override
	public void sendeAnServer(String text) throws IOException
	{
		LOGGER.log(Level.INFO, text);
	}

	@Override
	public void verbinden(String ip, int port) throws IOException
	{
		// fake -> Direktverbindung
	}

	@Override
	public String read()
	{
		return "Not implemented";
	}

	@Override
	public boolean verbindungTrennen()
	{
		return false;
	}

	@Override
	public void sendQuitMessage()
	{
		// fake -> wird nicht benötigt
	}
}
