package de.dhpoly.handel.view;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import de.dhpoly.feld.control.Strasse;
import de.dhpoly.feld.view.StrasseVorschauUI;
import de.dhpoly.fenster.view.Fenster;

public class StrasseAnbietenUI extends JPanel
{
	private static final long serialVersionUID = 1L;
	private Color hintergrund = Color.WHITE;
	private Color hintergrundAusgewaehlt = Color.RED;
	private StrassenAnbietenUI strasseAnbietenUi;
	private JButton butAnbieten;
	private boolean angeboten = false;
	private transient Strasse strasse;

	public StrasseAnbietenUI(Strasse strasse, StrassenAnbietenUI ui, boolean ausgewaehlt)
	{
		this.strasseAnbietenUi = ui;
		this.strasse = strasse;
		this.angeboten = ausgewaehlt;

		butAnbieten = Fenster.getButton("Anbieten");

		this.setLayout(new BorderLayout(10, 10));
		this.setBorder(new LineBorder(hintergrund, 10));
		this.add(new StrasseVorschauUI(strasse));

		this.setBackground(hintergrund);

		butAnbieten.addActionListener(e -> anbieten());
		this.add(butAnbieten, BorderLayout.SOUTH);

		aktualisiereWerte();
	}

	private void anbieten()
	{
		angeboten = !angeboten;
		if (angeboten)
		{
			strasseAnbietenUi.feldAuswaehlen(strasse);
		}
		else
		{
			strasseAnbietenUi.feldAuswaehlenRueckgaengig(strasse);
		}
		aktualisiereWerte();
	}

	private void aktualisiereWerte()
	{
		if (angeboten)
		{
			this.setBackground(hintergrundAusgewaehlt);
			butAnbieten.setText("Behalten");
		}
		else
		{
			this.setBackground(hintergrund);
			butAnbieten.setText("Anbieten");
		}
		this.setBorder(new LineBorder(getBackground(), 10));
	}
}
