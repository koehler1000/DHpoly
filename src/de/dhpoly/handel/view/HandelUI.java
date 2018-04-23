package de.dhpoly.handel.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

import de.dhpoly.handel.Handel;
import de.dhpoly.handel.control.HandelImpl;
import de.dhpoly.handel.model.Transaktion;
import de.dhpoly.handel.model.TransaktionsTyp;
import de.dhpoly.oberflaeche.ElementFactory;
import de.dhpoly.oberflaeche.view.Oberflaeche;
import de.dhpoly.oberflaeche.view.SpielfeldAnsicht;
import de.dhpoly.ressource.model.Ressource;
import observerpattern.Beobachter;

public class HandelUI extends Oberflaeche implements Beobachter // NOSONAR
{
	private static final long serialVersionUID = 1L;

	private transient Handel handel = new HandelImpl();

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
			pnlStrassen.add(new StrassenAnbietenUI(transaktion.getAnbietender(), transaktion));
			pnlStrassen.add(new StrassenAnbietenUI(transaktion.getHandelspartner(), transaktion));
			this.add(pnlStrassen, BorderLayout.CENTER);
		}
		else
		{
			this.add(ElementFactory.getButtonUeberschrift("Handel angenommen"));
		}

		butFertig = ElementFactory.getButtonUeberschrift("Fertig");
		butFertig.addActionListener(e -> handelAbschliessen());
		this.add(butFertig, BorderLayout.SOUTH);

		transaktion.addBeobachter(this);
		update();
	}

	private void handelAbschliessen()
	{
		super.schliessen();

		if (transaktion.getTransaktionsTyp().isHandelAnbieten())
		{
			handel.vorschlagAnbieten(transaktion.getTransaktionsGegenangebot());
		}
		else if (transaktion.getTransaktionsTyp().isHandelAnnehmen())
		{
			handel.vorschlagAnnehmen(transaktion);
		}
	}

	@Override
	public void update()
	{
		butFertig.setText(transaktion.getTransaktionsTyp().getBeschreibung());
	}
}
