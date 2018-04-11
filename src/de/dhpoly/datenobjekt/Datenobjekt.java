package de.dhpoly.datenobjekt;

import java.io.Serializable;

public abstract class Datenobjekt implements Serializable
{
	private static final long serialVersionUID = 1L;

	public String getClassName()
	{
		return this.getClass().getName();
	}

	public abstract String getTitel();
}
