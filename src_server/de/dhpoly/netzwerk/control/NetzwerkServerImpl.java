package de.dhpoly.netzwerk.control;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import de.dhpoly.datenobjekt.Datenobjekt;
import de.dhpoly.nachricht.model.Nachricht;
import de.dhpoly.netzwerk.NetzwerkServer;
import de.dhpoly.spiel.Spiel;

public class NetzwerkServerImpl implements NetzwerkServer
{
	private List<Spiel> interessenten = new ArrayList<>();

	@Override
	public void sende(Datenobjekt obj) throws IOException
	{
		try (FileOutputStream fos = new FileOutputStream("dummy.ser");
				ObjectOutputStream oos = new ObjectOutputStream(fos))
		{
			oos.writeObject(obj);
		}

		// TODO senden

	}

	@Override
	public void sende(String string) throws IOException
	{
		try (FileOutputStream fos = new FileOutputStream("dummy.ser");
				ObjectOutputStream oos = new ObjectOutputStream(fos))
		{
			oos.writeObject(string);
		}

		// TODO senden
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
}
