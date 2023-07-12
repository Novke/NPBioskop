package rs.np.ac.bg.bioskop_server.operation.Ocena;

import rs.np.ac.bg.bioskop_common.domen.Film;
import rs.np.ac.bg.bioskop_common.domen.Karta;
import rs.np.ac.bg.bioskop_common.domen.Korisnik;
import rs.np.ac.bg.bioskop_common.domen.Ocena;
import rs.np.ac.bg.bioskop_server.Controller.Controller;
import rs.np.ac.bg.bioskop_server.operation.AbstractGenericOperation;

import java.util.ArrayList;
import java.util.List;

/**
 * Operacija koja vraća sve ocene.
 */
public class VratiOcene extends AbstractGenericOperation {

	/**
	 * Lista svih ocena koja sluzi za vracanje kontroleru preko Getter-a
	 */
    private List<Ocena> list = new ArrayList<>();

    /**
     * Proverava preuslove za izvršavanje operacije.
     *
     * @param param parametar operacije (null)
     * @throws Exception ukoliko dođe do greške prilikom provere preuslova
     */
    @Override
    protected void preconditions(Object param) throws Exception {
    }

    /**
     * Izvršava operaciju vraćanja svih ocena.
     *
     * @param param parametar operacije (null)
     * @throws Exception ukoliko dođe do greške prilikom izvršavanja operacije
     */
    @Override
    protected void executeOperation(Object param) throws Exception {
        list = repository.getAll(new Ocena());

        for (Ocena o : list){
            o.setFilm((Film) repository.getById(o.getFilm()));
            o.setKorisnik((Korisnik) repository.getById(o.getKorisnik()));
        }
    }

    /**
     * Vraća listu svih ocena.
     *
     * @return lista ocena
     */
    public List<Ocena> getList() {
        return list;
    }
}
