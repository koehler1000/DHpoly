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

import de.dhpoly.feld.Feld;
import de.dhpoly.feld.model.Strasse;
import de.dhpoly.feld.view.HaeuserUI;
import de.dhpoly.feld.view.StrasseInfoUI;
import de.dhpoly.handel.model.Transaktion;
import de.dhpoly.karte.model.Karte;
import de.dhpoly.karte.view.KarteUI;
import de.dhpoly.oberflaeche.ElementFactory;
import de.dhpoly.spiel.Spiel;
import de.dhpoly.spiel.view.SpielerUebersichtUI;
import de.dhpoly.spieler.Spieler;
import de.dhpoly.spieler.view.KontoauszugUI;
import de.dhpoly.spielfeld.view.SpielfeldUI;
import de.dhpoly.wuerfel.model.Wuerfel;
import de.dhpoly.wuerfel.view.WuerfelUI;

public class SpielfeldAnsicht extends JPanel // NOSONAR
{
	private static final long serialVersionUID = 1L;

	private JButton butWeiter;
	private JTabbedPane tabRand;
	private transient Spieler spieler;
	private transient Spiel spiel;

	private SpielfeldUI spielfeld;

	private transient Map<Object, Oberflaeche> inhalte = new HashMap<>();

	public SpielfeldAnsicht(Spiel spiel, List<Wuerfel> wuerfel, Spieler spieler)
	{
		this.spiel = spiel;
		this.spieler = spieler;
		this.spielfeld = new SpielfeldUI(spiel.getFelder(), this);

		ElementFactory.bearbeitePanel(this);
		butWeiter = ElementFactory.getButtonUeberschrift("Bitte warten...");
		tabRand = ElementFactory.getTabbedPane();

		this.add(spielfeld);

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

	public void hinzu(String beschreibung, Object obj, Oberflaeche oberflaeche)
	{
		tabRand.addTab(beschreibung, oberflaeche);
		inhalte.put(obj, oberflaeche);
		tabRand.setSelectedComponent(oberflaeche);
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
		hinzu("Straße", feld, new StrasseInfoUI(feld, spielfeldAnsicht));
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
