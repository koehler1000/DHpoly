package de.dhpoly.fakes;

import de.dhpoly.datenobjekt.Datenobjekt;
import de.dhpoly.netzwerk.Client;
import de.dhpoly.netzwerk.Datenobjektverwalter;

public class ClientFake implements Client
{
	public static ClientFake clientFake = new ClientFake();

	private Datenobjektverwalter verwalter;

	@Override
	public void setDatenobjektverwalter(Datenobjektverwalter verwalter)
	{
		this.verwalter = verwalter;
	}

	@Override
	public void sendeAnServer(Datenobjekt obj)
	{
		// TODO Auto-generated method stub

	}

	public void empfange(Datenobjekt obj)
	{
		verwalter.empfange(obj);
	}
}
