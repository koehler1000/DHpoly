package de.dhpoly.nachricht.view;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JTextArea;

import de.dhpoly.nachricht.model.Nachricht;
import de.dhpoly.oberflaeche.ElementFactory;
import de.dhpoly.oberflaeche.view.Oberflaeche;
import de.dhpoly.oberflaeche.view.SpielfeldAnsicht;

public class NachrichtenErstellerUI extends Oberflaeche // NOSONAR
{
	private static final long serialVersionUID = 1L;

	public NachrichtenErstellerUI(SpielfeldAnsicht ansicht)
	{
		super(ansicht);

		JPanel pnlInhalt = ElementFactory.erzeugePanel();

		JTextArea c = ElementFactory.getTextFeld("Ich finde das Spiel super :)", true);
		pnlInhalt.add(c);
		this.add(pnlInhalt);

		getSchliessenButton().setText("Absenden");
		getSchliessenButton().addActionListener(e -> {
			sendeAnServer(new Nachricht(c.getText()));
			schliessen();
		});
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
