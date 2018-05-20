package de.dhpoly.feld.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import de.dhpoly.feld.model.Hausbau;
import de.dhpoly.feld.model.StrasseDaten;
import de.dhpoly.oberflaeche.ElementFactory;
import de.dhpoly.oberflaeche.view.Oberflaeche;
import de.dhpoly.oberflaeche.view.SpielfeldAnsicht;

public class HausUI extends Oberflaeche // NOSONAR
{
	private static final long serialVersionUID = 1L;

	public HausUI(StrasseDaten strasse, SpielfeldAnsicht ansicht)
	{
		super(ansicht);

		JTextArea txtName = ElementFactory.getTextFeldUeberschrift(strasse.getName());
		JTextArea txtAktuelleMiete = ElementFactory.getTextFeld("", false);

		JPanel pnlHaeuser = ElementFactory.erzeugePanel();
		pnlHaeuser.setLayout(new GridLayout(1, 3, 10, 10));

		pnlHaeuser.add(txtAktuelleMiete);

		JButton butHausBauen = ElementFactory.getButtonUeberschrift("+");
		butHausBauen.addActionListener(e -> super.sendeAnServer(new Hausbau(strasse, +1)));
		pnlHaeuser.add(butHausBauen);

		JButton butHausVerkaufen = ElementFactory.getButtonUeberschrift("-");
		butHausVerkaufen.addActionListener(e -> super.sendeAnServer(new Hausbau(strasse, -1)));

		pnlHaeuser.add(butHausVerkaufen);

		this.add(txtName, BorderLayout.NORTH);
		this.add(pnlHaeuser, BorderLayout.CENTER);

		butHausVerkaufen.setEnabled(strasse.getHaeuser() > 0);
		txtAktuelleMiete.setText("Aktuelle Miete: " + strasse.getAkuelleMiete() + System.lineSeparator() + "Häuser: "
				+ strasse.getHaeuser());

		this.remove(getSchliessenButton());
	}

	@Override
	public boolean isInvalideBeiSpielerWechsel()
	{
		return false;
	}

	@Override
	public void zeige(String beschreibung)
	{
		zeigeLinks(beschreibung);
	}

	@Override
	public List<Oberflaeche> durchHinzufuegenUngueltigWerdend(List<Oberflaeche> oberflaechen)
	{
		return new ArrayList<>();
	}
}
