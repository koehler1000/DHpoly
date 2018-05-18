package de.dhpoly.oberflaeche.view;

import java.awt.BorderLayout;
import java.awt.Component;
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
import de.dhpoly.nachricht.view.NachrichtenErstellerUI;
import de.dhpoly.netzwerk.Datenobjektverwalter;
import de.dhpoly.netzwerk.NetzwerkClient;
import de.dhpoly.oberflaeche.ElementFactory;
import de.dhpoly.spiel.model.SpielStart;
import de.dhpoly.spieler.model.Spieler;
import de.dhpoly.spieler.view.KontoauszugUI;
import de.dhpoly.wuerfel.model.WuerfelAufruf;
import de.dhpoly.wuerfel.model.WuerfelWeitergabe;

public class SpielfeldAnsicht extends JPanel implements Datenobjektverwalter// NOSONAR
{
	private static final long serialVersionUID = 1L;

	private JTabbedPane tabLinks = new JTabbedPane();
	private JTabbedPane tabMitte = new JTabbedPane();
	private JTabbedPane tabRechts = new JTabbedPane();
	private Spieler spieler;

	private JButton butSpielStarten;

	private transient Optional<NetzwerkClient> client;

	private transient Map<Object, Oberflaeche> inhalte = new HashMap<>();

	public SpielfeldAnsicht(Spieler spieler, NetzwerkClient client)
	{
		this.spieler = spieler;
		this.client = Optional.ofNullable(client);

		ElementFactory.bearbeitePanel(this);
		this.setLayout(new BorderLayout(10, 10));

		tabLinks = ElementFactory.getTabbedPane();
		tabLinks.setPreferredSize(new Dimension(500, 0));

		initRandRechts();

		tabMitte = ElementFactory.getTabbedPane();
		this.add(tabMitte, BorderLayout.CENTER);

		this.client.ifPresent(c -> c.setDatenobjektverwalter(this));

		this.add(tabLinks, BorderLayout.WEST);

		butSpielStarten = ElementFactory
				.getButtonUeberschrift("Warte auf weitere Spieler" + System.lineSeparator() + "Spiel starten");
		butSpielStarten.addActionListener(e -> this.sendeAnServer(new SpielStart(spieler)));

		tabMitte.addTab("Start", butSpielStarten);
	}

	private void initRandRechts()
	{
		tabRechts = ElementFactory.getTabbedPane();
		tabRechts.setPreferredSize(new Dimension(200, 0));

		JPanel pnlRandRechts = ElementFactory.erzeugePanel();
		pnlRandRechts.setLayout(new GridLayout(10, 1, 10, 10));

		JButton butWuerfeln = ElementFactory.getButton("W¸rfeln");
		butWuerfeln.addActionListener(e -> sendeAnServer(new WuerfelAufruf(spieler)));
		pnlRandRechts.add(butWuerfeln);

		JButton butWuerfelWeitergeben = ElementFactory.getButton("W¸rfel weitergeben");
		butWuerfelWeitergeben.addActionListener(e -> sendeAnServer(new WuerfelWeitergabe(spieler)));
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

		JButton butFeedback = ElementFactory.getButton("Entwickler kontaktieren");
		butFeedback.addActionListener(e -> hinzuLinks("Feedback", "Feedback", new NachrichtenErstellerUI(this)));
		pnlRandRechts.add(butFeedback);

		JButton butKontakt = ElementFactory.getButton("Danke sagen");
		butKontakt.addActionListener(e -> sendeAnServer(new Nachricht("Danke")));
		pnlRandRechts.add(butKontakt);

		tabRechts.addTab("Aktionen", pnlRandRechts);

		this.add(tabRechts, BorderLayout.EAST);
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
		tabLinks.remove(oberflaeche);
		inhalte.remove(obj);
	}

	private void fuegeInhaltHinzu(String beschreibung, Object obj, Oberflaeche oberflaeche, JTabbedPane tabPane)
	{
		tabPane.addTab(beschreibung, oberflaeche);

		if (inhalte.containsKey(obj))
		{
			Component component = inhalte.get(obj);
			tabPane.remove(component);
		}

		inhalte.put(obj, oberflaeche);
		tabPane.setSelectedComponent(oberflaeche);
	}

	public void leereRand()
	{
		List<Object> zuLoeschen = new ArrayList<>();

		for (Entry<Object, Oberflaeche> obj : inhalte.entrySet())
		{
			zuLoeschen.add(obj.getKey());
			tabLinks.remove(obj.getValue());
		}

		zuLoeschen.forEach(e -> inhalte.remove(e));
	}

	public void zeigeHausbaumoeglichkeit()
	{
		hinzuLinks("Hausbau", spieler.getStrassen(), new HaeuserUI(spieler.getStrassen(), this));
	}

	public void sperreOberflaeche(Transaktion transaktion)
	{
		loesche(transaktion);
	}

	public void zeigeKontoauszug(Spieler spieler)
	{
		hinzuLinks("Kontoauszug", spieler, new KontoauszugUI(spieler, this));
	}

	public void entferne(Oberflaeche oberflaeche)
	{
		if (inhalte.containsValue(oberflaeche))
		{
			inhalte.remove(inhalte.get(oberflaeche));
		}
		tabLinks.remove(oberflaeche);
	}

	public void zeigeStrasseInfo(StrasseDaten feld, SpielfeldAnsicht spielfeldAnsicht)
	{
		hinzuLinks("Straﬂe", feld, new StrasseInfoUI(feld, spielfeldAnsicht));
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

	public void hinzuRechts(String beschreibung, Object objekt, Oberflaeche oberflaeche)
	{
		JTabbedPane tabPane = tabRechts;
		fuegeInhaltHinzu(beschreibung, objekt, oberflaeche, tabPane);
	}

	public void hinzuMitte(String beschreibung, Object objekt, Oberflaeche oberflaeche)
	{
		JTabbedPane tabPane = tabMitte;
		fuegeInhaltHinzu(beschreibung, objekt, oberflaeche, tabPane);
	}

	public void hinzuLinks(String beschreibung, Object objekt, Oberflaeche oberflaeche)
	{
		JTabbedPane tabPane = tabLinks;
		fuegeInhaltHinzu(beschreibung, objekt, oberflaeche, tabPane);
	}
}
