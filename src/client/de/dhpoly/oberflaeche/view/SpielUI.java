package de.dhpoly.oberflaeche.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import de.dhpoly.datenobjekt.Datenobjekt;
import de.dhpoly.feld.model.StrasseDaten;
import de.dhpoly.feld.view.HaeuserUI;
import de.dhpoly.feld.view.StrasseInfoUI;
import de.dhpoly.nachricht.view.NachrichtenErstellerUI;
import de.dhpoly.netzwerk.Datenobjektverwalter;
import de.dhpoly.netzwerk.NetzwerkClient;
import de.dhpoly.oberflaeche.ElementFactory;
import de.dhpoly.spiel.view.SpielstartUI;
import de.dhpoly.spieler.model.Spieler;
import de.dhpoly.spieler.view.KontoauszugUI;
import de.dhpoly.spieler.view.SpielerUI;
import de.dhpoly.wuerfel.model.WuerfelAufruf;
import de.dhpoly.wuerfel.model.WuerfelWeitergabe;

public class SpielUI extends JPanel implements Datenobjektverwalter// NOSONAR
{
	private static final long serialVersionUID = 1L;

	private JTabbedPane tabLinks = new JTabbedPane();
	private JTabbedPane tabMitte = new JTabbedPane();
	private JTabbedPane tabRechts = new JTabbedPane();
	private Spieler spieler;
	private List<Oberflaeche> oberflaechen = new ArrayList<>();

	private JButton butWuerfeln = ElementFactory.getButton("...");
	private JButton butWuerfelWeitergeben = ElementFactory.getButton("...");

	private transient Optional<NetzwerkClient> client;

	public SpielUI(Spieler spieler, NetzwerkClient client)
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

		new SpielstartUI(this).zeige("Start");
	}

	private void initRandRechts()
	{
		tabRechts = ElementFactory.getTabbedPane();
		tabRechts.setPreferredSize(new Dimension(200, 0));

		JPanel pnlRandRechts = ElementFactory.erzeugePanel();
		pnlRandRechts.setLayout(new GridLayout(10, 1, 10, 10));

		butWuerfeln = ElementFactory.getButton("W¸rfeln");
		butWuerfeln.setEnabled(false);
		butWuerfeln.addActionListener(e -> sendeAnServer(new WuerfelAufruf(spieler)));
		pnlRandRechts.add(butWuerfeln);

		butWuerfelWeitergeben = ElementFactory.getButton("W¸rfel weitergeben");
		butWuerfelWeitergeben.setEnabled(false);
		butWuerfelWeitergeben.addActionListener(e -> sendeAnServer(new WuerfelWeitergabe(spieler)));
		pnlRandRechts.add(butWuerfelWeitergeben);

		pnlRandRechts.add(ElementFactory.erzeugePanel());
		pnlRandRechts.add(ElementFactory.erzeugePanel());

		JButton butHausbau = ElementFactory.getButton("H‰user verwalten");
		butHausbau.addActionListener(e -> zeigeHausbaumoeglichkeit());
		pnlRandRechts.add(butHausbau);

		JButton butKonto = ElementFactory.getButton("Kontoauszug ˆffnen");
		butKonto.addActionListener(e -> zeigeKontoauszug(spieler));
		pnlRandRechts.add(butKonto);

		JButton butSpieler = ElementFactory.getButton("Spieler ˆffnen");
		butSpieler.addActionListener(e -> zeigeSpieler(spieler));
		pnlRandRechts.add(butSpieler);

		pnlRandRechts.add(ElementFactory.erzeugePanel());
		pnlRandRechts.add(ElementFactory.erzeugePanel());

		JButton butFeedback = ElementFactory.getButton("Entwickler kontaktieren");
		butFeedback.addActionListener(e -> hinzuLinks("Feedback", new NachrichtenErstellerUI(this)));
		pnlRandRechts.add(butFeedback);

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

	private void loesche(Oberflaeche obj)
	{
		tabLinks.remove(obj);
	}

	private void fuegeInhaltHinzu(String beschreibung, Oberflaeche oberflaeche, JTabbedPane tabPane)
	{
		tabPane.addTab(beschreibung, oberflaeche);

		List<Oberflaeche> oberflaechenAlt = new ArrayList<>();
		oberflaechenAlt.addAll(oberflaeche.durchHinzufuegenUngueltigWerdend(oberflaechen));

		oberflaechenAlt.forEach(e -> {
			oberflaeche.remove(e);
			tabPane.remove(e);
		});

		oberflaechen.add(oberflaeche);

		tabPane.setSelectedComponent(oberflaeche);
	}

	public void zeigeHausbaumoeglichkeit()
	{
		hinzuLinks("Hausbau", new HaeuserUI(spieler.getStrassen(), this));
	}

	public void sperreOberflaeche(Oberflaeche oberflaeche)
	{
		loesche(oberflaeche);
	}

	public void zeigeKontoauszug(Spieler spieler)
	{
		new KontoauszugUI(spieler, this).zeige("Kontoauszug");
	}

	public void zeigeSpieler(Spieler spieler)
	{
		new SpielerUI(spieler, this).zeigeLinks("Ich");
	}

	public void entferne(Oberflaeche oberflaeche)
	{
		tabLinks.remove(oberflaeche);
		tabMitte.remove(oberflaeche);
		tabRechts.remove(oberflaeche);
	}

	public void zeigeStrasseInfo(StrasseDaten feld, SpielUI spielfeldAnsicht)
	{
		hinzuLinks("Straﬂe", new StrasseInfoUI(feld, spielfeldAnsicht));
	}

	public Spieler getSpieler()
	{
		return spieler;
	}

	@Override
	public void empfange(Datenobjekt datenobjekt)
	{
		datenobjekt.anzeigen(this);

		if (spieler.equals(datenobjekt) && !spieler.isAnDerReihe())
		{
			oberflaechen.stream().filter(Oberflaeche::isInvalideBeiSpielerWechsel).forEach(Oberflaeche::schliessen);
		}
	}

	public void hinzuRechts(String beschreibung, Oberflaeche oberflaeche)
	{
		JTabbedPane tabPane = tabRechts;
		fuegeInhaltHinzu(beschreibung, oberflaeche, tabPane);
	}

	public void hinzuMitte(String beschreibung, Oberflaeche oberflaeche)
	{
		JTabbedPane tabPane = tabMitte;
		fuegeInhaltHinzu(beschreibung, oberflaeche, tabPane);
	}

	public void hinzuLinks(String beschreibung, Oberflaeche oberflaeche)
	{
		JTabbedPane tabPane = tabLinks;
		fuegeInhaltHinzu(beschreibung, oberflaeche, tabPane);
	}

	public boolean isSpielerInhaberDerAnsicht(Spieler spieler)
	{
		return spieler == this.spieler;
	}

	public void setWuerfelnEnabled(boolean value)
	{
		butWuerfeln.setEnabled(value);
	}

	public void setWuerfelWeitergabeEnabled(boolean value)
	{
		butWuerfelWeitergeben.setEnabled(value);
	}
}
