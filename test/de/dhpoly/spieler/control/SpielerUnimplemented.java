package de.dhpoly.spieler.control;

import java.util.List;

import de.dhpoly.datenobjekt.Datenobjekt;
import de.dhpoly.feld.Feld;
import de.dhpoly.feld.model.StrasseKaufen;
import de.dhpoly.karte.model.Karte;
import de.dhpoly.oberflaeche.view.SpielfeldAnsicht;
import de.dhpoly.spiel.Spiel;
import de.dhpoly.spieler.Spieler;
import de.dhpoly.spieler.model.SpielerDaten;
import de.dhpoly.spieler.model.SpielerTyp;

public class SpielerUnimplemented implements Spieler
{
	@Override
	public void verarbeiteKarte(Karte karte)
	{}

	@Override
	public List<Feld> getFelder()
	{
		return null;
	}

	@Override
	public boolean hatVerloren()
	{
		return false;
	}

	@Override
	public void setSpielfeldAnsicht(SpielfeldAnsicht ansicht)
	{}

	@Override
	public void zeigeDatenobjekt(Datenobjekt objekt)
	{}

	@Override
	public void vergebeRessourcen(int ertrag)
	{}

	@Override
	public void setWuerfelnMoeglich(boolean value)
	{}

	@Override
	public void setWuerfelWeitergabeMoeglich(boolean value)
	{}

	@Override
	public void kaufe(StrasseKaufen strasse)
	{}

	@Override
	public SpielerDaten getDaten()
	{
		return new SpielerDaten(SpielerTyp.LOKAL, "Typ von 'Unimplemented'");
	}

	@Override
	public void verarbeite(Datenobjekt objekt, Spiel spiel)
	{}
}
