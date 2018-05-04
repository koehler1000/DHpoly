package de.dhpoly.netzwerk.control;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import de.dhpoly.datenobjekt.Datenobjekt;
import de.dhpoly.nachricht.model.Nachricht;
import de.dhpoly.netzwerk.NetzwerkClient;
import de.dhpoly.oberflaeche.view.SpielfeldAnsicht;

public class NetzwerkClientImpl implements NetzwerkClient
{
	private List<SpielfeldAnsicht> interessenten = new ArrayList<>();
	private BufferedReader in;
	private PrintWriter out;
	private String serverIp;

	private Socket socket;

	public NetzwerkClientImpl(String serverIp)
	{
		this.serverIp = serverIp;
	}

	public void empfangen() throws IOException
	{
		empfange(in.readLine());
	}

	public void verbindungAufbauen() throws IOException
	{
		socket = new Socket(serverIp, 9898);
		in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		out = new PrintWriter(socket.getOutputStream(), true);
		while (!in.ready())
		{
			String nachricht = in.readLine();
			if (nachricht != null && nachricht != "")
			{
				empfange(nachricht);
			}
		}
		in.close();
	}

	@Override
	public void sende(Datenobjekt obj) throws IOException
	{
		sende(Serialisierer.toString(obj));
	}

	@Override
	public void sende(String string) throws IOException
	{
		out.println(string);
	}

	@Override
	public void addAnsicht(SpielfeldAnsicht ansicht)

	{
		interessenten.add(ansicht);
	}

	private void empfange(String string)
	{
		Nachricht nachricht = new Nachricht(string);
		empfange(nachricht);
	}

	@Override
	public void empfange(Datenobjekt objekt)
	{
		interessenten.forEach(objekt::anzeigen);
	}

	@Override
	public void verbindungAbbauen() throws IOException
	{
		socket.close();
	}
}
