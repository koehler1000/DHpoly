package de.dhpoly.netzwerk.control;

import java.io.Serializable;

import com.google.gson.Gson;

import de.dhpoly.datenobjekt.Datenobjekt;
import de.dhpoly.datenobjekt.netzwerk.NetzwerkObjekt;

public class Serialisierer
{
	private static Gson gson = new Gson();

	private Serialisierer()
	{}

	public static String toString(NetzwerkObjekt o)
	{
		String str = gson.toJson(o);
		System.out.println(str);
		return str;
	}

	public static String toString(Datenobjekt o)
	{
		String str = gson.toJson(o);
		System.out.println(str);
		return str;
	}

	public static Serializable toObject(String s)
	{
		NetzwerkObjekt obj = gson.fromJson(s, NetzwerkObjekt.class);
		System.out.println(obj.getKlasse());
		return obj.getDatenobjekt();
	}
}
