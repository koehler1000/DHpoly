package de.dhpoly.spiel.view;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;

import de.dhpoly.bilderverwalter.Bilderverwalter;
import de.dhpoly.oberflaeche.view.Fenster;
import de.dhpoly.oberflaeche.view.SpielfeldAnsicht;
import de.dhpoly.spiel.Spiel;
import de.dhpoly.spieler.Spieler;

public class SpielUIVerwalter
{
	private boolean animationen = true;

	private Map<Spieler, SpielfeldAnsicht> ui = new HashMap<>();
	private Fenster fenster;

	public SpielUIVerwalter()
	{
		fenster = new Fenster(new Bilderverwalter());
	}

	public void createOberflaeche(Spieler spieler, Spiel spiel)
	{
		if (!ui.containsKey(spieler))
		{
			SpielfeldAnsicht ansicht = new SpielfeldAnsicht(spiel, spieler);
			ui.put(spieler, ansicht);
			fenster.zeigeSpielansicht(ansicht, spieler.getName());

			spieler.setSpielfeldAnsicht(ansicht);
		}
	}

	public void setAnimationen(boolean value)
	{
		animationen = value;
	}

	public void leereRand()
	{
		for (Entry<Spieler, SpielfeldAnsicht> uiSpieler : ui.entrySet())
		{
			uiSpieler.getValue().leereRand();
		}
	}

	public void leereRand(Spieler spieler)
	{
		Optional<SpielfeldAnsicht> oberflaecheSpieler = Optional.ofNullable(ui.get(spieler));
		oberflaecheSpieler.ifPresent(SpielfeldAnsicht::leereRand);
	}

	public void zeigeNachricht(String nachricht)
	{
		for (Entry<Spieler, SpielfeldAnsicht> uiSpieler : ui.entrySet())
		{
			uiSpieler.getValue().zeigeNachricht(nachricht);
		}
	}

	public void zeigeNachricht(String nachricht, Spieler spieler)
	{
		spieler.zeigeNachricht(nachricht);
	}

	public boolean sollAnimiertAnzeigen()
	{
		return animationen;
	}

	public void zeigeSpieler(Spieler spielerAktuell)
	{
		fenster.zeigeTab(spielerAktuell.getName());
	}
}
