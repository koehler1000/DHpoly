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

public class Server implements NetzwerkServer
{
	private static final Logger LOGGER = Logger.getLogger(Server.class.getName());

	private Optional<Datenobjektverwalter> verwalter = Optional.empty();

	private static Server server = new Server();

	private Server()
	{}

	public static NetzwerkServer erzeugeServer()
	{
		return server;
	}

	@Override
	public void setDatenobjektverwalter(Datenobjektverwalter verwalter)
	{
		this.verwalter = Optional.ofNullable(verwalter);
	}

	@Override
	public void sendeAnSpieler(Datenobjekt obj, Spieler spieler)
	{
		ClientVerwalter.getClient(spieler).empfange(obj);
		LOGGER.log(Level.INFO, obj.getClassName());
	}

	@Override
	public void sendeAnSpieler(Datenobjekt obj, List<Spieler> spieler)
	{
		spieler.forEach(s -> sendeAnSpieler(obj, s));
	}

	@Override
	public void sendeAnSpieler(Datenobjekt obj)
	{
		ClientVerwalter.getClients().forEach(e -> e.empfange(obj));
		LOGGER.log(Level.INFO, obj.getClassName());
	}

	public void empfange(Datenobjekt obj)
	{
		System.out.println("Empfange ...");
		System.out.println(verwalter);
		verwalter.ifPresent(e -> e.empfange(obj));
		LOGGER.log(Level.INFO, obj.getClassName());
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
