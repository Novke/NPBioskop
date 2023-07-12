package rs.np.ac.bg.bioskop_server.operation.Korisnik;

import domen.Korisnik;
import rs.np.ac.bg.bioskop_server.operation.AbstractGenericOperation;

import java.util.Date;

/**
 * Operacija za izmenu podataka o korisniku.
 */
public class IzmenaKorisnika extends AbstractGenericOperation {

    /**
     * Proverava preuslove za izvršavanje operacije izmene korisnika.
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
    }

    /**
     * Izvršava operaciju izmene podataka o korisniku.
     *
     * @param param objekat koji predstavlja korisnika
     * @throws Exception ukoliko dođe do greške prilikom izvršavanja operacije
     */
    @Override
    protected void executeOperation(Object param) throws Exception {
        repository.edit((Korisnik) param);
    }
}
