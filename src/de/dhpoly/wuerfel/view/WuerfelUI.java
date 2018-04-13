package de.dhpoly.wuerfel.view;

import javax.swing.JLabel;

import de.dhpoly.bilderverwalter.Bilderverwalter;
import de.dhpoly.oberflaeche.ElementFactory;
import de.dhpoly.oberflaeche.view.Oberflaeche;
import de.dhpoly.oberflaeche.view.SpielfeldAnsicht;
import de.dhpoly.wuerfel.Wuerfelpaar;
import de.dhpoly.wuerfel.control.WuerfelpaarImpl;
import observerpattern.Beobachter;

public class WuerfelUI extends Oberflaeche implements Beobachter // NOSONAR
{
	private static final long serialVersionUID = 1L;

	private JLabel lblWuerfelBild = new JLabel();

	private int wuerfelNr = 0;

	private transient Wuerfelpaar wuerfel;

	private transient Bilderverwalter bilderverwalter = new Bilderverwalter();

	public WuerfelUI(WuerfelpaarImpl wuerfel, int wuerfelNr, SpielfeldAnsicht ansicht)
	{
		super(ansicht);

		ElementFactory.bearbeitePanel(this);

		this.wuerfel = wuerfel;
		this.wuerfelNr = wuerfelNr;

		this.add(lblWuerfelBild);

		wuerfel.addBeobachter(this);
		update();
	}

	@Override
	public void update()
	{
		if (wuerfelNr == 1)
		{
			lblWuerfelBild.setIcon(bilderverwalter.getWuerfelBild(wuerfel.getWuerfelErgebnis1()));
		}
		else
		{
			lblWuerfelBild.setIcon(bilderverwalter.getWuerfelBild(wuerfel.getWuerfelErgebnis2()));
		}
	}
}
