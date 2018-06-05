package de.dhpoly.spieler.view;

import java.awt.Component;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

import de.dhpoly.oberflaeche.ElementFactory;
import de.dhpoly.oberflaeche.view.Oberflaeche;
import de.dhpoly.oberflaeche.view.SpielUI;
import de.dhpoly.ressource.model.Ressource;
import de.dhpoly.ressource.model.RessourcenDatensatz;
import de.dhpoly.spieler.model.Spieler;

public class KontoauszugUI extends Oberflaeche // NOSONAR
{
	private static final long serialVersionUID = 1L;

	public KontoauszugUI(Spieler spieler, SpielUI ansicht)
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
				else if (col == idx + 1)
				{
					return datensatz.getString();
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

	@Override
	public void zeige(String beschreibung)
	{
		zeigeMitte(beschreibung);
		//zeigeLinks(beschreibung);
	}

	@Override
	public List<Oberflaeche> durchHinzufuegenUngueltigWerdend(List<Oberflaeche> oberflaechen)
	{
		return new ArrayList<>();
	}
}
