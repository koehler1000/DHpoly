package de.dhpoly.feld;

import java.util.Optional;

import de.dhpoly.spieler.Spieler;

public interface Feld
{
	public void betreteFeld(Spieler spieler, int augensumme);

	public void kaufe(Spieler potentiellerKaeufer, int betrag);

	public void kaufe(Spieler potentiellerKaeufer);

	public Optional<Spieler> getEigentuemer();

	public int getGruppe();

	public void setEigentuemer(Spieler anbietender);
}
