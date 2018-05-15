package de.dhpoly.spielfeld.view;

import java.awt.Component;
import java.awt.GridLayout;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JPanel;

import de.dhpoly.feld.model.EreignisfeldDaten;
import de.dhpoly.feld.model.FeldDaten;
import de.dhpoly.feld.model.LosfeldDaten;
import de.dhpoly.feld.model.StrasseDaten;
import de.dhpoly.feld.view.EreignisfeldUI;
import de.dhpoly.feld.view.LosfeldUI;
import de.dhpoly.feld.view.RessourcenfeldUI;
import de.dhpoly.feld.view.StrasseUI;
import de.dhpoly.oberflaeche.ElementFactory;
import de.dhpoly.oberflaeche.view.Oberflaeche;
import de.dhpoly.oberflaeche.view.SpielfeldAnsicht;
import de.dhpoly.spiel.model.SpielfeldDaten;
import de.dhpoly.spielfeld.model.RessourcenfeldDaten;

public class SpielfeldUI extends Oberflaeche // NOSONAR
{
	private static final long serialVersionUID = 1L;

	private int felderProSeite;

	private Map<StrasseDaten, StrasseUI> strassen = new HashMap<>();

	public SpielfeldUI(SpielfeldDaten spielfelder, SpielfeldAnsicht ansicht)
	{
		super(ansicht);

		JPanel pnlContent = ElementFactory.erzeugePanel();
		felderProSeite = spielfelder.size() / 4;
		pnlContent.setLayout(new GridLayout(felderProSeite + 1, felderProSeite + 1, 1, 1));

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
			FeldDaten feld = spielfelder.get(i);
			felder[0][i] = getFeldUI(feld, ansicht);
		}

		// Seite 2
		for (int i = 0; i < felderProSeite; i++)
		{
			FeldDaten feld = spielfelder.get(i + felderProSeite);
			felder[i][felderProSeite] = getFeldUI(feld, ansicht);
		}

		// Seite 3
		for (int i = 0; i < felderProSeite; i++)
		{
			FeldDaten feld = spielfelder.get(i + felderProSeite * 2);
			felder[felderProSeite][felderProSeite - i] = getFeldUI(feld, ansicht);
		}

		// Seite 4
		for (int i = 0; i < felderProSeite; i++)
		{
			FeldDaten feld = spielfelder.get(i + felderProSeite * 3);
			felder[felderProSeite - i][0] = getFeldUI(feld, ansicht);
		}

		// auf Panel malen
		for (int i = felderProSeite; i >= 0; i--)
		{
			for (int j = felderProSeite; j >= 0; j--)
			{
				pnlContent.add(felder[i][j]);
			}
		}

		this.add(pnlContent);
		this.remove(getSchliessenButton());
	}

	private Component getFeldUI(FeldDaten feld, SpielfeldAnsicht ansicht)
	{
		if (feld instanceof StrasseDaten)
		{
			StrasseUI ui = new StrasseUI((StrasseDaten) feld, ansicht);
			strassen.put((StrasseDaten) feld, ui);
			return ui;
		}
		else if (feld instanceof EreignisfeldDaten)
		{
			return new EreignisfeldUI((EreignisfeldDaten) feld, ansicht);
		}
		else if (feld instanceof RessourcenfeldDaten)
		{
			return new RessourcenfeldUI((RessourcenfeldDaten) feld, ansicht);
		}
		else if (feld instanceof LosfeldDaten)
		{
			return new LosfeldUI((LosfeldDaten) feld, ansicht);
		}
		else
		{
			return ElementFactory.erzeugePanel();
		}
	}

	@Override
	public boolean isInvalideBeiSpielerWechsel()
	{
		return false;
	}
}
