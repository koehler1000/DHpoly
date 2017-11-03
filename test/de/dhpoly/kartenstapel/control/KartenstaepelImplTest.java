package de.dhpoly.kartenstapel.control;

import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.core.Is;
import org.junit.Before;
import org.junit.Test;

import de.dhpoly.karte.control.BezahlKarte;
import de.dhpoly.kartenstapel.Kartenstaepel;
import de.dhpoly.kartenstapel.control.KartenstaepelImpl;
import de.dhpoly.kartenstapel.model.GeldTransfer;

public class KartenstaepelImplTest
{
	private Kartenstaepel kartenstaepel;

	@Before
	public void setUp()
	{
		List<BezahlKarte> beispielkarten = new ArrayList<>();
		beispielkarten.add(new BezahlKarte("Du erhältst DM 50", GeldTransfer.BANK_SPIELER, 50));

		kartenstaepel = new KartenstaepelImpl(beispielkarten, beispielkarten);
	}

	@Test
	public void zieheGemeinschaftskarteGibtKartenobjektZurueck()
	{
		assertThat(kartenstaepel.zieheGemeinschaftskarte(), Is.isA(BezahlKarte.class));
	}

	@Test
	public void zieheEreigniskarteGibtKartenobjektZurueck()
	{
		assertThat(kartenstaepel.zieheEreigniskarte(), Is.isA(BezahlKarte.class));
	}

}
