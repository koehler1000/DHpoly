package de.dhpoly.wuerfel.view;

import javax.swing.JLabel;

import de.dhpoly.bilderverwalter.Bilderverwalter;
import de.dhpoly.oberflaeche.ElementFactory;
import de.dhpoly.oberflaeche.view.Oberflaeche;
import de.dhpoly.oberflaeche.view.SpielfeldAnsicht;
import de.dhpoly.wuerfel.model.Wuerfel;
import observerpattern.Beobachter;

public class WuerfelUI extends Oberflaeche implements Beobachter // NOSONAR
{
	private static final long serialVersionUID = 1L;

	private JLabel lblWuerfelBild = new JLabel();

	private transient Wuerfel wuerfel;

	private transient Bilderverwalter bilderverwalter = new Bilderverwalter();

	public WuerfelUI(Wuerfel wuerfel, SpielfeldAnsicht ansicht)
	{
		super(ansicht);
		this.wuerfel = wuerfel;

		ElementFactory.bearbeitePanel(this);
		this.add(lblWuerfelBild);

		update();
	}

	@Override
	public void update()
	{
		lblWuerfelBild.setIcon(bilderverwalter.getWuerfelBild(wuerfel.getZahl()));
	}

	@Override
	public boolean isEinmalig()
	{
		return true;
	}
}
