package de.dhpoly.feld;

import java.util.Optional;

import de.dhpoly.spieler.Spieler;
import de.dhpoly.spieler.Geldhaber;

public abstract class Feld
{
	public abstract void betreteFeld(Spieler spieler, int augensumme);

	public abstract void kaufe(Geldhaber potentiellerKaeufer, int betrag);

	public abstract void kaufe(Geldhaber potentiellerKaeufer);

	public abstract Optional<Geldhaber> getEigentuemer();

	public abstract int getGruppe();
}
