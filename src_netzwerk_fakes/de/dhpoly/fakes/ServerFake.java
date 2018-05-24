package de.dhpoly.fakes;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import de.dhpoly.datenobjekt.Datenobjekt;
import de.dhpoly.netzwerk.Datenobjektverwalter;
import de.dhpoly.netzwerk.NetzwerkServer;
import de.dhpoly.spieler.model.Spieler;

public class ServerFake implements NetzwerkServer
{
	public static final ServerFake SERVER_FAKE = new ServerFake();
	private static final Logger LOGGER = Logger.getLogger(ServerFake.class.getName());

	private Datenobjektverwalter verwalter;

	@Override
	public void setDatenobjektverwalter(Datenobjektverwalter verwalter)
	{
		this.verwalter = verwalter;
	}

	@Override
	public void sendeAnSpieler(Datenobjekt obj, Spieler spieler)
	{
		LOGGER.log(Level.INFO, obj.getClassName());
		ClientFake.CLIENT_FAKE.empfange(obj);
	}

	@Override
	public void sendeAnSpieler(Datenobjekt obj, List<Spieler> spieler)
	{
		LOGGER.log(Level.INFO, obj.getClassName());
		ClientFake.CLIENT_FAKE.empfange(obj);
	}

	@Override
	public void sendeAnSpieler(Datenobjekt obj)
	{
		LOGGER.log(Level.INFO, obj.getClassName());
		ClientFake.CLIENT_FAKE.empfange(obj);
	}

	public void empfange(Datenobjekt obj)
	{
		LOGGER.log(Level.INFO, obj.getClassName());
		verwalter.empfange(obj);
	}

	@Override
	public String getIp() throws UnknownHostException
	{
		return null;
	}

	@Override
	public void verbindungAbbauen() throws IOException
	{
		// fake -> wird nicht ben�tigt
	}

	@Override
	public void run(String[] args) throws IOException
	{
		// fake -> wird nicht ben�tigt
	}
}