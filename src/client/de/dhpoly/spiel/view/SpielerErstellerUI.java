package de.dhpoly.spiel.view;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import de.dhpoly.ai.AI;
import de.dhpoly.fakes.ClientFactory;
import de.dhpoly.netzwerk.NetzwerkClient;
import de.dhpoly.netzwerk.NetzwerkServer;
import de.dhpoly.oberflaeche.ElementFactory;
import de.dhpoly.oberflaeche.view.Fenster;
import de.dhpoly.oberflaeche.view.SpielUI;
import de.dhpoly.spiel.model.SpielStart;
import de.dhpoly.spieler.model.Spieler;

public class SpielerErstellerUI
{
	private Fenster fenster;

	private JTextArea txtName;

	private int spielerAnz = 1;

	public SpielerErstellerUI(Fenster fenster, NetzwerkServer server)
	{
		this.fenster = fenster;

		JPanel pnl = ElementFactory.erzeugePanel();
		pnl.setLayout(new GridLayout(4, 2, 10, 10));

		pnl.add(ElementFactory.getTextFeldUeberschrift("Name:"));
		String name = System.getProperties().getProperty("user.name");
		txtName = ElementFactory.getTextFeld(name, true);
		pnl.add(txtName);

		pnl.add(ElementFactory.getTextFeldUeberschrift("Lokalen Spieler hinzufügen:"));
		JButton butSpielLokal = ElementFactory.getButtonUeberschrift("Spieler hinzufügen");
		butSpielLokal.addActionListener(e -> spielerHinzuLokal());
		pnl.add(butSpielLokal);

		pnl.add(ElementFactory.getTextFeldUeberschrift("Computer Spieler hinzufügen:"));
		JButton butSpielPC = ElementFactory.getButtonUeberschrift("Spieler hinzufügen");
		butSpielPC.addActionListener(e -> spielerHinzuPC());
		pnl.add(butSpielPC);

		pnl.add(ElementFactory.getTextFeldUeberschrift("Spiel beginnen:"));
		JButton butStart = ElementFactory.getButtonUeberschrift("Spiel starten");
		butStart.addActionListener(e -> {
			server.empfange(new SpielStart(new Spieler("default")));
			fenster.loescheKomponente(pnl);
		});
		pnl.add(butStart);

		fenster.zeigeComponente(pnl, "+");
	}

	private void spielerHinzuLokal()
	{
		Spieler spieler = new Spieler(txtName.getText());
		SpielUI verwalter2 = new SpielUI(spieler, getClient());
		fenster.zeigeSpielansicht(verwalter2, txtName.getText());
		verwalter2.sendeAnServer(spieler);

		spielerAnz++;
		nameAendern();
	}

	private void spielerHinzuPC()
	{
		AI ai = new AI();
		ai.erzeugeComputerspieler(getClient(), txtName.getText());

		spielerAnz++;
		nameAendern();
	}

	private void nameAendern()
	{
		txtName.setText("Spieler " + spielerAnz);
	}

	private NetzwerkClient getClient()
	{
		return ClientFactory.CLIENT;
	}
}
