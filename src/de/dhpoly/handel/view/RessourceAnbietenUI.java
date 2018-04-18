package de.dhpoly.handel.view;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

import de.dhpoly.handel.model.Transaktion;
import de.dhpoly.ressource.model.Ressource;
import de.dhpoly.ressource.model.RessourcenDatensatz;
import de.dhpoly.spieler.Spieler;
import de.dhpoly.spieler.view.SpielerFarben;

public class RessourceAnbietenUI extends JPanel
{
	private static final long serialVersionUID = 1L;

	private Ressource ressource;
	private JSlider numAnzahl;
	private JLabel lblAnzahl;

	private Transaktion transaktion;

	public RessourceAnbietenUI(Transaktion transaktion, Ressource ressource, Spieler spieler)
	{
		this.ressource = ressource;
		this.transaktion = transaktion;

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
		numAnzahl.setMinimum(min);
		numAnzahl.setValue(transaktion.getWert(ressource, spieler));
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
		transaktion.addDatensatzBekommen(new RessourcenDatensatz(ressource, numAnzahl.getValue()));
	}

	public RessourcenDatensatz getDatensatz()
	{
		return new RessourcenDatensatz(ressource, numAnzahl.getValue());
	}
}
