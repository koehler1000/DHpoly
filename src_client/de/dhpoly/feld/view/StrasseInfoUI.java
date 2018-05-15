package de.dhpoly.feld.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.util.Optional;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;

import de.dhpoly.feld.model.StrasseDaten;
import de.dhpoly.oberflaeche.ElementFactory;
import de.dhpoly.oberflaeche.view.Oberflaeche;
import de.dhpoly.oberflaeche.view.SpielfeldAnsicht;
import de.dhpoly.ressource.model.Ressource;
import de.dhpoly.ressource.model.RessourcenDatensatz;
import de.dhpoly.spieler.model.Spieler;
import de.dhpoly.spieler.view.SpielerFarben;

public class StrasseInfoUI extends Oberflaeche // NOSONAR
{
	private static final long serialVersionUID = 1L;

	private JButton butName = new JButton();
	private JButton butBesitzer = new JButton();

	public StrasseInfoUI(StrasseDaten feld, SpielfeldAnsicht ansicht)
	{
		super(ansicht);

		JPanel frameMieten = ElementFactory.erzeugePanel();
		frameMieten.setLayout(new GridLayout(2, 1));

		StringBuilder sb = new StringBuilder();
		sb.append("Aktuelle Miete: " + feld.getAkuelleMiete() + System.lineSeparator());
		sb.append("Ein Haus kostet: " + System.lineSeparator());
		for (RessourcenDatensatz datensatz : feld.getKostenHaus())
		{
			sb.append(datensatz.getString() + System.lineSeparator());
		}

		JTextArea txtMiete = ElementFactory.getTextFeld(sb.toString(), false);
		frameMieten.add(txtMiete);

		JTextArea txtMieten = ElementFactory.getTextFeld(getMietenText(feld.getMiete()), false);
		frameMieten.add(txtMieten);

		this.add(frameMieten, BorderLayout.CENTER);

		Color backcolor = new Strassengruppe().getColor(feld.getGruppe());

		butName = ElementFactory.getButtonUeberschrift(feld.getName(), backcolor);
		butName.addActionListener(e -> schliessen());
		this.add(butName, BorderLayout.NORTH);

		butBesitzer = ElementFactory.getButtonUeberschrift(getEigentuemerString(feld));
		butBesitzer.addActionListener(e -> schliessen());
		this.add(butBesitzer, BorderLayout.SOUTH);

		feld.getEigentuemer().ifPresent(this::farbeSetzen);
	}

	private String getEigentuemerString(StrasseDaten feld)
	{
		Optional<Spieler> eigentuemer = feld.getEigentuemer();
		return eigentuemer.isPresent() ? eigentuemer.get().getName() : "Zu kaufen";
	}

	private String getMietenText(int[] miete)
	{
		StringBuilder sb = new StringBuilder();
		int z = 0;
		for (int i : miete)
		{
			String text = "Miete mit " + z + " Häusern: ";
			if (z == 0)
			{
				text = "Miete ohne Häuser: ";
			}
			else if (z == 1)
			{
				text = "Miete mit einem Haus: ";
			}
			sb.append(text + Ressource.GELD.getString(i) + System.lineSeparator());
			z++;
		}
		return sb.toString();
	}

	private void farbeSetzen(Spieler spieler)
	{
		Color farbe = SpielerFarben.getSpielerfarbe(spieler.getSpielerNr());
		this.setBorder(new LineBorder(farbe, 10));
	}

	@Override
	public boolean isInvalideBeiSpielerWechsel()
	{
		return false;
	}
}
