package de.dhpoly.spiel.view;

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
import de.dhpoly.spieler.model.Spieler;

public class SpielerErstellerUI
{
	private Fenster fenster;

	private JTextArea txtName;

	public SpielerErstellerUI(Fenster fenster)
	{
		this.fenster = fenster;

		JPanel pnl = ElementFactory.erzeugePanel();
		pnl.setLayout(new GridLayout(2, 2, 10, 10));

		pnl.add(ElementFactory.getTextFeldUeberschrift("Name:"));
		String name = System.getProperties().getProperty("user.name");
		txtName = ElementFactory.getTextFeld(name, true);
		pnl.add(txtName);

		pnl.add(ElementFactory.getTextFeldUeberschrift("Spieler hinzufügen:"));
		JButton butSpielBeitreten = ElementFactory.getButtonUeberschrift("Spieler hinzufügen");
		butSpielBeitreten.addActionListener(e -> spielerHinzu());
		pnl.add(butSpielBeitreten);

		fenster.zeigeComponente(pnl, "+");
	}

	private void spielerHinzu()
	{
		Spieler spieler = new Spieler(txtName.getText());
		SpielUI verwalter2 = new SpielUI(spieler, getClient());
		fenster.zeigeSpielansicht(verwalter2, txtName.getText());
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
