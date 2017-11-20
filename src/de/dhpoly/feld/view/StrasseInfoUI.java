package de.dhpoly.feld.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;

import de.dhpoly.feld.control.Strasse;
import de.dhpoly.spieler.Spieler;
import de.dhpoly.spieler.view.SpielerFarben;

public class StrasseInfoUI extends JPanel
{
	private static final long serialVersionUID = 1L;

	private JTextPane txtName = new JTextPane();
	private JTextPane txtBesitzer = new JTextPane();

	public StrasseInfoUI(Strasse feld)
	{
		feld.getEigentuemer().ifPresent(spieler -> farbeSetzen(spieler));

		this.setLayout(new BorderLayout());

		this.add(new JTextArea(getMietenText(feld.getMiete())), BorderLayout.CENTER);

		Color backcolor = new Strassengruppe().getColor(feld.getGruppe());

		txtName.setText(feld.getBeschriftung());
		txtName.setEditable(false);
		txtName.setFont(new Font("arial", Font.BOLD, 30));
		txtName.setBackground(backcolor);

		txtBesitzer.setEditable(false);
		txtBesitzer.setFont(new Font("arial", Font.BOLD, 30));
		txtBesitzer.setBackground(Color.WHITE);

		StyleContext.NamedStyle centerStyle = StyleContext.getDefaultStyleContext().new NamedStyle();
		StyleConstants.setAlignment(centerStyle, StyleConstants.ALIGN_CENTER);
		txtName.setLogicalStyle(centerStyle);
		txtBesitzer.setLogicalStyle(centerStyle);

		this.add(txtBesitzer, BorderLayout.SOUTH);
		this.add(txtName, BorderLayout.NORTH);

		this.setBorder(BorderFactory.createLineBorder(Color.BLACK));

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
