package de.dhpoly.spieler.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
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

	private Spieler spieler;
	private Spiel spiel;
	private JTextArea txtKontostand = new JTextArea();
	private JTextArea txtName;
	private JButton butHausBau;
	private JButton butHandel;

	public SpielerUI(Spieler spieler, Spiel spiel)
	{
		this.spieler = spieler;
		this.spiel = spiel;

		Color backcolor = SpielerFarben.getSpielerfarbe(spieler.getSpielerNr());

		txtKontostand = new JTextArea();
		for (Ressource res : Ressource.values())
		{
			txtKontostand.setText(txtKontostand.getText() + Ressource.getString(res, spieler.getRessourcenWerte(res))
					+ System.lineSeparator());
		}

		txtKontostand.setEditable(false);
		txtKontostand.setFont(new Font("arial", Font.PLAIN, 20));
		txtKontostand.setBackground(backcolor);
		txtKontostand.setBorder(new LineBorder(backcolor, 10));

		txtName = new JTextArea(spieler.getName() + ": ");
		txtName.setEditable(false);
		txtName.setFont(Fenster.getUeberschriftFont());
		txtName.setBackground(backcolor);
		txtName.setBorder(new LineBorder(backcolor, 10));

		JPanel pnlSueden = new JPanel(new GridLayout(1, 2));

		butHausBau = erzeugeButton("Häuser" + System.lineSeparator() + "anzeigen");
		butHausBau.addActionListener(e -> oeffneHausbauFenster());
		butHausBau.setFont(Fenster.getStandardFont());
		// butHausBau.setBackground(backcolor);
		// butHausBau.setForeground(Color.BLACK);
		pnlSueden.add(butHausBau);

		JButton butKontoauszug = erzeugeButton("Konto" + System.lineSeparator() + "anzeigen");
		butKontoauszug.addActionListener(e -> oeffneKontoauszugFenster());
		butKontoauszug.setFont(Fenster.getStandardFont());
		pnlSueden.add(butKontoauszug);

		butHandel = erzeugeButton("Handeln");
		butHandel.addActionListener(e -> oeffneHandelFenster());
		butHandel.setFont(Fenster.getStandardFont());
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

	private JButton erzeugeButton(String text)
	{
		return new JButton("<html><p style='text-align:center'>" + text.replaceAll(System.lineSeparator(), "<br>")
				+ "</p></html>");
	}

	private void oeffneKontoauszugFenster()
	{
		KontoauszugUI pnlKonto = new KontoauszugUI(spieler);
		new Fenster(pnlKonto, "Häuser von " + spieler.getName());
	}

	private void oeffneHausbauFenster()
	{
		HaeuserUI pnlHaeuser = new HaeuserUI(spieler.getFelder());
		new Fenster(pnlHaeuser, "Häuser von " + spieler.getName());
	}

	private void oeffneHandelFenster()
	{
		Fenster fenster = new Fenster();
		fenster.setInhalt(new HandelUI(spiel.getAktuellerSpieler(), spieler, fenster),
				"Handel mit " + spieler.getName());
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

		butHausBau.setEnabled(spieler.isAktuellerSpieler() && spieler.getFelder().size() > 0);
		butHandel.setEnabled(!spieler.isAktuellerSpieler());
	}
}
