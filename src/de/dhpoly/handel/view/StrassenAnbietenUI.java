package de.dhpoly.handel.view;

import java.awt.Color;
import java.awt.GridLayout;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.LineBorder;

import de.dhpoly.feld.Feld;
import de.dhpoly.feld.control.Strasse;
import de.dhpoly.handel.model.Transaktion;
import de.dhpoly.spieler.Spieler;
import de.dhpoly.spieler.view.SpielerFarben;

public class StrassenAnbietenUI extends JPanel
{
	private static final long serialVersionUID = 1L;

	private Transaktion transaktion;

	public StrassenAnbietenUI(Spieler spieler, Transaktion transaktion)
	{
		this.transaktion = transaktion;

		this.setBackground(Color.WHITE);
		this.setLayout(new GridLayout());

		Color farbe = SpielerFarben.getSpielerfarbe(spieler.getSpielerNr());
		this.setBorder(new LineBorder(farbe));

		JPanel pnlStrassen = new JPanel(new GridLayout(transaktion.getFelderEigentumswechsel(spieler).size(), 1));
		pnlStrassen.setBackground(Color.WHITE);

		for (Feld feld : spieler.getFelder())
		{
			if (feld instanceof Strasse)
			{
				pnlStrassen.add(new StrasseAnbietenUI((Strasse) feld, this,
						transaktion.getFelderEigentumswechsel().contains(feld)));
			}
		}

		this.add(new JScrollPane(pnlStrassen));
	}

	void feldAuswaehlen(Feld feld)
	{
		transaktion.addDatensatzFelderwechsel(feld);
	}

	void feldAuswaehlenRueckgaengig(Feld feld)
	{
		transaktion.removeDatensatzFelderwechsel(feld);
	}

	public List<Feld> getStrassen()
	{
		return transaktion.getFelderEigentumswechsel();
	}
}
