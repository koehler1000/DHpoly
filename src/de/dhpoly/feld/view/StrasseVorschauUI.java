package de.dhpoly.feld.view;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JPanel;

import de.dhpoly.feld.control.Strasse;
import de.dhpoly.fenster.view.Fenster;

public class StrasseVorschauUI extends JPanel
{
	private static final long serialVersionUID = 1L;

	public StrasseVorschauUI(Strasse strasse)
	{
		this.setLayout(new BorderLayout());

		Color farbe = new Strassengruppe().getColor(strasse.getGruppe());
		JButton lblName = Fenster.getButtonUeberschrift(strasse.getBeschriftung(), farbe);
		this.add(lblName);
	}
}
