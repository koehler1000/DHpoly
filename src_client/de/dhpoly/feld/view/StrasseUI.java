package de.dhpoly.feld.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.border.LineBorder;

import de.dhpoly.datenobjekt.Datenobjekt;
import de.dhpoly.feld.model.StrasseDaten;
import de.dhpoly.oberflaeche.view.Oberflaeche;
import de.dhpoly.oberflaeche.view.SpielfeldAnsicht;
import de.dhpoly.spieler.model.Spieler;
import de.dhpoly.spieler.view.SpielerFarben;

public class StrasseUI extends FeldUI // NOSONAR
{
	private static final long serialVersionUID = 1L;

	private static final String ARIAL = "arial";
	private static final Font SCHRIFT_SPIELFELD_STRASSENNAME = new Font(ARIAL, Font.BOLD, 15);
	private static final Font SCHRIFT_SPIELFELD_BESITZER = new Font(ARIAL, Font.PLAIN, 12);

	private StrasseDaten feld;
	private JButton butName = new JButton();
	private JButton butBesitzer = new JButton();

	public StrasseUI(StrasseDaten feld2, SpielfeldAnsicht ansicht)
	{
		super(feld2, ansicht);
		this.feld = feld2;
		butName.setText(feld2.getName());
		this.setBorder(BorderFactory.createLineBorder(Color.BLACK));

		Color strassengruppeFarbe = new Strassengruppe().getColor(feld2.getGruppe());

		butName.setFont(SCHRIFT_SPIELFELD_STRASSENNAME);
		butName.setBackground(strassengruppeFarbe);
		butName.setBorder(new LineBorder(Color.BLACK));

		butBesitzer.setFont(SCHRIFT_SPIELFELD_BESITZER);
		butBesitzer.setBackground(Color.WHITE);

		butBesitzer.addActionListener(e -> zeigeDetails());
		butName.addActionListener(e -> zeigeDetails());

		setFarbe(SpielerFarben.getSpielerfarbe(feld.getEigentuemer()));
		setBeschriftung(feld.getEigentuemer());

		this.add(butName, BorderLayout.NORTH);
		this.add(butBesitzer, BorderLayout.CENTER);
	}

	private void zeigeDetails()
	{
		ansicht.ifPresent(e -> e.zeigeStrasseInfo(feld, ansicht.get()));
	}

	private void setFarbe(Color farbe)
	{
		butBesitzer.setBackground(farbe);
		this.setBackground(farbe);
	}

	private void setBeschriftung(Optional<Spieler> spieler)
	{
		butBesitzer.setText(spieler.isPresent() ? spieler.get().getName() : feld.getKaufpreis() + "€");
	}

	@Override
	public void zeige(String beschreibung, Datenobjekt objekt)
	{
		// wird nicht direkt angezeigt
	}

	@Override
	public List<Oberflaeche> durchHinzufuegenUngueltigWerdend(List<Oberflaeche> oberflaechen)
	{
		return new ArrayList<>();
	}
}