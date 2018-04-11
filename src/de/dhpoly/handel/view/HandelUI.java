package de.dhpoly.handel.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;

import de.dhpoly.handel.Handel;
import de.dhpoly.handel.control.HandelImpl;
import de.dhpoly.handel.model.Transaktion;
import de.dhpoly.oberflaeche.ElementFactory;
import de.dhpoly.oberflaeche.view.Oberflaeche;
import de.dhpoly.oberflaeche.view.SpielfeldAnsicht;
import de.dhpoly.ressource.model.Ressource;
import observerpattern.Beobachter;

public class HandelUI extends Oberflaeche implements Beobachter // NOSONAR
{
	private static final long serialVersionUID = 1L;
	private List<RessourceAnbietenUI> ressourcenGeben = new ArrayList<>();
	private List<RessourceAnbietenUI> ressourcenBekommen = new ArrayList<>();
	private StrassenAnbietenUI felderBekommen;
	private StrassenAnbietenUI felderGeben;
	private transient Transaktion vorgeschlagen;

	private transient Handel handel = new HandelImpl();

	private Transaktion transaktion;

	private JButton butFertig = new JButton("");

	public HandelUI(Transaktion transaktion, SpielfeldAnsicht ansicht)
	{
		super(ansicht);
		this.transaktion = transaktion;

		butFertig = ElementFactory.getButtonUeberschrift("");
		butFertig.addActionListener(e -> handelAbschliessen());

		// TODO refactoring
		// TODO Veränderungen ins Objekt "Transaktion" aufnehmen

		JPanel pnlRessourcen = ElementFactory.erzeugePanel();
		pnlRessourcen.setLayout(new GridLayout(Ressource.values().length, 2, 10, 10));
		for (Ressource res : Ressource.values())
		{
			RessourceAnbietenUI resAnbieten = new RessourceAnbietenUI(transaktion.getAnbietender(), res,
					vorgeschlagen.getWertGeben(res));
			ressourcenGeben.add(resAnbieten);
			pnlRessourcen.add(resAnbieten);

			RessourceAnbietenUI resBekommen = new RessourceAnbietenUI(transaktion.getHandelspartner(), res,
					vorgeschlagen.getWertBekommen(res));
			ressourcenBekommen.add(resBekommen);
			pnlRessourcen.add(resBekommen);
		}
		this.add(pnlRessourcen, BorderLayout.NORTH);

		JPanel pnlStrassen = ElementFactory.erzeugePanel();
		pnlStrassen.setLayout(new GridLayout(1, 2, 10, 10));
		felderBekommen = new StrassenAnbietenUI(transaktion.getAnbietender(),
				vorgeschlagen.getFelderEigentumswechsel());
		pnlStrassen.add(felderBekommen);
		felderGeben = new StrassenAnbietenUI(transaktion.getHandelspartner(),
				vorgeschlagen.getFelderEigentumswechsel()); // hier
		pnlStrassen.add(felderGeben);

		this.add(pnlStrassen, BorderLayout.CENTER);

		butFertig.addActionListener(e -> handelAbschliessen());
		this.add(butFertig, BorderLayout.SOUTH);

		// TODO ende

		transaktion.addBeobachter(this);
		update();
	}

	private void handelAbschliessen()
	{
		if (transaktion.isVeraendert())
		{
			handel.vorschlagAnbieten(transaktion);
		}
		else
		{
			handel.vorschlagAnnehmen(transaktion);
		}
		this.setVisible(false);
	}

	@Override
	public void update()
	{
		String beschriftung = transaktion.isVeraendert() ? "Anbieten" : "Annehmen";
		butFertig.setText(beschriftung);
	}
}
