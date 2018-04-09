package de.dhpoly.feld.view;

import java.util.ArrayList;
import java.util.Optional;

import javax.swing.JFrame;

import de.dhpoly.feld.control.Ereignisfeld;
import de.dhpoly.kartenstapel.control.KartenstapelImpl;

public class EreignisfeldUITest // NOSONAR
{
	public static void main(String[] args)
	{
		EreignisfeldUI ui = new EreignisfeldUI(new Ereignisfeld(new KartenstapelImpl(new ArrayList<>())),
				Optional.ofNullable(null));

		JFrame frame = new JFrame("EreignisfeldUITest");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(ui);
		frame.setSize(200, 300);
		frame.setVisible(true);
	}
}
