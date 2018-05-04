package de.dhpoly.spieler.control;

import java.util.List;

import de.dhpoly.datenobjekt.Datenobjekt;
import de.dhpoly.nachricht.model.Nachricht;
import de.dhpoly.oberflaeche.view.SpielfeldAnsicht;
import de.dhpoly.ressource.model.RessourcenDatensatz;
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
	public void auszahlen(List<RessourcenDatensatz> datensaetze)
	{
		for (RessourcenDatensatz ressourcenDatensatz : datensaetze)
		{
			auszahlen(ressourcenDatensatz);
		}
	}

	@Override
	public void gewonnen()
	{
		zeigeDatenobjekt(new Nachricht("Gewonnen"));
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
