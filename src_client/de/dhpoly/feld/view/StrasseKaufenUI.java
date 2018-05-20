package de.dhpoly.feld.view;

import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;

import de.dhpoly.datenobjekt.Datenobjekt;
import de.dhpoly.feld.model.StrasseKaufen;
import de.dhpoly.feld.model.StrasseKaufenStatus;
import de.dhpoly.oberflaeche.ElementFactory;
import de.dhpoly.oberflaeche.view.Oberflaeche;
import de.dhpoly.oberflaeche.view.SpielfeldAnsicht;

public class StrasseKaufenUI extends Oberflaeche // NOSONAR
{
	private static final long serialVersionUID = 1L;

	public StrasseKaufenUI(StrasseKaufen strasse, SpielfeldAnsicht ansicht)
	{
		super(ansicht);

		if (strasse.getSender() == ansicht.getSpieler())
		{
			initStrasseKaufen(strasse, ansicht);
		}
		else
		{
			initStrasseKaufenInfo(strasse, ansicht);
		}
	}

	private void initStrasseKaufenInfo(StrasseKaufen strasse, SpielfeldAnsicht ansicht)
	{
		JPanel pnlInhalt = ElementFactory.erzeugePanel();
		pnlInhalt.setLayout(new GridLayout(1, 2, 10, 10));

		pnlInhalt.add(new StrasseInfoUI(strasse.getStrasse(), ansicht));

		this.add(pnlInhalt);
	}

	private void initStrasseKaufen(StrasseKaufen strasse, SpielfeldAnsicht ansicht)
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
		strasse.setStatus(StrasseKaufenStatus.ANGENOMMEN);
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
		return false;
	}

	@Override
	public void zeige(String beschreibung, Datenobjekt objekt)
	{
		zeigeLinks(beschreibung, objekt);
	}

	@Override
	public List<Oberflaeche> durchHinzufuegenUngueltigWerdend(List<Oberflaeche> oberflaechen)
	{
		return new ArrayList<>();
	}
}
