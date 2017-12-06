package de.dhpoly.feld.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.util.Optional;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;

import de.dhpoly.feld.control.Strasse;
import de.dhpoly.fenster.view.Fenster;
import de.dhpoly.ressource.RessourcenDatensatz;
import de.dhpoly.ressource.model.Ressource;
import de.dhpoly.spieler.Spieler;
import de.dhpoly.spieler.view.SpielerFarben;

public class StrasseInfoUI extends JPanel
{
	private static final long serialVersionUID = 1L;

	private JButton butName = new JButton();
	private JButton butBesitzer = new JButton();

	public StrasseInfoUI(Strasse feld, Fenster fenster)
	{
		this.setLayout(new BorderLayout());

		JPanel frameMieten = new JPanel();
		frameMieten.setLayout(new GridLayout(2, 1));

		StringBuilder sb = new StringBuilder();
		sb.append("Aktuelle Miete: " + feld.getAkuelleMiete() + System.lineSeparator());
		sb.append("Ein Haus kostet: " + System.lineSeparator());
		for (RessourcenDatensatz datensatz : feld.getKostenHaus())
		{
			sb.append(datensatz.getString() + System.lineSeparator());
		}

		JTextArea txtMiete = new JTextArea(sb.toString());
		txtMiete.setFont(Fenster.getStandardFont());
		frameMieten.add(txtMiete);

		JTextArea txtMieten = new JTextArea(getMietenText(feld.getMiete()));
		txtMieten.setFont(Fenster.getStandardFont());
		frameMieten.add(txtMieten);

		this.add(frameMieten, BorderLayout.CENTER);

		Color backcolor = new Strassengruppe().getColor(feld.getGruppe());

		butName.setText(feld.getBeschriftung());
		butName.setFont(Fenster.getUeberschriftFont());
		butName.setBackground(backcolor);

		butBesitzer.setText(getEigentuemerString(feld));
		butBesitzer.setFont(Fenster.getUeberschriftFont());
		butBesitzer.setBackground(Color.WHITE);

		this.add(butBesitzer, BorderLayout.SOUTH);
		this.add(butName, BorderLayout.NORTH);

		butName.addActionListener(e -> Optional.ofNullable(fenster).ifPresent(f -> f.schliessen()));
		butBesitzer.addActionListener(e -> Optional.ofNullable(fenster).ifPresent(f -> f.schliessen()));

		this.setBorder(new LineBorder(Color.WHITE, 10));
		feld.getEigentuemer().ifPresent(spieler -> farbeSetzen(spieler));
	}

	private String getEigentuemerString(Strasse feld)
	{
		if (feld.getEigentuemer().isPresent())
		{
			return feld.getEigentuemer().get().getName();
		}
		return "Zu kaufen";
	}

	private String getMietenText(int[] miete)
	{
		StringBuilder sb = new StringBuilder();
		int z = 0;
		for (int i : miete)
		{
			String text = "Miete mit " + z + " Häusern: ";
			if (z == 0)
			{
				text = "Miete ohne Häuser: ";
			}
			else if (z == 1)
			{
				text = "Miete mit einem Haus: ";
			}
			sb.append(text + Ressource.GELD.getString(i) + System.lineSeparator());
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
}
