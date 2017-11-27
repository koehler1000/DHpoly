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

		lblName = new JLabel(strasse.getBeschriftung());
		numAnzahl = new JSpinner();
		lblAktuelleMiete = new JLabel("Aktuelle Miete: " + strasse.getAkuelleMiete());

		this.add(lblName, BorderLayout.NORTH);
		this.add(numAnzahl);
		this.add(lblAktuelleMiete, BorderLayout.SOUTH);
		update();

		strasse.addBeobachter(this);
	}

	@Override
	public void update()
	{
		this.remove(numAnzahl);

		SpinnerModel model = new SpinnerNumberModel(strasse.getHaueser(), 0, strasse.getMiete().length, 1);
		numAnzahl = new JSpinner(model);
		numAnzahl.addChangeListener(e -> haeuserAendern());

		this.add(numAnzahl, BorderLayout.CENTER);
		lblAktuelleMiete.setText("Aktuelle Miete: " + strasse.getAkuelleMiete());
	}

	private void haeuserAendern()
	{
		if ((int) numAnzahl.getValue() > strasse.getHaueser())
		{
			strasse.hausBauen();
			System.out.println("Haus gebaut (Anzahl: " + strasse.getHaueser() + ")");
		}
		else if ((int) numAnzahl.getValue() < strasse.getHaueser())
		{
			strasse.hausVerkaufen();
			System.out.println("Haus verkauft");
		}
	}
}
