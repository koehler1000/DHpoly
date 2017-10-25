package de.dhpoly.wuerfel.control;

import java.util.Random;

import de.dhpoly.wuerfel.Wuerfel;

public class WuerfelImpl implements Wuerfel
{

	@Override
	public int wuerfeln()
	{
		Random r = new Random();
		int erg = 1 + r.nextInt(6);
		return erg;
	}

	@Override
	public boolean isPasch(int zahl1, int zahl2)
	{
		if(zahl1 == zahl2){
			return true;
		}
		else{
			return false;
		}
	}

}
