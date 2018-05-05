package de.dhpoly.datenobjekt;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;

import de.dhpoly.fehler.model.Fehler;
import de.dhpoly.fehler.model.FehlerTyp;
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

	@Deprecated
	public abstract Class<? extends Oberflaeche> getClassUI();

	@Deprecated
	public void anzeigen(SpielfeldAnsicht ansicht)
	{
		try
		{
			Oberflaeche o = (Oberflaeche) getClassUI().getConstructors()[0].newInstance(this, ansicht);
			o.zeige(getTitel(), this);
		}
		catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| SecurityException ex)
		{
			Fehler fehler = new Fehler("Fehler beim Anzeigen (" + ex.getMessage() + ")", FehlerTyp.FEHLER_ALLE);
			fehler.anzeigen(ansicht);
		}
	}
}
