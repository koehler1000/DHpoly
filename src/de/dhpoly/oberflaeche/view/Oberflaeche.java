package de.dhpoly.oberflaeche.view;

import java.util.Optional;

import javax.swing.JPanel;

import de.dhpoly.oberflaeche.ElementFactory;

public class Oberflaeche extends JPanel
{
	protected Optional<SpielfeldAnsicht> ansicht;

	public Oberflaeche(SpielfeldAnsicht ansicht)
	{
		this.ansicht = Optional.ofNullable(ansicht);
		ElementFactory.bearbeitePanel(this);
	}

	protected void entfernen()
	{
		ansicht.ifPresent(e -> e.entferne(this));
	}

	protected void sperren()
	{

	}
}
