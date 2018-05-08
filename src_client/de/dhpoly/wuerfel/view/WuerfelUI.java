package de.dhpoly.wuerfel.view;

import javax.swing.JLabel;

import de.dhpoly.bilderverwalter.Bilderverwalter;
import de.dhpoly.oberflaeche.ElementFactory;
import de.dhpoly.oberflaeche.view.Oberflaeche;
import de.dhpoly.oberflaeche.view.SpielfeldAnsicht;
import de.dhpoly.wuerfel.model.WuerfelDaten;

public class WuerfelUI extends Oberflaeche // NOSONAR
{
	private static final long serialVersionUID = 1L;

	private JLabel lblWuerfelBild = new JLabel();

	private transient WuerfelDaten wuerfel;

	private transient Bilderverwalter bilderverwalter = new Bilderverwalter();

	public WuerfelUI(WuerfelDaten wuerfel, SpielfeldAnsicht ansicht)
	{
		super(ansicht);
		this.wuerfel = wuerfel;

		ElementFactory.bearbeitePanel(this);
		this.add(lblWuerfelBild);
		lblWuerfelBild.setIcon(bilderverwalter.getWuerfelBild(wuerfel.getWuerfel().get(0).getZahl()));
	}

	@Override
	public boolean isEinmalig()
	{
		return false;
	}
}
