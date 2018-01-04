package de.dhpoly.fenster.view;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

public class BildHTML extends JPanel
{
	private static String RESSOURCE_HOLZ = "https://raw.githubusercontent.com/koehler1000/DHpoly/master/pics/default/ressourcen/HOLZ.png";

	private static final long serialVersionUID = 2L;
	private JButton butBild = new JButton("");

	public BildHTML(String pfad)
	{
		butBild.setText(getHTMLBild(pfad));
		this.setLayout(new GridLayout(1, 1));
		this.add(butBild);
	}

	private String getHTMLBild(String pfad)
	{
		char anfuehrungszeichen = '"';
		return "<html><img src=" + // html
				anfuehrungszeichen + pfad + anfuehrungszeichen + // src
				" alt = " + anfuehrungszeichen + pfad + anfuehrungszeichen + // alternative Anzeige
				" width=" + anfuehrungszeichen + "50px" + anfuehrungszeichen + " /></html>";
	}

	public void setBildPfad(String pfad)
	{
		butBild.setText(getHTMLBild(pfad));
	}
}
