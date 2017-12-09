package de.dhpoly.wuerfel.view;

import java.util.Observable;
import java.util.Observer;

import javax.swing.JLabel;
import javax.swing.JPanel;

import de.dhpoly.bilderverwalter.Bilderverwalter;
import de.dhpoly.fenster.view.Fenster;
import de.dhpoly.wuerfel.Wuerfel;
import de.dhpoly.wuerfel.control.WuerfelImpl;

public class WuerfelUI extends JPanel implements Observer
{
	private static final long serialVersionUID = 1L;

	private JLabel lblWuerfelBild = new JLabel();

	private int wuerfelNr = 0;

	private Wuerfel wuerfel;

	public WuerfelUI(WuerfelImpl wuerfel, int wuerfelNr)
	{
		this.wuerfel = wuerfel;
		this.wuerfelNr = wuerfelNr;

		this.setBackground(Fenster.getDesignfarbe());
		this.add(lblWuerfelBild);

		wuerfel.addObserver(this);
		update();
	}

	@Override
	public void update(Observable o, Object arg)
	{
		update();
	}

	private void update()
	{
		if (wuerfelNr == 1)
		{
			lblWuerfelBild.setIcon(Bilderverwalter.getWuerfelBild(wuerfel.getWuerfelErgebnis1()));
		}
		else
		{
			lblWuerfelBild.setIcon(Bilderverwalter.getWuerfelBild(wuerfel.getWuerfelErgebnis2()));
		}
	}
}
