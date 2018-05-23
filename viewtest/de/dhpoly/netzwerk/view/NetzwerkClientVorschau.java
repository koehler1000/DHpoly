package de.dhpoly.netzwerk.view;

import javax.swing.JOptionPane;

import de.dhpoly.ai.AI;
import de.dhpoly.bilderverwalter.Bilderverwalter;
import de.dhpoly.netzwerk.control.NetzwerkServerImpl;
import de.dhpoly.oberflaeche.view.Fenster;

public class NetzwerkClientVorschau
{
	public static void main(String[] args) throws Exception
	{
		String name;
		while ((name = JOptionPane.showInputDialog("Name für Computerspieler")) != "")
		{
			AI ai = new AI();
			ai.erzeugeComputerspieler("127.0.1", name);
		}

	}
}
