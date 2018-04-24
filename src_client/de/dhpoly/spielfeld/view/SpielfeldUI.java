package de.dhpoly.spielfeld.view;

import java.awt.Component;
import java.awt.GridLayout;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.JPanel;

import de.dhpoly.feld.Feld;
import de.dhpoly.feld.control.FeldEreignis;
import de.dhpoly.feld.control.FeldLos;
import de.dhpoly.feld.control.FeldRessource;
import de.dhpoly.feld.control.FeldStrasse;
import de.dhpoly.feld.model.Strasse;
import de.dhpoly.feld.view.EreignisfeldUI;
import de.dhpoly.feld.view.LosfeldUI;
import de.dhpoly.feld.view.RessourcenfeldUI;
import de.dhpoly.feld.view.StrasseUI;
import de.dhpoly.oberflaeche.ElementFactory;
import de.dhpoly.oberflaeche.view.Oberflaeche;
import de.dhpoly.oberflaeche.view.SpielfeldAnsicht;

public class SpielfeldUI extends Oberflaeche // NOSONAR
{
	private static final long serialVersionUID = 1L;

	private int felderProSeite;

	private Map<FeldStrasse, StrasseUI> strassen = new HashMap<>();

	public SpielfeldUI(List<Feld> spielfelder, SpielfeldAnsicht ansicht)
	{
		super(ansicht);

		felderProSeite = spielfelder.size() / 4;

		this.setLayout(new GridLayout(felderProSeite + 1, felderProSeite + 1, 1, 1));

		Component[][] felder = new Component[felderProSeite + 1][felderProSeite + 1];
		for (int i = 0; i < felderProSeite + 1; i++)
		{
			for (int j = 0; j < felderProSeite + 1; j++)
			{
				JPanel pnlLeer = ElementFactory.erzeugePanel();
				felder[i][j] = pnlLeer;
			}
		}

		// Seite 1
		for (int i = 0; i < felderProSeite; i++)
		{
			Feld feld = spielfelder.get(i);
			felder[0][i] = getFeldUI(feld, ansicht);
		}

		// Seite 2
		for (int i = 0; i < felderProSeite; i++)
		{
			Feld feld = spielfelder.get(i + felderProSeite);
			felder[i][felderProSeite] = getFeldUI(feld, ansicht);
		}

		// Seite 3
		for (int i = 0; i < felderProSeite; i++)
		{
			Feld feld = spielfelder.get(i + felderProSeite * 2);
			felder[felderProSeite][felderProSeite - i] = getFeldUI(feld, ansicht);
		}

		// Seite 4
		for (int i = 0; i < felderProSeite; i++)
		{
			Feld feld = spielfelder.get(i + felderProSeite * 3);
			felder[felderProSeite - i][0] = getFeldUI(feld, ansicht);
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

	private Component getFeldUI(Feld feld, SpielfeldAnsicht ansicht)
	{
		if (feld instanceof FeldStrasse)
		{
			StrasseUI ui = new StrasseUI((FeldStrasse) feld, ansicht);
			strassen.put((FeldStrasse) feld, ui);
			return ui;
		}
		else if (feld instanceof FeldEreignis)
		{
			return new EreignisfeldUI((FeldEreignis) feld, ansicht);
		}
		else if (feld instanceof FeldRessource)
		{
			return new RessourcenfeldUI((FeldRessource) feld, ansicht);
		}
		else if (feld instanceof FeldLos)
		{
			return new LosfeldUI((FeldLos) feld, ansicht);
		}
		else
		{
			return ElementFactory.erzeugePanel();
		}
	}

	public void aktualisiere(Strasse objekt)
	{
		for (Entry<FeldStrasse, StrasseUI> st : strassen.entrySet())
		{
			if (st.getKey().getStrasse() == objekt)
			{
				st.getValue().update();
			}
		}
	}
}
