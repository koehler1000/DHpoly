package de.dhpoly.fakes;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

import de.dhpoly.datenobjekt.Datenobjekt;
import de.dhpoly.netzwerk.Datenobjektverwalter;
import de.dhpoly.netzwerk.NetzwerkServer;
import de.dhpoly.spieler.model.Spieler;

public class ServerFactory implements NetzwerkServer
{
	public static final ServerFactory SERVER = new ServerFactory();
	private static final Logger LOGGER = Logger.getLogger(ServerFactory.class.getName());

	private Optional<Datenobjektverwalter> verwalter = Optional.empty();

	@Override
	public void setDatenobjektverwalter(Datenobjektverwalter verwalter)
	{
		this.verwalter = Optional.ofNullable(verwalter);
	}

	@Override
	public void sendeAnSpieler(Datenobjekt obj, Spieler spieler)
	{
		sendeAnSpieler(obj);
	}

	@Override
	public void sendeAnSpieler(Datenobjekt obj, List<Spieler> spieler)
	{
		sendeAnSpieler(obj);
	}

	@Override
	public void sendeAnSpieler(Datenobjekt obj)
	{
		ClientFactory.CLIENT.empfange(obj);
		LOGGER.log(Level.INFO, obj.getClassName());
	}

	public void empfange(Datenobjekt obj)
	{
		LOGGER.log(Level.INFO, obj.getClassName());
		verwalter.ifPresent(e -> e.empfange(obj));
	}

	@Override
	public String getIp() throws UnknownHostException
	{
		return null;
	}

	@Override
	public void verbindungAbbauen() throws IOException
	{
		// fake -> wird nicht benötigt
	}

	@Override
	public void run(String[] args) throws IOException
	{
		// fake -> wird nicht benötigt
	}
}
