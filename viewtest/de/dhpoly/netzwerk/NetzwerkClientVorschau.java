package de.dhpoly.netzwerk;

import java.io.IOException;

public class NetzwerkClientVorschau
{
	public static void main(String[] args) throws IOException
	{
		CreateServer server = new CreateServer();
		CreateClient client = new CreateClient();
		String ip = "172....";
		client.connectToServer();
	}
}
