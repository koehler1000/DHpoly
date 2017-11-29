package de.dhpoly.handel.view;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import de.dhpoly.feld.Feld;
import de.dhpoly.feld.control.Strasse;
import de.dhpoly.spieler.Spieler;
import de.dhpoly.spieler.view.SpielerFarben;

public class StrassenAnbietenUI extends JPanel
{
	private static final long serialVersionUID = 1L;

	private List<Feld> ausgewaehlteStrassen = new ArrayList<>();

	public StrassenAnbietenUI(Spieler spieler)
	{
		Color farbe = SpielerFarben.getSpielerfarbe(spieler.getSpielerNr());
		this.setBorder(new LineBorder(farbe));

		for (Feld feld : spieler.getFelder())
		{
			if (feld instanceof Strasse)
			{
				this.add(new StrasseAnbietenUI((Strasse) feld, this));
			}
		}
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
