package de.dhpoly.spieler.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import de.dhpoly.oberflaeche.ElementFactory;
import de.dhpoly.oberflaeche.view.Oberflaeche;
import de.dhpoly.oberflaeche.view.SpielfeldAnsicht;
import de.dhpoly.ressource.model.RessourcenDatensatz;
import de.dhpoly.spieler.model.Spieler;

public class KontoauszugUI extends Oberflaeche // NOSONAR
{
	private static final long serialVersionUID = 1L;

	public KontoauszugUI(Spieler spieler, SpielfeldAnsicht ansicht)
	{
		super(ansicht);

		JPanel pnlInhalt = ElementFactory.erzeugePanel();
		pnlInhalt.setLayout(new BorderLayout(10, 10));

		JPanel pnl = ElementFactory.erzeugePanel();
		pnl.setLayout(new GridLayout(1, 1));

		JTextArea txtText = ElementFactory.getTextFeld("", false);
		JTextArea txtWert = ElementFactory.getTextFeld("", false);

		for (RessourcenDatensatz transaktion : spieler.getKasse())
		{
			txtText.setText(txtText.getText() + transaktion.getBeschreibung() + System.lineSeparator());
			txtWert.setText(txtWert.getText() + transaktion.getString() + System.lineSeparator());
		}

		JPanel pnlWerte = new JPanel();
		pnlWerte.setLayout(new GridLayout(1, 1));
		pnlWerte.add(txtText);
		pnlWerte.add(txtWert);

		pnl.add(new JScrollPane(pnlWerte));

		pnlInhalt.add(pnl, BorderLayout.CENTER);
		pnlInhalt.add(getSchliessenButton(), BorderLayout.SOUTH);

		this.add(pnlInhalt);
	}

	@Override
	public boolean isInvalideBeiSpielerWechsel()
	{
		return false;
	}
}
