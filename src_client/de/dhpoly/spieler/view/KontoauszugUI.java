package de.dhpoly.spieler.view;

import java.awt.Component;
import java.util.Arrays;

import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

import de.dhpoly.oberflaeche.ElementFactory;
import de.dhpoly.oberflaeche.view.Oberflaeche;
import de.dhpoly.oberflaeche.view.SpielfeldAnsicht;
import de.dhpoly.ressource.model.Ressource;
import de.dhpoly.ressource.model.RessourcenDatensatz;
import de.dhpoly.spieler.model.Spieler;

public class KontoauszugUI extends Oberflaeche // NOSONAR
{
	private static final long serialVersionUID = 1L;

	public KontoauszugUI(Spieler spieler, SpielfeldAnsicht ansicht)
	{
		super(ansicht);

		TableModel dataModel = new AbstractTableModel()
		{
			private static final long serialVersionUID = 1L;

			@Override
			public int getColumnCount()
			{
				return Ressource.values().length + 1;
			}

			@Override
			public int getRowCount()
			{
				return spieler.getKasse().size();
			}

			@Override
			public Object getValueAt(int row, int col)
			{
				RessourcenDatensatz datensatz = spieler.getKasse().get(row);
				int idx = Arrays.asList(Ressource.values()).indexOf(datensatz.getRessource());

				if (col == 0)
				{
					return datensatz.getBeschreibung();
				}
				else if (col == idx)
				{
					return spieler.getKasse().get(row).getString();
				}
				else
				{
					return "";
				}
			}
		};

		JTable table = ElementFactory.getTable(dataModel);
		Component pane = ElementFactory.erzeugeScrollPanel(table);
		this.add(pane);
	}

	@Override
	public boolean isInvalideBeiSpielerWechsel()
	{
		return false;
	}
}
