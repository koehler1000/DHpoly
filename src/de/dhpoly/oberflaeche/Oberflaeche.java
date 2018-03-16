package de.dhpoly.oberflaeche;

import java.awt.Component;

import de.dhpoly.bilderverwalter.Bilderverwalter;
import de.dhpoly.oberflaeche.view.OberflaecheUI;

public class Oberflaeche
{
	private static Oberflaeche oberflaeche = new Oberflaeche();
	private OberflaecheUI ui = new OberflaecheUI(new Bilderverwalter());
	private boolean animationen = true;

	public static Oberflaeche getInstance()
	{
		return oberflaeche;
	}

	public void setAnimationen(boolean value)
	{
		animationen = value;
	}

	public void zeigeAufRand(String beschreibung, Component component)
	{
		ui.zeigeAufRand(beschreibung, component);
	}

	public void zeigeKomplettesFenster(Component component)
	{
		ui.zeigeKomplettesFenster(component);
	}

	public void leereRand()
	{
		ui.leereRand();
	}

	public Component getRand()
	{
		return ui.getRand();
	}

	public void zeigeNachricht(String nachricht)
	{
		zeigeAufRand("Info", ElementFactory.getNachrichtPanel("Info", nachricht));
	}

	public boolean sollAnimiertAnzeigen()
	{
		return animationen;
	}
}
