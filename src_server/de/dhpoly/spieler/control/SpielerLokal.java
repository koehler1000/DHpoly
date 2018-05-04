package de.dhpoly.spieler.control;

import de.dhpoly.datenobjekt.Datenobjekt;
import de.dhpoly.oberflaeche.view.SpielfeldAnsicht;
import de.dhpoly.spiel.Spiel;
import de.dhpoly.spieler.model.SpielerDaten;
import de.dhpoly.spieler.model.SpielerTyp;

public class SpielerLokal extends SpielerImpl
{
	public SpielerLokal(String name, Spiel spiel)
	{
		super(new SpielerDaten(SpielerTyp.LOKAL, name), spiel);
	}

	@Override
	public void setSpielfeldAnsicht(SpielfeldAnsicht ansicht)
	{}

	@Override
	public void zeigeDatenobjekt(Datenobjekt objekt)
	{}

	@Override
	public void setWuerfelnMoeglich(boolean value)
	{}

	@Override
	public void setWuerfelWeitergabeMoeglich(boolean value)
	{}
}
