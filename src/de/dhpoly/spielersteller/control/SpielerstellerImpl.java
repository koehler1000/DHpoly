package de.dhpoly.spielersteller.control;

import de.dhpoly.einstellungen.Einstellungen;
import de.dhpoly.einstellungen.model.EinstellungenImpl;
import de.dhpoly.oberflaeche.view.Fenster;
import de.dhpoly.spiel.Spiel;
import de.dhpoly.spiel.control.SpielImpl;
import de.dhpoly.spielersteller.Spielersteller;
import de.dhpoly.spielfeld.control.SpielfeldImpl;
import de.dhpoly.wuerfel.control.WuerfelpaarImpl;

public class SpielerstellerImpl implements Spielersteller
{
	private Spiel spiel;

	public SpielerstellerImpl(Fenster fenster)
	{
		Einstellungen einstellungen = new EinstellungenImpl();
		spiel = new SpielImpl(fenster, new SpielfeldImpl().getStandardSpielfeld(), einstellungen,
				new WuerfelpaarImpl());
	}

	@Override
	public void lokalenSpielerHinzufuegen(String name)
	{
		spiel.fuegeSpielerHinzu(name);
	}
}
