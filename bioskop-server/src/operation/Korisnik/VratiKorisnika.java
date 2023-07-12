package operation.Korisnik;

import domen.Korisnik;
import operation.AbstractGenericOperation;

/**
 * Operacija koja vraća korisnika sa datim ID-om.
 */
public class VratiKorisnika extends AbstractGenericOperation {

	/**
	 * Instanca korisnika namenjena za vracanje kontroleru preko Getter-a
	 */
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
     * @throws Exception ukol
