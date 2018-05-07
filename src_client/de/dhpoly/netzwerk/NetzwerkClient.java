package de.dhpoly.netzwerk;

import java.io.IOException;

import de.dhpoly.datenobjekt.Datenobjekt;
import de.dhpoly.oberflaeche.view.SpielfeldAnsicht;

public interface NetzwerkClient
{
	void sendeAnServer(String text) throws IOException;
	
	void sendeAnServer(Object ob);

	void addAnsicht(SpielfeldAnsicht ansicht);
	
	String read();
	
	void sendQuitMessage(); 

}
