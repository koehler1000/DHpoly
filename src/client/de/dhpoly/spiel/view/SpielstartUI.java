package de.dhpoly.spiel.view;

import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;

import de.dhpoly.ai.AI;
import de.dhpoly.fakes.ClientFake;
import de.dhpoly.netzwerk.NetzwerkClient;
import de.dhpoly.oberflaeche.ElementFactory;
import de.dhpoly.oberflaeche.view.Oberflaeche;
import de.dhpoly.oberflaeche.view.SpielUI;
import de.dhpoly.spiel.model.SpielStart;

public class SpielstartUI extends Oberflaeche // NOSONAR
{
	private static final long serialVersionUID = 1L;

	public SpielstartUI(SpielUI ansicht)
	{
		super(ansicht);

		JPanel pnlInhalt = ElementFactory.erzeugePanel();
		pnlInhalt.setLayout(new GridLayout(3, 1, 10, 10));

		JButton butHallo = ElementFactory.getButtonUeberschrift("Hallo " + ansicht.getSpieler().getName());
		pnlInhalt.add(butHallo);

		JButton butStart = ElementFactory.getButtonUeberschrift("Spiel starten");
		butStart.addActionListener(e -> this.sendeAnServer(new SpielStart(ansicht.getSpieler())));
		pnlInhalt.add(butStart);

		JButton butPCSpieler = ElementFactory.getButtonUeberschrift("PC-Spieler hinzufügen");
		butPCSpieler.addActionListener(e -> computerSpielerHinzu());
		pnlInhalt.add(butPCSpieler);

		this.add(pnlInhalt);
		this.remove(getSchliessenButton());
	}

	private void computerSpielerHinzu()
	{
		NetzwerkClient client = ClientFake.CLIENT_FAKE;
		new AI().erzeugeComputerspieler(client, "PC");
	}

	@Override
	public void zeige(String beschreibung)
	{
		zeigeMitte(beschreibung);
	}

	@Override
	public boolean isInvalideBeiSpielerWechsel()
	{
		return false;
	}

	@Override
	public List<Oberflaeche> durchHinzufuegenUngueltigWerdend(List<Oberflaeche> oberflaechen)
	{
		return new ArrayList<>();
	}

}
