package de.dhpoly.oberflaeche.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
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
import de.dhpoly.feld.model.StrasseDaten;
import de.dhpoly.feld.view.HaeuserUI;
import de.dhpoly.feld.view.StrasseInfoUI;
import de.dhpoly.handel.model.Transaktion;
import de.dhpoly.nachricht.model.Nachricht;
import de.dhpoly.netzwerk.Datenobjektverwalter;
import de.dhpoly.netzwerk.NetzwerkClient;
import de.dhpoly.oberflaeche.ElementFactory;
import de.dhpoly.spiel.model.SpielStart;
import de.dhpoly.spiel.model.SpielfeldDaten;
import de.dhpoly.spieler.model.Spieler;
import de.dhpoly.spieler.view.KontoauszugUI;
import de.dhpoly.wuerfel.model.WuerfelAufruf;

public class SpielfeldAnsicht extends JPanel implements Datenobjektverwalter// NOSONAR
{
	private static final long serialVersionUID = 1L;

	private JTabbedPane tabRandLinks = new JTabbedPane();
	private JTabbedPane tabMitte = new JTabbedPane();
	private Spieler spieler;

	private transient Optional<NetzwerkClient> client;

	private transient Map<Object, Oberflaeche> inhalte = new HashMap<>();

	public SpielfeldAnsicht(Spieler spieler, NetzwerkClient client)
	{
		this.spieler = spieler;
		this.client = Optional.ofNullable(client);

		ElementFactory.bearbeitePanel(this);
		this.setLayout(new BorderLayout(10, 10));

		tabRandLinks = ElementFactory.getTabbedPane();
		tabRandLinks.setPreferredSize(new Dimension(500, 0));

		initRandRechts();

		tabMitte = ElementFactory.getTabbedPane();
		this.add(tabMitte, BorderLayout.CENTER);

		this.client.ifPresent(c -> c.setDatenobjektverwalter(this));

		this.add(tabRandLinks, BorderLayout.WEST);

		JButton butSpielStarten = ElementFactory
				.getButtonUeberschrift("Warte auf weitere Spieler" + System.lineSeparator() + "Spiel starten");
		butSpielStarten.addActionListener(e -> this.sendeAnServer(new SpielStart(spieler)));

		tabMitte.addTab("Start", butSpielStarten);
	}

	private void initRandRechts()
	{
		JTabbedPane tabRandRechts = ElementFactory.getTabbedPane();
		tabRandRechts.setPreferredSize(new Dimension(200, 0));

		JPanel pnlRandRechts = ElementFactory.erzeugePanel();
		pnlRandRechts.setLayout(new GridLayout(10, 1, 10, 10));

		JButton butWuerfeln = ElementFactory.getButton("W¸rfeln");
		butWuerfeln.addActionListener(e -> sendeAnServer(new WuerfelAufruf(spieler)));
		pnlRandRechts.add(butWuerfeln);

		JButton butWuerfelWeitergeben = ElementFactory.getButton("W¸rfel weitergeben");
		butWuerfelWeitergeben.addActionListener(e -> sendeAnServer(new WuerfelAufruf(spieler)));
		pnlRandRechts.add(butWuerfelWeitergeben);

		pnlRandRechts.add(ElementFactory.erzeugePanel());

		JButton butHausbau = ElementFactory.getButton("H‰user verwalten");
		butHausbau.addActionListener(e -> zeigeHausbaumoeglichkeit());
		pnlRandRechts.add(butHausbau);

		JButton butKonto = ElementFactory.getButton("Kontoauszug ˆffnen");
		butKonto.addActionListener(e -> zeigeKontoauszug(spieler));
		pnlRandRechts.add(butKonto);

		pnlRandRechts.add(ElementFactory.erzeugePanel());
		pnlRandRechts.add(ElementFactory.erzeugePanel());
		pnlRandRechts.add(ElementFactory.erzeugePanel());
		pnlRandRechts.add(ElementFactory.erzeugePanel());

		JButton butKontakt = ElementFactory.getButton("Danke sagen");
		butKontakt.addActionListener(e -> sendeAnServer(new Nachricht("Danke")));
		pnlRandRechts.add(butKontakt);

		tabRandRechts.addTab("Aktionen", pnlRandRechts);

		this.add(tabRandRechts, BorderLayout.EAST);
	}

	public void sendeAnServer(Datenobjekt objekt)
	{
		client.ifPresent(c -> sende(c, objekt));
	}

	public void sende(NetzwerkClient c, Datenobjekt obj)
	{
		c.sendeAnServer(obj);
	}

	private void loesche(Object obj)
	{
		Oberflaeche oberflaeche = inhalte.get(obj);
		tabRandLinks.remove(oberflaeche);
		inhalte.remove(obj);
	}

	public void hinzu(String beschreibung, Object obj, Oberflaeche oberflaeche)
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

	private void fuegeInhaltHinzuRand(String beschreibung, Object obj, Oberflaeche oberflaeche)
	{
		JTabbedPane tabPane = tabRandLinks;
		if (inhalte.containsKey(obj))
		{
			Optional.ofNullable(inhalte.get(obj).getParent()).ifPresent(e -> e.remove(inhalte.get(obj)));
			inhalte.remove(obj);
		}
		fuegeInhaltHinzu(beschreibung, obj, oberflaeche, tabPane);
	}

	private void fuegeInhaltHinzuMitte(String beschreibung, Object obj, Oberflaeche oberflaeche)
	{
		tabMitte.removeAll();

		JTabbedPane tabPane = tabMitte;
		fuegeInhaltHinzu(beschreibung, obj, oberflaeche, tabPane);
	}

	private void fuegeInhaltHinzu(String beschreibung, Object obj, Oberflaeche oberflaeche, JTabbedPane tabPane)
	{
		tabPane.addTab(beschreibung, oberflaeche);
		inhalte.put(obj, oberflaeche);
		tabPane.setSelectedComponent(oberflaeche);
	}

	public void leereRand()
	{
		List<Object> zuLoeschen = new ArrayList<>();

		for (Entry<Object, Oberflaeche> obj : inhalte.entrySet())
		{
			zuLoeschen.add(obj.getKey());
			tabRandLinks.remove(obj.getValue());
		}

		zuLoeschen.forEach(e -> inhalte.remove(e));
	}

	public void zeigeHausbaumoeglichkeit()
	{
		hinzu("Hausbau", spieler.getStrassen(), new HaeuserUI(spieler.getStrassen(), this));
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
		if (inhalte.containsValue(oberflaeche))
		{
			inhalte.remove(inhalte.get(oberflaeche));
		}
		tabRandLinks.remove(oberflaeche);
	}

	public void zeigeStrasseInfo(StrasseDaten feld, SpielfeldAnsicht spielfeldAnsicht)
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
