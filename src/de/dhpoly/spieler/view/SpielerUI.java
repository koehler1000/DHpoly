package de.dhpoly.spieler.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;

import de.dhpoly.handel.view.HandelUI;
import de.dhpoly.oberflaeche.ElementFactory;
import de.dhpoly.ressource.model.Ressource;
import de.dhpoly.spiel.Spiel;
import de.dhpoly.spieler.Spieler;
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

		txtKontostand = ElementFactory.getTextFeld("", false);
		for (Ressource res : Ressource.values())
		{
			txtKontostand.setText(txtKontostand.getText() + Ressource.getString(res, spieler.getRessourcenWerte(res))
					+ System.lineSeparator());
		}
		txtKontostand.setBackground(backcolor);
		txtKontostand.setBorder(new LineBorder(backcolor, 10));

		txtName = ElementFactory.getTextFeldUeberschrift(spieler.getName() + ": ");
		txtName.setBackground(backcolor);
		txtName.setBorder(new LineBorder(backcolor, 10));

		JPanel pnlSueden = new JPanel(new GridLayout(1, 2, 10, 10));
		pnlSueden.setBackground(backcolor);
		pnlSueden.setBorder(new LineBorder(backcolor, 10));

		butHausBau = ElementFactory.getButton("Häuser" + System.lineSeparator() + "anzeigen");
		butHausBau.addActionListener(e -> spieler.zeigeHausbauMoeglichkeit());
		butHausBau.setForeground(backcolor);
		pnlSueden.add(butHausBau);

		JButton butKontoauszug = ElementFactory.getButton("Konto" + System.lineSeparator() + "anzeigen");
		butKontoauszug.addActionListener(e -> oeffneKontoauszugElementFactory());
		butKontoauszug.setForeground(backcolor);
		pnlSueden.add(butKontoauszug);

		butHandel = ElementFactory.getButton("Handeln");
		butHandel.addActionListener(e -> oeffneHandelElementFactory());
		butHandel.setForeground(backcolor);
		pnlSueden.add(butHandel);

		this.setLayout(new BorderLayout());
		this.add(txtName, BorderLayout.NORTH);
		this.add(txtKontostand, BorderLayout.CENTER);
		this.add(pnlSueden, BorderLayout.SOUTH);

		this.setBackground(backcolor);
		this.setBorder(BorderFactory.createLineBorder(Color.BLACK));

		update();

		spieler.addBeobachterHinzu(this);
	}

	private void oeffneKontoauszugElementFactory()
	{
		// FIXME
		// Oberflaeche.getInstance().zeigeAufRand("Kontostand von " + spieler.getName(),
		// new KontoauszugUI(spieler),
		// spieler);
	}

	private void oeffneHandelElementFactory()
	{
		spiel.zeigeAktuellemSpieler("Handel", new HandelUI(spiel.getAktuellerSpieler(), spieler));
	}

	@Override
	public void update()
	{
		txtKontostand.setText("");

		if (!spieler.hatVerloren())
		{
			for (Ressource res : Ressource.values())
			{
				txtKontostand.setText(txtKontostand.getText()
						+ Ressource.getString(res, spieler.getRessourcenWerte(res)) + System.lineSeparator());
			}

			if (spieler.isAktuellerSpieler())
			{
				this.setBorder(BorderFactory.createLineBorder(this.getBackground(), 10));
			}
			else
			{
				// TODO prüfen, ob Farbe setzen nötig ist.
				// this.setBorder(BorderFactory.createLineBorder(ElementFactory.getKontrastfarbe(),
				// 10));
			}

			butHausBau.setEnabled(spieler.isAktuellerSpieler() && !spieler.getFelder().isEmpty());
			butHandel.setEnabled(!spieler.isAktuellerSpieler());
		}
	}
}
