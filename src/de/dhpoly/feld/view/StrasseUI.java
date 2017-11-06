package de.dhpoly.feld.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;

import de.dhpoly.feld.control.Strasse;

public class StrasseUI extends JPanel implements Observer
{
	private static final long serialVersionUID = 1L;

	private Strasse strasse;
	private JTextPane txtName = new JTextPane();
	private JTextPane txtBesitzer = new JTextPane();

	private Component pnlSpieler = new JPanel();

	public StrasseUI(Strasse strasse)
	{
		this.strasse = strasse;
		txtName.setText(strasse.getName());
		update();

		this.setLayout(new BorderLayout());

		strasse.addObserver(this);

		Color backcolor = Color.WHITE;

		txtName.setEditable(false);
		txtName.setFont(new Font("arial", Font.BOLD, 30));
		txtName.setBackground(backcolor);

		StyleContext.NamedStyle centerStyle = StyleContext.getDefaultStyleContext().new NamedStyle();
		StyleConstants.setAlignment(centerStyle, StyleConstants.ALIGN_CENTER);

		txtName.setLogicalStyle(centerStyle);
		txtBesitzer.setLogicalStyle(centerStyle);

		txtBesitzer.setEditable(false);
		txtBesitzer.setFont(new Font("arial", Font.BOLD, 30));
		txtBesitzer.setBackground(backcolor);

		this.add(txtBesitzer, BorderLayout.CENTER);
		this.add(txtName, BorderLayout.NORTH);

		this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
	}

	@Override
	public void update(Observable o, Object arg)
	{
		update();
	}

	private void update()
	{
		if (strasse.getEigentuemer().isPresent())
		{
			txtBesitzer.setText(strasse.getEigentuemer().get().getName());
			txtBesitzer.setBackground(Color.GREEN);
		}
		else
		{
			txtBesitzer.setText("Zu kaufen für " + strasse.getKaufpreis() + "€");
			txtBesitzer.setBackground(Color.WHITE);
		}

		this.remove(pnlSpieler);
		pnlSpieler = new FeldUI().getSpieler(strasse.getSpielerAufFeld());
		this.add(pnlSpieler, BorderLayout.SOUTH);
	}
}
