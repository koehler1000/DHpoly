package de.dhpoly.spieler;

import de.dhpoly.handel.model.Transaktion;

public interface Spieler
{
	public int getBargeld();

	public void einzahlen(int betrag);

	public void auszahlen(int betrag);

	public void ueberweiseGeld(int betrag, Spieler empfaenger);

	public boolean isNegative();

	public int getFeldNr();

	public String getName();

	public void setFeldNr(int feldNrSoll);

	public void zeigeTransaktionsvorschlag(Transaktion transaktion);
}
