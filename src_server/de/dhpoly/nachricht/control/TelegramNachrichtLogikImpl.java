package de.dhpoly.nachricht.control;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.logging.Level;
import java.util.logging.Logger;

import de.dhpoly.datenobjekt.Datenobjekt;
import de.dhpoly.empfaenger.model.Empfaenger;
import de.dhpoly.nachricht.TelegramNachrichtLogik;
import de.dhpoly.nachricht.model.Nachricht;
import de.dhpoly.spiel.Spiel;

public class TelegramNachrichtLogikImpl implements TelegramNachrichtLogik
{
	private static final Logger LOGGER = Logger.getLogger(TelegramNachrichtLogikImpl.class.getName());

	private static final String CHAT_ID = "-1001290532140";

	public void sendTelegramMessage(String thema, String nachricht) throws IOException
	{
		String toSend = ersetzeUmlaute(thema) + " - " + ersetzeUmlaute(nachricht);
		String command = "https://api.telegram.org/bot444829640:AAEZSHnTqmqtRkLualeRH4JmvkV95t5o8b8/sendmessage?chat_id="
				+ CHAT_ID + "&text=";
		command += toSend;

		URL url = new URL(command);
		URLConnection connection = url.openConnection();
		connection.getInputStream();
	}

	private String ersetzeUmlaute(String in)
	{
		return in //
				.replaceAll("[äÄ]", "ae") //
				.replaceAll("[öÖ]", "oe") //
				.replaceAll("[üÜ]", "ue") //
				.replaceAll("ß", "ss")//
				.replaceAll("\n", System.lineSeparator())//
				.replaceAll(System.lineSeparator(), " - ");
	}

	@Override
	public void verarbeite(Datenobjekt objekt, Spiel spiel)
	{
		try
		{
			if (objekt instanceof Nachricht)
			{
				Nachricht nachricht = (Nachricht) objekt;
				if (nachricht.getEmpfaenger().isEntwicklerInformieren())
				{
					sendTelegramMessage(nachricht.getTitel(), nachricht.getText());
				}
			}
		}
		catch (IOException ex)
		{
			LOGGER.log(Level.INFO, ex.getMessage(), ex);
			Nachricht fehler = new Nachricht("Fehler beim Senden der Nachricht", Empfaenger.ALLE_SPIELER);
			spiel.zeigeAllenSpielern(fehler);
		}
	}
}
