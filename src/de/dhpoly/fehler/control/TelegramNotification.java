package de.dhpoly.fehler.control;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.net.UnknownHostException;

public class TelegramNotification
{
	private static final String chatID = "-1001131918455";

	public static void sendTelegramMessage(String thema, String nachricht) throws UnknownHostException, IOException
	{
		String toSend = thema + " - " + nachricht;
		String command = "https://api.telegram.org/bot444829640:AAEZSHnTqmqtRkLualeRH4JmvkV95t5o8b8/sendmessage?chat_id="
				+ chatID + "&text=";
		command += toSend;

		URL url = new URL(command);
		URLConnection connection = url.openConnection();
		connection.getInputStream();
	}
}
