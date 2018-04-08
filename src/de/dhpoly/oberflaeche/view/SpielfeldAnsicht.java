package de.dhpoly.oberflaeche.view;

import java.awt.Component;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import de.dhpoly.oberflaeche.ElementFactory;

public class SpielfeldAnsicht extends JPanel
{
	private static final long serialVersionUID = 1L;

	private JButton butWeiter;
	private JTabbedPane tabRand;

	public SpielfeldAnsicht()
	{
		ElementFactory.bearbeitePanel(this);
		butWeiter = ElementFactory.getButton("Spiel beginnen");
		tabRand = ElementFactory.getTabbedPane();
	}

	public void zeigeAufRand(String beschreibung, Component component)
	{
		tabRand.addTab(beschreibung, component);
	}

	public void leereRand()
	{
		tabRand.removeAll();
	}

	public void setAnDerReihe(boolean value)
	{
		butWeiter.setEnabled(value);
	}
}
