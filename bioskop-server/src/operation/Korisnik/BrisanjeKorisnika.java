package operation.Korisnik;

import domen.Korisnik;
import operation.AbstractGenericOperation;

/**
 * Operacija za brisanje korisnika.
 */
public class BrisanjeKorisnika extends AbstractGenericOperation {

    /**
     * Proverava preuslove za izvršavanje operacije brisanja korisnika.
     *
     * @param param objekat koji predstavlja korisnika
     * @throws Exception ukoliko dođe do greške prilikom provere preuslova
     */
    @Override
    protected void preconditions(Object param) throws Exception {
        Korisnik korisnik = (Korisnik) param;
        if (korisnik.getId() == null) {
            throw new Exception("Korisnik nema ID, nemoguće brisanje");
        }
    }

    /**
     * Izvršava operaciju brisanja korisnika.
     *
     * @param param objekat koji predstavlja korisnika
     * @throws Exception ukoliko dođe do greške prilikom izvršavanja operacije
     */
    @Override
    protected void executeOperation(Object param) throws Exception {
        repository.delete((Korisnik) param);
    }
}
