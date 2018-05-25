package de.dhpoly.spiel.view;

import java.awt.Component;

import javax.swing.JButton;

import de.dhpoly.netzwerk.NetzwerkClient;
import de.dhpoly.netzwerk.NetzwerkServer;
import de.dhpoly.oberflaeche.ElementFactory;
import de.dhpoly.oberflaeche.view.Fenster;
import de.dhpoly.oberflaeche.view.SpielUI;
import de.dhpoly.spiel.control.SpielImpl;
import de.dhpoly.spieler.model.Spieler;

public class SpielerstellerUI
{
	private Fenster fenster;
	private NetzwerkServer server;
	private NetzwerkClient client;

	public SpielerstellerUI(Fenster fenster, NetzwerkServer server, NetzwerkClient client)
	{
		this.fenster = fenster;
		this.server = server;
		this.client = client;
		JButton butSpielHosten = ElementFactory.getButtonUeberschrift("Spiel hosten");
		butSpielHosten.addActionListener(e -> spielHosten(butSpielHosten));
		fenster.zeigeComponente(butSpielHosten, "+");
	}

	private void spielHosten(Component c)
	{
		fenster.loescheKomponente(c);
		new SpielImpl(server);
		spielerHinzu("Hans");
	}

	private void spielerHinzu(String name)
	{
		Spieler spieler = new Spieler(name);
		SpielUI verwalter2 = new SpielUI(spieler, client);
		fenster.zeigeSpielansicht(verwalter2, name);
		verwalter2.sendeAnServer(spieler);
	}
}
