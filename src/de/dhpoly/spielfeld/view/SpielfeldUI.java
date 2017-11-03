package de.dhpoly.spielfeld.view;

import java.awt.GridLayout;
import java.util.List;

import javax.swing.JPanel;

import de.dhpoly.feld.Feld;

public class SpielfeldUI extends JPanel
{
	public SpielfeldUI(List<Feld> spielfelder)
	{
		this.setLayout(new GridLayout(10, 10));
	}
}
