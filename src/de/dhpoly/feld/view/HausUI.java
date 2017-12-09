package de.dhpoly.feld.view;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import de.dhpoly.feld.control.Strasse;
import observerpattern.Beobachter;

public class HausUI extends JPanel implements Beobachter
{
	private static final long serialVersionUID = 1L;

	private Strasse strasse;
	private JLabel lblName;
	private JLabel lblAktuelleMiete;

	private JButton butHausBauen;

	private JButton butHausVerkaufen;

	public HausUI(Strasse strasse)
	{
		this.strasse = strasse;
		this.setLayout(new BorderLayout());

		// hier werden alle Komponenten hinzugefügt, die sich in der Laufzeit NICHT
		// ändern können
		lblName = new JLabel(strasse.getBeschriftung());
		lblAktuelleMiete = new JLabel("Aktuelle Miete: " + strasse.getAkuelleMiete());

		JPanel pnlHaeuser = new JPanel();

		butHausBauen = new JButton("+");
		butHausBauen.addActionListener(e -> strasse.hausBauen());
		pnlHaeuser.add(butHausBauen);

		butHausVerkaufen = new JButton("-");
		butHausVerkaufen.addActionListener(e -> strasse.hausVerkaufen());
		pnlHaeuser.add(butHausVerkaufen);

		this.add(lblName, BorderLayout.NORTH);
		this.add(pnlHaeuser, BorderLayout.CENTER);
		this.add(lblAktuelleMiete, BorderLayout.SOUTH);
		update();

		strasse.addBeobachter(this);
	}

	// wird immer aufgerufen, wenn sich etwas im Modell (also in der Straße) ändert.
	// Darunter z.B. Häuser bauen, verkaufen, Eigentümerwechsel, ...
	@Override
	public void update()
	{
		butHausVerkaufen.setEnabled(strasse.getHaeuser() > 0);
		butHausBauen.setEnabled(strasse.isHausbauMoeglich());
		lblAktuelleMiete.setText("Aktuelle Miete: " + strasse.getAkuelleMiete() + " | Häuser: " + strasse.getHaeuser());
	}
}
