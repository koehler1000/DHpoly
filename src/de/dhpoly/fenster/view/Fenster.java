package de.dhpoly.fenster.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;

import de.dhpoly.bilderverwalter.Bilderverwalter;

public class Fenster extends JFrame
{
	private static final long serialVersionUID = 1L;

	private JPanel inhalt;

	public Fenster(JPanel pnlInhalt)
	{
		this.setTitle("");
		this.setIconImage(Bilderverwalter.getBild(Bilderverwalter.LOGO).getImage());
		this.setLayout(new BorderLayout());
		setInhalt(pnlInhalt);

		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);

		this.setSize(500, 500);
		this.setVisible(true);
	}

	public Fenster(JPanel pnlInhalt, boolean hauptfenster)
	{
		this(pnlInhalt);
		if (hauptfenster)
		{
			this.setTitle("DHPoly");
			this.setIconImage(Bilderverwalter.getBild(Bilderverwalter.LOGO).getImage());
			this.setExtendedState(JFrame.MAXIMIZED_BOTH);
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		}
	}

	public Fenster(JPanel pnlInhalt, String string)
	{
		this(pnlInhalt);
		this.setTitle(string);
	}

	public Fenster()
	{
		this(new JPanel());
		this.setVisible(false);
	}

	public void setInhalt(JPanel pnl, String string)
	{
		setInhalt(pnl);
		this.setTitle(string);
		this.setVisible(true);
	}

	private void setInhalt(JPanel pnl)
	{
		if (inhalt != null)
		{
			this.remove(inhalt);
		}
		inhalt = pnl;
		this.add(pnl);
	}

	public void schliessen()
	{
		this.setVisible(false);
	}

	public static JTextArea getTextFeldUeberschrift(String text)
	{
		JTextArea txtArea = getTextFeld(text, false);
		txtArea.setFont(getUeberschriftFont());
		return txtArea;
	}

	public static JTextArea getTextFeld(String text, boolean aenderbar)
	{
		JTextArea txtArea = new JTextArea(text);
		txtArea.setEditable(aenderbar);
		txtArea.setFont(getStandardFont());
		txtArea.setBackground(getDesignfarbe());
		txtArea.setBorder(new LineBorder(getDesignfarbe(), 10));
		txtArea.setLineWrap(true);
		txtArea.setForeground(getKontrastfarbe());
		return txtArea;
	}

	public static JButton getButton(String text)
	{
		JButton butButton = new JButton(text);
		butButton.setBorder(new LineBorder(getKontrastfarbe(), 10));
		butButton.setFont(getStandardFont());
		butButton.setBackground(getKontrastfarbe());
		butButton.setForeground(getDesignfarbe());
		return butButton;
	}

	public static JButton getButtonUeberschrift(String text)
	{
		JButton butButton = getButton(text);
		butButton.setFont(getUeberschriftFont());
		return butButton;
	}

	public static Font getStandardFont()
	{
		return new Font("arial", Font.PLAIN, 20);
	}

	public static Font getUeberschriftFont()
	{
		return new Font("arial", Font.BOLD, 30);
	}

	public static Font getSpielfeldStrassennameFont()
	{
		return new Font("arial", Font.BOLD, 15);
	}

	public static Font getSpielfeldBesitzerFont()
	{
		return new Font("arial", Font.PLAIN, 12);
	}

	public static Color getDesignfarbe()
	{
		return Color.YELLOW;
	}

	public static Color getKontrastfarbe()
	{
		return Color.BLUE;
	}

	public static JButton getButtonUeberschrift(String beschriftung, Color backcolor)
	{
		JButton but = getButtonUeberschrift(beschriftung);
		but.setBackground(backcolor);
		but.setBorder(new LineBorder(backcolor, 10));
		return but;
	}
}
