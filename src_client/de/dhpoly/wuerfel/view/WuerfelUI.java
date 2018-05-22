package de.dhpoly.wuerfel.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;

import de.dhpoly.bilderverwalter.Bilderverwalter;
import de.dhpoly.oberflaeche.ElementFactory;
import de.dhpoly.oberflaeche.view.Oberflaeche;
import de.dhpoly.oberflaeche.view.SpielUI;
import de.dhpoly.wuerfel.model.Wuerfel;
import de.dhpoly.wuerfel.model.WuerfelDaten;

public class WuerfelUI extends Oberflaeche // NOSONAR
{
	private static final long serialVersionUID = 1L;

	private transient Bilderverwalter bilderverwalter = new Bilderverwalter();

	public WuerfelUI(WuerfelDaten wuerfel, SpielUI ansicht)
	{
		super(ansicht, 3);

		JPanel pnlInhalt = ElementFactory.erzeugePanel();
		pnlInhalt.setLayout(new GridLayout(wuerfel.getWuerfel().size(), 1));

		List<JLabel> lblsWuerfel = new ArrayList<>();

		for (Wuerfel w : wuerfel.getWuerfel())
		{
			JLabel lblW = new JLabel();
			lblW.setIcon(bilderverwalter.getWuerfelBild(w.getZahl()));
			lblsWuerfel.add(lblW);
		}

		lblsWuerfel.forEach(pnlInhalt::add);
		this.add(pnlInhalt, BorderLayout.CENTER);
	}

	@Override
	public boolean isInvalideBeiSpielerWechsel()
	{
		return true;
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
		oberflaechen.stream().filter(e -> (e instanceof WuerfelUI)).forEach(ret::add);
		return ret;
	}
}
