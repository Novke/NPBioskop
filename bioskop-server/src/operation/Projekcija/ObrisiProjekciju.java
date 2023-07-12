package operation.Projekcija;

import domen.Projekcija;
import operation.AbstractGenericOperation;

/**
 * Operacija za brisanje projekcije.
 */
public class ObrisiProjekciju extends AbstractGenericOperation {
    /**
     * Proverava preuslove za izvršavanje operacije.
     *
     * @param param parametar operacije (Projekcija)
     * @throws Exception ukoliko dođe do greške prilikom provere preuslova
     */
    @Override
    protected void preconditions(Object param) throws Exception {
        Projekcija projekcija = (Projekcija) param;
        if (projekcija.getId() == null) throw new Exception("Projekcija nema ID, nemoguće brisanje!");
    }

    /**
     * Izvršava operaciju brisanja projekcije.
     *
     * @param param parametar operacije (Projekcija)
     * @throws Exception ukoliko dođe do greške prilikom izvršavanja operacije
     */
    @Override
    protected void executeOperation(Object param) throws Exception {
        repository.delete((Projekcija) param);
    }
}
