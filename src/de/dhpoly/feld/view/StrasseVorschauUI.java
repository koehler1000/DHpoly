package de.dhpoly.feld.view;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import de.dhpoly.feld.control.Strasse;

public class StrasseVorschauUI extends JPanel
{
	private static final long serialVersionUID = 1L;

	public StrasseVorschauUI(Strasse strasse)
	{
		this.setLayout(new BorderLayout());

		JLabel lblName = new JLabel(strasse.getName());
		Color farbe = new Strassengruppe().getColor(strasse.getGruppe());
		lblName.setBorder(new LineBorder(farbe, 10));
		lblName.setBackground(farbe);
		this.setBackground(farbe);

		this.add(lblName);
	}
}
