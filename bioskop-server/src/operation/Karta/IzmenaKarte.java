package operation.Karta;

import domen.Karta;
import operation.AbstractGenericOperation;

/**
 * Operacija za izmenu podataka o karti.
 */
public class IzmenaKarte extends AbstractGenericOperation {

    /**
     * Proverava preuslove za izvršavanje operacije izmene karte.
     *
     * @param param objekat koji predstavlja kartu
     * @throws Exception ukoliko dođe do greške prilikom provere preuslova
     */
    @Override
    protected void preconditions(Object param) throws Exception {
        if (param == null) {
            throw new Exception("Parametar je null");
        }

        Karta karta = (Karta) param;

        if (karta.getKorisnik() == null) {
            throw new Exception("Korisnik na karti je null");
        }
        if (karta.getMesto() <= 0) {
            throw new Exception("Broj mesta na karti je negativan ili jednak nuli");
        }
        if (karta.getRed() <= 0) {
            throw new Exception("Broj reda na karti je negativan ili jednak nuli");
        }
        if (karta.getProjekcija() == null) {
            throw new Exception("Projekcija na karti je null");
        }
        if (karta.getTipKarte().isBlank()) {
            throw new Exception("Tip karte na karti je prazan string");
        }
    }

    /**
     * Izvršava operaciju izmene podataka o karti.
     *
     * @param param objekat koji predstavlja kartu
     * @throws Exception ukoliko dođe do greške prilikom izvršavanja operacije
     */
    @Override
    protected void executeOperation(Object param) throws Exception {
        repository.edit((Karta) param);
    }
}
