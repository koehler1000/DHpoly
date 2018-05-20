package de.dhpoly.datenobjekt;

import java.lang.reflect.InvocationTargetException;

import de.dhpoly.empfaenger.model.Empfaenger;
import de.dhpoly.nachricht.model.Nachricht;
import de.dhpoly.oberflaeche.view.Oberflaeche;
import de.dhpoly.oberflaeche.view.SpielfeldAnsicht;

public abstract class DatenobjektAnClient extends Datenobjekt
{
	private static final long serialVersionUID = 1L;

	public abstract Class<? extends Oberflaeche> getClassUI();

	@Override
	public void anzeigen(SpielfeldAnsicht ansicht)
	{
		try
		{
			Oberflaeche o = (Oberflaeche) getClassUI().getConstructors()[0].newInstance(this, ansicht);
			o.zeige(getTitel());
		}
		catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| SecurityException ex)
		{
			Nachricht fehler = new Nachricht("Fehler beim Anzeigen (" + ex.getMessage() + ")", Empfaenger.ALLE);
			fehler.anzeigen(ansicht);
		}
	}
}
