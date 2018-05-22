package de.dhpoly.spieler.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;

import de.dhpoly.handel.model.Transaktion;
import de.dhpoly.oberflaeche.ElementFactory;
import de.dhpoly.oberflaeche.view.Oberflaeche;
import de.dhpoly.oberflaeche.view.SpielUI;
import de.dhpoly.ressource.model.Ressource;
import de.dhpoly.spieler.model.Spieler;
import de.dhpoly.spieler.model.SpielerStatus;

public class SpielerUI extends Oberflaeche // NOSONAR
{
	private static final long serialVersionUID = 1L;

	private Spieler spieler;
	private JTextArea txtKontostand = new JTextArea();
	private JTextArea txtName;
	private JButton butHandel;

	public SpielerUI(Spieler spieler, SpielUI ansicht)
	{
		super(ansicht);

		this.spieler = spieler;

		Color backcolor = SpielerFarben.getSpielerfarbe(spieler.getSpielerNr());

		txtKontostand = ElementFactory.getTextFeld("", false);
		for (Ressource res : Ressource.values())
		{
			txtKontostand.setText(txtKontostand.getText() + Ressource.getString(res, spieler.getRessourcenWert(res))
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

		JButton butKontoauszug = ElementFactory.getButton("Konto" + System.lineSeparator() + "anzeigen");
		butKontoauszug.addActionListener(e -> ansicht.zeigeKontoauszug(spieler));
		butKontoauszug.setForeground(backcolor);
		pnlSueden.add(butKontoauszug);

		butHandel = ElementFactory.getButton("Handeln");
		butHandel.setEnabled(spieler != ansicht.getSpieler());
		butHandel.addActionListener(e -> new Transaktion(ansicht.getSpieler(), spieler).anzeigen(ansicht));
		butHandel.setForeground(backcolor);
		pnlSueden.add(butHandel);

		JPanel pnlInhalt = ElementFactory.erzeugePanel();
		pnlInhalt.setLayout(new BorderLayout());
		pnlInhalt.add(txtName, BorderLayout.NORTH);
		pnlInhalt.add(txtKontostand, BorderLayout.CENTER);
		pnlInhalt.add(pnlSueden, BorderLayout.SOUTH);
		this.add(pnlInhalt);

		this.setBackground(backcolor);
		this.setBorder(BorderFactory.createLineBorder(Color.BLACK));

		if (ansicht.isSpielerInhaberDerAnsicht(spieler))
		{
			ansicht.setWuerfelnEnabled(spieler.getStatus() == SpielerStatus.MUSS_WUERFELN);
			ansicht.setWuerfelWeitergabeEnabled(spieler.getStatus() == SpielerStatus.MUSS_WUERFEL_WEITERGEBEN);
		}

		update();
	}

	public Spieler getSpieler()
	{
		return spieler;
	}

	private void update()
	{
		if (spieler.getStatus() != SpielerStatus.VERLOREN)
		{
			StringBuilder builder = new StringBuilder();
			for (Ressource res : Ressource.values())
			{
				builder.append(Ressource.getString(res, spieler.getRessourcenWert(res)) + System.lineSeparator());
			}
			txtKontostand.setText(builder.toString());

			ElementFactory.setzeRand(this, 10, spieler.isAnDerReihe() ? spieler : null);
		}
	}

	@Override
	public boolean isInvalideBeiSpielerWechsel()
	{
		return false;
	}

	@Override
	public void zeige(String beschreibung)
	{
		// zunaechst nicht anzeigen
		// zeigeLinks(spieler.getName());
	}

	@Override
	public List<Oberflaeche> durchHinzufuegenUngueltigWerdend(List<Oberflaeche> oberflaechen)
	{
		List<Oberflaeche> ret = new ArrayList<>();
		oberflaechen.stream().filter(e -> (e instanceof SpielerUI && ((SpielerUI) e).getSpieler() == spieler))
				.forEach(ret::add);
		return ret;
	}
}
