package de.dhpoly.fehler.view;

import javax.swing.JPanel;

import de.dhpoly.oberflaeche.ElementFactory;

public class FehlerUI extends JPanel
{
	private static final long serialVersionUID = 1L;

	public FehlerUI(String fehler)
	{
		ElementFactory.bearbeitePanel(this);
		this.add(ElementFactory.getNachrichtPanel("Fehler", fehler));
	}
}
