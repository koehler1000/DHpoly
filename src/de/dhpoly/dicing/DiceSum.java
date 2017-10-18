package de.dhpoly.dicing;

import com.sun.prism.Image;

public enum DiceSum
{
	ONE(1, null), TWO(2, null);

	private int wert;
	private Image picture;

	private DiceSum(int wert, Image picture)
	{
		this.wert = wert;
		this.picture = picture;
	}

	public int getWert()
	{
		return wert;
	}

	public Image getPicture()
	{
		return picture;
	}
}
