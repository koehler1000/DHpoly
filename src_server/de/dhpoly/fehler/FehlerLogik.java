package de.dhpoly.fehler;

import java.io.IOException;

import de.dhpoly.logik.Logik;

public interface FehlerLogik extends Logik
{
	void sendTelegramMessage(String thema, String nachricht) throws IOException;
}
