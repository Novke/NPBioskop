package operacije;

import java.io.Serializable;

/**
 * Enumeracija za operaciju, opisuje sta je potrebno uciniti sa poslatim domenskim objektom.
 * 
 * @author Novica
 */

public enum Operation implements Serializable {
    LOGIN,    VRATI_KORISNIKE,    VRATI_FILMOVE,
    VRATI_PROJEKCIJE,    OBRISI_FILM,    OBRISI_KORISNIKA,
    OBRISI_PROJEKCIJU,    KREIRAJ_KORISNIKA,    IZMENI_KORISNIKA,
    VRATI_KARTE,    VRATI_OCENE_FILMA,    VRATI_OCENE,
    OCENI_FILM,    KREIRAJ_PROJEKCIJU,    KREIRAJ_FILM,
    IZMENI_FILM,    VRATI_REZERVACIJE,    REZERVISI_KARTE,
    OBRISI_REZERVACIJE
}
