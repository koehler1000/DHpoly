package de.dhpoly.netzwerk.control;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Inet4Address;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import de.dhpoly.datenobjekt.Datenobjekt;
import de.dhpoly.nachricht.model.Nachricht;
import de.dhpoly.netzwerk.NetzwerkServer;
import de.dhpoly.spiel.Spiel;

public class NetzwerkServerImpl implements NetzwerkServer
{
	private List<Spiel> interessenten = new ArrayList<>();
	private Capitalizer capitalizer;

	public NetzwerkServerImpl() throws IOException
	{
		int clientNumber = 0;
		ServerSocket listener = new ServerSocket(9898);
		try
		{
			while (true)
			{
				capitalizer = new Capitalizer(listener.accept(), clientNumber++);
				capitalizer.start();
			}
		}
		finally
		{
			listener.close();
		}
	}

	public void senden(String content)
	{
		capitalizer.senden(content);
	}

	public String empfangen() throws Exception
	{
		return capitalizer.empfangen();
	}

	private static class Capitalizer extends Thread
	{
		private BufferedReader eingabe;
		private PrintWriter ausgabe;

		public Capitalizer(Socket socket, int clientNummer) throws IOException
		{
			eingabe = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			ausgabe = new PrintWriter(socket.getOutputStream(), true);
		}

		public void senden(String content)
		{
			ausgabe.println(content);
		}

		public String empfangen() throws Exception
		{
			return eingabe.readLine();
		}
	}

	@Override
	public void sende(Datenobjekt obj) throws IOException
	{
		try (FileOutputStream fos = new FileOutputStream("dummy.ser");
				ObjectOutputStream oos = new ObjectOutputStream(fos))
		{
			oos.writeObject(obj);
		}
		// TODO sende("");
	}

	@Override
	public void sende(String string) throws IOException
	{
		senden(string);
	}

	@Override
	public void addInteressent(Spiel spiel)
	{
		interessenten.add(spiel);
	}

	private void empfange(String string)
	{
		Nachricht nachricht = new Nachricht(string);
		empfange(nachricht);
	}

	private void empfange(Datenobjekt objekt)
	{
		interessenten.forEach(e -> e.empfange(objekt));
	}

	@Override
	public String getIp() throws UnknownHostException
	{
		return Inet4Address.getLocalHost().getHostAddress();
	}
}
