package de.dhpoly.fehler.view;

import javax.swing.JOptionPane;

public class FehlerUI
{
	public FehlerUI(String fehler)
	{
		// TODO Fehler auf Oberfläche anzeigen
		JOptionPane.showMessageDialog(null, fehler);
	}
}
