package de.dhpoly.oberflaeche.view;

import java.util.Optional;

import javax.swing.JButton;
import javax.swing.JPanel;

import de.dhpoly.datenobjekt.Datenobjekt;
import de.dhpoly.oberflaeche.ElementFactory;

public abstract class Oberflaeche extends JPanel
{
	private static final long serialVersionUID = 1L;
	protected transient Optional<SpielfeldAnsicht> ansicht;

	public Oberflaeche(SpielfeldAnsicht ansicht)
	{
		this.ansicht = Optional.ofNullable(ansicht);
		ElementFactory.bearbeitePanel(this);
	}

	protected JButton getSchliessenButton()
	{
		JButton butSchliessen = ElementFactory.getButtonUeberschrift("Schließen");
		butSchliessen.addActionListener(e -> schliessen());
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
}
