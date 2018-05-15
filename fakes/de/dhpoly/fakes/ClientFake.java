package de.dhpoly.fakes;

import java.io.IOException;
import java.net.ConnectException;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

import de.dhpoly.datenobjekt.Datenobjekt;
import de.dhpoly.netzwerk.Datenobjektverwalter;
import de.dhpoly.netzwerk.NetzwerkClient;

public class ClientFake implements NetzwerkClient
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

	@Override
	public void sendeAnServer(String text) throws IOException
	{
		LOGGER.log(Level.INFO, text);
	}

	@Override
	public void verbinden(String ip, int port) throws ConnectException, UnknownHostException, IOException
	{}

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
	{}
}
