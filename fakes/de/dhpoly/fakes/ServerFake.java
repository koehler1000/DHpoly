package de.dhpoly.fakes;

import java.util.List;

import de.dhpoly.datenobjekt.Datenobjekt;
import de.dhpoly.netzwerk.Datenobjektverwalter;
import de.dhpoly.netzwerk.Server;
import de.dhpoly.spieler.model.Spieler;

public class ServerFake implements Server
{
	public static ServerFake serverfake = new ServerFake();

	private Datenobjektverwalter verwalter;

	@Override
	public void setDatenobjektverwalter(Datenobjektverwalter verwalter)
	{
		this.verwalter = verwalter;
	}

	@Override
	public void sendeAnSpieler(Datenobjekt obj, Spieler spieler)
	{
		ClientFake.clientFake.empfange(obj);
	}

	@Override
	public void sendeAnSpieler(Datenobjekt obj, List<Spieler> spieler)
	{
		ClientFake.clientFake.empfange(obj);
	}

	@Override
	public void sendeAnSpieler(Datenobjekt obj)
	{
		ClientFake.clientFake.empfange(obj);
	}

	public void empfange(Datenobjekt obj)
	{
		verwalter.empfange(obj);
	}
}
