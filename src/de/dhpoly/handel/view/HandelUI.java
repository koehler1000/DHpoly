package de.dhpoly.handel.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;

import de.dhpoly.feld.Feld;
import de.dhpoly.handel.Handel;
import de.dhpoly.handel.control.HandelImpl;
import de.dhpoly.handel.model.Transaktion;
import de.dhpoly.oberflaeche.ElementFactory;
import de.dhpoly.oberflaeche.Oberflaeche;
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
	private transient Spieler handelAnbieter;
	private transient Spieler handelPartner;
	private transient Transaktion vorgeschlagen;

	private transient Handel handel = new HandelImpl();

	public HandelUI(Spieler spieler, Spieler handelsPartner, Transaktion vorgeschlagen)
	{
		this.handelPartner = handelsPartner;
		this.handelAnbieter = spieler;
		if (vorgeschlagen == null)
		{
			vorgeschlagen = new Transaktion(new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), spieler,
					handelsPartner);
		}
		else
		{
			// FIXME Text "Vorschlag annehmen" auf UI, bis irgendein Wert verändert wird,
			// dann "Gegenvorschlag senden" als Text
		}
		this.vorgeschlagen = vorgeschlagen;

		ElementFactory.bearbeitePanel(this);

		JPanel pnlRessourcen = ElementFactory.erzeugePanel();
		pnlRessourcen.setLayout(new GridLayout(Ressource.values().length, 2, 10, 10));
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

		JPanel pnlStrassen = ElementFactory.erzeugePanel();
		pnlStrassen.setLayout(new GridLayout(1, 2, 10, 10));
		felderBekommen = new StrassenAnbietenUI(spieler, vorgeschlagen.getFelderEigentumswechsel());
		pnlStrassen.add(felderBekommen);
		felderGeben = new StrassenAnbietenUI(handelsPartner, vorgeschlagen.getFelderEigentumswechsel()); // hier
		pnlStrassen.add(felderGeben);

		this.add(pnlStrassen, BorderLayout.CENTER);

		JButton butFertig = ElementFactory.getButtonUeberschrift("Anbieten");
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

		List<Feld> felderEigentumswechsel = new ArrayList<>();
		felderEigentumswechsel.addAll(felderGeben.getStrassen());
		felderEigentumswechsel.addAll(felderBekommen.getStrassen());

		Transaktion transaktion = new Transaktion(felderEigentumswechsel, datensaetzeGeben, datensaetzeBekommen,
				handelAnbieter, handelPartner);

		if (transaktion.isGleich(vorgeschlagen))
		{
			handel.vorschlagAnnehmen(transaktion);
			Oberflaeche.getInstance()
					.zeigeKomplettesFenster(ElementFactory.getTextInfoPanel("Info", "Handel angenommen"));
		}
		else
		{
			handel.vorschlagAnbieten(transaktion);
		}

		this.setVisible(false);
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
