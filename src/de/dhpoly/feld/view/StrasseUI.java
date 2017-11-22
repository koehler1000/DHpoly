package de.dhpoly.feld.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import de.dhpoly.feld.control.Strasse;
import de.dhpoly.spieler.view.SpielerFarben;
import observerpattern.Beobachter;

public class StrasseUI extends JButton implements Beobachter
{
	private static final long serialVersionUID = 1L;

	private Strasse feld;
	private JButton butName = new JButton();
	private JButton butBesitzer = new JButton();

	private Component pnlSpieler = new JPanel();

	public StrasseUI(Strasse feld)
	{
		this.feld = feld;
		butName.setText(feld.getName());
		this.setLayout(new BorderLayout());
		this.setBorder(BorderFactory.createLineBorder(Color.BLACK));

		Color backcolor = new Strassengruppe().getColor(feld.getGruppe());

		butName.setFont(new Font("arial", Font.BOLD, 15));
		butName.setBackground(backcolor);
		this.add(butName, BorderLayout.NORTH);

		butBesitzer.setFont(new Font("arial", Font.PLAIN, 12));
		butBesitzer.setBackground(Color.WHITE);
		this.add(butBesitzer, BorderLayout.CENTER);

		butBesitzer.addActionListener(e -> zeigeDetails());
		butName.addActionListener(e -> zeigeDetails());
		this.addActionListener(e -> zeigeDetails());

		update();
		feld.addBeobachter(this);
	}

	private void zeigeDetails()
	{
		StrasseInfoUI infoUI = new StrasseInfoUI(feld);
		JFrame frame = new JFrame(feld.getBeschriftung());
		frame.add(infoUI);
		frame.setSize(1000, 1000);
		frame.setVisible(true);
	}

	@Override
	public void update()
	{
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
			butBesitzer.setText(feld.getKaufpreis() + "€");
			butBesitzer.setBackground(Color.WHITE);
			this.setBackground(Color.WHITE);
			butName.setBorder(new LineBorder(Color.BLACK));
		}

		this.remove(pnlSpieler);
		pnlSpieler = new FeldUI(feld.getSpielerAufFeld(), this.getBackground());
		this.add(pnlSpieler, BorderLayout.SOUTH);
	}
}