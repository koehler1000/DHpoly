package de.dhpoly.oberflaeche.view;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import de.dhpoly.datenobjekt.Datenobjekt;
import de.dhpoly.feld.model.Strasse;
import de.dhpoly.feld.view.HaeuserUI;
import de.dhpoly.feld.view.StrasseInfoUI;
import de.dhpoly.handel.model.Transaktion;
import de.dhpoly.karte.model.Karte;
import de.dhpoly.netzwerk.Client;
import de.dhpoly.netzwerk.Datenobjektverwalter;
import de.dhpoly.oberflaeche.ElementFactory;
import de.dhpoly.spiel.model.SpielStart;
import de.dhpoly.spiel.model.SpielfeldDaten;
import de.dhpoly.spieler.model.Spieler;
import de.dhpoly.spieler.view.KontoauszugUI;

public class SpielfeldAnsicht extends JPanel implements Datenobjektverwalter// NOSONAR
{
	private static final long serialVersionUID = 1L;

	private JTabbedPane tabRand = new JTabbedPane();
	private JTabbedPane tabCenter = new JTabbedPane();
	private Spieler spieler;

	private transient Optional<Client> client;

	private transient Map<Datenobjekt, Oberflaeche> inhalte = new HashMap<>();

	public SpielfeldAnsicht(Spieler spieler, Client client)
	{
		this.spieler = spieler;
		this.client = Optional.ofNullable(client);

		ElementFactory.bearbeitePanel(this);
		this.setLayout(new BorderLayout(10, 10));

		tabRand = ElementFactory.getTabbedPane();
		tabRand.setPreferredSize(new Dimension(500, 0));

		tabCenter = ElementFactory.getTabbedPane();
		this.add(tabCenter, BorderLayout.CENTER);

		this.client.ifPresent(c -> c.setDatenobjektverwalter(this));

		this.add(tabRand, BorderLayout.WEST);

		JButton butSpielStarten = ElementFactory
				.getButtonUeberschrift("Warte auf weitere Spieler" + System.lineSeparator() + "Spiel starten");
		butSpielStarten.addActionListener(e -> this.sendeAnServer(new SpielStart(spieler)));

		tabCenter.addTab("Start", butSpielStarten);
	}

	public void sendeAnServer(Datenobjekt objekt)
	{
		client.ifPresent(c -> sende(c, objekt));
	}

	public void sende(Client c, Datenobjekt obj)
	{
		c.sendeAnServer(obj);
	}

	private void loesche(Object obj)
	{
		Oberflaeche oberflaeche = inhalte.get(obj);
		tabRand.remove(oberflaeche);
		inhalte.remove(obj);
	}

	public void hinzu(String beschreibung, Datenobjekt obj, Oberflaeche oberflaeche)
	{
		if (Optional.ofNullable(oberflaeche).isPresent())
		{
			if (obj instanceof SpielfeldDaten)
			{
				fuegeInhaltHinzuMitte(beschreibung, obj, oberflaeche);
			}
			else
			{
				fuegeInhaltHinzuRand(beschreibung, obj, oberflaeche);
			}
		}
	}

	private void fuegeInhaltHinzuRand(String beschreibung, Datenobjekt obj, Oberflaeche oberflaeche)
	{
		JTabbedPane tabPane = tabRand;
		fuegeInhaltHinzu(beschreibung, obj, oberflaeche, tabPane);
	}

	private void fuegeInhaltHinzuMitte(String beschreibung, Datenobjekt obj, Oberflaeche oberflaeche)
	{
		tabCenter.removeAll();

		JTabbedPane tabPane = tabCenter;
		fuegeInhaltHinzu(beschreibung, obj, oberflaeche, tabPane);
	}

	private void fuegeInhaltHinzu(String beschreibung, Datenobjekt obj, Oberflaeche oberflaeche, JTabbedPane tabPane)
	{
		if (inhalte.containsKey(obj))
		{
			Container parent = inhalte.get(obj).getParent();
			parent.remove(inhalte.get(obj));
		}

		tabPane.addTab(beschreibung, oberflaeche);
		inhalte.put(obj, oberflaeche);
		tabPane.setSelectedComponent(oberflaeche);
	}

	public void leereRand()
	{
		List<Datenobjekt> zuLoeschen = new ArrayList<>();

		for (Entry<Datenobjekt, Oberflaeche> obj : inhalte.entrySet())
		{
			zuLoeschen.add(obj.getKey());
			tabRand.remove(obj.getValue());
		}

		zuLoeschen.forEach(e -> inhalte.remove(e));
	}

	public void zeigeHausbaumoeglichkeit(List<Strasse> felder)
	{
		tabRand.addTab("H‰user", new HaeuserUI(felder, this));
	}

	@Deprecated
	public void zeigeKarte(Karte karte)
	{
		// TODO
		// hinzu(karte.getTitel(), karte, new KarteUI(karte, this));
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
		hinzu("Straﬂe", feld, new StrasseInfoUI(feld, spielfeldAnsicht));
	}

	public Spieler getSpieler()
	{
		return spieler;
	}

	@Override
	public void empfange(Datenobjekt datenobjekt)
	{
		datenobjekt.anzeigen(this);

		if (spieler.equals(datenobjekt) && !((Spieler) datenobjekt).isAnDerReihe())
		{
			leereRand();
		}
	}
}
