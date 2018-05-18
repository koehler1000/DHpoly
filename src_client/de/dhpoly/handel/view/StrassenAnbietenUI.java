package de.dhpoly.handel.view;

import java.awt.Color;
import java.awt.GridLayout;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.LineBorder;

import de.dhpoly.datenobjekt.Datenobjekt;
import de.dhpoly.feld.model.StrasseDaten;
import de.dhpoly.handel.model.Transaktion;
import de.dhpoly.oberflaeche.view.Oberflaeche;
import de.dhpoly.oberflaeche.view.SpielfeldAnsicht;
import de.dhpoly.spieler.model.Spieler;
import de.dhpoly.spieler.view.SpielerFarben;

public class StrassenAnbietenUI extends Oberflaeche // NOSONAR
{
	private static final long serialVersionUID = 1L;

	private Transaktion transaktion;

	public StrassenAnbietenUI(Spieler spielerDaten, Transaktion transaktion, SpielfeldAnsicht ansicht)
	{
		super(ansicht);
		this.transaktion = transaktion;

		this.setBackground(Color.WHITE);
		this.setLayout(new GridLayout());

		Color farbe = SpielerFarben.getSpielerfarbe(spielerDaten.getSpielerNr());
		this.setBorder(new LineBorder(farbe));

		JPanel pnlStrassen = new JPanel(new GridLayout(transaktion.getFelderEigentumswechsel(spielerDaten).size(), 1));
		pnlStrassen.setBackground(Color.WHITE);

		// TODO Felder der Spieler anzeigen
		for (StrasseDaten str : spielerDaten.getStrassen())
		{
			pnlStrassen.add(new StrasseAnbietenUI(str, this, transaktion.getFelderEigentumswechsel().contains(str)));
		}

		this.add(new JScrollPane(pnlStrassen));
	}

	void feldAuswaehlen(StrasseDaten strasse)
	{
		transaktion.addDatensatzFelderwechsel(strasse);
	}

	void feldAuswaehlenRueckgaengig(StrasseDaten strasse)
	{
		transaktion.removeDatensatzFelderwechsel(strasse);
	}

	public List<StrasseDaten> getStrassen()
	{
		return transaktion.getFelderEigentumswechsel();
	}

	@Override
	public boolean isInvalideBeiSpielerWechsel()
	{
		return false;
	}

	@Override
	public void zeige(String beschreibung, Datenobjekt objekt)
	{
		// wird nicht direkt angezeigt
	}
}
