package de.dhpoly.nachricht.control;

import static org.junit.Assert.assertThat;

import org.hamcrest.core.Is;
import org.junit.Test;

import de.dhpoly.datenobjekt.Datenobjekt;
import de.dhpoly.empfaenger.model.Empfaenger;
import de.dhpoly.nachricht.SpielerInformierenLogik;
import de.dhpoly.nachricht.model.Nachricht;
import de.dhpoly.spiel.Spiel;
import de.dhpoly.spiel.control.SpielUnimplemented;
import de.dhpoly.spieler.model.Spieler;

public class SpielerInformierenLogikImplTest
{
	private SpielerInformierenLogik spielerInformierenLogik = new SpielerInformierenLogikImpl();

	private Datenobjekt objAnAktuellenSpieler;
	private Datenobjekt objAnAlleSpieler;

	private Spiel spiel = new SpielUnimplemented()
	{
		public void zeigeAllenSpielern(Datenobjekt objekt)
		{
			objAnAlleSpieler = objekt;
		}

		public void zeigeSpieler(Spieler sp, Datenobjekt objekt)
		{
			objAnAktuellenSpieler = objekt;
		}
	};

	@Test
	public void nachrichtAnAktuellenSpieler() throws Exception
	{
		Nachricht nachricht = new Nachricht("bla", Empfaenger.AKTUELLER_SPIELER);
		spielerInformierenLogik.verarbeite(nachricht, spiel);

		assertThat(objAnAktuellenSpieler, Is.is(nachricht));
	}

	@Test
	public void nachrichtAnAlleSpieler() throws Exception
	{
		Nachricht nachricht = new Nachricht("bla", Empfaenger.ALLE_SPIELER);
		spielerInformierenLogik.verarbeite(nachricht, spiel);

		assertThat(objAnAlleSpieler, Is.is(nachricht));
	}
}
