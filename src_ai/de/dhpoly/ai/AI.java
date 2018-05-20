package de.dhpoly.ai;

import de.dhpoly.datenobjekt.Datenobjekt;
import de.dhpoly.handel.model.Transaktion;
import de.dhpoly.handel.model.TransaktionsTyp;
import de.dhpoly.netzwerk.Datenobjektverwalter;
import de.dhpoly.netzwerk.NetzwerkClient;
import de.dhpoly.netzwerk.control.NetzwerkClientImpl;
import de.dhpoly.spieler.model.Spieler;
import de.dhpoly.wuerfel.model.WuerfelAufruf;
import de.dhpoly.wuerfel.model.WuerfelWeitergabe;

public class AI implements Datenobjektverwalter
{
	private NetzwerkClient client;
	protected Spieler spieler;

	public void erzeugeComputerspieler(String ip, String name)
	{
		erzeugeComputerspieler(new NetzwerkClientImpl(ip), name);
	}

	public void erzeugeComputerspieler(NetzwerkClient client, String name)
	{
		this.client = client;
		spieler = new Spieler(name);
		client.sendeAnServer(spieler);
		client.setDatenobjektverwalter(this);
	}

	@Override
	public void empfange(Datenobjekt datenobjekt)
	{
		if (datenobjekt instanceof Transaktion)
		{
			Transaktion transaktion = (Transaktion) datenobjekt;
			transaktion.setTransaktionsTyp(TransaktionsTyp.ABGELEHNT);
			client.sendeAnServer(transaktion);
		}

		else if (datenobjekt instanceof Spieler)
		{
			Spieler s = (Spieler) datenobjekt;
			if (this.spieler == s && s.isAnDerReihe())
			{
				WuerfelAufruf aufruf = new WuerfelAufruf(this.spieler);
				client.sendeAnServer(aufruf);

				WuerfelWeitergabe weitergabe = new WuerfelWeitergabe(this.spieler);
				client.sendeAnServer(weitergabe);
			}
		}
	}
}
