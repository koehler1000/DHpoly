package de.dhpoly.handel.view;

import java.awt.Color;
import java.awt.GridLayout;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.LineBorder;

import de.dhpoly.feld.Feld;
import de.dhpoly.handel.model.Transaktion;
import de.dhpoly.spieler.model.SpielerDaten;
import de.dhpoly.spieler.view.SpielerFarben;

public class StrassenAnbietenUI extends JPanel
{
	private static final long serialVersionUID = 1L;

	private Transaktion transaktion;

	public StrassenAnbietenUI(SpielerDaten spielerDaten, Transaktion transaktion)
	{
		this.transaktion = transaktion;

		this.setBackground(Color.WHITE);
		this.setLayout(new GridLayout());

		Color farbe = SpielerFarben.getSpielerfarbe(spielerDaten.getSpielerNr());
		this.setBorder(new LineBorder(farbe));

		JPanel pnlStrassen = new JPanel(new GridLayout(transaktion.getFelderEigentumswechsel(spielerDaten).size(), 1));
		pnlStrassen.setBackground(Color.WHITE);

		// TODO
		// for (Feld feld : spielerDaten.getFelder())
		// {
		// if (feld instanceof FeldStrasse)
		// {
		// pnlStrassen.add(new StrasseAnbietenUI((FeldStrasse) feld, this,
		// transaktion.getFelderEigentumswechsel().contains(feld)));
		// }
		// }

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
