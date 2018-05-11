package de.dhpoly.ai;

import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import de.dhpoly.datenobjekt.Datenobjekt;
import de.dhpoly.netzwerk.Datenobjektverwalter;
import de.dhpoly.netzwerk.NetzwerkClient;
import de.dhpoly.spieler.model.Spieler;
import de.dhpoly.wuerfel.model.WuerfelAufruf;

public class AITest
{
	private AI ai;
	private Spieler spieler;

	@Before
	public void startUp() throws IOException
	{
		ai = new AI();
		ai.erzeugeComputerspieler("0.0.0.0", "doofer PC");
		spieler = ai.spieler;
		ai.client = client;
	}

	@Test
	public void wuerfelnSobaldMoeglich() throws Exception
	{
		spieler.setAktuellerSpieler(true);
		ai.empfange(spieler);

		assertTrue(objGesendet instanceof WuerfelAufruf);
	}

	private Object objGesendet;

	NetzwerkClient client = new NetzwerkClient()
	{
		@Override
		public void setDatenobjektverwalter(Datenobjektverwalter verwalter)
		{}

		@Override
		public void sendeAnServer(Datenobjekt ob)
		{
			objGesendet = ob;
		}

		@Override
		public void sendeAnServer(String text) throws IOException
		{}

		@Override
		public void sendQuitMessage()
		{}

		@Override
		public String read()
		{
			return null;
		}
	};
}
