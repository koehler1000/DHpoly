package de.dhpoly.feld;

import java.util.Optional;

import de.dhpoly.spieler.Spieler;

public abstract class Feld
{
	public abstract void betreteFeld(Spieler spieler, int augensumme);

	public abstract void kaufe(Spieler potentiellerKaeufer, int betrag);

	public abstract void kaufe(Spieler potentiellerKaeufer);

	public abstract Optional<Spieler> getEigentuemer();

	public abstract int getGruppe();
}
