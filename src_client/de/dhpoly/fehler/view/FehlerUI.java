package de.dhpoly.fehler.view;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;

import de.dhpoly.fehler.model.Fehler;
import de.dhpoly.oberflaeche.ElementFactory;
import de.dhpoly.oberflaeche.view.Oberflaeche;
import de.dhpoly.oberflaeche.view.SpielfeldAnsicht;

public class FehlerUI extends Oberflaeche // NOSONAR
{
	private static final long serialVersionUID = 1L;

	public FehlerUI(Fehler fehler, SpielfeldAnsicht ansicht)
	{
		super(ansicht);

		JPanel pnlInhalt = ElementFactory.erzeugePanel();
		pnlInhalt.setLayout(new BorderLayout(10, 10));

		JButton butUeberschrift = ElementFactory.getButtonUeberschrift(fehler.getTitel());
		JButton butText = ElementFactory.getButton(fehler.getFehlertext());

		pnlInhalt.add(butUeberschrift, BorderLayout.NORTH);
		pnlInhalt.add(butText);

		butUeberschrift.addActionListener(e -> super.schliessen());
		butText.addActionListener(e -> super.schliessen());

		this.add(pnlInhalt);
	}

	@Override
	public boolean isInvalideBeiSpielerWechsel()
	{
		return false;
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
