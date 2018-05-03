package de.dhpoly.netzwerk.control;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Inet4Address;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

import de.dhpoly.datenobjekt.Datenobjekt;
import de.dhpoly.nachricht.model.Nachricht;
import de.dhpoly.netzwerk.NetzwerkServer;
import de.dhpoly.spiel.Spiel;
import de.dhpoly.spiel.control.SpielImpl;

public class NetzwerkServerImpl implements NetzwerkServer
{
	private PrintWriter ausgabe;
	private BufferedReader eingabe;
	private ServerSocket listener;
	private Socket socket;

	private Spiel spiel;

	public NetzwerkServerImpl() throws IOException
	{
		spiel = new SpielImpl();
		listener = new ServerSocket(9898);
		socket = listener.accept();
		ausgabe = new PrintWriter(socket.getOutputStream(), true);
		eingabe = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		ausgabe.print("test");
	}

	public void run() throws IOException
	{
		listener = new ServerSocket(9898);
		socket = listener.accept();
		ausgabe = new PrintWriter(socket.getOutputStream(), true);
		eingabe = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		ausgabe.print("test");
	}

	public void senden(String content)
	{
		ausgabe.print(content);
	}

	public String empfangen() throws IOException
	{
		return eingabe.readLine();
	}

	@Override
	public void sende(Datenobjekt obj) throws IOException
	{
		if (obj != null)
		{
			sende(Serialisierer.toString(obj));
			empfange("Nachricht wurde gesendet");
		}
	}

	@Override
	public void sende(String string) throws IOException
	{
		senden(string);
	}

	private void empfange(String string)
	{
		Nachricht nachricht = new Nachricht(string);
		empfange(nachricht);
	}

	private void empfange(Datenobjekt objekt)
	{
		spiel.empfange(objekt);
	}

	@Override
	public String getIp() throws UnknownHostException
	{
		return Inet4Address.getLocalHost().getHostAddress();
	}

	@Override
	public void verbindungAbbauen() throws IOException
	{
		socket.close();
	}
}
