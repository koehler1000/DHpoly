package de.dhpoly.feld.model;

import de.dhpoly.datenobjekt.Datenobjekt;

public class StrasseKaufen extends Datenobjekt
{
	private static final long serialVersionUID = 1L;

	private StrasseKaufenStatus status = StrasseKaufenStatus.ANGEBOTEN;
	private Strasse strasse;

	public StrasseKaufen(Strasse strasse)
	{
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

	public Strasse getStrasse()
	{
		return strasse;
	}

	@Override
	public String getTitel()
	{
		return "Strassenkauf";
	}
}
