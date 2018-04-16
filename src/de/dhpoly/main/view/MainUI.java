package de.dhpoly.main.view;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import de.dhpoly.oberflaeche.ElementFactory;
import de.dhpoly.oberflaeche.view.Fenster;
import de.dhpoly.spiel.Spiel;
import de.dhpoly.spiel.control.SpielImpl;
import de.dhpoly.spielfeld.model.Standardspielfeld;

public class MainUI extends JPanel
{
	private static final long serialVersionUID = 1L;

	public MainUI(Fenster fenster)
	{
		this.setLayout(new GridLayout(2, 3));

		Spiel spiel = new SpielImpl(fenster);
		spiel.setFelder(new Standardspielfeld().getStandardSpielfeld());

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
			spiel.starteSpiel();
			fenster.loescheKomponente(this);
		});
		this.add(butStart);

		fenster.zeigeComponente(this, "Spielersteller");
	}
}
