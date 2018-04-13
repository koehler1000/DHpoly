package de.dhpoly.wuerfel;

import java.util.List;

import de.dhpoly.wuerfel.control.Wuerfel;

public interface Wuerfelpaar
{
	void wuerfeln();

	int berechneWuerfelSumme();

	List<Wuerfel> getWuerfel();

	@Deprecated
	int getWuerfelErgebnis2();

	@Deprecated
	int getWuerfelErgebnis1();
}
