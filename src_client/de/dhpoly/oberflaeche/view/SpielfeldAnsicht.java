package de.dhpoly.oberflaeche.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import de.dhpoly.datenobjekt.Datenobjekt;
import de.dhpoly.fehler.model.Fehler;
import de.dhpoly.fehler.model.FehlerTyp;
import de.dhpoly.fehler.view.FehlerUI;
import de.dhpoly.feld.Feld;
import de.dhpoly.feld.model.Strasse;
import de.dhpoly.feld.view.HaeuserUI;
import de.dhpoly.feld.view.StrasseInfoUI;
import de.dhpoly.handel.model.Transaktion;
import de.dhpoly.karte.model.Karte;
import de.dhpoly.karte.view.KarteUI;
import de.dhpoly.netzwerk.NetzwerkClient;
import de.dhpoly.oberflaeche.ElementFactory;
import de.dhpoly.spieler.Spieler;
import de.dhpoly.spieler.model.SpielerDaten;
import de.dhpoly.spieler.view.KontoauszugUI;
import de.dhpoly.wuerfel.model.WuerfelAufruf;
import de.dhpoly.wuerfel.model.WuerfelDaten;
import de.dhpoly.wuerfel.view.WuerfelUI;

public class SpielfeldAnsicht extends JPanel // NOSONAR
{
	private static final long serialVersionUID = 1L;

	private JButton butWeiter = new JButton("");
	private JTabbedPane tabRand = new JTabbedPane();
	private SpielerDaten spieler;

	private transient NetzwerkClient client;

	private transient Map<Object, Oberflaeche> inhalte = new HashMap<>();

	public SpielfeldAnsicht(SpielerDaten spieler, NetzwerkClient client)
	{
		this.spieler = spieler;
		this.client = client;

		ElementFactory.bearbeitePanel(this);
		butWeiter = ElementFactory.getButtonUeberschrift("Bitte warten...");
		tabRand = ElementFactory.getTabbedPane();

		butWeiter.addActionListener(e -> weiter());

		client.addAnsicht(this);
	}

	public void empfangeWuerfel(WuerfelDaten wuerfel)
	{
		JPanel pnlWest = ElementFactory.erzeugePanel();

		JPanel pnlWuerfel = ElementFactory.erzeugePanel();
		pnlWuerfel.setLayout(new GridLayout(1, 1));

		wuerfel.getWuerfel().forEach(e -> pnlWuerfel.add(new WuerfelUI(e, this)));

		pnlWest.add(pnlWuerfel, BorderLayout.NORTH);
		pnlWest.add(tabRand);
		pnlWest.setPreferredSize(new Dimension(500, 1000));

		pnlWest.add(butWeiter, BorderLayout.SOUTH);
		this.add(pnlWest, BorderLayout.WEST);
	}

	// TODO
	// private void empfangeSpielFeld(SpielfeldDaten spielfeld)
	// {
	// this.spielfeld = new SpielfeldUI(spiel.getFelder(), this);
	// this.add(spielfeld);
	// }

	// TODO
	// private void empfangeSpieler(SpielerDaten daten)
	// {
	// this.add(new SpielerUebersichtUI(spiel, this), BorderLayout.EAST);
	// }

	private void weiter()
	{
		WuerfelAufruf aufruf = new WuerfelAufruf(spieler);
		sendeAnServer(aufruf);

		// if (spiel.kannWuerfelWeitergeben(spieler))
		// {
		// spiel.wuerfelWeitergeben(spieler);
		// }
	}

	public void sendeAnServer(Datenobjekt objekt)
	{
		try
		{
			client.sende(objekt);
		}
		catch (IOException ex)
		{
			Fehler fehler = new Fehler(ex.getMessage(), FehlerTyp.FEHLER_ALLE);
			hinzu("Fehler", fehler, new FehlerUI(fehler, this));
		}
	}

	private void loesche(Object obj)
	{
		Oberflaeche oberflaeche = inhalte.get(obj);
		tabRand.remove(oberflaeche);
		inhalte.remove(obj);
	}

	public void hinzu(String beschreibung, Object obj, Oberflaeche oberflaeche)
	{
		if (Optional.of(oberflaeche).isPresent())
		{
			tabRand.addTab(beschreibung, oberflaeche);
			inhalte.put(obj, oberflaeche);
			tabRand.setSelectedComponent(oberflaeche);
		}
	}

	public void leereRand()
	{
		tabRand.removeAll();
		inhalte.clear();
	}

	public void zeigeHausbaumoeglichkeit(List<Feld> felder)
	{
		tabRand.addTab("H�user", new HaeuserUI(felder, this));
	}

	public void zeigeKarte(Karte karte)
	{
		hinzu(karte.getTitel(), karte, new KarteUI(karte, this));
	}

	public void sperreOberflaeche(Transaktion transaktion)
	{
		loesche(transaktion);
	}

	public void zeigeKontoauszug(Spieler spieler)
	{
		hinzu("Kontoauszug", spieler, new KontoauszugUI(spieler, this));
	}

	public void entferne(Oberflaeche oberflaeche)
	{
		tabRand.remove(oberflaeche);
	}

	public void zeigeStrasseInfo(Strasse feld, SpielfeldAnsicht spielfeldAnsicht)
	{
		hinzu("Stra�e", feld, new StrasseInfoUI(feld, spielfeldAnsicht));
	}

	public SpielerDaten getSpieler()
	{
		return spieler;
	}

	public void wuerfelnErmoeglichen(boolean b)
	{
		butWeiter.setEnabled(b);
		butWeiter.setText("W�rfeln");
	}

	public void wuerfelWeitergabeErmoeglichen(boolean b)
	{
		butWeiter.setEnabled(b);
		butWeiter.setText("W�rfel weitergeben");
	}
}
