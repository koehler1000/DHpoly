package de.dhpoly.spiel.view;

import de.dhpoly.einstellungen.Einstellungen;
import de.dhpoly.einstellungen.model.EinstellungenImpl;
import de.dhpoly.oberflaeche.Oberflaeche;
import de.dhpoly.spiel.Spiel;
import de.dhpoly.spiel.control.SpielImpl;
import de.dhpoly.spieler.control.SpielerImpl;
import de.dhpoly.spielfeld.control.SpielfeldImpl;
import de.dhpoly.wuerfel.control.WuerfelImpl;

public class SpielUITest // NOSONAR
{
	public static void main(String[] args)
	{
		Einstellungen einstellungen = new EinstellungenImpl();
		Spiel spiel = new SpielImpl(new SpielfeldImpl().getStandardSpielfeld(), einstellungen, new WuerfelImpl());

		spiel.fuegeSpielerHinzu(new SpielerImpl("Rico", einstellungen, spiel));
		spiel.fuegeSpielerHinzu(new SpielerImpl("Sven", einstellungen, spiel));
		spiel.fuegeSpielerHinzu(new SpielerImpl("Alex", einstellungen, spiel));

		Oberflaeche.getInstance().zeigeKomplettesFenster(new SpielUI(spiel));
	}
}
