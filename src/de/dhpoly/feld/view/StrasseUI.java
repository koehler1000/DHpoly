package de.dhpoly.feld.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.util.Optional;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.border.LineBorder;

import de.dhpoly.feld.control.Strasse;
import de.dhpoly.fenster.view.Fenster;
import de.dhpoly.spiel.Spiel;
import de.dhpoly.spieler.Spieler;
import de.dhpoly.spieler.view.SpielerFarben;
import observerpattern.Beobachter;

public class StrasseUI extends FeldUI implements Beobachter // NOSONAR
{
	private static final long serialVersionUID = 1L;

	private transient Strasse feld;
	private JButton butName = new JButton();
	private JButton butBesitzer = new JButton();

	public StrasseUI(Strasse feld)
	{
		super(feld);
		this.feld = feld;
		butName.setText(feld.getName());
		this.setLayout(new BorderLayout());
		this.setBorder(BorderFactory.createLineBorder(Color.BLACK));

		Color backcolor = new Strassengruppe().getColor(feld.getGruppe());

		butName.setFont(Fenster.getSpielfeldStrassennameFont());
		butName.setBackground(backcolor);
		butName.setBorder(new LineBorder(Color.BLACK));
		this.add(butName, BorderLayout.NORTH);

		butBesitzer.setFont(Fenster.getSpielfeldBesitzerFont());
		butBesitzer.setBackground(Color.WHITE);
		this.add(butBesitzer, BorderLayout.CENTER);

		butBesitzer.addActionListener(e -> zeigeDetails());
		butName.addActionListener(e -> zeigeDetails());

		update();
		feld.addBeobachter(this);
	}

	private void zeigeDetails()
	{
		Spiel.setPanel("Straﬂe", new StrasseInfoUI(feld));
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