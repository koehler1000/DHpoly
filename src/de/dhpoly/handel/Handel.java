package de.dhpoly.handel;

import de.dhpoly.handel.model.Transaktion;

public interface Handel
{
	void vorschlagAnbieten(Transaktion transaktion);

	void vorschlagAnnehmen(Transaktion transaktion);

	void vorschlagAblehnen(Transaktion transaktion);
}
