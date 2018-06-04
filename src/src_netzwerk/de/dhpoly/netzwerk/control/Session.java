package de.dhpoly.netzwerk.control;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Session
{
	private static final Logger LOGGER = Logger.getLogger(Session.class.getName());

	private String username;
	private Socket socket;
	private PrintWriter outputWriter;
	private BufferedReader inputBuffer;

	/**
	 * Constructor establishes a Connection for a given connected socket.
	 * 
	 * @param socket
	 *            A connected socket for communication.
	 */
	Session(Socket socket)
	{
		this.socket = socket;
		try
		{
			inputBuffer = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
			outputWriter = new PrintWriter(this.socket.getOutputStream(), true);
		}
		catch (IOException e)
		{
			LOGGER.log(Level.SEVERE, e.getMessage(), e);
		}
	}

	/** Write to the connection socket */
	public void write(String msg)
	{
		outputWriter.println(msg);
		outputWriter.flush();
	}

	/** Attempt to read from the connection socket. */
	public String read()
	{
		String line = null;
		try
		{
			line = inputBuffer.readLine();
		}
		catch (SocketException e)
		{
			LOGGER.log(Level.WARNING, "Log: Client disconnected, session ended");
		}
		catch (IOException e)
		{
			LOGGER.log(Level.SEVERE, e.getMessage(), e);
		}
		return line;
	}

	/** Attempt to close the connection, including input/output streams. */
	public boolean disconnect()
	{
		try
		{
			socket.close();
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

	/** Set the username associated with the given connection */
	public void setUsername(String username)
	{
		this.username = username;
	}

	public Socket getSocket()
	{
		return socket;
	}

	public String getUsername()
	{
		return username;
	}
}