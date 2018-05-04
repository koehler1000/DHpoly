package de.dhpoly.spieler.control;

import java.util.List;
import java.util.Optional;

import de.dhpoly.datenobjekt.Datenobjekt;
import de.dhpoly.karte.model.Karte;
import de.dhpoly.nachricht.model.Nachricht;
import de.dhpoly.oberflaeche.view.SpielfeldAnsicht;
import de.dhpoly.ressource.model.RessourcenDatensatz;
import de.dhpoly.spiel.Spiel;
import de.dhpoly.spieler.model.SpielerDaten;
import de.dhpoly.spieler.model.SpielerTyp;

public class SpielerLokal extends SpielerImpl
{
	private Optional<SpielfeldAnsicht> ui = Optional.empty();

	public SpielerLokal(String name, Spiel spiel)
	{
		super(new SpielerDaten(SpielerTyp.LOKAL, name), spiel);
	}

	@Override
	public void verarbeiteKarte(Karte karte)
	{
		spiel.verarbeiteKarte(karte);
	}

	@Override
	public void setAktuellerSpieler(boolean isAktuell)
	{
		aktuellerSpieler = isAktuell;
	}

	@Override
	public boolean isAktuellerSpieler()
	{
		return aktuellerSpieler;
	}

	@Override
	public void einzahlen(List<RessourcenDatensatz> datensaetze)
	{
		for (RessourcenDatensatz ressourcenDatensatz : datensaetze)
		{
			einzahlen(ressourcenDatensatz);
		}
	}

	@Override
	public void auszahlen(List<RessourcenDatensatz> datensaetze)
	{
		for (RessourcenDatensatz ressourcenDatensatz : datensaetze)
		{
			auszahlen(ressourcenDatensatz);
		}
	}

	@Override
	public boolean kannBezahlen(List<RessourcenDatensatz> kostenHaus)
	{
		for (RessourcenDatensatz ressourcenDatensatz : kostenHaus)
		{
			if (getRessourcenWerte(ressourcenDatensatz.getRessource()) < ressourcenDatensatz.getAnzahl())
			{
				return false;
			}
		}
		return true;
	}

	@Override
	public void ausscheiden()
	{
		strassenZurueckgeben();
		verloren = true;
	}

	@Override
	public void gewonnen()
	{
		zeigeDatenobjekt(new Nachricht("Gewonnen"));
	}

	@Override
	public void setSpielfeldAnsicht(SpielfeldAnsicht ansicht)
	{
		ui = Optional.of(ansicht);
	}

	@Override
	public void zeigeDatenobjekt(Datenobjekt objekt)
	{
		Optional.ofNullable(objekt).ifPresent(obj -> ui.ifPresent(obj::anzeigen));
	}

	@Override
	public void setWuerfelnMoeglich(boolean value)
	{
		ui.ifPresent(e -> e.wuerfelnErmoeglichen(value));
	}

	@Override
	public void setWuerfelWeitergabeMoeglich(boolean value)
	{
		ui.ifPresent(e -> e.wuerfelWeitergabeErmoeglichen(value));
	}
}
