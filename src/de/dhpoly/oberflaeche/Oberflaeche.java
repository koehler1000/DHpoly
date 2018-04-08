package de.dhpoly.oberflaeche;

import java.awt.Component;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;

import de.dhpoly.bilderverwalter.Bilderverwalter;
import de.dhpoly.oberflaeche.view.SpielansichtUI;
import de.dhpoly.spieler.Spieler;

public class Oberflaeche
{
	private static Oberflaeche oberflaeche = new Oberflaeche();
	private boolean animationen = true;

	private Map<Spieler, SpielansichtUI> ui = new HashMap<>();

	public static Oberflaeche getInstance()
	{
		return oberflaeche;
	}

	public void createOberflaeche(Spieler spieler)
	{
		if (!ui.containsKey(spieler))
		{
			ui.put(spieler, new SpielansichtUI(new Bilderverwalter(), spieler.getName()));
		}
	}

	public void setAnimationen(boolean value)
	{
		animationen = value;
	}

	public void zeigeAufRand(String beschreibung, Component component, Spieler spieler)
	{
		Optional<SpielansichtUI> oberflaecheSpieler = Optional.ofNullable(ui.get(spieler));
		oberflaecheSpieler.ifPresent(e -> e.zeigeAufRand(beschreibung, component));
	}

	// public void zeigeAufRand(String beschreibung, Component component)
	// {
	// Spieler aktuellerSpieler = spiel.getAktuellerSpieler();
	// oberflaecheSpieler.ifPresent(e -> e.zeigeAufRand(beschreibung, component));
	// }

	public void zeigeKomplettesFenster(Component component)
	{
		for (Entry<Spieler, SpielansichtUI> uiSpieler : ui.entrySet())
		{
			// FIXME Componenten können nur einen Parent haben
			uiSpieler.getValue().zeigeKomplettesFenster(component);
		}
	}

	public void zeigeKomplettesFenster(Component component, Spieler spieler)
	{
		Optional<SpielansichtUI> oberflaecheSpieler = Optional.ofNullable(ui.get(spieler));
		oberflaecheSpieler.ifPresent(e -> e.zeigeKomplettesFenster(component));
	}

	public void leereRand()
	{
		for (Entry<Spieler, SpielansichtUI> uiSpieler : ui.entrySet())
		{
			uiSpieler.getValue().leereRand();
		}
	}

	public void leereRand(Spieler spieler)
	{
		Optional<SpielansichtUI> oberflaecheSpieler = Optional.ofNullable(ui.get(spieler));
		oberflaecheSpieler.ifPresent(SpielansichtUI::leereRand);
	}

	public Component getRand(Spieler spieler)
	{
		if (!ui.containsKey(spieler))
		{
			this.createOberflaeche(spieler);
		}

		return ui.get(spieler).getRand();
	}

	public void zeigeNachricht(String nachricht)
	{
		for (Entry<Spieler, SpielansichtUI> uiSpieler : ui.entrySet())
		{
			uiSpieler.getValue().zeigeAufRand("Info", ElementFactory.getNachrichtPanel("Info", nachricht));
		}
	}

	public void zeigeNachricht(String nachricht, Spieler spieler)
	{
		zeigeAufRand("Info", ElementFactory.getNachrichtPanel("Info", nachricht), spieler);
	}

	public boolean sollAnimiertAnzeigen()
	{
		return animationen;
	}

	public void zeigeNachricht(String titel, String nachricht)
	{
		for (Entry<Spieler, SpielansichtUI> uiSpieler : ui.entrySet())
		{
			uiSpieler.getValue().zeigeKomplettesFenster(ElementFactory.getNachrichtPanel(titel, nachricht));
		}
	}
}
