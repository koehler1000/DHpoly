package de.dhpoly.spieler.view;

import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;
import javax.swing.JTextArea;

import de.dhpoly.spieler.Spieler;

public class Konto extends JPanel implements Observer
{
	private static final long serialVersionUID = 1L;

	private Spieler spieler;
	private JTextArea txtKontostand;

	public Konto(Spieler spieler)
	{
		this.spieler = spieler;
		txtKontostand = new JTextArea(spieler.getBargeld() + "€");

		this.add(txtKontostand);
	}

	@Override
	public void update(Observable o, Object arg)
	{
		txtKontostand.setText(spieler.getBargeld() + "€");
	}
}
