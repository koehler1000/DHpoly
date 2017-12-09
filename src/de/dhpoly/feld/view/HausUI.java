package de.dhpoly.feld.view;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;

import de.dhpoly.feld.control.Strasse;
import de.dhpoly.fenster.view.Fenster;
import observerpattern.Beobachter;

public class HausUI extends JPanel implements Beobachter
{
	private static final long serialVersionUID = 1L;

	private Strasse strasse;
	private JLabel lblName;
	private JTextArea txtAktuelleMiete;

	private JButton butHausBauen;

	private JButton butHausVerkaufen;

	public HausUI(Strasse strasse)
	{
		this.strasse = strasse;
		this.setLayout(new BorderLayout());

		this.setBackground(Fenster.getDesignfarbe());

		lblName = new JLabel(strasse.getBeschriftung());
		lblName.setFont(Fenster.getUeberschriftFont());
		lblName.setBorder(new LineBorder(Fenster.getDesignfarbe(), 10));
		txtAktuelleMiete = new JTextArea();
		txtAktuelleMiete.setFont(Fenster.getStandardFont());
		txtAktuelleMiete.setEditable(false);
		txtAktuelleMiete.setBorder(new LineBorder(Fenster.getDesignfarbe(), 10));
		txtAktuelleMiete.setBackground(Fenster.getDesignfarbe());

		JPanel pnlHaeuser = new JPanel();
		pnlHaeuser.setBackground(Fenster.getDesignfarbe());

		butHausBauen = new JButton("+");
		butHausBauen.addActionListener(e -> strasse.hausBauen());
		butHausBauen.setFont(Fenster.getUeberschriftFont());
		pnlHaeuser.add(butHausBauen);

		butHausVerkaufen = new JButton("-");
		butHausVerkaufen.addActionListener(e -> strasse.hausVerkaufen());
		butHausVerkaufen.setFont(Fenster.getUeberschriftFont());
		pnlHaeuser.add(butHausVerkaufen);

		this.add(lblName, BorderLayout.NORTH);
		this.add(pnlHaeuser, BorderLayout.CENTER);
		this.add(txtAktuelleMiete, BorderLayout.SOUTH);
		update();

		strasse.addBeobachter(this);
	}

	// wird immer aufgerufen, wenn sich etwas im Modell (also in der Straße) ändert.
	// Darunter z.B. Häuser bauen, verkaufen, Eigentümerwechsel, ...
	@Override
	public void update()
	{
		butHausVerkaufen.setEnabled(strasse.getHaeuser() > 0);
		butHausBauen.setEnabled(strasse.isHausbauMoeglich());
		txtAktuelleMiete.setText("Aktuelle Miete: " + strasse.getAkuelleMiete() + System.lineSeparator() + "Häuser: "
				+ strasse.getHaeuser());
	}
}
