package de.dhpoly.spiel.view;

import java.awt.Component;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import de.dhpoly.fakes.ClientFake;
import de.dhpoly.fakes.ServerFake;
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

	private JTextArea txtName;

	public SpielerstellerUI(Fenster fenster)
	{
		this.fenster = fenster;

		JPanel pnl = ElementFactory.erzeugePanel();
		pnl.setLayout(new GridLayout(3, 2, 10, 10));

		// Spielername
		pnl.add(ElementFactory.getTextFeldUeberschrift("Name:"));
		String name = System.getProperties().getProperty("user.name");
		txtName = ElementFactory.getTextFeld(name, true);
		pnl.add(txtName);

		// Spiel beitreten
		pnl.add(ElementFactory.getTextFeldUeberschrift("Spiel auf einem fremden Rechner:"));
		JButton butSpielBeitreten = ElementFactory.getButtonUeberschrift("Spiel beitreten");
		butSpielBeitreten.addActionListener(e -> spielBeitreten(pnl));
		pnl.add(butSpielBeitreten);

		// Spiel hosten
		pnl.add(ElementFactory.getTextFeldUeberschrift("Spiel auf diesem Rechner:"));
		JButton butSpielHosten = ElementFactory.getButtonUeberschrift("Spiel hosten");
		butSpielHosten.addActionListener(e -> spielHosten(pnl));
		pnl.add(butSpielHosten);

		fenster.zeigeComponente(pnl, "+");
	}

	private void spielBeitreten(JPanel pnl)
	{
		fenster.loescheKomponente(pnl);

		// TODO Spiel beitreten
		// new SpielBeitretenUI mit IP und OK-Button
	}

	private void spielHosten(Component c)
	{
		fenster.loescheKomponente(c);
		new SpielImpl(getServer());

		spielerHinzu(txtName.getText());
	}

	private void spielerHinzu(String name)
	{
		Spieler spieler = new Spieler(name);
		SpielUI verwalter2 = new SpielUI(spieler, getClient());
		fenster.zeigeSpielansicht(verwalter2, name);
		verwalter2.sendeAnServer(spieler);
	}

	private NetzwerkServer getServer()
	{
		// TODO Server erzeugen
		return ServerFake.SERVER_FAKE;
	}

	private NetzwerkClient getClient()
	{
		// TODO Client erzeugen
		return ClientFake.CLIENT_FAKE;
	}
}
