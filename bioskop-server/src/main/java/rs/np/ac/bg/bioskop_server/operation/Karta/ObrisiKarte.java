package rs.np.ac.bg.bioskop_server.operation.Karta;

import rs.np.ac.bg.bioskop_common.domen.Karta;
import rs.np.ac.bg.bioskop_server.operation.AbstractGenericOperation;

import java.util.List;

/**
 * Operacija za brisanje karata.
 */
public class ObrisiKarte extends AbstractGenericOperation {

    /**
     * Proverava preuslove za izvršavanje operacije.
     *
     * @param param objekat koji predstavlja listu karata
     * @throws Exception ukoliko dođe do greške prilikom provere preuslova
     */
    @Override
    protected void preconditions(Object param) throws Exception {
        if (param == null) throw new Exception("Parametar je null");

        List<Karta> list = (List<Karta>) param;
        for (Karta karta : list) {
            if (karta.getId() == null) throw new Exception("ID Karte je null");
        }
    }

    /**
     * Izvršava operaciju brisanja karata.
     *
     * @param param objekat koji predstavlja listu karata
     * @throws Exception ukoliko dođe do greške prilikom izvršavanja operacije
     */
    @Override
    protected void executeOperation(Object param) throws Exception {
        List<Karta> list = (List<Karta>) param;
        for (Karta karta : list) {
            repository.delete(karta);
        }
    }
}
