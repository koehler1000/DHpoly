package de.dhpoly.nachricht.view;

import java.awt.BorderLayout;

import javax.swing.JButton;

import de.dhpoly.nachricht.model.Nachricht;
import de.dhpoly.oberflaeche.ElementFactory;
import de.dhpoly.oberflaeche.view.Oberflaeche;
import de.dhpoly.oberflaeche.view.SpielfeldAnsicht;

public class NachrichtUI extends Oberflaeche // NOSONAR
{
	private static final long serialVersionUID = 1L;

	public NachrichtUI(Nachricht nachricht, SpielfeldAnsicht ansicht)
	{
		super(ansicht);

		this.setLayout(new BorderLayout(10, 10));

		JButton butUeberschrift = ElementFactory.getButtonUeberschrift(nachricht.getTitel());
		JButton butText = ElementFactory.getButton(nachricht.getNachricht());

		this.add(butUeberschrift, BorderLayout.NORTH);
		this.add(butText);

		butUeberschrift.addActionListener(e -> super.schliessen());
		butText.addActionListener(e -> super.schliessen());
	}

	@Override
	public boolean isEinmalig()
	{
		return false;
	}

}
