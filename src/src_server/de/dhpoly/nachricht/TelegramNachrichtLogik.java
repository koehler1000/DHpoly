package de.dhpoly.nachricht;

import java.io.IOException;

import de.dhpoly.logik.Logik;

public interface TelegramNachrichtLogik extends Logik
{
	void sendTelegramMessage(String thema, String nachricht) throws IOException;
}
