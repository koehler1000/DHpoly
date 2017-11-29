package de.dhpoly.handel.view;

import javax.swing.JPanel;
import javax.swing.JSlider;

import de.dhpoly.ressource.RessourcenDatensatz;
import de.dhpoly.ressource.control.RessourcenDatensatzImpl;
import de.dhpoly.ressource.model.Ressource;
import de.dhpoly.spieler.Spieler;

public class RessourceAnbietenUI extends JPanel
{
	private static final long serialVersionUID = 1L;

	private Ressource ressource;
	private JSlider numAnzahl;

	public RessourceAnbietenUI(Spieler spieler, Ressource ressource)
	{
		this.ressource = ressource;

		int min = 0;
		int max = spieler.getRessourcenWerte(ressource);

		numAnzahl = new JSlider();
		numAnzahl.setMaximum(max);
		numAnzahl.setValue(min);

		this.add(numAnzahl);
	}

	public RessourcenDatensatz getDatensatz()
	{
		return new RessourcenDatensatzImpl(ressource, numAnzahl.getValue());
	}
}
