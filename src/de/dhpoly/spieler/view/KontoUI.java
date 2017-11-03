package de.dhpoly.spieler.view;

import java.awt.Color;
import java.awt.Font;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import de.dhpoly.spieler.Spieler;
import de.dhpoly.spieler.control.SpielerImpl;

public class KontoUI extends JPanel implements Observer
{
	private static final long serialVersionUID = 1L;

	private Spieler spieler;
	private JTextArea txtKontostand;
	private JTextArea txtName;

	public KontoUI(Spieler spieler)
	{
		this.spieler = spieler;

		Color backcolor = Color.LIGHT_GRAY;

		txtKontostand = new JTextArea(spieler.getBargeld() + "€");
		txtKontostand.setEditable(false);
		txtKontostand.setFont(new Font("arial", Font.BOLD, 30));
		txtKontostand.setBackground(backcolor);

		txtName = new JTextArea(spieler.getName() + ": ");
		txtName.setEditable(false);
		txtName.setFont(new Font("arial", Font.BOLD, 30));
		txtName.setBackground(backcolor);

		update();

		this.add(txtName);
		this.add(txtKontostand);

		this.setBackground(backcolor);
		this.setBorder(BorderFactory.createLineBorder(Color.BLACK));

		SpielerImpl spielerImpl = (SpielerImpl) spieler;
		spielerImpl.addObserver(this);
	}

	@Override
	public void update(Observable o, Object arg)
	{
		update();
	}

	private void update()
	{
		txtKontostand.setText(spieler.getBargeld() + "€");

		if (spieler.getBargeld() >= 0)
		{
			txtKontostand.setForeground(Color.BLACK);
		}
		else
		{
			txtKontostand.setForeground(Color.RED);
		}
	}
}
