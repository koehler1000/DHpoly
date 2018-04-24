package de.dhpoly.feld.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import de.dhpoly.feld.control.FeldStrasse;
import de.dhpoly.oberflaeche.ElementFactory;
import observerpattern.Beobachter;

public class HausUI extends JPanel implements Beobachter
{
	private static final long serialVersionUID = 1L;

	private transient FeldStrasse strasse;
	private JTextArea txtName;
	private JTextArea txtAktuelleMiete;

	private JButton butHausBauen;

	private JButton butHausVerkaufen;

	public HausUI(FeldStrasse strasse)
	{
		this.strasse = strasse;
		ElementFactory.bearbeitePanel(this);

		txtName = ElementFactory.getTextFeldUeberschrift(strasse.getBeschriftung());
		txtAktuelleMiete = ElementFactory.getTextFeld("", false);

		JPanel pnlHaeuser = ElementFactory.erzeugePanel();
		pnlHaeuser.setLayout(new GridLayout(1, 3, 10, 10));

		pnlHaeuser.add(txtAktuelleMiete);

		butHausBauen = ElementFactory.getButtonUeberschrift("+");
		butHausBauen.addActionListener(e -> strasse.hausBauen());
		pnlHaeuser.add(butHausBauen);

		butHausVerkaufen = ElementFactory.getButtonUeberschrift("-");
		butHausVerkaufen.addActionListener(e -> strasse.hausVerkaufen());
		pnlHaeuser.add(butHausVerkaufen);

		this.add(txtName, BorderLayout.NORTH);
		this.add(pnlHaeuser, BorderLayout.CENTER);
		update();

		strasse.addBeobachter(this);
	}

	// wird immer aufgerufen, wenn sich etwas im Modell (also in der Straße) ändert.
	// Darunter z.B. Häuser bauen, verkaufen, Eigentümerwechsel, ...
	@Override
	public void update()
	{
		butHausVerkaufen.setEnabled(strasse.getHaeuser() > 0);
		butHausBauen.setEnabled(strasse.kannBebautWerden());
		txtAktuelleMiete.setText("Aktuelle Miete: " + strasse.getAkuelleMiete() + System.lineSeparator() + "Häuser: "
				+ strasse.getHaeuser());
	}
}
