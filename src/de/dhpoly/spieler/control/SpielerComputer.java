package de.dhpoly.spieler.control;

import java.util.List;
import java.util.Optional;

import de.dhpoly.datenobjekt.Datenobjekt;
import de.dhpoly.feld.control.FeldStrasse;
import de.dhpoly.karte.Karte;
import de.dhpoly.oberflaeche.view.Fenster;
import de.dhpoly.oberflaeche.view.SpielfeldAnsicht;
import de.dhpoly.spiel.Spiel;
import de.dhpoly.wuerfel.control.Wuerfel;

public class SpielerComputer extends SpielerImpl
{
	public SpielerComputer(String name, Spiel spiel)
	{
		super(name, spiel);
	}

	@Override
	public void zeigeKaufmoeglichkeit(FeldStrasse strasse)
	{
		strasse.kaufe(this);
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
}
