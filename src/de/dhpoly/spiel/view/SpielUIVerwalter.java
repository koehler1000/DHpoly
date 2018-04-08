package de.dhpoly.spiel.view;

import java.awt.Component;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;

import de.dhpoly.bilderverwalter.Bilderverwalter;
import de.dhpoly.oberflaeche.ElementFactory;
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
			SpielfeldAnsicht ansicht = new SpielfeldAnsicht();
			ui.put(spieler, ansicht);
			fenster.zeigeSpielansicht(ansicht, spieler.getName());
		}
	}

	public void setAnimationen(boolean value)
	{
		animationen = value;
	}

	public void zeigeAufRand(String beschreibung, Component component, Spieler spieler)
	{
		Optional<SpielfeldAnsicht> oberflaecheSpieler = Optional.ofNullable(ui.get(spieler));
		oberflaecheSpieler.ifPresent(e -> e.zeigeAufRand(beschreibung, component));
	}

	// public void zeigeAufRand(String beschreibung, Component component)
	// {
	// Spieler aktuellerSpieler = spiel.getAktuellerSpieler();
	// oberflaecheSpieler.ifPresent(e -> e.zeigeAufRand(beschreibung, component));
	// }
	//
	// public void zeigeKomplettesFenster(Component component)
	// {
	// for (Entry<Spieler, SpielfeldAnsicht> uiSpieler : ui.entrySet())
	// {
	// // FIXME Componenten können nur einen Parent haben
	// // uiSpieler.getValue().zeigeKomplettesFenster(component);
	// }
	// }
	//
	// public void zeigeKomplettesFenster(Component component, Spieler spieler)
	// {
	// Optional<SpielfeldAnsicht> oberflaecheSpieler =
	// Optional.ofNullable(ui.get(spieler));
	// oberflaecheSpieler.ifPresent(e -> e.zeigeKomplettesFenster(component));
	// }

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

	// public void zeigeNachricht(String titel, String nachricht)
	// {
	// for (Entry<Spieler, SpielfeldAnsicht> uiSpieler : ui.entrySet())
	// {
	// uiSpieler.getValue().zeigeKomplettesFenster(ElementFactory.getNachrichtPanel(titel,
	// nachricht));
	// }
	// }

	public void setNichtAnDerReihe(Spieler spieler)
	{
		Optional<SpielfeldAnsicht> oberflaecheSpieler = Optional.ofNullable(ui.get(spieler));
		oberflaecheSpieler.ifPresent(e -> e.setAnDerReihe(false));
	}
}
