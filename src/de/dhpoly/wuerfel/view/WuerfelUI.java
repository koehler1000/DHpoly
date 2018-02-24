package de.dhpoly.wuerfel.view;

import javax.swing.JLabel;
import javax.swing.JPanel;

import de.dhpoly.bilderverwalter.Bilderverwalter;
import de.dhpoly.fenster.view.Fenster;
import de.dhpoly.wuerfel.Wuerfel;
import de.dhpoly.wuerfel.control.WuerfelImpl;
import observerpattern.Beobachter;

public class WuerfelUI extends JPanel implements Beobachter
{
	private static final long serialVersionUID = 1L;

	private JLabel lblWuerfelBild = new JLabel();

	private int wuerfelNr = 0;

	private transient Wuerfel wuerfel;

	private transient Bilderverwalter bilderverwalter = new Bilderverwalter();

	public WuerfelUI(WuerfelImpl wuerfel, int wuerfelNr)
	{
		this.wuerfel = wuerfel;
		this.wuerfelNr = wuerfelNr;

		this.setBackground(Fenster.getDesignfarbe());
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
