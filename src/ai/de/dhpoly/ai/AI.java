package de.dhpoly.ai;

import de.dhpoly.datenobjekt.Datenobjekt;
import de.dhpoly.feld.model.StrasseKaufen;
import de.dhpoly.feld.model.StrasseKaufenStatus;
import de.dhpoly.handel.model.Transaktion;
import de.dhpoly.handel.model.TransaktionsTyp;
import de.dhpoly.netzwerk.Datenobjektverwalter;
import de.dhpoly.netzwerk.NetzwerkClient;
import de.dhpoly.netzwerk.control.NetzwerkClientImpl;
import de.dhpoly.spieler.model.Spieler;
import de.dhpoly.spieler.model.SpielerStatus;
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
		client.setDatenobjektverwalter(this);
		client.sendeAnServer(spieler);
	}

	@Override
	public void empfange(Datenobjekt datenobjekt)
	{
		new Thread(() -> {
			// TODO sollte asynchron ausgeführt werden können ohne Warten zu müssen
			try
			{
				Thread.sleep(1000);
			}
			catch (InterruptedException ex) // NOSONAR
			{
				// ignorieren
			}
			verarbeite(datenobjekt);
		}).start();
	}

	protected void verarbeite(Datenobjekt datenobjekt)
	{
		if (datenobjekt instanceof Transaktion)
		{
			verarbeiteTransaktion((Transaktion) datenobjekt);
		}
		else if (datenobjekt instanceof StrasseKaufen)
		{
			verarbeiteStrasseKaufen((StrasseKaufen) datenobjekt);
		}
		else if (datenobjekt instanceof Spieler && spieler.equals(datenobjekt))
		{
			verarbeiteSpieler((Spieler) datenobjekt);
		}
	}

	private void verarbeiteStrasseKaufen(StrasseKaufen strasseKaufen)
	{
		if (strasseKaufen.getSender().equals(spieler))
		{
			strasseKaufen.setAntwortDaten(StrasseKaufenStatus.ANGENOMMEN, spieler);
			client.sendeAnServer(strasseKaufen);
		}
	}

	private void verarbeiteSpieler(Spieler spieler)
	{
		if (spieler.getStatus() == SpielerStatus.MUSS_WUERFELN)
		{
			WuerfelAufruf aufruf = new WuerfelAufruf(spieler);
			client.sendeAnServer(aufruf);
		}
		else if (spieler.getStatus() == SpielerStatus.MUSS_WUERFEL_WEITERGEBEN)
		{
			// TODO sollte asynchron ausgeführt werden können ohne Warten zu müssen
			try
			{
				Thread.sleep(1000);
			}
			catch (InterruptedException ex) // NOSONAR
			{
				// ignorieren
			}
			WuerfelWeitergabe weitergabe = new WuerfelWeitergabe(this.spieler);
			client.sendeAnServer(weitergabe);
		}
	}

	private void verarbeiteTransaktion(Transaktion transaktion)
	{
		transaktion.setTransaktionsTyp(TransaktionsTyp.ABGELEHNT);
		client.sendeAnServer(transaktion);
	}
}
