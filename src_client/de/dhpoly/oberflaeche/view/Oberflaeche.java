package de.dhpoly.oberflaeche.view;

import java.awt.BorderLayout;
import java.util.Optional;

import javax.swing.JButton;
import javax.swing.JPanel;

import de.dhpoly.datenobjekt.Datenobjekt;
import de.dhpoly.oberflaeche.ElementFactory;

public abstract class Oberflaeche extends JPanel
{
	private static final long serialVersionUID = 1L;
	protected transient Optional<SpielfeldAnsicht> ansicht;

	private JButton butSchliessen;

	public Oberflaeche(SpielfeldAnsicht ansicht)
	{
		this.ansicht = Optional.ofNullable(ansicht);
		ElementFactory.bearbeitePanel(this);
		this.setLayout(new BorderLayout());

		butSchliessen = ElementFactory.getButtonUeberschrift("Schließen");
		butSchliessen.addActionListener(e -> schliessen());
		this.add(getSchliessenButton(), BorderLayout.SOUTH);
	}

	protected JButton getSchliessenButton()
	{
		return butSchliessen;
	}

	protected void schliessen()
	{
		ansicht.ifPresent(e -> e.entferne(this));
	}

	protected void sperren()
	{
		this.setEnabled(false);
	}

	public void zeige(String beschreibung, Datenobjekt objekt)
	{
		ansicht.ifPresent(ui -> ui.hinzu(beschreibung, objekt, this));
	}

	public void sendeAnServer(Datenobjekt antwort)
	{
		ansicht.ifPresent(ui -> ui.sendeAnServer(antwort));
	}

	public abstract boolean isInvalideBeiSpielerWechsel();
}
