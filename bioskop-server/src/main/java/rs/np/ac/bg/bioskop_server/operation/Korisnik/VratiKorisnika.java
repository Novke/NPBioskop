package rs.np.ac.bg.bioskop_server.operation.Korisnik;

import rs.np.ac.bg.bioskop_common.domen.Korisnik;
import rs.np.ac.bg.bioskop_server.operation.AbstractGenericOperation;


/**
 * Operacija koja vraća korisnika sa datim ID-om.
 */
public class VratiKorisnika extends AbstractGenericOperation {

    private Korisnik korisnik;

    /**
     * Proverava preuslove za izvršavanje operacije.
     *
     * @param param parametar operacije (Korisnik)
     * @throws Exception ukoliko dođe do greške prilikom provere preuslova
     */
    @Override
    protected void preconditions(Object param) throws Exception {
        if (param == null) throw new Exception("Parametar je null");
        Korisnik korisnik = (Korisnik) param;
        if (korisnik.getId() == null) throw new Exception("ID je null, nemoguće pretraživanje");
    }

    /**
     * Izvršava operaciju vraćanja korisnika sa datim ID-om.
     *
     * @param param parametar operacije (Korisnik)
     * @throws Exception ukoliko dođe do greške prilikom izvršavanja operacije
     */
    @Override
    protected void executeOperation(Object param) throws Exception {
        korisnik = (Korisnik) repository.getById((Korisnik) param);
    }

    /**
     * Vraća korisnika.
     *
     * @return korisnik
     */
    public Korisnik getKorisnik() {
        return korisnik;
    }
}

