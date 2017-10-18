package de.dhpoly.kartenstapel.view;

import javax.swing.JPanel;
import javax.swing.JTextArea;

import de.dhpoly.kartenstapel.model.Karte;

public class KartenOberflaeche extends JPanel
{
	private static final long serialVersionUID = 1L;

	public KartenOberflaeche(Karte karte)
	{
		JTextArea txtBeschreibung = new JTextArea(karte.getBeschreibung());
		this.add(txtBeschreibung);
		txtBeschreibung.setLineWrap(true);
		txtBeschreibung.setEditable(false);
	}
}
