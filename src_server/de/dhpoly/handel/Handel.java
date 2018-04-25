package de.dhpoly.handel;

import de.dhpoly.handel.model.Transaktion;
import de.dhpoly.logik.Logik;

public interface Handel extends Logik
{
	void vorschlagAnbieten(Transaktion transaktion);

	void vorschlagAnnehmen(Transaktion transaktion);

	void vorschlagAblehnen(Transaktion transaktion);
}
