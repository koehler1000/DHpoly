package de.dhpoly.wuerfel.view;

import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;

import de.dhpoly.bilderverwalter.Bilderverwalter;
import de.dhpoly.oberflaeche.ElementFactory;
import de.dhpoly.oberflaeche.view.Oberflaeche;
import de.dhpoly.oberflaeche.view.SpielfeldAnsicht;
import de.dhpoly.wuerfel.model.Wuerfel;
import de.dhpoly.wuerfel.model.WuerfelDaten;

public class WuerfelUI extends Oberflaeche // NOSONAR
{
	private static final long serialVersionUID = 1L;

	private transient Bilderverwalter bilderverwalter = new Bilderverwalter();

	public WuerfelUI(WuerfelDaten wuerfel, SpielfeldAnsicht ansicht)
	{
		super(ansicht);

		ElementFactory.bearbeitePanel(this);
		this.setLayout(new GridLayout(1, 1));

		List<JLabel> lblsWuerfel = new ArrayList<>();

		for (Wuerfel w : wuerfel.getWuerfel())
		{
			JLabel lblW = new JLabel();
			lblW.setIcon(bilderverwalter.getWuerfelBild(w.getZahl()));
			lblsWuerfel.add(lblW);
		}

		lblsWuerfel.forEach(this::add);
	}

	@Override
	public boolean isInvalideBeiSpielerWechsel()
	{
		return false;
	}
}
