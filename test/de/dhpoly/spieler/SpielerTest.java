package de.dhpoly.spieler;

import static org.junit.Assert.assertThat;

import org.hamcrest.core.Is;
import org.junit.Test;

import de.dhpoly.spieler.control.SpielerImpl;

public class SpielerTest
{

	@Test
	public void ueberweiseGeld()
	{
		Spieler empfaenger = new SpielerImpl("me", 100);
		Spieler sender = new SpielerImpl("ich", 100);

		sender.ueberweiseGeld(50, empfaenger);

		assertThat(sender.getBargeld(), Is.is(50));
		assertThat(empfaenger.getBargeld(), Is.is(150));
	}

}
