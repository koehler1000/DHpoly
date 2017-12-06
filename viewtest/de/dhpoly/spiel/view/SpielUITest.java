package de.dhpoly.spiel.view;

import de.dhpoly.einstellungen.Einstellungen;
import de.dhpoly.einstellungen.model.EinstellungenImpl;
import de.dhpoly.fenster.view.Fenster;
import de.dhpoly.spiel.Spiel;
import de.dhpoly.spiel.control.SpielImpl;
import de.dhpoly.spieler.control.SpielerImpl;
import de.dhpoly.spielfeld.control.SpielfeldImpl;
import de.dhpoly.wuerfel.control.WuerfelImpl;

public class SpielUITest
{
	public static void main(String[] args)
	{
		Einstellungen einstellungen = new EinstellungenImpl();
		Spiel spiel = new SpielImpl(new SpielfeldImpl().getStandardSpielfeld(), einstellungen, new WuerfelImpl());

		spiel.fuegeSpielerHinzu(new SpielerImpl("Rico", einstellungen, spiel));
		spiel.fuegeSpielerHinzu(new SpielerImpl("Sven", einstellungen, spiel));
		spiel.fuegeSpielerHinzu(new SpielerImpl("Alex", einstellungen, spiel));

		new Fenster(new SpielUI(spiel), true);
	}
}
