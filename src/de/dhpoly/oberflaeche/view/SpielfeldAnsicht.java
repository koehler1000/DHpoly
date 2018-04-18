package de.dhpoly.oberflaeche.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import de.dhpoly.datenobjekt.Datenobjekt;
import de.dhpoly.fehler.model.Fehler;
import de.dhpoly.fehler.model.FehlerTyp;
import de.dhpoly.fehler.view.FehlerUI;
import de.dhpoly.feld.Feld;
import de.dhpoly.feld.control.Strasse;
import de.dhpoly.feld.view.HaeuserUI;
import de.dhpoly.feld.view.StrasseInfoUI;
import de.dhpoly.feld.view.StrasseKaufenUI;
import de.dhpoly.handel.model.Transaktion;
import de.dhpoly.handel.view.HandelUI;
import de.dhpoly.karte.Karte;
import de.dhpoly.karte.control.BezahlKarte;
import de.dhpoly.karte.control.RueckenKarte;
import de.dhpoly.karte.control.WetterKarte;
import de.dhpoly.karte.view.KarteUI;
import de.dhpoly.nachricht.model.Nachricht;
import de.dhpoly.nachricht.view.NachrichtUI;
import de.dhpoly.oberflaeche.ElementFactory;
import de.dhpoly.spiel.Spiel;
import de.dhpoly.spiel.view.SpielerUebersichtUI;
import de.dhpoly.spieler.Spieler;
import de.dhpoly.spieler.view.KontoauszugUI;
import de.dhpoly.spielfeld.view.SpielfeldUI;
import de.dhpoly.wuerfel.control.Wuerfel;
import de.dhpoly.wuerfel.view.WuerfelUI;

public class SpielfeldAnsicht extends JPanel // NOSONAR
{
	private static final long serialVersionUID = 1L;

	private JButton butWeiter;
	private JTabbedPane tabRand;
	private transient Spieler spieler;
	private transient Spiel spiel;

	private transient Map<Object, Oberflaeche> inhalte = new HashMap<>();

	public SpielfeldAnsicht(Spiel spiel, List<Wuerfel> wuerfel, Spieler spieler)
	{
		this.spiel = spiel;
		this.spieler = spieler;

		ElementFactory.bearbeitePanel(this);
		butWeiter = ElementFactory.getButton("Bitte warten...");
		tabRand = ElementFactory.getTabbedPane();

		this.add(new SpielfeldUI(spiel.getFelder(), this));

		this.add(new SpielerUebersichtUI(spiel, this), BorderLayout.EAST);

		JPanel pnlWest = ElementFactory.erzeugePanel();

		JButton butImpressum = ElementFactory.getButtonUeberschrift("DHpoly");
		pnlWest.add(butImpressum, BorderLayout.CENTER);

		JPanel pnlWuerfel = ElementFactory.erzeugePanel();
		pnlWuerfel.setLayout(new GridLayout(1, 1));

		wuerfel.forEach(e -> pnlWuerfel.add(new WuerfelUI(e, this)));

		pnlWest.add(pnlWuerfel, BorderLayout.NORTH);

		pnlWest.add(tabRand);

		pnlWest.setPreferredSize(new Dimension(500, 1000));

		butWeiter.addActionListener(e -> weiter());

		pnlWest.add(butWeiter, BorderLayout.SOUTH);
		this.add(pnlWest, BorderLayout.WEST);
	}

	private void weiter()
	{
		if (spiel.kannWuerfeln(spieler))
		{
			spiel.wuerfeln(spieler);
		}

		if (spiel.kannWuerfelWeitergeben(spieler))
		{
			spiel.wuerfelWeitergeben(spieler);
		}
	}

	private void loesche(Object obj)
	{
		Oberflaeche oberflaeche = inhalte.get(obj);
		tabRand.remove(oberflaeche);
		inhalte.remove(obj);
	}

	private void hinzu(String beschreibung, Object obj, Oberflaeche oberflaeche)
	{
		tabRand.addTab(beschreibung, oberflaeche);
		inhalte.put(obj, oberflaeche);
	}

	public void leereRand()
	{
		tabRand.removeAll();
		inhalte.clear();
	}

	public void zeigeHausbaumoeglichkeit(List<Feld> felder)
	{
		tabRand.addTab("Häuser", new HaeuserUI(felder, this));
	}

	public void zeigeTransaktion(Transaktion transaktion)
	{
		zeigeObjekt(transaktion);
	}

	public void zeigeKaufmoeglichkeit(Strasse strasse, Spieler spieler)
	{
		tabRand.addTab("Kaufen", new StrasseKaufenUI(strasse, spieler, this));
	}

	public void zeigeKarte(Karte karte)
	{
		tabRand.addTab(karte.getTitel(), new KarteUI(karte, this));
	}

	public void sperreOberflaeche(Transaktion transaktion)
	{
		loesche(transaktion);
	}

	public void zeigeKontoauszug(Spieler spieler)
	{
		tabRand.addTab("Kontoauszug", new KontoauszugUI(spieler));
	}

	public void entferne(Oberflaeche oberflaeche)
	{
		tabRand.remove(oberflaeche);
	}

	public void zeigeStrasseInfo(Strasse feld, SpielfeldAnsicht spielfeldAnsicht)
	{
		hinzu("Straße", feld, new StrasseInfoUI(feld, spielfeldAnsicht));
	}

	public void zeigeObjekt(Datenobjekt objekt)
	{
		String fehlerText = "Keine Oberfläche für " + objekt.getClassName() + " implementiert.";
		Fehler fehler = new Fehler(fehlerText, FehlerTyp.FEHLER_ALLE);
		Oberflaeche oberflaeche = new FehlerUI(fehler, this);

		if (objekt instanceof Nachricht)
		{
			oberflaeche = new NachrichtUI((Nachricht) objekt, this);
		}
		else if (objekt instanceof Transaktion)
		{
			oberflaeche = new HandelUI((Transaktion) objekt, this);
		}
		else if (objekt instanceof Fehler)
		{
			oberflaeche = new FehlerUI((Fehler) objekt, this);
		}
		else if (objekt instanceof BezahlKarte)
		{
			oberflaeche = new KarteUI((BezahlKarte) objekt, this);
		}
		else if (objekt instanceof RueckenKarte)
		{
			oberflaeche = new KarteUI((RueckenKarte) objekt, this);
		}
		else if (objekt instanceof WetterKarte)
		{
			oberflaeche = new KarteUI((WetterKarte) objekt, this);
		}
		else
		{
			spiel.verarbeiteFehler(fehler);
		}

		hinzu(objekt.getTitel(), objekt, oberflaeche);
	}

	public Spieler getSpieler()
	{
		return spieler;
	}

	public void wuerfelnErmoeglichen(boolean b)
	{
		butWeiter.setEnabled(b);
		butWeiter.setText("Würfeln");
	}

	public void wuerfelWeitergabeErmoeglichen(boolean b)
	{
		butWeiter.setEnabled(b);
		butWeiter.setText("Würfel weitergeben");
	}
}
