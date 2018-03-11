package de.dhpoly.fehler.control;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;

public class TelegramNotification
{
	private static final String CHAT_ID = "-1001131918455";

	private TelegramNotification()
	{}

	public static void sendTelegramMessage(String thema, String nachricht) throws IOException
	{
		String toSend = thema + " - " + nachricht;
		String command = "https://api.telegram.org/bot444829640:AAEZSHnTqmqtRkLualeRH4JmvkV95t5o8b8/sendmessage?chat_id="
				+ CHAT_ID + "&text=";
		command += toSend;

		URL url = new URL(command);
		URLConnection connection = url.openConnection();
		connection.getInputStream();
	}
}
