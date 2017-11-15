package de.dhpoly.feld.view;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import de.dhpoly.feld.control.Strasse;
import de.dhpoly.spieler.Spieler;
import de.dhpoly.spieler.view.SpielerFarben;

public class StrasseInfoUI extends JPanel
{
	private static final long serialVersionUID = 1L;

	public StrasseInfoUI(Strasse strasse)
	{
		strasse.getEigentuemer().ifPresent(spieler -> farbeSetzen(spieler));

		this.setLayout(new GridLayout(3, 1));

		JLabel lblName = new JLabel(strasse.getName());
		// TODO Farbe der Gruppe
		this.add(lblName);

		this.add(new JTextArea(getMietenText(strasse.getMiete())));
	}

	private String getMietenText(int[] miete)
	{
		StringBuilder sb = new StringBuilder();

		for (int i : miete)
		{
			sb.append(i + System.lineSeparator());
		}

		return sb.toString();
	}

	private void farbeSetzen(Spieler spieler)
	{
		Color farbe = SpielerFarben.getSpielerfarbe(spieler.getSpielerNr());
		this.setBackground(farbe);
	}

}
