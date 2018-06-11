package de.dhpoly.datenobjekt.netzwerk;

import java.io.IOException;

import de.dhpoly.datenobjekt.Datenobjekt;
import de.dhpoly.netzwerk.control.Serialisierer;

public class NetzwerkObjekt
{
	private Class<? extends Datenobjekt> klasse;
	private String jsonObj;

	public NetzwerkObjekt(Datenobjekt objekt) throws IOException
	{
		klasse = objekt.getClass();
		jsonObj = Serialisierer.toString(objekt);
	}

	public Datenobjekt getDatenobjekt()
	{
		return (Datenobjekt) Serialisierer.toObject(jsonObj);
	}

	public Class<? extends Datenobjekt> getKlasse()
	{
		return klasse;
	}
}