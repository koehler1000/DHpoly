package de.dhpoly.spieler.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;

import de.dhpoly.handel.model.Transaktion;
import de.dhpoly.oberflaeche.ElementFactory;
import de.dhpoly.oberflaeche.view.Oberflaeche;
import de.dhpoly.oberflaeche.view.SpielfeldAnsicht;
import de.dhpoly.ressource.model.Ressource;
import de.dhpoly.spieler.Spieler;
import observerpattern.Beobachter;

public class SpielerUI extends Oberflaeche implements Beobachter // NOSONAR
{
	private static final long serialVersionUID = 1L;

	private transient Spieler spieler;
	private JTextArea txtKontostand = new JTextArea();
	private JTextArea txtName;
	private JButton butHausBau;
	private JButton butHandel;

	public SpielerUI(Spieler spieler, SpielfeldAnsicht ansicht)
	{
		super(ansicht);

		this.spieler = spieler;

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
		butHausBau.addActionListener(e -> ansicht.zeigeHausbaumoeglichkeit(spieler.getFelder()));
		butHausBau.setForeground(backcolor);
		pnlSueden.add(butHausBau);

		JButton butKontoauszug = ElementFactory.getButton("Konto" + System.lineSeparator() + "anzeigen");
		butKontoauszug.addActionListener(e -> ansicht.zeigeKontoauszug(spieler));
		butKontoauszug.setForeground(backcolor);
		pnlSueden.add(butKontoauszug);

		butHandel = ElementFactory.getButton("Handeln");
		butHandel.setEnabled(spieler != ansicht.getSpieler());

		butHandel.addActionListener(e -> ansicht.zeigeObjekt(new Transaktion(ansicht.getSpieler(), spieler)));

		butHandel.setForeground(backcolor);
		pnlSueden.add(butHandel);

		this.setLayout(new BorderLayout());
		this.add(txtName, BorderLayout.NORTH);
		this.add(txtKontostand, BorderLayout.CENTER);
		this.add(pnlSueden, BorderLayout.SOUTH);

		this.setBackground(backcolor);
		this.setBorder(BorderFactory.createLineBorder(Color.BLACK));

		update();
	}

	private boolean kannHaeuserBauen()
	{
		return spieler.isAktuellerSpieler() // an der Reihe
				&& ansicht.isPresent() // Ansicht existiert
				&& ansicht.get().getSpieler() == spieler; // Spieler gehört die Ansicht
	}

	@Override
	public void update()
	{
		if (spieler.hatVerloren())
		{
			butHausBau.setEnabled(false);
		}
		else
		{
			StringBuilder builder = new StringBuilder();
			for (Ressource res : Ressource.values())
			{
				builder.append(Ressource.getString(res, spieler.getRessourcenWerte(res)) + System.lineSeparator());
			}
			txtKontostand.setText(builder.toString());

			ElementFactory.setzeRand(this, 10, spieler.isAktuellerSpieler() ? spieler : null);

			butHausBau.setEnabled(kannHaeuserBauen());
		}
	}
}
