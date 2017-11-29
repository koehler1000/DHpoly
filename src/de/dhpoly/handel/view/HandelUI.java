package de.dhpoly.handel.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

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

	public HandelUI(Spieler spieler, Spieler handelsPartner)
	{
		this.handelPartner = handelsPartner;
		this.handelAnbieter = spieler;

		Color hintergrund = Color.LIGHT_GRAY;

		this.setLayout(new BorderLayout(10, 10));
		this.setBorder(new LineBorder(hintergrund, 10));
		this.setBackground(hintergrund);

		JPanel pnlRessourcen = new JPanel(new GridLayout(Ressource.values().length, 2, 10, 10));
		pnlRessourcen.setBackground(hintergrund);
		for (Ressource res : Ressource.values())
		{
			RessourceAnbietenUI resAnbieten = new RessourceAnbietenUI(spieler, res);
			ressourcenBekommen.add(resAnbieten);
			pnlRessourcen.add(resAnbieten);

			RessourceAnbietenUI resBekommen = new RessourceAnbietenUI(handelsPartner, res);
			ressourcenBekommen.add(resBekommen);
			pnlRessourcen.add(resBekommen);
		}
		this.add(pnlRessourcen, BorderLayout.NORTH);

		JPanel pnlStrassen = new JPanel(new GridLayout(1, 2, 10, 10));
		pnlStrassen.setBackground(hintergrund);
		felderBekommen = new StrassenAnbietenUI(spieler);
		pnlStrassen.add(felderBekommen);
		felderGeben = new StrassenAnbietenUI(handelsPartner);
		pnlStrassen.add(felderGeben);

		this.add(pnlStrassen, BorderLayout.CENTER);

		JButton butFertig = new JButton("Anbieten");
		butFertig.addActionListener(e -> handelAnbieten());
		this.add(butFertig, BorderLayout.SOUTH);
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

		handelPartner.zeigeTransaktionsvorschlag(transaktion);
	}
}
