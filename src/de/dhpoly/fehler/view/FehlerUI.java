package de.dhpoly.fehler.view;

import javax.swing.JOptionPane;

public class FehlerUI
{
	public FehlerUI(String fehler)
	{
		// TODO Fehler auf Oberfl�che anzeigen
		JOptionPane.showMessageDialog(null, fehler);
	}
}
