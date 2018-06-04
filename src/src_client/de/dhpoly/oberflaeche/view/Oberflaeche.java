package de.dhpoly.oberflaeche.view;

import java.awt.BorderLayout;
import java.util.List;
import java.util.Optional;

import javax.swing.JButton;
import javax.swing.JPanel;

import de.dhpoly.datenobjekt.Datenobjekt;
import de.dhpoly.oberflaeche.ElementFactory;

public abstract class Oberflaeche extends JPanel
{
	private static final long serialVersionUID = 1L;
	protected transient Optional<SpielUI> ansicht;

	private JButton butSchliessen;

	public Oberflaeche(SpielUI ansicht)
	{
		this.ansicht = Optional.ofNullable(ansicht);
		ElementFactory.bearbeitePanel(this);
		super.setLayout(new BorderLayout());

		butSchliessen = ElementFactory.getButtonUeberschrift("Schlie�en");
		butSchliessen.addActionListener(e -> schliessen());
		this.add(getSchliessenButton(), BorderLayout.SOUTH);
	}

	public Oberflaeche(SpielUI ansicht, int sekunden)
	{
		this(ansicht);
		new Thread(() -> {
			try
			{
				for (int i = sekunden; i > 0; i--)
				{
					setSchliessenButtonText(i);
					Thread.sleep(1000); // NOSONAR
				}
			}
			catch (InterruptedException ex) // NOSONAR
			{
				// ignorieren
			}
			schliessen();
		}).start();
	}

	public void setSchliessenButtonText(String text)
	{
		butSchliessen.setText(text);
	}

	public void setSchliessenButtonText(int sekunden)
	{
		setSchliessenButtonText("Schlie�en (" + sekunden + ")");
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

	public abstract void zeige(String beschreibung);

	protected void zeigeLinks(String beschreibung)
	{
		ansicht.ifPresent(ui -> ui.hinzuLinks(beschreibung, this));
	}

	protected void zeigeMitte(String beschreibung)
	{
		ansicht.ifPresent(ui -> ui.hinzuMitte(beschreibung, this));
	}

	protected void zeigeRechts(String beschreibung)
	{
		ansicht.ifPresent(ui -> ui.hinzuRechts(beschreibung, this));
	}

	public void sendeAnServer(Datenobjekt antwort)
	{
		ansicht.ifPresent(ui -> ui.sendeAnServer(antwort));
	}

	public abstract boolean isInvalideBeiSpielerWechsel();

	public abstract List<Oberflaeche> durchHinzufuegenUngueltigWerdend(List<Oberflaeche> oberflaechen);
}
