package de.dhpoly.feld.model;

import java.util.ArrayList;
import java.util.List;

import de.dhpoly.datenobjekt.Datenobjekt;
import de.dhpoly.feld.view.StrasseKaufenUI;
import de.dhpoly.oberflaeche.view.Oberflaeche;
import de.dhpoly.ressource.model.Ressource;
import de.dhpoly.ressource.model.RessourcenDatensatz;
import de.dhpoly.spieler.model.Spieler;

public class StrasseKaufen extends Datenobjekt
{
	private static final long serialVersionUID = 1L;

	private StrasseKaufenStatus status = StrasseKaufenStatus.ANGEBOTEN;
	private StrasseDaten strasse;
	private Spieler spieler;

	public StrasseKaufen(StrasseDaten strasse, Spieler spieler)
	{
		this.spieler = spieler;
		this.strasse = strasse;
	}

	public StrasseKaufenStatus getStatus()
	{
		return status;
	}

	public void setStatus(StrasseKaufenStatus status)
	{
		this.status = status;
	}

	public StrasseDaten getStrasse()
	{
		return strasse;
	}

	@Override
	public String getTitel()
	{
		return "Strassenkauf";
	}

	public List<RessourcenDatensatz> getKaufpreis()
	{
		List<RessourcenDatensatz> kaufPreis = new ArrayList<>();
		kaufPreis.add(new RessourcenDatensatz(Ressource.GELD, strasse.getKaufpreis(), "Kauf von " + strasse.getName()));
		return kaufPreis;
	}

	public boolean isKaufbar()
	{
		return !strasse.getEigentuemer().isPresent();
	}

	public void setEigentuemer(Spieler sp)
	{
		strasse.setEigentuemer(sp);
	}

	@Override
	public Class<? extends Oberflaeche> getClassUI()
	{
		return StrasseKaufenUI.class;
	}

	public Spieler getSender()
	{
		return spieler;
	}
}
