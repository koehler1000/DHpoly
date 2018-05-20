package de.dhpoly.karte.view;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;

import de.dhpoly.karte.model.Karte;
import de.dhpoly.oberflaeche.ElementFactory;
import de.dhpoly.oberflaeche.view.Oberflaeche;
import de.dhpoly.oberflaeche.view.SpielfeldAnsicht;

public class KarteUI extends Oberflaeche // NOSONAR
{
	private static final long serialVersionUID = 1L;

	public KarteUI(Karte karte, SpielfeldAnsicht ansicht)
	{
		super(ansicht, 5);

		JPanel pnlInhalt = ElementFactory.erzeugePanel();
		pnlInhalt.setLayout(new BorderLayout(10, 10));

		JButton butUeberschrift = ElementFactory.getButtonUeberschrift(karte.getTitel());
		JButton butText = ElementFactory.getButton(karte.getBeschreibung());

		pnlInhalt.add(butUeberschrift, BorderLayout.NORTH);
		pnlInhalt.add(butText);

		butUeberschrift.addActionListener(e -> super.schliessen());
		butText.addActionListener(e -> super.schliessen());

		this.add(pnlInhalt);
	}

	@Override
	public boolean isInvalideBeiSpielerWechsel()
	{
		return true;
	}

	@Override
	public void zeige(String beschreibung)
	{
		zeigeLinks(beschreibung);
	}

	@Override
	public List<Oberflaeche> durchHinzufuegenUngueltigWerdend(List<Oberflaeche> oberflaechen)
	{
		return new ArrayList<>();
	}
}
