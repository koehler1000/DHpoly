package de.dhpoly.wuerfel.view;

import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.JTextArea;

public class WuerfelUI extends JPanel
{
	private static final long serialVersionUID = 1L;

	public WuerfelUI(int ergebnis1, int ergebnis2)
	{
		this.setLayout(new GridLayout(1, 2, 10, 10));

		this.add(getWuerfel(ergebnis1));
		this.add(getWuerfel(ergebnis2));
	}

	private Component getWuerfel(int nummer)
	{
		JTextArea wuerfel = new JTextArea();
		wuerfel.setFont(new Font("arial", Font.BOLD, 30));
		wuerfel.setEditable(false);
		wuerfel.setText("" + nummer);
		return wuerfel;
	}
}
