package de.dhpoly.bilderverwalter;

import javax.swing.ImageIcon;

public interface Bilderverwalter
{
	public ImageIcon getBild(String ordnername, String bildname);

	public ImageIcon getBild(String pfad);
}
