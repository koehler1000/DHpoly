package de.dhpoly.handel.view;

import java.awt.BorderLayout;

import javax.swing.JLabel;
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
	private JLabel lblAnzahl;

	public RessourceAnbietenUI(Spieler spieler, Ressource ressource)
	{
		this.ressource = ressource;

		this.setLayout(new BorderLayout());
		this.add(new JLabel(ressource.toString() + " von " + spieler.getName()), BorderLayout.NORTH);

		int min = 0;
		int max = spieler.getRessourcenWerte(ressource);

		lblAnzahl = new JLabel();
		this.add(lblAnzahl, BorderLayout.SOUTH);

		numAnzahl = new JSlider();
		numAnzahl.setMaximum(max);
		numAnzahl.setValue(min);
		numAnzahl.addChangeListener(e -> lblAktualisieren());

		this.add(numAnzahl);

		lblAktualisieren();
	}

	private void lblAktualisieren()
	{
		lblAnzahl.setText("Aktuelle Auswahl: " + ressource.getString(numAnzahl.getValue()));
	}

	public RessourcenDatensatz getDatensatz()
	{
		return new RessourcenDatensatzImpl(ressource, numAnzahl.getValue());
	}
}
