package de.dhpoly.oberflaeche.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import de.dhpoly.datenobjekt.Datenobjekt;
import de.dhpoly.fehler.view.FehlerUI;
import de.dhpoly.feld.Feld;
import de.dhpoly.feld.control.Strasse;
import de.dhpoly.feld.view.HaeuserUI;
import de.dhpoly.feld.view.StrasseInfoUI;
import de.dhpoly.feld.view.StrasseKaufenUI;
import de.dhpoly.handel.model.Transaktion;
import de.dhpoly.handel.view.HandelUI;
import de.dhpoly.karte.Karte;
import de.dhpoly.karte.view.KarteUI;
import de.dhpoly.nachricht.model.Nachricht;
import de.dhpoly.nachricht.view.NachrichtUI;
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

	private transient Map<Object, Oberflaeche> inhalte = new HashMap<>();

	public SpielfeldAnsicht(Spiel spiel, Spieler spieler)
	{
		this.spieler = spieler;
		spieler.addBeobachterHinzu(this);

		ElementFactory.bearbeitePanel(this);
		butWeiter = ElementFactory.getButton("Spiel beginnen");
		tabRand = ElementFactory.getTabbedPane();

		this.add(new SpielfeldUI(spiel.getFelder(), this));

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
		tabRand.addTab("H‰user", new HaeuserUI(felder, this));
	}

	public void zeigeTransaktion(Transaktion transaktion)
	{
		Spieler handelspartner = transaktion.getHandelspartner();
		tabRand.addTab("Handel", new HandelUI(transaktion.getAnbietender(), handelspartner, transaktion, this));
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
		hinzu("Straﬂe", feld, new StrasseInfoUI(feld, spielfeldAnsicht));
	}

	public void zeigeHandelOberflaeche(Spieler handelsPartner)
	{
		// TODO null ersetzen
		hinzu("Handel", null, new HandelUI(spieler, handelsPartner));
	}

	public void zeigeFehler(String nachricht)
	{
		// TODO null ersetzen
		hinzu("Fehler", null, new FehlerUI(nachricht, this));
	}

	public void zeigeObjekt(Datenobjekt objekt)
	{
		leereRand();

		if (objekt instanceof Nachricht)
		{
			hinzu(objekt.getClassName(), objekt, new NachrichtUI((Nachricht) objekt, this));
		}
		else
		{
			// TODO FEHLER anzeigen
		}
	}
}
