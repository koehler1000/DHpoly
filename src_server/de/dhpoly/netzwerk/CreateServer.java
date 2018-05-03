package de.dhpoly.netzwerk;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class CreateServer
{
	private Capitalizer capitalizer;

	public CreateServer() throws Exception
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

		public Capitalizer(Socket socket, int clientNummer) throws Exception
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
}
