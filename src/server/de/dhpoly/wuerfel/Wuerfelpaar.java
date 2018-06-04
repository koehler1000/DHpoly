package de.dhpoly.wuerfel;

import java.util.List;

import de.dhpoly.logik.Logik;
import de.dhpoly.wuerfel.model.Wuerfel;

public interface Wuerfelpaar extends Logik
{
	void wuerfeln();

	int berechneWuerfelSumme();

	List<Wuerfel> getWuerfel();
}
