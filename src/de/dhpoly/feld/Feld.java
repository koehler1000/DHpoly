package de.dhpoly.feld;

import java.util.Optional;

import de.dhpoly.player.Geldhaber;
import de.dhpoly.player.Player;

public abstract class Feld
{
	public abstract void betreteFeld(Player spieler, int augensumme);

	public abstract void kaufe(Geldhaber potentiellerKaeufer, int betrag);

	public abstract void kaufe(Geldhaber potentiellerKaeufer);

	public abstract Optional<Geldhaber> getEigentuemer();

	public abstract int getGruppe();
}
