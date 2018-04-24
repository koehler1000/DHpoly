package de.dhpoly.feld.model;

import de.dhpoly.feld.control.FeldStrasse;

public class StrasseKaufen
{
	private StrasseKaufenStatus status = StrasseKaufenStatus.ANGEBOTEN;
	private FeldStrasse strasse;

	public StrasseKaufen(FeldStrasse strasse)
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

	public FeldStrasse getStrasse()
	{
		return strasse;
	}
}
