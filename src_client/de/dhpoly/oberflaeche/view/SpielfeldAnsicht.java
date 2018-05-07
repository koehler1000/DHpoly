package de.dhpoly.oberflaeche.view;

import java.awt.BorderLayout;
import java.awt.Container;
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
import de.dhpoly.feld.model.Strasse;
import de.dhpoly.feld.view.HaeuserUI;
import de.dhpoly.feld.view.StrasseInfoUI;
import de.dhpoly.handel.model.Transaktion;
import de.dhpoly.karte.model.Karte;
import de.dhpoly.karte.view.KarteUI;
import de.dhpoly.netzwerk.Datenobjektverwalter;
import de.dhpoly.netzwerk.NetzwerkClient;
import de.dhpoly.oberflaeche.ElementFactory;
import de.dhpoly.spieler.model.Spieler;
import de.dhpoly.spieler.view.KontoauszugUI;
import de.dhpoly.wuerfel.model.WuerfelAufruf;
import de.dhpoly.wuerfel.model.WuerfelDaten;
import de.dhpoly.wuerfel.view.WuerfelUI;

public class SpielfeldAnsicht extends JPanel implements Datenobjektverwalter// NOSONAR
{
	private static final long serialVersionUID = 1L;

	private JButton butWeiter = new JButton("");
	private JTabbedPane tabRand = new JTabbedPane();
	private Spieler spieler;

	private transient Optional<NetzwerkClient> client;

	private transient Map<Object, Oberflaeche> inhalte = new HashMap<>();

	public SpielfeldAnsicht(Spieler spieler, NetzwerkClient client)
	{
		this.spieler = spieler;
		this.client = Optional.ofNullable(client);

		ElementFactory.bearbeitePanel(this);
		butWeiter = ElementFactory.getButtonUeberschrift("Bitte warten...");
		tabRand = ElementFactory.getTabbedPane();

		butWeiter.addActionListener(e -> weiter());

		this.client.ifPresent(c -> c.setDatenobjektverwalter(this));
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

	private void weiter()
	{
		if (tabRand.getTabCount() > 0)
		{
			tabRand.remove(tabRand.getSelectedIndex());
		}
		else
		{
			WuerfelAufruf aufruf = new WuerfelAufruf(spieler);
			sendeAnServer(aufruf);
		}
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
		tabRand.remove(oberflaeche);
		inhalte.remove(obj);
	}

	public void hinzu(String beschreibung, Object obj, Oberflaeche oberflaeche)
	{
		if (Optional.of(oberflaeche).isPresent())
		{
			if (inhalte.containsKey(obj))
			{
				Container parent = inhalte.get(obj).getParent();
				parent.remove(inhalte.get(obj));
			}

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

	public void zeigeHausbaumoeglichkeit(List<Strasse> felder)
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

	@Override
	public void empfange(Datenobjekt datenobjekt)
	{
		datenobjekt.anzeigen(this);
	}
}
