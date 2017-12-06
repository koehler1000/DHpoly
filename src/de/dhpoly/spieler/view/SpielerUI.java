package de.dhpoly.spieler.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import de.dhpoly.feld.view.HaeuserUI;
import de.dhpoly.handel.view.HandelUI;
import de.dhpoly.ressource.model.Ressource;
import de.dhpoly.spiel.Spiel;
import de.dhpoly.spieler.Spieler;
import de.dhpoly.spieler.control.SpielerImpl;
import observerpattern.Beobachter;

public class SpielerUI extends JPanel implements Beobachter
{
	private static final long serialVersionUID = 1L;

	private Spieler spieler;
	private Spiel spiel;
	private JTextArea txtKontostand;
	private JTextArea txtName;
	private JButton butHausBau;
	private JButton butHandel;

	public SpielerUI(Spieler spieler, Spiel spiel)
	{
		this.spieler = spieler;
		this.spiel = spiel;

		Color backcolor = SpielerFarben.getSpielerfarbe(spieler.getSpielerNr());

		txtKontostand = new JTextArea(spieler.getRessourcenWerte(Ressource.GELD) + "€");
		txtKontostand.setEditable(false);
		txtKontostand.setFont(new Font("arial", Font.BOLD, 30));
		txtKontostand.setBackground(backcolor);

		txtName = new JTextArea(spieler.getName() + ": ");
		txtName.setEditable(false);
		txtName.setFont(new Font("arial", Font.BOLD, 30));
		txtName.setBackground(backcolor);

		JPanel pnlSueden = new JPanel(new GridLayout(1, 2));

		butHausBau = new JButton("Häuser verwalten");
		butHausBau.addActionListener(e -> oeffneHausbauFenster());
		pnlSueden.add(butHausBau);

		JButton butKontoauszug = new JButton("Details anzeigen");
		butKontoauszug.addActionListener(e -> oeffneKontoauszugFenster());
		pnlSueden.add(butKontoauszug);

		butHandel = new JButton("Handeln");
		butHandel.addActionListener(e -> oeffneHandelFenster());
		pnlSueden.add(butHandel);

		this.setLayout(new BorderLayout());
		this.add(txtName, BorderLayout.NORTH);
		this.add(txtKontostand, BorderLayout.CENTER);
		this.add(pnlSueden, BorderLayout.SOUTH);

		this.setBackground(backcolor);
		this.setBorder(BorderFactory.createLineBorder(Color.BLACK));

		update();

		SpielerImpl spielerImpl = (SpielerImpl) spieler;
		spielerImpl.addBeobachter(this);
	}

	private void oeffneKontoauszugFenster()
	{
		KontoauszugUI pnlKonto = new KontoauszugUI(spieler);
		JFrame frameHaueser = new JFrame();
		frameHaueser.add(pnlKonto);
		frameHaueser.setTitle("Häuser von " + spieler.getName());
		frameHaueser.setSize(1000, 1000);
		frameHaueser.setVisible(true);
	}

	private void oeffneHausbauFenster()
	{
		HaeuserUI pnlHaeuser = new HaeuserUI(spieler.getFelder());
		JFrame frameHaueser = new JFrame();
		frameHaueser.add(pnlHaeuser);
		frameHaueser.setTitle("Häuser von " + spieler.getName());
		frameHaueser.setSize(1000, 1000);
		frameHaueser.setVisible(true);
	}

	private void oeffneHandelFenster()
	{
		JFrame frame = new JFrame();
		HandelUI pnlHandel = new HandelUI(spiel.getAktuellerSpieler(), spieler, frame);
		frame.add(pnlHandel);
		frame.setTitle("Handel mit " + spieler.getName());
		frame.setSize(1000, 1000);
		frame.setVisible(true);
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

		butHausBau.setEnabled(spieler.isAktuellerSpieler());
		butHandel.setEnabled(!spieler.isAktuellerSpieler());
	}
}
