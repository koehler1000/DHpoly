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

public class SpielerUI extends Oberflaeche implements Beobachter
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

		butHausBau = ElementFactory.getButton("H�user" + System.lineSeparator() + "anzeigen");
		butHausBau.addActionListener(e -> spieler.zeigeHausbauMoeglichkeit());
		butHausBau.setForeground(backcolor);
		pnlSueden.add(butHausBau);

		JButton butKontoauszug = ElementFactory.getButton("Konto" + System.lineSeparator() + "anzeigen");
		butKontoauszug.addActionListener(e -> ansicht.zeigeKontoauszug(spieler));
		butKontoauszug.setForeground(backcolor);
		pnlSueden.add(butKontoauszug);

		butHandel = ElementFactory.getButton("Handeln");

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

		spieler.addBeobachterHinzu(this);
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
				ElementFactory.setzeRand(this, 10, this.getBackground());
			}
			else
			{
				ElementFactory.setzeRand(this, 10);
			}

			butHausBau.setEnabled(spieler.isAktuellerSpieler() && !spieler.getFelder().isEmpty());
			butHandel.setEnabled(!spieler.isAktuellerSpieler());
		}
	}
}
