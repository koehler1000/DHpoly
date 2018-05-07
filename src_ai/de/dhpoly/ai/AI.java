package de.dhpoly.ai;

import java.io.IOException;

import de.dhpoly.datenobjekt.Datenobjekt;
import de.dhpoly.handel.model.Transaktion;
import de.dhpoly.handel.model.TransaktionsTyp;
import de.dhpoly.netzwerk.Datenobjektverwalter;
import de.dhpoly.netzwerk.NetzwerkClient;
import de.dhpoly.netzwerk.control.NetzwerkClientImpl;
import de.dhpoly.spieler.model.Spieler;
import de.dhpoly.spieler.model.SpielerTyp;
import de.dhpoly.wuerfel.model.WuerfelAufruf;

public class AI implements Datenobjektverwalter
{
	NetzwerkClient client;
	Spieler spieler;

	public void erzeugeComputerspieler(String ip, String name) throws IOException
	{
		client = new NetzwerkClientImpl(ip);
		spieler = new Spieler(SpielerTyp.COMPUTER, name);
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
			}
		}
	}
}
