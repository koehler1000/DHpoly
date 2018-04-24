package de.dhpoly.datenobjekt;

import java.io.Serializable;

import observerpattern.Beobachtbarer;

public abstract class Datenobjekt extends Beobachtbarer implements Serializable
{
	private static final long serialVersionUID = 1L;

	public String getClassName()
	{
		return this.getClass().getName();
	}

	public abstract String getTitel();
}
