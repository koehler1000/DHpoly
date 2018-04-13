package de.dhpoly.spielersteller.view;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import de.dhpoly.einstellungen.model.EinstellungenImpl;
import de.dhpoly.oberflaeche.ElementFactory;
import de.dhpoly.oberflaeche.view.Fenster;
import de.dhpoly.spiel.Spiel;
import de.dhpoly.spiel.control.SpielImpl;
import de.dhpoly.spiel.model.SpielStatus;
import de.dhpoly.spielfeld.model.Standardspielfeld;
import de.dhpoly.wuerfel.control.WuerfelpaarImpl;

public class SpielerstellerUI extends JPanel
{
	private static final long serialVersionUID = 1L;

	public SpielerstellerUI(Fenster fenster)
	{
		this.setLayout(new GridLayout(2, 3));

		Spiel spiel = new SpielImpl(fenster, new Standardspielfeld().getStandardSpielfeld(), new EinstellungenImpl(),
				new WuerfelpaarImpl());

		this.add(ElementFactory.getTextFeldUeberschrift("Spieler"));

		JTextArea txtName = ElementFactory.getTextFeld("Name", true);
		this.add(txtName);

		JButton butHinzu = ElementFactory.getButtonUeberschrift("+");
		butHinzu.addActionListener(e -> spiel.fuegeSpielerHinzu(txtName.getText()));
		this.add(butHinzu);

		this.add(ElementFactory.getTextFeldUeberschrift("Spiel verwalten"));
		this.add(ElementFactory.getTextFeldUeberschrift(""));

		JButton butStart = ElementFactory.getButtonUeberschrift("Spiel starten");
		butStart.addActionListener(e -> {
			spiel.setStatus(SpielStatus.SPIEL_LAEUFT);
			fenster.loescheKomponente(this);
		});
		this.add(butStart);

		fenster.zeigeComponente(this, "Spielersteller");
	}
}
