
package de.dhpoly;

import de.dhpoly.bilderverwalter.Bilderverwalter;
import de.dhpoly.oberflaeche.view.Fenster;
import de.dhpoly.spielersteller.view.SpielerstellerUI;

public class Main
{
	public static void main(String[] args)
	{
		new SpielerstellerUI(new Fenster(new Bilderverwalter()));
	}
}
