package de.dhpoly.feld.view;

import java.awt.BorderLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;

import de.dhpoly.feld.control.Strasse;
import observerpattern.Beobachter;

public class HausUI extends JPanel implements Beobachter
{
	private static final long serialVersionUID = 1L;

	private Strasse strasse;
	private JLabel lblName;
	private JSpinner numAnzahl;
	private JLabel lblAktuelleMiete;

	public HausUI(Strasse strasse)
	{
		this.strasse = strasse;
		this.setLayout(new BorderLayout());

		// hier werden alle Komponenten hinzugefügt, die sich in der Laufzeit NICHT
		// ändern können
		lblName = new JLabel(strasse.getBeschriftung());
		numAnzahl = new JSpinner();
		lblAktuelleMiete = new JLabel("Aktuelle Miete: " + strasse.getAkuelleMiete());

		this.add(lblName, BorderLayout.NORTH);
		this.add(numAnzahl);
		this.add(lblAktuelleMiete, BorderLayout.SOUTH);
		update();

		strasse.addBeobachter(this);
	}

	// wird immer aufgerufen, wenn sich etwas im Modell (also in der Straße) ändert.
	// Darunter z.B. Häuser bauen, verkaufen, Eigentümerwechsel, ...
	@Override
	public void update()
	{
		// Zu ändernde Komponenten entfernen
		this.remove(numAnzahl);

		// Komponenten bearbeiten
		SpinnerModel model = new SpinnerNumberModel(strasse.getHaueser(), 0, strasse.getMiete().length, 1);
		numAnzahl = new JSpinner(model);
		numAnzahl.addChangeListener(e -> haeuserAendern());

		// Komponenten wieder hinzufügen
		this.add(numAnzahl, BorderLayout.CENTER);
		lblAktuelleMiete.setText("Aktuelle Miete: " + strasse.getAkuelleMiete());
	}

	// Funtion wird immer dann aufgerufen, wenn sich die Zahl im Spinner ändert
	private void haeuserAendern()
	{

		if ((int) numAnzahl.getValue() > strasse.getHaueser())
		{
			strasse.hausBauen();
		}
		else if ((int) numAnzahl.getValue() < strasse.getHaueser())
		{
			strasse.hausVerkaufen();
		}
	}
}
