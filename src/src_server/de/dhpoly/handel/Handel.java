package de.dhpoly.handel;

import de.dhpoly.handel.model.Transaktion;
import de.dhpoly.logik.Logik;
import de.dhpoly.spiel.Spiel;

public interface Handel extends Logik
{
	void vorschlagAblehnen(Transaktion transaktion, Spiel spiel);

	void vorschlagAnnehmen(Transaktion transaktion, Spiel spiel);

	void vorschlagAnbieten(Transaktion transaktion, Spiel spiel);
}
