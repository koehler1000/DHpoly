package de.dhpoly.spieler.view;

import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import de.dhpoly.fenster.view.Fenster;
import de.dhpoly.ressource.RessourcenDatensatz;
import de.dhpoly.spieler.Spieler;

public class KontoauszugUI extends JPanel
{
	private static final long serialVersionUID = 1L;

	public KontoauszugUI(Spieler spieler)
	{
		this.setLayout(new GridLayout(1, 1));

		JTextArea txtText = Fenster.getTextFeld("", false);
		JTextArea txtWert = Fenster.getTextFeld("", false);

		for (RessourcenDatensatz transaktion : spieler.getRessourcenTransaktionen())
		{
			txtText.setText(txtText.getText() + transaktion.getBeschreibung() + System.lineSeparator());
			txtWert.setText(txtWert.getText() + transaktion.getString() + System.lineSeparator());
		}

		JPanel pnlWerte = new JPanel();
		pnlWerte.setLayout(new GridLayout(1, 1));
		pnlWerte.add(txtText);
		pnlWerte.add(txtWert);

		this.add(new JScrollPane(pnlWerte));
	}
}
