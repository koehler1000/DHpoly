package de.dhpoly.netzwerk;

import java.io.IOException;

public interface NetzwerkClient
{
	void sendeAnServer(String text) throws IOException;

	void sendeAnServer(Object ob);

	void setDatenobjektverwalter(Datenobjektverwalter verwalter);

	String read();

	void sendQuitMessage();

}
