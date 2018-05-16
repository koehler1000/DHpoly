package de.dhpoly.handel.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

import de.dhpoly.handel.model.Transaktion;
import de.dhpoly.handel.model.TransaktionsTyp;
import de.dhpoly.oberflaeche.ElementFactory;
import de.dhpoly.oberflaeche.view.Oberflaeche;
import de.dhpoly.oberflaeche.view.SpielfeldAnsicht;
import de.dhpoly.ressource.model.Ressource;

public class HandelUI extends Oberflaeche // NOSONAR
{
	private static final long serialVersionUID = 1L;

	private Transaktion transaktion;

	private JButton butFertig;

	public HandelUI(Transaktion transaktion, SpielfeldAnsicht ansicht)
	{
		super(ansicht);
		this.transaktion = transaktion;

		if (transaktion.getTransaktionsTyp() != TransaktionsTyp.ANGENOMMEN)
		{
			JPanel pnlRessourcen = ElementFactory.erzeugePanel();
			pnlRessourcen.setLayout(new GridLayout(Ressource.values().length, 2, 10, 10));
			for (Ressource res : Ressource.values())
			{
				pnlRessourcen.add(new RessourceAnbietenUI(transaktion, res, transaktion.getAnbietender()));
				pnlRessourcen.add(new RessourceAnbietenUI(transaktion, res, transaktion.getHandelspartner()));
			}
			this.add(pnlRessourcen, BorderLayout.NORTH);

			JPanel pnlStrassen = ElementFactory.erzeugePanel();
			pnlStrassen.setLayout(new GridLayout(1, 2, 10, 10));
			pnlStrassen.add(new StrassenAnbietenUI(transaktion.getAnbietender(), transaktion, ansicht));
			pnlStrassen.add(new StrassenAnbietenUI(transaktion.getHandelspartner(), transaktion, ansicht));
			this.add(pnlStrassen, BorderLayout.CENTER);
		}
		else
		{
			this.add(ElementFactory.getButtonUeberschrift("Handel angenommen"));
		}

		butFertig = getSchliessenButton();

		butFertig.addActionListener(e -> handelAbschliessen());
		this.add(butFertig, BorderLayout.SOUTH);

		update();
	}

	private void handelAbschliessen()
	{
		super.schliessen();
		if (transaktion.getTransaktionsTyp().isHandelAnbieten())
		{
			transaktion.setTransaktionsTyp(TransaktionsTyp.ANGENOMMEN);
			sendeAnServer(transaktion);
		}
		else if (transaktion.getTransaktionsTyp().isHandelAnnehmen())
		{
			transaktion.setTransaktionsTyp(TransaktionsTyp.NEUER_VORSCHLAG);
			sendeAnServer(transaktion);
		}
	}

	public void update()
	{
		butFertig.setText(transaktion.getTransaktionsTyp().getBeschreibung());
	}

	@Override
	public boolean isInvalideBeiSpielerWechsel()
	{
		return false;
	}
}
