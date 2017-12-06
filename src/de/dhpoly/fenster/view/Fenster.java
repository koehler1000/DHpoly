package de.dhpoly.fenster.view;

import javax.swing.JFrame;
import javax.swing.JPanel;

import de.dhpoly.bilderverwalter.Bilderverwalter;

public class Fenster extends JFrame
{
	private static final long serialVersionUID = 1L;

	public Fenster(JPanel pnlInhalt)
	{
		this.setTitle("");
		this.setIconImage(Bilderverwalter.getBild(Bilderverwalter.LOGO).getImage());
		this.add(pnlInhalt);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.setVisible(true);
	}

	public Fenster(JPanel pnlInhalt, boolean hauptfenster)
	{
		this(pnlInhalt);
		if (hauptfenster)
		{
			this.setTitle("DHPoly");
			this.setIconImage(Bilderverwalter.getBild(Bilderverwalter.LOGO).getImage());
			this.setExtendedState(JFrame.MAXIMIZED_BOTH);
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		}
	}

	public void schliessen()
	{
		this.setVisible(false);
	}
}
