package de.dhpoly.handel.view;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import de.dhpoly.feld.model.StrasseDaten;
import de.dhpoly.feld.view.StrasseVorschauUI;
import de.dhpoly.oberflaeche.ElementFactory;

public class StrasseAnbietenUI extends JPanel
{
	private static final long serialVersionUID = 1L;

	private Color hintergrund = Color.WHITE;
	private Color hintergrundAusgewaehlt = Color.RED;
	private StrassenAnbietenUI strassenAnbietenUi;
	private JButton butAnbieten;
	private boolean angeboten = false;
	private StrasseDaten strasse;

	public StrasseAnbietenUI(StrasseDaten str, StrassenAnbietenUI ui, boolean ausgewaehlt)
	{
		this.strassenAnbietenUi = ui;
		this.strasse = str;
		this.angeboten = ausgewaehlt;

		butAnbieten = ElementFactory.getButton("Anbieten");

		this.setLayout(new BorderLayout(10, 10));
		this.setBorder(new LineBorder(hintergrund, 10));
		this.add(new StrasseVorschauUI(str));

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
			strassenAnbietenUi.feldAuswaehlen(strasse);
		}
		else
		{
			strassenAnbietenUi.feldAuswaehlenRueckgaengig(strasse);
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
