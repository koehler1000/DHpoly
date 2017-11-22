package de.dhpoly.feld.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;

import org.omg.CosNaming.IstringHelper;

import de.dhpoly.feld.control.Strasse;
import de.dhpoly.ressource.RessourcenDatensatz;
import de.dhpoly.ressource.model.Ressource;
import de.dhpoly.spieler.Spieler;
import de.dhpoly.spieler.view.SpielerFarben;

public class StrasseInfoUI extends JPanel
{
	private static final long serialVersionUID = 1L;

	private JButton butName = new JButton();
	private JButton butBesitzer = new JButton();

	public StrasseInfoUI(Strasse feld)
	{
		this.setLayout(new BorderLayout());
		
		JPanel frameMieten = new JPanel();
		frameMieten.setLayout(new GridLayout(3, 1));
		
		Font font = new Font("arial", Font.PLAIN, 30);
		
		StringBuilder sb = new StringBuilder();
		sb.append("Aktuelle Miete: "+feld.getMiete()[feld.getHaueser()] + System.lineSeparator());
		sb.append("Ein Haus kostet: "+System.lineSeparator());
		for (int i = 0; i < getKostenHaus(feld).size(); i++) {
			sb.append(getKostenHaus(feld).get(i).getString() + System.lineSeparator());
		}
		JTextArea texMiete = new JTextArea(sb.toString());
		texMiete.setFont(font);
		frameMieten.add(texMiete);
		
		JTextArea texMieten = new JTextArea(getMietenText(feld.getMiete()));
		texMieten.setFont(font);
		frameMieten.add(texMieten);
		
		this.add(frameMieten, BorderLayout.CENTER);
		

		Color backcolor = new Strassengruppe().getColor(feld.getGruppe());

		butName.setText(feld.getBeschriftung());
		butName.setFont(new Font("arial", Font.BOLD, 30));
		butName.setBackground(backcolor);

		butBesitzer.setFont(new Font("arial", Font.BOLD, 30));
		butBesitzer.setBackground(Color.WHITE);

		this.add(butBesitzer, BorderLayout.SOUTH);
		this.add(butName, BorderLayout.NORTH);

		butName.addActionListener(e -> this.setVisible(false));

		feld.getEigentuemer().ifPresent(spieler -> farbeSetzen(spieler));
	}

	private String getMietenText(int[] miete)
	{
		StringBuilder sb = new StringBuilder();
		int z = 1;
		for (int i : miete)
		{
			String text = "Miete mit "+z+" Häusern: ";
			if(z == 1) {
				text = "Miete mit einem Haus: ";
			}
			sb.append(text + i + System.lineSeparator());
			z++;
		}
		return sb.toString();
	}

	private void farbeSetzen(Spieler spieler)
	{
		Color farbe = SpielerFarben.getSpielerfarbe(spieler.getSpielerNr());
		this.setBackground(farbe);
		this.setBorder(new LineBorder(farbe, 10));
	}

	private int getKostenHaus(Strasse strasse, Ressource ressource)
	{
		int anzahl = 0;
		for (RessourcenDatensatz datensatz : getKostenHaus(strasse))
		{
			if (datensatz.getRessource() == ressource)
			{
				anzahl += datensatz.getAnzahl();
			}
		}
		return anzahl;
	}

	private List<RessourcenDatensatz> getKostenHaus(Strasse strasse)
	{
		return strasse.getKostenHaus();
	}

	private int[] getMiete(Strasse strasse)
	{
		return strasse.getMiete();
	}
}
