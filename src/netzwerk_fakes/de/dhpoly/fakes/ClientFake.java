package de.dhpoly.fakes;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import de.dhpoly.datenobjekt.Datenobjekt;
import de.dhpoly.netzwerk.Datenobjektverwalter;
import de.dhpoly.netzwerk.NetzwerkClient;
import de.dhpoly.netzwerk.control.Serialisierer;

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
		ServerFake.SERVER_FAKE.empfange(Serialisierer.toString(obj));
	}

	@Override
	public void sendeAnServer(String text) throws IOException
	{
		LOGGER.log(Level.INFO, text);
	}

	private void empfange(Datenobjekt obj)
	{
		LOGGER.log(Level.INFO, obj.getClassName());
		verwalter.forEach(v -> v.empfange(obj));
	}

	public void empfange(String string)
	{
		try
		{
			Serializable object = Serialisierer.toObject(string);
			if (object instanceof Datenobjekt)
			{
				empfange((Datenobjekt) object);
			}
		}
		catch (Exception ex)
		{
			LOGGER.log(Level.WARNING, ex.getMessage());
		}
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
		// fake -> wird nicht benötigt
	}
}
