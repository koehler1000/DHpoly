package de.dhpoly.spieler.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;

import de.dhpoly.feld.view.HaeuserUI;
import de.dhpoly.fenster.view.Fenster;
import de.dhpoly.handel.view.HandelUI;
import de.dhpoly.ressource.model.Ressource;
import de.dhpoly.spiel.Spiel;
import de.dhpoly.spieler.Spieler;
import de.dhpoly.spieler.control.SpielerImpl;
import observerpattern.Beobachter;

public class SpielerUI extends JPanel implements Beobachter
{
	private static final long serialVersionUID = 1L;

	private transient Spieler spieler;
	private transient Spiel spiel;
	private JTextArea txtKontostand = new JTextArea();
	private JTextArea txtName;
	private JButton butHausBau;
	private JButton butHandel;

	public SpielerUI(Spieler spieler, Spiel spiel)
	{
		this.spieler = spieler;
		this.spiel = spiel;

		Color backcolor = SpielerFarben.getSpielerfarbe(spieler.getSpielerNr());

		txtKontostand = Fenster.getTextFeld("", false);
		for (Ressource res : Ressource.values())
		{
			txtKontostand.setText(txtKontostand.getText() + Ressource.getString(res, spieler.getRessourcenWerte(res))
					+ System.lineSeparator());
		}
		txtKontostand.setBackground(backcolor);
		txtKontostand.setBorder(new LineBorder(backcolor, 10));

		txtName = Fenster.getTextFeldUeberschrift(spieler.getName() + ": ");
		txtName.setBackground(backcolor);
		txtName.setBorder(new LineBorder(backcolor, 10));

		JPanel pnlSueden = new JPanel(new GridLayout(1, 2, 10, 10));
		pnlSueden.setBackground(backcolor);
		pnlSueden.setBorder(new LineBorder(backcolor, 10));

		butHausBau = Fenster.getButton("Häuser" + System.lineSeparator() + "anzeigen");
		butHausBau.addActionListener(e -> oeffneHausbauFenster());
		butHausBau.setFont(Fenster.getStandardFont());
		butHausBau.setForeground(backcolor);
		pnlSueden.add(butHausBau);

		JButton butKontoauszug = Fenster.getButton("Konto" + System.lineSeparator() + "anzeigen");
		butKontoauszug.addActionListener(e -> oeffneKontoauszugFenster());
		butKontoauszug.setFont(Fenster.getStandardFont());
		butKontoauszug.setForeground(backcolor);
		pnlSueden.add(butKontoauszug);

		butHandel = Fenster.getButton("Handeln");
		butHandel.addActionListener(e -> oeffneHandelFenster());
		butHandel.setFont(Fenster.getStandardFont());
		butHandel.setForeground(backcolor);
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
		Spiel.setPanel("Kontostand von " + spieler.getName(), new KontoauszugUI(spieler));
	}

	private void oeffneHausbauFenster()
	{
		HaeuserUI pnlHaeuser = new HaeuserUI(spieler.getFelder());
		Spiel.setPanel("Häuser", pnlHaeuser);
	}

	private void oeffneHandelFenster()
	{
		Spiel.setPanel("Handel", new HandelUI(spiel.getAktuellerSpieler(), spieler));
	}

	@Override
	public void update()
	{
		txtKontostand.setText("");
		for (Ressource res : Ressource.values())
		{
			txtKontostand.setText(txtKontostand.getText() + Ressource.getString(res, spieler.getRessourcenWerte(res))
					+ System.lineSeparator());
		}

		if (spieler.getRessourcenWerte(Ressource.GELD) >= 0)
		{
			txtKontostand.setForeground(Fenster.getKontrastfarbe());
		}
		else
		{
			txtKontostand.setForeground(Fenster.getDesignfarbe());
		}

		if (spieler.isAktuellerSpieler())
		{
			this.setBorder(BorderFactory.createLineBorder(this.getBackground(), 10));
		}
		else
		{
			this.setBorder(BorderFactory.createLineBorder(Fenster.getKontrastfarbe(), 10));
		}

		butHausBau.setEnabled(spieler.isAktuellerSpieler() && !spieler.getFelder().isEmpty());
		butHandel.setEnabled(!spieler.isAktuellerSpieler());
	}
}
