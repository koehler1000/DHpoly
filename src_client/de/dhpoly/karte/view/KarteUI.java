package de.dhpoly.karte.view;

import java.awt.BorderLayout;

import javax.swing.JButton;

import de.dhpoly.karte.model.Karte;
import de.dhpoly.oberflaeche.ElementFactory;
import de.dhpoly.oberflaeche.view.Oberflaeche;
import de.dhpoly.oberflaeche.view.SpielfeldAnsicht;

public class KarteUI extends Oberflaeche // NOSONAR
{
	private static final long serialVersionUID = 1L;

	public KarteUI(Karte karte, SpielfeldAnsicht ansicht)
	{
		super(ansicht);
		this.setLayout(new BorderLayout(10, 10));

		JButton butUeberschrift = ElementFactory.getButtonUeberschrift(karte.getTitel());
		JButton butText = ElementFactory.getButton(karte.getBeschreibung());

		this.add(butUeberschrift, BorderLayout.NORTH);
		this.add(butText);

		butUeberschrift.addActionListener(e -> super.schliessen());
		butText.addActionListener(e -> super.schliessen());
	}
}
