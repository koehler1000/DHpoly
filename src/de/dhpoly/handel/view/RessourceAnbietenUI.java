package de.dhpoly.handel.view;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

import de.dhpoly.ressource.RessourcenDatensatz;
import de.dhpoly.ressource.control.RessourcenDatensatzImpl;
import de.dhpoly.ressource.model.Ressource;
import de.dhpoly.spieler.Spieler;
import de.dhpoly.spieler.view.SpielerFarben;

public class RessourceAnbietenUI extends JPanel
{
	private static final long serialVersionUID = 1L;

	private Ressource ressource;
	private JSlider numAnzahl;
	private JLabel lblAnzahl;

	public RessourceAnbietenUI(Spieler spieler, Ressource ressource)
	{
		this.ressource = ressource;

		Color hintergrund = Color.WHITE;
		Color randFarbe = SpielerFarben.getSpielerfarbe(spieler.getSpielerNr());

		Border rand = new LineBorder(hintergrund, 10);

		this.setBackground(hintergrund);

		JLabel lblTitel = new JLabel(ressource.toString() + " von " + spieler.getName());
		lblTitel.setBorder(rand);

		this.setLayout(new BorderLayout());
		this.add(lblTitel, BorderLayout.NORTH);

		int min = 0;
		int max = spieler.getRessourcenWerte(ressource);

		lblAnzahl = new JLabel();
		lblAnzahl.setBorder(rand);
		this.add(lblAnzahl, BorderLayout.SOUTH);

		numAnzahl = new JSlider();
		numAnzahl.setMaximum(max);
		numAnzahl.setValue(min);
		numAnzahl.addChangeListener(e -> lblAktualisieren());
		numAnzahl.setBackground(hintergrund);
		numAnzahl.setBorder(rand);

		this.add(numAnzahl);

		this.setBorder(new LineBorder(randFarbe));

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
