package de.dhpoly.spiel.view;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;

import de.dhpoly.oberflaeche.ElementFactory;
import de.dhpoly.oberflaeche.view.Oberflaeche;
import de.dhpoly.oberflaeche.view.SpielUI;
import de.dhpoly.spiel.model.SpielStart;

public class SpielstartUI extends Oberflaeche // NOSONAR
{
	private static final long serialVersionUID = 1L;

	public SpielstartUI(SpielUI ansicht)
	{
		super(ansicht);

		JButton butStart = ElementFactory
				.getButtonUeberschrift("Warte auf weitere Spieler" + System.lineSeparator() + "Spiel starten");
		butStart.addActionListener(e -> this.sendeAnServer(new SpielStart(ansicht.getSpieler())));

		this.add(butStart);
		this.remove(getSchliessenButton());
	}

	@Override
	public void zeige(String beschreibung)
	{
		zeigeMitte(beschreibung);
	}

	@Override
	public boolean isInvalideBeiSpielerWechsel()
	{
		return false;
	}

	@Override
	public List<Oberflaeche> durchHinzufuegenUngueltigWerdend(List<Oberflaeche> oberflaechen)
	{
		return new ArrayList<>();
	}

}
