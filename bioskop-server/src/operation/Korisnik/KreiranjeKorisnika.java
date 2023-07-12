package operation.Korisnik;

import domen.Korisnik;
import operation.AbstractGenericOperation;

import java.util.Date;
import java.util.List;

/**
 * Operacija za kreiranje korisnika.
 */
public class KreiranjeKorisnika extends AbstractGenericOperation {

    /**
     * Proverava preuslove za izvršavanje operacije kreiranja korisnika.
     *
     * @param param objekat koji predstavlja korisnika
     * @throws Exception ukoliko dođe do greške prilikom provere preuslova
     */
    @Override
    protected void preconditions(Object param) throws Exception {
        if (param == null) {
            throw new Exception("Parametar je null");
        }
        Korisnik korisnik = (Korisnik) param;
        if (korisnik.getIme().isBlank()) {
            throw new Exception("Ime je prazno");
        }
        if (korisnik.getDatumRodjenja().after(new Date())) {
            throw new Exception("Loš datum rođenja");
        }

        List<Korisnik> korisnikList = repository.getAll(new Korisnik());
        for (Korisnik k : korisnikList) {
            if (k.getIme().trim().equals(korisnik.getIme().trim())) {
                throw new Exception("Već postoji korisnik sa tim imenom!");
            }
        }
    }

    /**
     * Izvršava operaciju kreiranja korisnika.
     *
     * @param param objekat koji predstavlja korisnika
     * @throws Exception ukoliko dođe do greške prilikom izvršavanja operacije
     */
    @Override
    protected void executeOperation(Object param) throws Exception {
        repository.add((Korisnik) param);
    }
}
