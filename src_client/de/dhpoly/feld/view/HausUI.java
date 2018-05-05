package de.dhpoly.feld.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import de.dhpoly.feld.model.Strasse;
import de.dhpoly.oberflaeche.ElementFactory;
import de.dhpoly.spiel.Spiel;
import de.dhpoly.spiel.control.SpielImplTest;

public class HausUI extends JPanel
{
	private static final long serialVersionUID = 1L;

	private JTextArea txtName;
	private JTextArea txtAktuelleMiete;

	private JButton butHausBauen;

	private JButton butHausVerkaufen;

	public HausUI(Strasse strasse)
	{
		ElementFactory.bearbeitePanel(this);

		txtName = ElementFactory.getTextFeldUeberschrift(strasse.getName());
		txtAktuelleMiete = ElementFactory.getTextFeld("", false);

		JPanel pnlHaeuser = ElementFactory.erzeugePanel();
		pnlHaeuser.setLayout(new GridLayout(1, 3, 10, 10));

		pnlHaeuser.add(txtAktuelleMiete);

		Spiel spiel = SpielImplTest.getDefaultSpiel();

		butHausBauen = ElementFactory.getButtonUeberschrift("+");
		// TODO
		// butHausBauen.addActionListener(e -> strasse.hausBauen(spiel));
		pnlHaeuser.add(butHausBauen);

		butHausVerkaufen = ElementFactory.getButtonUeberschrift("-");
		// TODO
		// butHausVerkaufen.addActionListener(e -> strasse.hausVerkaufen(spiel));
		pnlHaeuser.add(butHausVerkaufen);

		this.add(txtName, BorderLayout.NORTH);
		this.add(pnlHaeuser, BorderLayout.CENTER);

		butHausVerkaufen.setEnabled(strasse.getHaueser() > 0);
		// TODO
		// butHausBauen.setEnabled(strasse.kannBebautWerden(spiel));
		// txtAktuelleMiete.setText("Aktuelle Miete: " + strasse.getAkuelleMiete() +
		// System.lineSeparator() + "H�user: "
		// + strasse.getHaeuser());
	}
}
