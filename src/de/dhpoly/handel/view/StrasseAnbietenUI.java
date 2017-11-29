package de.dhpoly.handel.view;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import de.dhpoly.feld.control.Strasse;
import de.dhpoly.feld.view.StrasseVorschauUI;

public class StrasseAnbietenUI extends JPanel
{
	private static final long serialVersionUID = 1L;
	private Color hintergrund = Color.WHITE;
	private Color hintergrundAusgewaehlt = Color.RED;
	private StrassenAnbietenUI ui;
	private JButton butAnbieten = new JButton("Anbieten");
	private boolean angeboten = false;
	private Strasse strasse;

	public StrasseAnbietenUI(Strasse strasse, StrassenAnbietenUI ui)
	{
		this.ui = ui;
		this.strasse = strasse;

		this.setLayout(new BorderLayout(10, 10));
		this.setBorder(new LineBorder(hintergrund));
		this.add(new StrasseVorschauUI(strasse));

		this.setBackground(hintergrund);

		butAnbieten.addActionListener(e -> anbieten());
		this.add(butAnbieten, BorderLayout.SOUTH);
	}

	private void anbieten()
	{
		angeboten = !angeboten;
		if (angeboten)
		{
			this.setBackground(hintergrundAusgewaehlt);
			butAnbieten.setText("Angebot zurückziehen");
			ui.feldAuswaehlenRueckgaengig(strasse);
		}
		else
		{
			this.setBackground(hintergrund);
			butAnbieten.setText("Anbieten");
			ui.feldAuswaehlen(strasse);
		}
	}
}
