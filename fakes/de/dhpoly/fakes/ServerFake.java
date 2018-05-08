package de.dhpoly.fakes;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import de.dhpoly.datenobjekt.Datenobjekt;
import de.dhpoly.netzwerk.Datenobjektverwalter;
import de.dhpoly.netzwerk.Server;
import de.dhpoly.spieler.model.Spieler;

public class ServerFake implements Server
{
	public static ServerFake serverfake = new ServerFake();
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
		ClientFake.clientFake.empfange(obj);
	}

	@Override
	public void sendeAnSpieler(Datenobjekt obj, List<Spieler> spieler)
	{
		LOGGER.log(Level.INFO, obj.getClassName());
		ClientFake.clientFake.empfange(obj);
	}

	@Override
	public void sendeAnSpieler(Datenobjekt obj)
	{
		LOGGER.log(Level.INFO, obj.getClassName());
		ClientFake.clientFake.empfange(obj);
	}

	public void empfange(Datenobjekt obj)
	{
		LOGGER.log(Level.INFO, obj.getClassName());
		verwalter.empfange(obj);
	}
}
