package de.dhpoly.feld.model;

import java.util.ArrayList;
import java.util.List;

import de.dhpoly.datenobjekt.Datenobjekt;
import de.dhpoly.feld.view.FeldUI;
import de.dhpoly.oberflaeche.view.Oberflaeche;
import de.dhpoly.spieler.model.Spieler;

public abstract class FeldDaten extends Datenobjekt
{
	private static final long serialVersionUID = 1L;

	private FeldTyp typ;

	private List<Spieler> spielerAufFeld = new ArrayList<>();

	public FeldDaten(FeldTyp typ)
	{
		this.typ = typ;
	}

	@Override
	public String getTitel()
	{
		return "Feld";
	}

	@Override
	public Class<? extends Oberflaeche> getClassUI()
	{
		return FeldUI.class;
	}

	public FeldTyp getTyp()
	{
		return typ;
	}

	public abstract boolean gehoertSpieler(Spieler spieler);

	public List<Spieler> getSpielerAufFeld()
	{
		return spielerAufFeld;
	}

	public void spielerEntfernen(Spieler spieler)
	{
		spielerAufFeld.remove(spieler);
	}

	public void spielerHinzu(Spieler spieler)
	{
		spielerAufFeld.add(spieler);
	}
}
