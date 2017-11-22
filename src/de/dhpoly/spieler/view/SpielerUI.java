package de.dhpoly.spieler.view;

import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import de.dhpoly.ressource.model.Ressource;
import de.dhpoly.spieler.Spieler;
import de.dhpoly.spieler.control.SpielerImpl;
import observerpattern.Beobachter;

public class SpielerUI extends JPanel implements Beobachter
{
	private static final long serialVersionUID = 1L;

	private Spieler spieler;
	private JTextArea txtKontostand;
	private JTextArea txtName;

	public SpielerUI(Spieler spieler)
	{
		this.spieler = spieler;

		Color backcolor = SpielerFarben.getSpielerfarbe(spieler.getSpielerNr());

		txtKontostand = new JTextArea(spieler.getRessourcenWerte(Ressource.GELD) + "€");
		txtKontostand.setEditable(false);
		txtKontostand.setFont(new Font("arial", Font.BOLD, 30));
		txtKontostand.setBackground(backcolor);

		txtName = new JTextArea(spieler.getName() + ": ");
		txtName.setEditable(false);
		txtName.setFont(new Font("arial", Font.BOLD, 30));
		txtName.setBackground(backcolor);

		this.add(txtName);
		this.add(txtKontostand);

		this.setBackground(backcolor);
		this.setBorder(BorderFactory.createLineBorder(Color.BLACK));

		update();

		SpielerImpl spielerImpl = (SpielerImpl) spieler;
		spielerImpl.addBeobachter(this);
	}

	@Override
	public void update()
	{
		txtKontostand.setText(spieler.getRessourcenWerte(Ressource.GELD) + "€");

		if (spieler.getRessourcenWerte(Ressource.GELD) >= 0)
		{
			txtKontostand.setForeground(Color.BLACK);
		}
		else
		{
			txtKontostand.setForeground(Color.RED);
		}

		if (spieler.isAktuellerSpieler())
		{
			this.setBorder(BorderFactory.createLineBorder(Color.WHITE, 10));
		}
		else
		{
			this.setBorder(BorderFactory.createLineBorder(Color.BLACK, 10));
		}
	}
}
