package de.dhpoly.karte.model;

public enum Wetter
{
	SONNE(5, "Endlich Sommer. Die Mieten steigen!"), //
	SCHNEE(3, "Zeit zum Schlittenfahren. Die Mieten steigen!"), //
	BEWOELKT(0, "Normales Wetter"), //
	REGEN(-3, "Eine Regenphase beeinflusst die Mieten negativ."), //
	GEWITTER(-5, "Kaum einer will bei einem solchen Gewitter Urlaub machen. Die Mieten fallen."), //
	;

	private Wetter(int mietbeeinflussung, String beschreibung)
	{
		this.mietbeeinflussung = mietbeeinflussung;
		this.beschreibung = beschreibung;
	}

	private int mietbeeinflussung;
	private String beschreibung;

	public String getBeschreibung()
	{
		return beschreibung;
	}

	public int getMietbeeinflussung()
	{
		// mehr Miete bei besserem Wetter, weniger bei Unwetter
		return mietbeeinflussung;
	}
}
