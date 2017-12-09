package de.dhpoly.feld.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import de.dhpoly.feld.control.Strasse;
import de.dhpoly.fenster.view.Fenster;
import observerpattern.Beobachter;

public class HausUI extends JPanel implements Beobachter
{
	private static final long serialVersionUID = 1L;

	private Strasse strasse;
	private JTextArea txtName;
	private JTextArea txtAktuelleMiete;

	private JButton butHausBauen;

	private JButton butHausVerkaufen;

	public HausUI(Strasse strasse)
	{
		this.strasse = strasse;
		this.setLayout(new BorderLayout());

		this.setBackground(Fenster.getDesignfarbe());

		txtName = Fenster.getTextFeldUeberschrift(strasse.getBeschriftung());
		txtAktuelleMiete = Fenster.getTextFeld("", false);

		JPanel pnlHaeuser = new JPanel();
		pnlHaeuser.setLayout(new GridLayout(1, 1, 10, 10));
		pnlHaeuser.setBackground(Fenster.getDesignfarbe());

		butHausBauen = Fenster.getButtonUeberschrift("+");
		butHausBauen.addActionListener(e -> strasse.hausBauen());
		pnlHaeuser.add(butHausBauen);

		butHausVerkaufen = Fenster.getButtonUeberschrift("-");
		butHausVerkaufen.addActionListener(e -> strasse.hausVerkaufen());
		pnlHaeuser.add(butHausVerkaufen);

		this.add(txtName, BorderLayout.NORTH);
		this.add(pnlHaeuser, BorderLayout.CENTER);
		this.add(txtAktuelleMiete, BorderLayout.SOUTH);
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
		txtAktuelleMiete.setText("Aktuelle Miete: " + strasse.getAkuelleMiete() + System.lineSeparator() + "Häuser: "
				+ strasse.getHaeuser());
	}
}
