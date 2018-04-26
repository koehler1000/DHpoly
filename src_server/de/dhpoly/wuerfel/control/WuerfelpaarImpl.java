package de.dhpoly.wuerfel.control;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import de.dhpoly.datenobjekt.Datenobjekt;
import de.dhpoly.spiel.Spiel;
import de.dhpoly.wuerfel.Wuerfelpaar;
import de.dhpoly.wuerfel.model.Wuerfel;
import de.dhpoly.wuerfel.model.WuerfelAufruf;
import de.dhpoly.wuerfel.model.WuerfelDaten;

public class WuerfelpaarImpl implements Wuerfelpaar
{
	private WuerfelDaten wuerfelDaten;

	public WuerfelpaarImpl()
	{
		List<Wuerfel> wuerfel = new ArrayList<>();
		wuerfel.add(new Wuerfel(1));
		wuerfel.add(new Wuerfel(4));

		wuerfelDaten = new WuerfelDaten(wuerfel);
	}

	@Override
	public void wuerfeln()
	{
		Random r = new Random();
		List<Wuerfel> wuerfel = new ArrayList<>();
		wuerfel.add(new Wuerfel(1 + r.nextInt(6)));
		wuerfel.add(new Wuerfel(1 + r.nextInt(6)));
	}

	@Override
	public int berechneWuerfelSumme()
	{
		int summe = 0;
		for (Wuerfel w : wuerfelDaten.getWuerfel())
		{
			summe += w.getZahl();
		}
		return summe;
	}

	@Override
	public List<Wuerfel> getWuerfel()
	{
		return wuerfelDaten.getWuerfel();
	}

	@Override
	public void verarbeite(Datenobjekt objekt, Spiel spiel) throws IOException
	{
		if (objekt instanceof WuerfelAufruf)
		{
			wuerfeln();
			spiel.empfange(wuerfelDaten);
		}
	}
}
