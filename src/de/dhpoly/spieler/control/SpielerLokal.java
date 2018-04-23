package de.dhpoly.spieler.control;

import java.util.List;
import java.util.Optional;

import de.dhpoly.datenobjekt.Datenobjekt;
import de.dhpoly.feld.control.Strasse;
import de.dhpoly.karte.Karte;
import de.dhpoly.nachricht.model.Nachricht;
import de.dhpoly.oberflaeche.view.Fenster;
import de.dhpoly.oberflaeche.view.SpielfeldAnsicht;
import de.dhpoly.ressource.model.Ressource;
import de.dhpoly.ressource.model.RessourcenDatensatz;
import de.dhpoly.spiel.Spiel;
import de.dhpoly.wuerfel.control.Wuerfel;
import observerpattern.Beobachter;

public class SpielerLokal extends SpielerImpl
{
	private Optional<SpielfeldAnsicht> ui = Optional.empty();

	public SpielerLokal(String name, Spiel spiel)
	{
		super(name, spiel);
	}

	@Override
	public int getSpielerNr()
	{
		return spielerNr;
	}

	@Override
	public void zeigeKaufmoeglichkeit(Strasse strasse)
	{
		if (strasse.getKaufpreis() <= getRessourcenWerte(Ressource.GELD))
		{
			ui.ifPresent(e -> e.zeigeKaufmoeglichkeit(strasse, this));
		}
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
		informiereBeobachter();
	}

	@Override
	public boolean isAktuellerSpieler()
	{
		return aktuellerSpieler;
	}

	@Override
	public void addBeobachterHinzu(Beobachter beobachter)
	{
		addBeobachter(beobachter);
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
		informiereBeobachter();
	}

	@Override
	public void gewonnen()
	{
		zeigeDatenobjekt(new Nachricht("Gewonnen"));
	}

	@Override
	public void setSpielerNr(int nr)
	{
		spielerNr = nr;
	}

	@Override
	public void setSpielfeldAnsicht(SpielfeldAnsicht ansicht)
	{
		ui = Optional.of(ansicht);
	}

	@Override
	public void zeigeDatenobjekt(Datenobjekt objekt)
	{
		Optional.ofNullable(objekt).ifPresent(obj -> ui.ifPresent(e -> e.zeigeObjekt(obj)));
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

	@Override
	public void setSpielfeldAnsichtDaten(Optional<Fenster> fenster, List<Wuerfel> wuerfel)
	{
		setSpielfeldAnsicht(new SpielfeldAnsicht(spiel, wuerfel, this));
		fenster.ifPresent(e -> ui.ifPresent(u -> e.zeigeSpielansicht(u, name)));
	}
}
