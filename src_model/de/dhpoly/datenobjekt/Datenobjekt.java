package de.dhpoly.datenobjekt;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import de.dhpoly.fehler.model.Fehler;
import de.dhpoly.fehler.model.FehlerTyp;
import de.dhpoly.oberflaeche.view.Oberflaeche;
import de.dhpoly.oberflaeche.view.SpielfeldAnsicht;
import de.dhpoly.spieler.model.Spieler;

public abstract class Datenobjekt implements Serializable
{
	private static final long serialVersionUID = 1L;

	public String getClassName()
	{
		return this.getClass().getName();
	}

	public List<Spieler> getBeteiligteSpieler()
	{
		return new ArrayList<>();
	}

	public abstract String getTitel();

	public abstract Class<? extends Oberflaeche> getClassUI();

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
			Fehler fehler = new Fehler("Fehler beim Anzeigen von " + this.getTitel() + " (" + this.getClassName()
					+ ") über " + getClassUI() + " (FEHLER: Class: " + ex.getClass() + " Nachricht: " + ex.getMessage()
					+ ")", FehlerTyp.FEHLER_ALLE);
			ansicht.sendeAnServer(fehler);
			fehler.anzeigen(ansicht);
		}
	}
}
