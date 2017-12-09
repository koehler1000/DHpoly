package de.dhpoly.spielfeld.view;

import java.awt.Component;
import java.awt.GridLayout;
import java.util.List;

import javax.swing.JPanel;

import de.dhpoly.feld.Feld;
import de.dhpoly.feld.control.Ereignisfeld;
import de.dhpoly.feld.control.Losfeld;
import de.dhpoly.feld.control.Ressourcenfeld;
import de.dhpoly.feld.control.Strasse;
import de.dhpoly.feld.view.EreignisfeldUI;
import de.dhpoly.feld.view.LosfeldUI;
import de.dhpoly.feld.view.RessourcenfeldUI;
import de.dhpoly.feld.view.StrasseUI;
import de.dhpoly.fenster.view.Fenster;

public class SpielfeldUI extends JPanel
{
	private static final long serialVersionUID = 1L;

	private int felderProSeite;

	public SpielfeldUI(List<Feld> spielfelder)
	{
		this.setBackground(Fenster.getDesignfarbe());

		felderProSeite = spielfelder.size() / 4;

		this.setLayout(new GridLayout(felderProSeite + 1, felderProSeite + 1, 1, 1));

		Component[][] felder = new Component[felderProSeite + 1][felderProSeite + 1];
		for (int i = 0; i < felderProSeite + 1; i++)
		{
			for (int j = 0; j < felderProSeite + 1; j++)
			{
				JPanel pnlLeer = new JPanel();
				pnlLeer.setBackground(Fenster.getDesignfarbe());
				felder[i][j] = pnlLeer;
			}
		}

		// Seite 1
		for (int i = 0; i < felderProSeite; i++)
		{
			Feld feld = spielfelder.get(i);
			felder[0][i] = getFeldUI(feld);
		}

		// Seite 2
		for (int i = 0; i < felderProSeite; i++)
		{
			Feld feld = spielfelder.get(i + felderProSeite);
			felder[i][felderProSeite] = getFeldUI(feld);
		}

		// Seite 3
		for (int i = 0; i < felderProSeite; i++)
		{
			Feld feld = spielfelder.get(i + felderProSeite * 2);
			felder[felderProSeite][felderProSeite - i] = getFeldUI(feld);
		}

		// Seite 4
		for (int i = 0; i < felderProSeite; i++)
		{
			Feld feld = spielfelder.get(i + felderProSeite * 3);
			felder[felderProSeite - i][0] = getFeldUI(feld);
		}

		// auf Panel malen
		for (int i = felderProSeite; i >= 0; i--)
		{
			for (int j = felderProSeite; j >= 0; j--)
			{
				this.add(felder[i][j]);
			}
		}
	}

	private Component getFeldUI(Feld feld)
	{
		if (feld instanceof Strasse)
		{
			return new StrasseUI((Strasse) feld);
		}
		else if (feld instanceof Ereignisfeld)
		{
			return new EreignisfeldUI((Ereignisfeld) feld);
		}
		else if (feld instanceof Ressourcenfeld)
		{
			return new RessourcenfeldUI((Ressourcenfeld) feld);
		}
		else if (feld instanceof Losfeld)
		{
			return new LosfeldUI((Losfeld) feld);
		}
		else
		{
			JPanel pnlInhalt = new JPanel();
			pnlInhalt.setBackground(Fenster.getDesignfarbe());
			return pnlInhalt;
		}
	}
}
