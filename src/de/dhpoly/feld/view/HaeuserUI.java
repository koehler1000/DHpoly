package de.dhpoly.feld.view;

import java.awt.GridLayout;
import java.util.List;

import javax.swing.JPanel;

public class HaeuserUI extends JPanel
{
	private static final long serialVersionUID = 1L;

	public HaeuserUI(List<HausUI> UIs)
	{
		this.setLayout(new GridLayout(1, 1, 10, 10));
		for (HausUI hausUI : UIs)
		{
			this.add(hausUI);
		}
	}
}
