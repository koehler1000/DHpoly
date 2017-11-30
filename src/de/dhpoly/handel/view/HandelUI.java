package de.dhpoly.handel.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import de.dhpoly.handel.Handel;
import de.dhpoly.handel.control.HandelImpl;
import de.dhpoly.handel.model.Transaktion;
import de.dhpoly.ressource.RessourcenDatensatz;
import de.dhpoly.ressource.model.Ressource;
import de.dhpoly.spieler.Spieler;

public class HandelUI extends JPanel
{
	private static final long serialVersionUID = 1L;
	private List<RessourceAnbietenUI> ressourcenGeben = new ArrayList<>();
	private List<RessourceAnbietenUI> ressourcenBekommen = new ArrayList<>();
	private StrassenAnbietenUI felderBekommen;
	private StrassenAnbietenUI felderGeben;
	private Spieler handelAnbieter;
	private Spieler handelPartner;
	private Transaktion vorgeschlagen;

	private Color hintergrund = Color.LIGHT_GRAY;
	private Handel handel = new HandelImpl();

	public HandelUI(Spieler spieler, Spieler handelsPartner, Transaktion vorgeschlagen)
	{
		this.handelPartner = handelsPartner;
		this.handelAnbieter = spieler;
		if (vorgeschlagen == null)
		{
			vorgeschlagen = new Transaktion(new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>(),
					spieler, handelsPartner);
		}
		this.vorgeschlagen = vorgeschlagen;

		this.setLayout(new BorderLayout(10, 10));
		this.setBorder(new LineBorder(hintergrund, 10));
		this.setBackground(hintergrund);

		JPanel pnlRessourcen = new JPanel(new GridLayout(Ressource.values().length, 2, 10, 10));
		pnlRessourcen.setBackground(hintergrund);
		for (Ressource res : Ressource.values())
		{
			RessourceAnbietenUI resAnbieten = new RessourceAnbietenUI(spieler, res, getWertGeben(res));
			ressourcenGeben.add(resAnbieten);
			pnlRessourcen.add(resAnbieten);

			RessourceAnbietenUI resBekommen = new RessourceAnbietenUI(handelsPartner, res, getWertBekommen(res));
			ressourcenBekommen.add(resBekommen);
			pnlRessourcen.add(resBekommen);
		}
		this.add(pnlRessourcen, BorderLayout.NORTH);

		JPanel pnlStrassen = new JPanel(new GridLayout(1, 2, 10, 10));
		pnlStrassen.setBackground(hintergrund);
		felderBekommen = new StrassenAnbietenUI(spieler, vorgeschlagen.getFelderBekommen());
		pnlStrassen.add(felderBekommen);
		felderGeben = new StrassenAnbietenUI(handelsPartner, vorgeschlagen.getFelderGeben()); // hier
		pnlStrassen.add(felderGeben);

		this.add(pnlStrassen, BorderLayout.CENTER);

		JButton butFertig = new JButton("Anbieten");
		butFertig.addActionListener(e -> handelAnbieten());
		this.add(butFertig, BorderLayout.SOUTH);
	}

	public HandelUI(Spieler spieler, Spieler handelsPartner)
	{
		this(spieler, handelsPartner, null);
	}

	private void handelAnbieten()
	{
		List<RessourcenDatensatz> datensaetzeGeben = new ArrayList<>();
		List<RessourcenDatensatz> datensaetzeBekommen = new ArrayList<>();

		for (RessourceAnbietenUI handelAngebot : ressourcenBekommen)
		{
			datensaetzeBekommen.add(handelAngebot.getDatensatz());
		}

		for (RessourceAnbietenUI handelAngebot : ressourcenGeben)
		{
			datensaetzeGeben.add(handelAngebot.getDatensatz());
		}

		Transaktion transaktion = new Transaktion(felderGeben.getStrassen(), felderBekommen.getStrassen(),
				datensaetzeGeben, datensaetzeBekommen, handelAnbieter, handelPartner);

		if (transaktion.isGleich(vorgeschlagen))
		{
			handel.vorschlagAnnehmen(transaktion);
			System.out.println("Handel angenommen");
		}
		else
		{
			handel.vorschlagAnbieten(transaktion);
		}
	}

	private int getWertBekommen(Ressource ressource)
	{
		return vorgeschlagen.getWertBekommen(ressource);
	}

	private int getWertGeben(Ressource ressource)
	{
		return vorgeschlagen.getWertGeben(ressource);
	}
}
