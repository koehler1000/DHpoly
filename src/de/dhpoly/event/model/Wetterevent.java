package de.dhpoly.event.model;

import de.dhpoly.event.Event;

public class Wetterevent implements Event
{
	private Wetter wetter;

	@Override
	public String getErklaerung()
	{
		return wetter.getBeschreibung();
	}

	@Override
	public String getTitel()
	{
		return "Wetterumschwung";
	}

}
