package de.dhpoly.fakes;

import javax.swing.JOptionPane;

public class Handel
{

	public static void main(String[] args)
	{
		JOptionPane.showConfirmDialog(null,
				"Peter möchte mit dir tauschen!\n\nEr bietet:\n- Karlsruhe\n\nEr möchte dafür:\n- 100.000€\n\nMöchtest du diesen Handel eingehen?",
				"Handel mit Peter", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
	}

}
