package de.dhpoly.spieler.control;

import java.util.List;
import java.util.Optional;

import de.dhpoly.datenobjekt.Datenobjekt;
import de.dhpoly.karte.model.Karte;
import de.dhpoly.oberflaeche.view.Fenster;
import de.dhpoly.oberflaeche.view.SpielfeldAnsicht;
import de.dhpoly.spiel.Spiel;
import de.dhpoly.spieler.model.SpielerDaten;
import de.dhpoly.spieler.model.SpielerTyp;
import de.dhpoly.wuerfel.model.Wuerfel;

public class SpielerComputer extends SpielerImpl
{
	private SpielerDaten daten;

	public SpielerComputer(String name, Spiel spiel)
	{
		super(name, spiel);
		daten = new SpielerDaten(SpielerTyp.COMPUTER, name);
	}

	@Override
	public void verarbeiteKarte(Karte karte)
	{
		spiel.verarbeiteKarte(karte);
	}

	@Override
	public void zeigeDatenobjekt(Datenobjekt objekt)
	{
		// ignorieren
	}

	@Override
	public void setSpielfeldAnsicht(SpielfeldAnsicht ansicht)
	{
		// ignorieren
	}

	@Override
	public void setWuerfelnMoeglich(boolean value)
	{
		if (value)
		{
			spiel.wuerfeln(this);
		}
	}

	@Override
	public void setWuerfelWeitergabeMoeglich(boolean value)
	{
		if (value)
		{
			spiel.wuerfelWeitergeben(this);
		}
	}

	@Override
	public void setSpielfeldAnsichtDaten(Optional<Fenster> fenster, List<Wuerfel> wuerfel)
	{
		// ignorieren
	}

	@Override
	public SpielerDaten getDaten()
	{
		return daten;
	}
}
