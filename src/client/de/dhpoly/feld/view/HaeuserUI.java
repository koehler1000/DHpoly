package de.dhpoly.feld.view;

import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;

import de.dhpoly.feld.model.StrasseDaten;
import de.dhpoly.oberflaeche.ElementFactory;
import de.dhpoly.oberflaeche.view.Oberflaeche;
import de.dhpoly.oberflaeche.view.SpielUI;

public class HaeuserUI extends Oberflaeche // NOSONAR
{
	private static final long serialVersionUID = 1L;

	public HaeuserUI(List<StrasseDaten> felder, SpielUI ansicht)
	{
		super(ansicht);

		JPanel pnlInhalt = ElementFactory.erzeugePanel();

		if (felder.isEmpty())
		{
			JButton butKeineStrassen = ElementFactory.getButton("Noch keine Stra�en im Besitz");
			butKeineStrassen.addActionListener(e -> schliessen());
			pnlInhalt.add(butKeineStrassen);
		}
		else
		{
			pnlInhalt.setLayout(new GridLayout(felder.size(), 1, 10, 10));

			for (StrasseDaten strasse : felder)
			{
				pnlInhalt.add(new HausUI(strasse, ansicht));
			}
		}
		this.add(pnlInhalt);
	}

	@Override
	public boolean isInvalideBeiSpielerWechsel()
	{
		return false;
	}

	@Override
	public void zeige(String beschreibung)
	{
		zeigeLinks(beschreibung);
	}

	@Override
	public List<Oberflaeche> durchHinzufuegenUngueltigWerdend(List<Oberflaeche> oberflaechen)
	{
		List<Oberflaeche> ret = new ArrayList<>();
		oberflaechen.stream().filter(e -> (e instanceof HaeuserUI)).forEach(ret::add);
		return ret;
	}
}
