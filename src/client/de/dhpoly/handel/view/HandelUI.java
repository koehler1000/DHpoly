package de.dhpoly.handel.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;

import de.dhpoly.handel.model.Transaktion;
import de.dhpoly.handel.model.TransaktionsTyp;
import de.dhpoly.oberflaeche.ElementFactory;
import de.dhpoly.oberflaeche.view.Oberflaeche;
import de.dhpoly.oberflaeche.view.SpielUI;
import de.dhpoly.ressource.model.Ressource;

public class HandelUI extends Oberflaeche // NOSONAR
{
	private static final long serialVersionUID = 1L;

	private Transaktion transaktion;

	private JButton butFertig;

	public HandelUI(Transaktion transaktion, SpielUI ansicht)
	{
		super(ansicht);
		this.transaktion = transaktion;

		JPanel pnl = ElementFactory.erzeugePanel();
		pnl.setLayout(new BorderLayout());

		if (transaktion.getTransaktionsTyp() != TransaktionsTyp.ANGENOMMEN)
		{
			JPanel pnlRessourcen = ElementFactory.erzeugePanel();
			pnlRessourcen.setLayout(new GridLayout(Ressource.values().length, 2, 10, 10));
			for (Ressource res : Ressource.values())
			{
				pnlRessourcen.add(new RessourceAnbietenUI(transaktion, res, transaktion.getAnbietender()));
				pnlRessourcen.add(new RessourceAnbietenUI(transaktion, res, transaktion.getHandelspartner()));
			}

			pnl.add(pnlRessourcen, BorderLayout.NORTH);

			JPanel pnlStrassen = ElementFactory.erzeugePanel();
			pnlStrassen.setLayout(new GridLayout(1, 2, 10, 10));
			pnlStrassen.add(new StrassenAnbietenUI(transaktion.getAnbietender(), transaktion, ansicht));
			pnlStrassen.add(new StrassenAnbietenUI(transaktion.getHandelspartner(), transaktion, ansicht));
			pnl.add(pnlStrassen, BorderLayout.CENTER);
		}
		else
		{
			JButton but = ElementFactory.getButtonUeberschrift("Handel angenommen");
			but.addActionListener(e -> schliessen());
			pnl.add(but);
		}
		this.add(pnl);

		butFertig = getSchliessenButton();
		butFertig.addActionListener(e -> handelAbschliessen());

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
