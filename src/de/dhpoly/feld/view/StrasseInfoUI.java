package de.dhpoly.feld.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;

import de.dhpoly.feld.control.Strasse;
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

		this.add(new JTextArea(getMietenText(feld.getMiete())), BorderLayout.CENTER);

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
		this.setBorder(new LineBorder(farbe, 10));
	}
}
