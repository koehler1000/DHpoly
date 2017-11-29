package de.dhpoly.handel.view;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;

import de.dhpoly.handel.model.Transaktion;
import de.dhpoly.ressource.RessourcenDatensatz;
import de.dhpoly.ressource.model.Ressource;
import de.dhpoly.spieler.Spieler;

public class HandelUI extends JPanel
{
	private static final long serialVersionUID = 1L;
	private List<RessourceAnbietenUI> ressourcenGeben = new ArrayList<>();
	private List<RessourceAnbietenUI> ressourcenBekommen = new ArrayList<>();
	private StrasseAnbietenUI felderBekommen;
	private StrasseAnbietenUI felderGeben;
	private Spieler handelAnbieter;
	private Spieler handelPartner;

	public HandelUI(Spieler spieler, Spieler handelsPartner)
	{
		this.handelPartner = handelsPartner;
		this.handelAnbieter = spieler;

		for (Ressource res : Ressource.values())
		{
			RessourceAnbietenUI resAnbieten = new RessourceAnbietenUI(spieler, res);
			ressourcenBekommen.add(resAnbieten);
			this.add(resAnbieten);

			RessourceAnbietenUI resBekommen = new RessourceAnbietenUI(handelsPartner, res);
			ressourcenBekommen.add(resBekommen);
			this.add(resBekommen);
		}

		felderBekommen = new StrasseAnbietenUI(spieler);
		this.add(felderBekommen);

		felderGeben = new StrasseAnbietenUI(spieler);
		this.add(felderGeben);

		JButton butFertig = new JButton("Anbieten");
		butFertig.addActionListener(e -> handelAnbieten());
		this.add(butFertig);
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
