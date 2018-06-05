package de.dhpoly.feld.view;

import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;

import de.dhpoly.feld.model.StrasseKaufen;
import de.dhpoly.feld.model.StrasseKaufenStatus;
import de.dhpoly.oberflaeche.ElementFactory;
import de.dhpoly.oberflaeche.view.Oberflaeche;
import de.dhpoly.oberflaeche.view.SpielUI;
import de.dhpoly.spieler.model.Spieler;

public class StrasseKaufenUI extends Oberflaeche // NOSONAR
{
	private static final long serialVersionUID = 1L;
	private boolean anzeigen = true;
	private Spieler spieler;

	public StrasseKaufenUI(StrasseKaufen strasse, SpielUI ansicht)
	{
		super(ansicht);
		this.spieler = ansicht.getSpieler();
		initStrasseKaufen(strasse, ansicht);

		anzeigen = (strasse.getSender() == spieler);
	}

	private void initStrasseKaufen(StrasseKaufen strasse, SpielUI ansicht)
	{
		JPanel pnlInhalt = ElementFactory.erzeugePanel();
		pnlInhalt.setLayout(new GridLayout(1, 2, 10, 10));

		pnlInhalt.add(new StrasseInfoUI(strasse.getStrasse(), ansicht));

		JButton butKaufen = ElementFactory.getButtonUeberschrift("Kaufen");
		butKaufen.addActionListener(e -> antworten(strasse));

		JButton butAbbrechen = ElementFactory.getButtonUeberschrift("Abbrechen");
		butAbbrechen.addActionListener(e -> abbrechen());

		JPanel pnlOptionen = ElementFactory.erzeugePanel();
		pnlOptionen.setLayout(new GridLayout(2, 1, 10, 10));
		pnlOptionen.add(butKaufen);
		pnlOptionen.add(butAbbrechen);

		pnlInhalt.add(pnlOptionen);

		this.add(pnlInhalt);
	}

	private void antworten(StrasseKaufen strasse)
	{
		strasse.setAntwortDaten(StrasseKaufenStatus.ANGENOMMEN, spieler);
		sendeAnServer(strasse);
		schliessen();
	}

	private void abbrechen()
	{
		schliessen();
	}

	@Override
	public boolean isInvalideBeiSpielerWechsel()
	{
		return true;
	}

	@Override
	public void zeige(String beschreibung)
	{
		if (anzeigen)
		{
			zeigeLinks(beschreibung);
		}
	}

	@Override
	public List<Oberflaeche> durchHinzufuegenUngueltigWerdend(List<Oberflaeche> oberflaechen)
	{
		return new ArrayList<>();
	}
}
