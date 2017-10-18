package de.dhpoly.kartenstapel.view;

import javax.swing.JOptionPane;
import de.dhpoly.kartenstapel.model.Karte;

public class KartenOberflaeche
{
	public KartenOberflaeche(Karte karte)
	{
		JOptionPane.showMessageDialog(null, karte.getBeschreibung());
	}
}