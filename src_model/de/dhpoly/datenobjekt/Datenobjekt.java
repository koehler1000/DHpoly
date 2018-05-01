package de.dhpoly.datenobjekt;

import java.io.Serializable;

import de.dhpoly.oberflaeche.view.Oberflaeche;
import de.dhpoly.oberflaeche.view.SpielfeldAnsicht;

public abstract class Datenobjekt implements Serializable
{
	private static final long serialVersionUID = 1L;

	public String getClassName()
	{
		return this.getClass().getName();
	}

	public abstract String getTitel();

	public abstract Class<? extends Oberflaeche> getClassUI();

	public void anzeigen(SpielfeldAnsicht ansicht)
	{
		try
		{
			getClassUI().newInstance().zeige(getTitel(), this, ansicht);
		}
		catch (InstantiationException | IllegalAccessException ex)
		{
			System.err.println(ex.getMessage());
		}
	}
}
