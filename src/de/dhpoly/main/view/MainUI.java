package de.dhpoly.main.view;

import java.awt.Component;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;

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
	private transient Spiel spiel;

	public MainUI(Fenster fenster)
	{
		this.setLayout(new GridLayout(2, 3));

		spiel = new SpielImpl(fenster);
		spiel.setFelder(new Standardspielfeld().getStandardSpielfeld());
		spiel.addBeobachter(this);

		this.add(getSpielerVerwalterPanel(spiel));
		this.add(getSpielVerwalterPanel(fenster, spiel));

		fenster.zeigeComponente(this, "Spielersteller");
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

	private Component getSpielerVerwalterPanel(Spiel spiel)
	{
		JPanel pnl = ElementFactory.erzeugePanel();
		pnl.setLayout(new GridLayout());

		pnl.add(ElementFactory.getTextFeldUeberschrift("Lokale Spieler"));

		txtName = ElementFactory.getTextFeld("Spieler " + (spiel.getSpieler().size() + 1), true);
		pnl.add(txtName);

		JButton butHinzu = ElementFactory.getButtonUeberschrift("+");
		butHinzu.addActionListener(e -> spiel.fuegeSpielerHinzu(txtName.getText()));
		pnl.add(butHinzu);

		return pnl;
	}

	@Override
	public void update()
	{
		txtName.setText("Spieler " + (spiel.getSpieler().size() + 1));
	}
}
