package rs.np.ac.bg.bioskop_server.operation.Korisnik;

import rs.np.ac.bg.bioskop_common.domen.Korisnik;
import rs.np.ac.bg.bioskop_server.operation.AbstractGenericOperation;

import java.util.List;

/**
 * Operacija koja vraća listu svih korisnika.
 */
public class NadjiKorisnike extends AbstractGenericOperation {
	/**
	 * Privatna lista korisnika namenjena za vracanje kontroleru preko Getter-a
	 */

    private List<Korisnik> list;

    /**
     * Proverava preuslove za izvršavanje operacije.
     *
     * @param param parametar operacije (null)
     * @throws Exception ukoliko dođe do greške prilikom provere preuslova
     */
    @Override
    protected void preconditions(Object param) throws Exception {
        // Nema preuslova za ovu operaciju
    }

    /**
     * Izvršava operaciju vraćanja liste korisnika.
     *
     * @param param parametar operacije (null)
     * @throws Exception ukoliko dođe do greške prilikom izvršavanja operacije
     */
    @Override
    protected void executeOperation(Object param) throws Exception {
        list = repository.getAll(new Korisnik());
    }

    /**
     * Vraća listu korisnika.
     *
     * @return lista korisnika
     */
    public List<Korisnik> getList() {
        return list;
    }
}
