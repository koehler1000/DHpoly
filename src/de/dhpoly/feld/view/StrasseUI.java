package de.dhpoly.feld.view;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.border.LineBorder;

import de.dhpoly.feld.control.Strasse;
import de.dhpoly.fenster.view.Fenster;
import de.dhpoly.spieler.view.SpielerFarben;
import observerpattern.Beobachter;

public class StrasseUI extends FeldUI implements Beobachter
{
	private static final long serialVersionUID = 1L;

	private Strasse feld;
	private JButton butName = new JButton();
	private JButton butBesitzer = new JButton();

	public StrasseUI(Strasse feld)
	{
		super(feld);
		this.feld = feld;
		butName.setText(feld.getName());
		this.setLayout(new BorderLayout());
		this.setBorder(BorderFactory.createLineBorder(Color.BLACK));

		Color backcolor = new Strassengruppe().getColor(feld.getGruppe());

		butName.setFont(Fenster.getSpielfeldStrassennameFont());
		butName.setBackground(backcolor);
		this.add(butName, BorderLayout.NORTH);

		butBesitzer.setFont(Fenster.getSpielfeldBesitzerFont());
		butBesitzer.setBackground(Color.WHITE);
		this.add(butBesitzer, BorderLayout.CENTER);

		butBesitzer.addActionListener(e -> zeigeDetails());
		butName.addActionListener(e -> zeigeDetails());

		update();
		feld.addBeobachter(this);
	}

	private void zeigeDetails()
	{
		Fenster fenster = new Fenster();
		fenster.setInhalt(new StrasseInfoUI(feld, fenster), "Details zu " + feld.getBeschriftung());
	}

	@Override
	public void update()
	{
		butBesitzer.setBorder(null);
		if (feld.getEigentuemer().isPresent())
		{
			butBesitzer.setText(feld.getEigentuemer().get().getName());
			Color farbe = SpielerFarben.getSpielerfarbe(feld.getEigentuemer().get().getSpielerNr());
			butBesitzer.setBackground(farbe);
			this.setBackground(farbe);
			butName.setBorder(new LineBorder(Color.BLACK));
		}
		else
		{
			butBesitzer.setText(feld.getKaufpreis() + "�");
			butBesitzer.setBackground(Color.WHITE);
			this.setBackground(Color.WHITE);
			butName.setBorder(new LineBorder(Color.BLACK));
		}

		super.update();
	}
}