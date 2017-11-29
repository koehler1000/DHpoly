package de.dhpoly.spieler.view;

import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import de.dhpoly.ressource.RessourcenDatensatz;
import de.dhpoly.spieler.Spieler;

public class KontoauszugUI extends JPanel
{
	private static final long serialVersionUID = 1L;

	public KontoauszugUI(Spieler spieler)
	{
		this.setLayout(new GridLayout(1, 1));

		JTextArea txtTransaktionen = new JTextArea();
		for (RessourcenDatensatz transaktion : spieler.getRessourcenTransaktionen())
		{
			String beschreibung = "";
			if (!transaktion.getBeschreibung().isEmpty())
			{
				beschreibung = "(" + transaktion.getBeschreibung() + ") ";
			}
			txtTransaktionen.setText(
					beschreibung + txtTransaktionen.getText() + transaktion.getString() + System.lineSeparator());
		}
		this.add(new JScrollPane(txtTransaktionen));
	}
}
