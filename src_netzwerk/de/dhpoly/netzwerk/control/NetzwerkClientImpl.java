package de.dhpoly.netzwerk.control;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

import de.dhpoly.datenobjekt.Datenobjekt;
import de.dhpoly.fakes.ServerFake;
import de.dhpoly.netzwerk.Datenobjektverwalter;
import de.dhpoly.netzwerk.NetzwerkClient;

public class NetzwerkClientImpl implements NetzwerkClient
{
	private static final Logger LOGGER = Logger.getLogger(ServerFake.class.getName());

	private Socket server;
	private PrintWriter outputWriter;
	private BufferedReader inputBuffer;
	private String username;

	public NetzwerkClientImpl(String string)
	{
		// TODO Auto-generated constructor stub
	}

	public void verbinden(String ip, int port) throws IOException
	{
		server = new Socket(ip, port);
		try
		{

			inputBuffer = new BufferedReader(new InputStreamReader(server.getInputStream()));
			outputWriter = new PrintWriter(server.getOutputStream());
		}
		catch (IOException e)
		{
			LOGGER.log(Level.SEVERE, e.getMessage(), e);
		}
	}

	/** Attempt to close the connection, including input/output streams. */
	public boolean verbindungTrennen()
	{
		try
		{
			server.close();
			inputBuffer.close();
		}
		catch (IOException e)
		{
			LOGGER.log(Level.SEVERE, e.getMessage(), e);
			return false;
		}
		outputWriter.close();
		return true;
	}

	/** Write to the connection socket */
	public void sendeAnServer(String msg)
	{
		outputWriter.println(msg);
		outputWriter.flush();
	}

	/** Attempt to read from the connection socket. */
	@Deprecated
	public String read()
	{
		String line = null;
		try
		{
			line = inputBuffer.readLine();
		}
		catch (IOException e)
		{
			LOGGER.log(Level.SEVERE, e.getMessage(), e);
		}
		return line;
	}

	public void sendQuitMessage()
	{
		sendeAnServer("QUIT");
	}

	@Deprecated
	public void setDatenobjektverwalter(Datenobjektverwalter verwalter)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void sendeAnServer(Datenobjekt obj)
	{
		// TODO Auto-generated method stub

	}

}