package de.dhpoly.feld.view;

import java.awt.Component;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

import de.dhpoly.feld.control.Ressourcenfeld;

public class RessourcenfeldUI extends JPanel implements Observer
{
	private static final long serialVersionUID = 1L;

	private Component pnlSpieler;

	private Ressourcenfeld feld;

	public RessourcenfeldUI(Ressourcenfeld feld)
	{
		this.feld = feld;
	}

	@Override
	public void update(Observable arg0, Object arg1)
	{
		update();
	}

	private void update()
	{
		this.remove(pnlSpieler);
		pnlSpieler = new FeldUI().getSpieler(feld.getSpielerAufFeld());
		this.add(pnlSpieler);
	}

}
