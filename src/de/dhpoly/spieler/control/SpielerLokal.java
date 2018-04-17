package de.dhpoly.spieler.control;

import java.util.List;
import java.util.Optional;

import de.dhpoly.datenobjekt.Datenobjekt;
import de.dhpoly.feld.control.Strasse;
import de.dhpoly.handel.model.Transaktion;
import de.dhpoly.karte.Karte;
import de.dhpoly.nachricht.model.Nachricht;
import de.dhpoly.oberflaeche.view.SpielfeldAnsicht;
import de.dhpoly.ressource.model.Ressource;
import de.dhpoly.ressource.model.RessourcenDatensatz;
import de.dhpoly.spiel.Spiel;
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
	public void zeigeTransaktionsvorschlag(Transaktion transaktion)
	{
		ui.ifPresent(e -> e.zeigeTransaktion(transaktion));
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
	public void zeigeTransaktionErfolgreich(Transaktion transaktion)
	{
		String nachrichtentext = "Handel mit " + transaktion.getHandelspartner().getName() + " wurde angenommen";
		Nachricht nachricht = new Nachricht(nachrichtentext);
		zeigeDatenobjekt(nachricht);
	}

	@Override
	public void zeigeTransaktionGescheitert(Transaktion transaktion)
	{
		String nachrichtentext = "Handel mit " + transaktion.getHandelspartner().getName() + " wurde abgelehnt";
		Nachricht nachricht = new Nachricht(nachrichtentext);
		zeigeDatenobjekt(nachricht);
	}

	@Override
	public void zeigeHausbauMoeglichkeit()
	{
		ui.ifPresent(e -> e.zeigeHausbaumoeglichkeit(spiel.getFelder(this)));
	}

	@Override
	public void zeigeDatenobjekt(Datenobjekt objekt)
	{
		Optional.ofNullable(objekt).ifPresent(obj -> ui.ifPresent(e -> e.zeigeObjekt(obj)));
	}

	@Override
	public void leereRand()
	{
		ui.ifPresent(SpielfeldAnsicht::leereRand);
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
