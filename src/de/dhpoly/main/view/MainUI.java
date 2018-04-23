package de.dhpoly.main.view;

import java.awt.Component;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import de.dhpoly.einstellungen.Einstellungen;
import de.dhpoly.einstellungen.model.EinstellungenImpl;
import de.dhpoly.oberflaeche.ElementFactory;
import de.dhpoly.oberflaeche.view.Fenster;
import de.dhpoly.spiel.Spiel;
import de.dhpoly.spiel.control.SpielImpl;
import de.dhpoly.spielfeld.model.Standardspielfeld;
import observerpattern.Beobachter;

public class MainUI extends JPanel implements Beobachter
{
	private static final long serialVersionUID = 1L;

	private JTextArea txtName;
	private JTextArea txtNamePC;
	private transient Spiel spiel;

	public MainUI(Fenster fenster)
	{
		this.setLayout(new GridLayout(3, 1));

		Einstellungen einstellungen = new EinstellungenImpl();

		spiel = new SpielImpl();
		spiel.setFenster(fenster);
		spiel.setFelder(new Standardspielfeld().getStandardSpielfeld(einstellungen));
		spiel.addBeobachter(this);

		this.add(getSpielerLokalVerwalterPanel(spiel));
		this.add(getSpielerComputerVerwalterPanel(spiel));
		this.add(getSpielVerwalterPanel(fenster, spiel));

		fenster.zeigeComponente(this, "Spielersteller");

		update();
	}

	private Component getSpielVerwalterPanel(Fenster fenster, Spiel spiel)
	{
		JPanel pnl = ElementFactory.erzeugePanel();
		pnl.setLayout(new GridLayout());

		pnl.add(ElementFactory.getTextFeldUeberschrift("Spiel verwalten"));
		pnl.add(ElementFactory.getTextFeldUeberschrift(""));

		JButton butStart = ElementFactory.getButtonUeberschrift("Spiel starten");
		butStart.addActionListener(e -> {
			spiel.starteSpiel();
			fenster.loescheKomponente(this);
		});
		pnl.add(butStart);

		return pnl;
	}

	private Component getSpielerComputerVerwalterPanel(Spiel spiel)
	{
		JPanel pnl = ElementFactory.erzeugePanel();
		pnl.setLayout(new GridLayout());

		pnl.add(ElementFactory.getTextFeldUeberschrift("Computerspieler"));

		txtNamePC = ElementFactory.getTextFeld("", true);
		pnl.add(txtNamePC);

		JButton butHinzu = ElementFactory.getButtonUeberschrift("+");
		butHinzu.addActionListener(e -> spiel.fuegeComputerSpielerHinzu(txtNamePC.getText()));
		pnl.add(butHinzu);

		return pnl;
	}

	private Component getSpielerLokalVerwalterPanel(Spiel spiel)
	{
		JPanel pnl = ElementFactory.erzeugePanel();
		pnl.setLayout(new GridLayout());

		pnl.add(ElementFactory.getTextFeldUeberschrift("Lokale Spieler"));

		txtName = ElementFactory.getTextFeld("", true);
		pnl.add(txtName);

		JButton butHinzu = ElementFactory.getButtonUeberschrift("+");
		butHinzu.addActionListener(e -> spiel.fuegeLokalenSpielerHinzu(txtName.getText()));
		pnl.add(butHinzu);

		return pnl;
	}

	@Override
	public void update()
	{
		String spielerName = "Spieler " + (spiel.getSpieler().size() + 1);
		txtName.setText(spielerName);
		txtNamePC.setText(spielerName + " (AI)");
	}
}
