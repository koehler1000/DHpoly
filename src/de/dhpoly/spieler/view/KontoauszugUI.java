package de.dhpoly.spieler.view;

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
		JTextArea txtTransaktionen = new JTextArea();
		for (RessourcenDatensatz transaktion : spieler.getRessourcenTransaktionen())
		{
			txtTransaktionen.setText(transaktion.getString());
		}
		this.add(new JScrollPane(txtTransaktionen));
	}
}
