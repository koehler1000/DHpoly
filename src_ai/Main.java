import java.io.IOException;

import de.dhpoly.netzwerk.NetzwerkClient;
import de.dhpoly.netzwerk.control.NetzwerkClientImpl;
import de.dhpoly.oberflaeche.view.SpielfeldAnsicht;
import de.dhpoly.spieler.model.Spieler;
import de.dhpoly.spieler.model.SpielerTyp;

public class Main
{
	public void erzeugeComputerspieler(String ip, String name) throws IOException
	{
		NetzwerkClient client = new NetzwerkClientImpl(ip);
		Spieler spieler = new Spieler(SpielerTyp.COMPUTER, name);
		client.sendeAnServer(spieler);
		client.addAnsicht(new SpielfeldAnsicht(spieler, client));
	}
}
