package de.dhpoly.netzwerk;

public class NetzwerkClientVorschau
{
	public static void main(String[] args) throws Exception
	{
		CreateServer.main(new String[] {});
		CreateClient client = new CreateClient();
		CreateClient.main(new String[] {});
		client.connectToServer();
	}
}
