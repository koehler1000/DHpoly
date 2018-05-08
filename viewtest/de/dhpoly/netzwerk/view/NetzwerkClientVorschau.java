package de.dhpoly.netzwerk.view;

import de.dhpoly.bilderverwalter.Bilderverwalter;
import de.dhpoly.netzwerk.control.NetzwerkServerImpl;
import de.dhpoly.oberflaeche.view.Fenster;

public class NetzwerkClientVorschau
{
	public static void main(String[] args) throws Exception
	{
		Fenster fenster = new Fenster(new Bilderverwalter());
		// TODO
		// Client client = new NetzwerkClientImpl("127.0.0.1");

		// SpielfeldAnsicht ansicht = new SpielfeldAnsicht(new
		// Spieler(SpielerTyp.NETZWERK, "Netzwerkspieler"), client);
		// client.setDatenobjektverwalter(ansicht);
		// fenster.zeigeSpielansicht(ansicht, "NetzwerkClientVorschau");

		NetzwerkServerImpl server = new NetzwerkServerImpl(0);
		Thread x = new Thread(() -> {
			// try
			// {
			// server.run();
			// }
			// catch (IOException e)
			// {
			// Fehler fehler = new Fehler(e.getMessage(), FehlerTyp.FEHLER_ALLE);
			// fehler.anzeigen(ansicht);
			// }
			// TODO viewtest wieder implementieren

		});
		x.start();

	}
}
