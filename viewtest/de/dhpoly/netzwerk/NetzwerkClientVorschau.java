package de.dhpoly.netzwerk;

import java.io.IOException;

public class NetzwerkClientVorschau
{
	public static void main(String[] args) throws IOException
	{
		try {
			CreateServer.main(new String[]{});
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		CreateClient client = new CreateClient();
		try {
			client.main(new String[]{});
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		client.connectToServer();
	}
}
