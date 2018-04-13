package de.dhpoly.fehler.control;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;

public class TelegamBenachrichtiger
{
	private static final String CHAT_ID = "-1001131918455";

	private TelegamBenachrichtiger()
	{}

	public static void sendTelegramMessage(String thema, String nachricht) throws IOException
	{
		String toSend = thema //
				.replaceAll("[äÄ]", "ae") //
				.replaceAll("[öÖ]", "oe") //
				.replaceAll("[üÜ]", "ue") //
				.replaceAll("ß", "ss") //
				+ " - " + nachricht;
		String command = "https://api.telegram.org/bot444829640:AAEZSHnTqmqtRkLualeRH4JmvkV95t5o8b8/sendmessage?chat_id="
				+ CHAT_ID + "&text=";
		command += toSend;

		URL url = new URL(command);
		URLConnection connection = url.openConnection();
		connection.getInputStream();
	}
}
