package de.dhpoly.main;

import java.awt.Component;
import java.io.IOException;

import javax.swing.JButton;

import de.dhpoly.ai.AI;
import de.dhpoly.bilderverwalter.Bilderverwalter;
import de.dhpoly.fakes.ClientFake;
import de.dhpoly.fakes.ServerFake;
import de.dhpoly.oberflaeche.ElementFactory;
import de.dhpoly.oberflaeche.view.Fenster;
import de.dhpoly.oberflaeche.view.SpielUI;
import de.dhpoly.spiel.control.SpielImpl;
import de.dhpoly.spieler.model.Spieler;

public class MainFake
{
	private Fenster fenster = new Fenster(new Bilderverwalter());
	private ClientFake client = ClientFake.CLIENT_FAKE;
	private ServerFake server = ServerFake.SERVER_FAKE;

	public static void main(String[] args) throws IOException
	{
		new MainFake();
	}

	public MainFake() throws IOException
	{
		JButton butSpielHosten = ElementFactory.getButtonUeberschrift("Spiel hosten");
		butSpielHosten.addActionListener(e -> spielHosten(butSpielHosten));
		fenster.zeigeComponente(butSpielHosten, "+");
	}

	private void spielHosten(Component c)
	{
		fenster.loescheKomponente(c);
		new SpielImpl(server);
		spielerHinzu("Hans");
		new AI().erzeugeComputerspieler(client, "PC1");
	}

	private void spielerHinzu(String name)
	{
		Spieler spieler = new Spieler(name);
		SpielUI verwalter2 = new SpielUI(spieler, client);
		fenster.zeigeSpielansicht(verwalter2, name);
		verwalter2.sendeAnServer(spieler);
	}
}
