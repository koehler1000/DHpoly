package de.dhpoly.fehler.model;

public enum FehlerTyp
{
	FEHLER_ENTWICKLER, // Fehler nur an Entwickler
	FEHLER_SPIELER, // Fehler nur an den aktuellen Spieler
	FEHLER_ALLE_SPIELER, // Fehler an alle Spieler
	FEHLER_ALLE // Fehler an alle Spieler und Entwickler
}
