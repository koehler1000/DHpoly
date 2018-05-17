package de.dhpoly.spieler.view;

import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

import de.dhpoly.oberflaeche.ElementFactory;
import de.dhpoly.oberflaeche.view.Oberflaeche;
import de.dhpoly.oberflaeche.view.SpielfeldAnsicht;
import de.dhpoly.spieler.model.Spieler;

public class KontoauszugUI extends Oberflaeche // NOSONAR
{
	private static final long serialVersionUID = 1L;

	public KontoauszugUI(Spieler spieler, SpielfeldAnsicht ansicht)
	{
		super(ansicht);

		TableModel dataModel = new AbstractTableModel()
		{
			public int getColumnCount()
			{
				return 2;
			}

			public int getRowCount()
			{
				return spieler.getKasse().size();
			}

			public Object getValueAt(int row, int col)
			{
				if (col == 0)
				{
					return spieler.getKasse().get(row).getBeschreibung();
				}
				else
				{
					return spieler.getKasse().get(row).getString();
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
