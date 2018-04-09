package de.dhpoly.oberflaeche.view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.List;
import java.util.Optional;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import de.dhpoly.feld.Feld;
import de.dhpoly.feld.control.Strasse;
import de.dhpoly.feld.view.HaeuserUI;
import de.dhpoly.feld.view.StrasseKaufenUI;
import de.dhpoly.handel.model.Transaktion;
import de.dhpoly.handel.view.HandelUI;
import de.dhpoly.karte.Karte;
import de.dhpoly.karte.view.KarteUI;
import de.dhpoly.oberflaeche.ElementFactory;
import de.dhpoly.spiel.Spiel;
import de.dhpoly.spiel.view.SpielerUebersichtUI;
import de.dhpoly.spieler.Spieler;
import de.dhpoly.spieler.view.KontoauszugUI;
import de.dhpoly.spielfeld.view.SpielfeldUI;
import de.dhpoly.wuerfel.control.WuerfelImpl;
import de.dhpoly.wuerfel.view.WuerfelUI;
import observerpattern.Beobachter;

public class SpielfeldAnsicht extends JPanel implements Beobachter
{
	private static final long serialVersionUID = 1L;

	private JButton butWeiter;
	private JTabbedPane tabRand;
	private transient Spieler spieler;

	public SpielfeldAnsicht(Spiel spiel, Spieler spieler)
	{
		this.spieler = spieler;
		spieler.addBeobachterHinzu(this);

		ElementFactory.bearbeitePanel(this);
		butWeiter = ElementFactory.getButton("Spiel beginnen");
		tabRand = ElementFactory.getTabbedPane();

		this.add(new SpielfeldUI(spiel.getFelder(), Optional.of(this)));

		this.add(new SpielerUebersichtUI(spiel, Optional.of(this)), BorderLayout.EAST);

		JPanel pnlWest = ElementFactory.erzeugePanel();

		JButton butImpressum = ElementFactory.getButtonUeberschrift("DHpoly");
		pnlWest.add(butImpressum, BorderLayout.CENTER);

		JPanel pnlWuerfel = ElementFactory.erzeugePanel();
		pnlWuerfel.setLayout(new GridLayout(1, 1));
		pnlWuerfel.add(new WuerfelUI((WuerfelImpl) spiel.getWuerfel(), 1));
		pnlWuerfel.add(new WuerfelUI((WuerfelImpl) spiel.getWuerfel(), 2));

		pnlWest.add(pnlWuerfel, BorderLayout.NORTH);

		pnlWest.add(tabRand);

		pnlWest.setPreferredSize(new Dimension(500, 1000));

		butWeiter = ElementFactory.getButtonUeberschrift(spiel.getBeschreibungNaechsterSchritt());
		butWeiter.addActionListener(e -> {
			spiel.naechsterSchritt();
			butWeiter.setText(spiel.getBeschreibungNaechsterSchritt());
		});

		pnlWest.add(butWeiter, BorderLayout.SOUTH);
		this.add(pnlWest, BorderLayout.WEST);
	}

	public void zeigeAufRand(String beschreibung, Component component)
	{
		tabRand.addTab(beschreibung, component);
	}

	public void leereRand()
	{
		tabRand.removeAll();
	}

	private void setAnDerReihe(boolean value)
	{
		butWeiter.setEnabled(value);
	}

	@Override
	public void update()
	{
		setAnDerReihe(spieler.isAktuellerSpieler());
	}

	public void zeigeHausbaumoeglichkeit(List<Feld> felder)
	{
		tabRand.addTab("Häuser", new HaeuserUI(felder, this));
	}

	public void zeigeTransaktion(Transaktion transaktion)
	{
		Spieler handelspartner = transaktion.getHandelspartner();
		tabRand.addTab("Handel", new HandelUI(transaktion.getAnbietender(), handelspartner, transaktion, this));
	}

	public void zeigeKaufmoeglichkeit(Strasse strasse, Spieler spieler)
	{
		tabRand.addTab("Kaufen", new StrasseKaufenUI(strasse, spieler));
	}

	public void zeigeKarte(Karte karte)
	{
		tabRand.addTab(karte.getTitel(), new KarteUI(karte, this));
	}

	public void zeigeTransaktionErfolgreich(Transaktion transaktion)
	{
		zeigeNachricht("Handel mit " + transaktion.getHandelspartner().getName() + " angenommen");
	}

	public void zeigeNachricht(String string)
	{
		tabRand.addTab("Nachricht", ElementFactory.getNachrichtPanel("Nachricht", string));
	}

	public void sperreOberflaeche(Transaktion transaktion)
	{
		// TODO später nur transaktion und nicht alles entfernen
		leereRand();
	}

	public void zeigeKontoauszug(Spieler spieler)
	{
		tabRand.addTab("Kontoauszug", new KontoauszugUI(spieler));
	}

	public void entferne(Oberflaeche oberflaeche)
	{
		tabRand.remove(oberflaeche);
	}
}
