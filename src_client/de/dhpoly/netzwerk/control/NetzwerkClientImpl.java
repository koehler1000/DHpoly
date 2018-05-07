package de.dhpoly.netzwerk.control;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.net.Socket;
import java.net.UnknownHostException;

import de.dhpoly.netzwerk.NetzwerkClient;
import de.dhpoly.oberflaeche.view.SpielfeldAnsicht;

public class NetzwerkClientImpl implements NetzwerkClient
{
	private Socket server;
	private PrintWriter outputWriter;
	private BufferedReader inputBuffer;
	private String username;

	public NetzwerkClientImpl(String string)
	{
		// TODO Auto-generated constructor stub
	}

	public void connect(String ip, int port) throws ConnectException, UnknownHostException, IOException
	{
		server = new Socket(ip, port);
		try
		{

			inputBuffer = new BufferedReader(new InputStreamReader(server.getInputStream()));
			outputWriter = new PrintWriter(server.getOutputStream());
		}
		catch (IOException e)
		{
			System.err.println(e);
			e.printStackTrace();
		}
	}

	/** Attempt to close the connection, including input/output streams. */
	public boolean disconnect()
	{
		try
		{
			server.close();
			inputBuffer.close();
		}
		catch (IOException e)
		{
			System.err.println(e);
			e.printStackTrace();
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
	public String read()
	{
		String line = null;
		try
		{
			line = inputBuffer.readLine();
		}
		catch (IOException e)
		{
			System.err.println(e);
			e.printStackTrace();
		}
		return line;
	}

	public void sendQuitMessage()
	{
		sendeAnServer("QUIT");
	}

	@Override
	public void sendeAnServer(Object ob)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void addAnsicht(SpielfeldAnsicht ansicht)
	{
		// TODO Auto-generated method stub

	}

}