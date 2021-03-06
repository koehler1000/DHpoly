package de.dhpoly.fakes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import de.dhpoly.datenobjekt.Datenobjekt;
import de.dhpoly.netzwerk.Datenobjektverwalter;
import de.dhpoly.netzwerk.NetzwerkClient;

public class Client implements NetzwerkClient
{
	private static final Logger LOGGER = Logger.getLogger(Server.class.getName());

	private List<Datenobjektverwalter> verwalter = new ArrayList<>();

	@Override
	public void setDatenobjektverwalter(Datenobjektverwalter verwalter)
	{
		this.verwalter.add(verwalter);
	}

	@Override
	public void sendeAnServer(String text) throws IOException
	{
		LOGGER.log(Level.INFO, text);
	}

	public void empfange(Datenobjekt obj)
	{
		LOGGER.log(Level.INFO, obj.getClassName());
		verwalter.forEach(v -> v.empfange(obj));
	}

	@Override
	public void verbinden(String ip, int port) throws IOException
	{
		// fake -> Direktverbindung
	}

	@Override
	public boolean verbindungTrennen()
	{
		return false;
	}

	@Override
	public void sendQuitMessage()
	{
		// fake -> wird nicht ben�tigt
	}

	@Override
	public void sendeAnServer(Datenobjekt obj)
	{
		LOGGER.log(Level.INFO, obj.getClassName());
		ServerVerwalter.getServer().empfange(obj);
	}
}
