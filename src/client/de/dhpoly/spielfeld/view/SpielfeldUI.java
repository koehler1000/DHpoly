package de.dhpoly.spielfeld.view;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

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
import de.dhpoly.oberflaeche.view.SpielUI;
import de.dhpoly.spiel.model.SpielfeldDaten;
import de.dhpoly.spiel.view.SpielstartUI;
import de.dhpoly.spielfeld.model.RessourcenfeldDaten;

public class SpielfeldUI extends Oberflaeche // NOSONAR
{
	private static final long serialVersionUID = 1L;

	private Map<StrasseDaten, StrasseUI> strassen = new HashMap<>();

	private JTabbedPane tabPane;

	public SpielfeldUI(SpielfeldDaten spielfelder, SpielUI ansicht)
	{
		super(ansicht);

		JPanel pnlContent = ElementFactory.erzeugePanel();
		int felderProSeite = spielfelder.size() / 4;
		pnlContent.setLayout(new GridBagLayout());

		GridBagConstraints c = new GridBagConstraints();
		c.weightx = 1;
		c.weighty = 1;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.fill = GridBagConstraints.BOTH;

		for (FeldDaten feld : spielfelder.getFelder())
		{
			c.gridx = spielfelder.getX(feld);
			c.gridy = spielfelder.getY(feld);
			pnlContent.add(getFeldUI(feld, ansicht), c);
		}

		c.gridheight = felderProSeite - 1;
		c.gridwidth = felderProSeite - 1;
		c.gridx = 1;
		c.gridy = 1;

		tabPane = ElementFactory.getTabbedPane();
		pnlContent.add(tabPane, c);

		this.add(pnlContent);
		this.remove(getSchliessenButton());
	}

	private Component getFeldUI(FeldDaten feld, SpielUI ansicht)
	{
		if (feld instanceof StrasseDaten)
		{
			StrasseUI ui = new StrasseUI((StrasseDaten) feld, ansicht);
			strassen.put((StrasseDaten) feld, ui);
			return ui;
		}
		if (feld instanceof EreignisfeldDaten)
		{
			return new EreignisfeldUI((EreignisfeldDaten) feld, ansicht);
		}
		if (feld instanceof RessourcenfeldDaten)
		{
			return new RessourcenfeldUI((RessourcenfeldDaten) feld, ansicht);
		}
		if (feld instanceof LosfeldDaten)
		{
			return new LosfeldUI((LosfeldDaten) feld, ansicht);
		}
		return ElementFactory.erzeugePanel();
	}

	@Override
	public boolean isInvalideBeiSpielerWechsel()
	{
		return false;
	}

	@Override
	public void zeige(String beschreibung)
	{
		zeigeMitte(beschreibung);
	}

	@Override
	public List<Oberflaeche> durchHinzufuegenUngueltigWerdend(List<Oberflaeche> oberflaechen)
	{
		List<Oberflaeche> ret = new ArrayList<>();
		oberflaechen.stream().filter(e -> (e instanceof SpielfeldUI || e instanceof SpielstartUI)).forEach(ret::add);
		return ret;
	}

	public void addTab(String titel, Component inhalt)
	{
		tabPane.add(titel, inhalt);
	}

	public void removeTab(Component inhalt)
	{
		tabPane.remove(inhalt);
	}
}
