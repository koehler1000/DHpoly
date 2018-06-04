package de.dhpoly.feld.view;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JPanel;

import de.dhpoly.feld.model.StrasseDaten;
import de.dhpoly.oberflaeche.ElementFactory;

public class StrasseVorschauUI extends JPanel
{
	private static final long serialVersionUID = 1L;

	public StrasseVorschauUI(StrasseDaten str)
	{
		this.setLayout(new BorderLayout());

		Color farbe = new Strassengruppe().getColor(str.getGruppe());
		JButton butName = ElementFactory.getButtonUeberschrift(str.getName(), farbe);
		this.add(butName);
	}
}
