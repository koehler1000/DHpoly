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
		this.setLayout(new BorderLayout());

		JPanel wu = ElementFactory.erzeugePanel();
		wu.setLayout(new GridLayout(wuerfel.getWuerfel().size(), 1));

		List<JLabel> lblsWuerfel = new ArrayList<>();

		for (Wuerfel w : wuerfel.getWuerfel())
		{
			JLabel lblW = new JLabel();
			lblW.setIcon(bilderverwalter.getWuerfelBild(w.getZahl()));
			lblsWuerfel.add(lblW);
		}

		lblsWuerfel.forEach(wu::add);
		this.add(wu, BorderLayout.CENTER);

		this.add(getSchliessenButton(), BorderLayout.SOUTH);
	}

	@Override
	public boolean isInvalideBeiSpielerWechsel()
	{
		return false;
	}
}
