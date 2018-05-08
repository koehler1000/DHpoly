package de.dhpoly.feld.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import de.dhpoly.feld.model.Hausbau;
import de.dhpoly.feld.model.Strasse;
import de.dhpoly.oberflaeche.ElementFactory;
import de.dhpoly.oberflaeche.view.Oberflaeche;
import de.dhpoly.oberflaeche.view.SpielfeldAnsicht;

public class HausUI extends Oberflaeche
{
	private static final long serialVersionUID = 1L;

	private JTextArea txtName;
	private JTextArea txtAktuelleMiete;

	private JButton butHausBauen;

	private JButton butHausVerkaufen;

	public HausUI(Strasse strasse, SpielfeldAnsicht ansicht)
	{
		super(ansicht);
		ElementFactory.bearbeitePanel(this);

		txtName = ElementFactory.getTextFeldUeberschrift(strasse.getName());
		txtAktuelleMiete = ElementFactory.getTextFeld("", false);

		JPanel pnlHaeuser = ElementFactory.erzeugePanel();
		pnlHaeuser.setLayout(new GridLayout(1, 3, 10, 10));

		pnlHaeuser.add(txtAktuelleMiete);

		butHausBauen = ElementFactory.getButtonUeberschrift("+");
		butHausBauen.addActionListener(e -> super.sendeAnServer(new Hausbau(strasse, +1)));
		pnlHaeuser.add(butHausBauen);

		butHausVerkaufen = ElementFactory.getButtonUeberschrift("-");
		butHausVerkaufen.addActionListener(e -> super.sendeAnServer(new Hausbau(strasse, -1)));

		pnlHaeuser.add(butHausVerkaufen);

		this.add(txtName, BorderLayout.NORTH);
		this.add(pnlHaeuser, BorderLayout.CENTER);

		butHausVerkaufen.setEnabled(strasse.getHaeuser() > 0);
		txtAktuelleMiete.setText("Aktuelle Miete: " + strasse.getAkuelleMiete() + System.lineSeparator() + "Häuser: "
				+ strasse.getHaeuser());
	}

	@Override
	public boolean isInvalideBeiSpielerWechsel()
	{
		return false;
	}
}
