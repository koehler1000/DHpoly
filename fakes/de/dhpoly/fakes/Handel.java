package de.dhpoly.fakes;

import javax.swing.JOptionPane;

public class Handel
{

	public static void main(String[] args)
	{
		JOptionPane.showConfirmDialog(null,
				"Peter m�chte mit dir tauschen!\n\nEr bietet:\n- Karlsruhe\n\nEr m�chte daf�r:\n- 100.000�\n\nM�chtest du diesen Handel eingehen?",
				"Handel mit Peter", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
	}

}
