package de.dhpoly.kartenstapel.view;

import javax.swing.JOptionPane;

import de.dhpoly.karte.control.BezahlKarte;

public class KartenOberflaeche
{
	public KartenOberflaeche(BezahlKarte karte)
	{
		JOptionPane.showMessageDialog(null, karte.getBeschreibung());
	}
}