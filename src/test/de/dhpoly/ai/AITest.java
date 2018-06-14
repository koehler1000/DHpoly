package de.dhpoly.ai;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.net.ConnectException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import de.dhpoly.datenobjekt.Datenobjekt;
import de.dhpoly.netzwerk.Datenobjektverwalter;
import de.dhpoly.netzwerk.NetzwerkClient;
import de.dhpoly.spieler.model.Spieler;
import de.dhpoly.spieler.model.SpielerStatus;
import de.dhpoly.wuerfel.model.WuerfelAufruf;

public class AITest
{
	private AI ai = new AI();
	private Spieler spieler;

	private List<Object> objGesendet = new ArrayList<>();

	private NetzwerkClient client = new NetzwerkClient()
	{
		@Override
		public void setDatenobjektverwalter(Datenobjektverwalter verwalter)
		{}

		@Override
		public void sendeAnServer(Datenobjekt ob)
		{
			objGesendet.add(ob);
		}

		@Override
		public void sendeAnServer(String text) throws IOException
		{}

		@Override
		public void sendQuitMessage()
		{}

		@Override
		public void verbinden(String ip, int port) throws ConnectException, UnknownHostException, IOException
		{}

		@Override
		public boolean verbindungTrennen()
		{
			return false;
		}
	};

	@Before
	public void startUp() throws IOException
	{
		ai.erzeugeComputerspieler(client, "doofer PC");
		spieler = ai.spieler;
	}

	@Test
	public void wuerfelnSobaldMoeglich() throws Exception
	{
		spieler.setSpielerStatus(SpielerStatus.MUSS_WUERFELN);
		ai.verarbeite(spieler);

		assertTrue(hatElementEmpfangen(WuerfelAufruf.class));
	}

	private boolean hatElementEmpfangen(Class<? extends Object> c)
	{
		for (Object object : objGesendet)
		{
			if (c.isInstance(object))
			{
				return true;
			}
		}

		return false;
	}
}
