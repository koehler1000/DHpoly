package de.dhpoly.fakes;

import java.util.logging.Level;
import java.util.logging.Logger;

import de.dhpoly.datenobjekt.Datenobjekt;
import de.dhpoly.netzwerk.Client;
import de.dhpoly.netzwerk.Datenobjektverwalter;

public class ClientFake implements Client
{
	public static ClientFake clientFake = new ClientFake();
	private static final Logger LOGGER = Logger.getLogger(ServerFake.class.getName());

	private Datenobjektverwalter verwalter;

	@Override
	public void setDatenobjektverwalter(Datenobjektverwalter verwalter)
	{
		this.verwalter = verwalter;
	}

	@Override
	public void sendeAnServer(Datenobjekt obj)
	{
		LOGGER.log(Level.INFO, obj.getClassName());
		ServerFake.serverfake.empfange(obj);
	}

	public void empfange(Datenobjekt obj)
	{
		LOGGER.log(Level.INFO, obj.getClassName());
		verwalter.empfange(obj);
	}
}
