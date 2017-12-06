package de.dhpoly.fenster.view;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import de.dhpoly.bilderverwalter.Bilderverwalter;

public class Fenster extends JFrame
{
	private static final long serialVersionUID = 1L;

	private JPanel inhalt;

	public Fenster(JPanel pnlInhalt)
	{
		this.setTitle("");
		this.setIconImage(Bilderverwalter.getBild(Bilderverwalter.LOGO).getImage());
		this.setLayout(new BorderLayout());
		setInhalt(pnlInhalt);
		this.setLocationRelativeTo(null);
		this.setSize(500, 500);
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

	public Fenster(JPanel pnlInhalt, String string)
	{
		this(pnlInhalt);
		this.setTitle(string);
	}

	public Fenster()
	{
		this(new JPanel());
		this.setVisible(false);
	}

	public void setInhalt(JPanel pnl, String string)
	{
		setInhalt(pnl);
		this.setTitle(string);
		this.setVisible(true);
	}

	private void setInhalt(JPanel pnl)
	{
		if (inhalt != null)
		{
			this.remove(inhalt);
		}
		inhalt = pnl;
		this.add(pnl);
	}

	public void schliessen()
	{
		this.setVisible(false);
	}
}
