package de.dhpoly.feld.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.border.LineBorder;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;

import de.dhpoly.feld.control.Strasse;
import de.dhpoly.spieler.view.SpielerFarben;
import observerpattern.Beobachter;

public class StrasseUI extends JPanel implements Beobachter
{
	private static final long serialVersionUID = 1L;

	private Strasse feld;
	private JTextPane txtName = new JTextPane();
	private JTextPane txtBesitzer = new JTextPane();

	private Component pnlSpieler = new JPanel();

	public StrasseUI(Strasse feld)
	{
		this.feld = feld;
		txtName.setText(feld.getName());
		this.setLayout(new BorderLayout());

		Color backcolor = new Strassengruppe().getColor(feld.getGruppe());

		txtName.setEditable(false);
		txtName.setFont(new Font("arial", Font.BOLD, 30));
		txtName.setBackground(backcolor);

		StyleContext.NamedStyle centerStyle = StyleContext.getDefaultStyleContext().new NamedStyle();
		StyleConstants.setAlignment(centerStyle, StyleConstants.ALIGN_CENTER);

		txtName.setLogicalStyle(centerStyle);
		txtBesitzer.setLogicalStyle(centerStyle);

		txtBesitzer.setEditable(false);
		txtBesitzer.setFont(new Font("arial", Font.BOLD, 30));
		txtBesitzer.setBackground(Color.WHITE);

		this.add(txtBesitzer, BorderLayout.CENTER);
		this.add(txtName, BorderLayout.NORTH);

		this.setBorder(BorderFactory.createLineBorder(Color.BLACK));

		update();
		feld.addBeobachter(this);
	}

	@Override
	public void update()
	{
		if (feld.getEigentuemer().isPresent())
		{
			txtBesitzer.setText(feld.getEigentuemer().get().getName());
			Color farbe = SpielerFarben.getSpielerfarbe(feld.getEigentuemer().get().getSpielerNr());
			txtBesitzer.setBackground(farbe);
			this.setBackground(farbe);
			txtName.setBorder(new LineBorder(Color.BLACK));
		}
		else
		{
			txtBesitzer.setText("Zu kaufen für " + feld.getKaufpreis() + "€");
			txtBesitzer.setBackground(Color.WHITE);
			this.setBackground(Color.WHITE);
			txtName.setBorder(new LineBorder(Color.BLACK));
		}

		this.remove(pnlSpieler);
		pnlSpieler = new FeldUI(feld.getSpielerAufFeld(), this.getBackground());
		this.add(pnlSpieler, BorderLayout.SOUTH);
	}
}