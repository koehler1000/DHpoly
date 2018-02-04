package de.dhpoly.handel.view;

import java.awt.Color;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.LineBorder;

import de.dhpoly.feld.Feld;
import de.dhpoly.feld.control.Strasse;
import de.dhpoly.spieler.Spieler;
import de.dhpoly.spieler.view.SpielerFarben;

public class StrassenAnbietenUI extends JPanel
{
	private static final long serialVersionUID = 1L;

	private List<Feld> ausgewaehlteStrassen = new ArrayList<>();

	public StrassenAnbietenUI(Spieler spieler, List<Feld> ausgewaehlte)
	{
		this.setBackground(Color.WHITE);
		this.setLayout(new GridLayout());

		for (Feld feld : ausgewaehlte)
		{
			if (feld instanceof Strasse)
			{
				Strasse strasse = (Strasse) feld;
				if (strasse.gehoertSpieler(spieler))
				{
					ausgewaehlteStrassen.add(strasse);
				}
			}
		}

		Color farbe = SpielerFarben.getSpielerfarbe(spieler.getSpielerNr());
		this.setBorder(new LineBorder(farbe));

		JPanel pnlStrassen = new JPanel(new GridLayout(ausgewaehlteStrassen.size(), 1));
		pnlStrassen.setBackground(Color.WHITE);

		for (Feld feld : spieler.getFelder())
		{
			if (feld instanceof Strasse)
			{
				pnlStrassen.add(new StrasseAnbietenUI((Strasse) feld, this, ausgewaehlteStrassen.contains(feld)));
			}
		}

		this.add(new JScrollPane(pnlStrassen));
	}

	void feldAuswaehlen(Feld feld)
	{
		ausgewaehlteStrassen.add(feld);
	}

	void feldAuswaehlenRueckgaengig(Feld feld)
	{
		ausgewaehlteStrassen.remove(feld);
	}

	public List<Feld> getStrassen()
	{
		return ausgewaehlteStrassen;
	}
}
