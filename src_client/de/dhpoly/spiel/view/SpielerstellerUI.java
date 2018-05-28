package de.dhpoly.spiel.view;

import java.awt.Component;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;

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

	private JTextArea txtName;

	public SpielerstellerUI(Fenster fenster, NetzwerkServer server, NetzwerkClient client)
	{
		this.fenster = fenster;
		this.server = server;
		this.client = client;

		JPanel pnl = ElementFactory.erzeugePanel();
		pnl.setLayout(new GridLayout(2, 1, 10, 10));

		txtName = ElementFactory.getTextFeld("Host", true);
		pnl.add(txtName);

		JButton butSpielHosten = ElementFactory.getButtonUeberschrift("Spiel hosten");
		butSpielHosten.addActionListener(e -> spielHosten(pnl));
		pnl.add(butSpielHosten);

		fenster.zeigeComponente(pnl, "+");
	}

	private void spielHosten(Component c)
	{
		fenster.loescheKomponente(c);
		new SpielImpl(server);

		spielerHinzu(txtName.getText());
	}

	private void spielerHinzu(String name)
	{
		Spieler spieler = new Spieler(name);
		SpielUI verwalter2 = new SpielUI(spieler, client);
		fenster.zeigeSpielansicht(verwalter2, name);
		verwalter2.sendeAnServer(spieler);
	}
}
