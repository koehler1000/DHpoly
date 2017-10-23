package de.dhpoly.spieler;

import static org.junit.Assert.*;

import org.hamcrest.core.Is;
import org.junit.Test;

import de.dhpoly.spieler.model.GeldhaberImpl;

public class GeldhaberTest
{

	@Test
	public void ueberweiseGeld()
	{
		Geldhaber empfaenger = new GeldhaberImpl(100);
		Geldhaber sender = new GeldhaberImpl(100);

		sender.ueberweiseGeld(50, empfaenger);

		assertThat(sender.getBargeld(), Is.is(50));
		assertThat(empfaenger.getBargeld(), Is.is(150));
	}

}
