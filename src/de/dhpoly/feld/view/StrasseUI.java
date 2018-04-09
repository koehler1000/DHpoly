package de.dhpoly.feld.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.util.Optional;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.border.LineBorder;

import de.dhpoly.feld.control.Strasse;
import de.dhpoly.oberflaeche.view.SpielfeldAnsicht;
import de.dhpoly.spieler.Spieler;
import de.dhpoly.spieler.view.SpielerFarben;
import observerpattern.Beobachter;

public class StrasseUI extends FeldUI implements Beobachter // NOSONAR
{
	private static final long serialVersionUID = 1L;

	private static final String ARIAL = "arial";
	private static final Font SCHRIFT_SPIELFELD_STRASSENNAME = new Font(ARIAL, Font.BOLD, 15);
	private static final Font SCHRIFT_SPIELFELD_BESITZER = new Font(ARIAL, Font.PLAIN, 12);

	private transient Strasse feld;
	private JButton butName = new JButton();
	private JButton butBesitzer = new JButton();

	public StrasseUI(Strasse feld, SpielfeldAnsicht ansicht)
	{
		super(feld, ansicht);
		this.feld = feld;
		butName.setText(feld.getName());
		this.setLayout(new BorderLayout());
		this.setBorder(BorderFactory.createLineBorder(Color.BLACK));

		Color backcolor = new Strassengruppe().getColor(feld.getGruppe());

		butName.setFont(SCHRIFT_SPIELFELD_STRASSENNAME);
		butName.setBackground(backcolor);
		butName.setBorder(new LineBorder(Color.BLACK));
		this.add(butName, BorderLayout.NORTH);

		butBesitzer.setFont(SCHRIFT_SPIELFELD_BESITZER);
		butBesitzer.setBackground(Color.WHITE);
		this.add(butBesitzer, BorderLayout.CENTER);

		butBesitzer.addActionListener(e -> zeigeDetails());
		butName.addActionListener(e -> zeigeDetails());

		update();
		feld.addBeobachter(this);
	}

	private void zeigeDetails()
	{
		ansicht.ifPresent(e -> e.zeigeStrasseInfo(feld, ansicht.get()));
				
//		e.zeigeAufRand("Straﬂe", new StrasseInfoUI(feld, ansicht.get())));
	}

	private void setFarbe(Color farbe)
	{
		butBesitzer.setBackground(farbe);
		this.setBackground(farbe);
	}

	private void setBeschriftung(Optional<Spieler> text)
	{
		butBesitzer.setText(text.isPresent() ? text.get().getName() : feld.getKaufpreis() + "Ä");
	}

	@Override
	public void update()
	{
		setFarbe(SpielerFarben.getSpielerfarbe(feld.getEigentuemer()));
		setBeschriftung(feld.getEigentuemer());

		super.update();
	}

}