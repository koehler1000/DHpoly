package de.dhpoly.fehler.control;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.logging.Level;
import java.util.logging.Logger;

import de.dhpoly.datenobjekt.Datenobjekt;
import de.dhpoly.fehler.FehlerLogik;
import de.dhpoly.fehler.model.Fehler;
import de.dhpoly.nachricht.model.Nachricht;
import de.dhpoly.spiel.Spiel;

public class FehlerLogikImpl implements FehlerLogik
{
	private static final Logger LOGGER = Logger.getLogger(FehlerLogikImpl.class.getName());

	private static final String CHAT_ID = "-1001131918455";

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
				.replaceAll("[��]", "ae") //
				.replaceAll("[��]", "oe") //
				.replaceAll("[��]", "ue") //
				.replaceAll("�", "ss")//
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
				sendTelegramMessage(nachricht.getTitel(), nachricht.getNachricht());
			}
			else if (objekt instanceof Fehler)
			{
				Fehler fehler = (Fehler) objekt;
				sendTelegramMessage(fehler.getTitel(), fehler.getFehlertext());
			}
		}
		catch (IOException ex)
		{
			LOGGER.log(Level.INFO, ex.getMessage(), ex);
		}
	}
}
