package rs.np.ac.bg.bioskop_server.operation.Projekcija;

import rs.np.ac.bg.bioskop_common.domen.Film;
import rs.np.ac.bg.bioskop_common.domen.Projekcija;
import rs.np.ac.bg.bioskop_common.domen.Sala;
import rs.np.ac.bg.bioskop_server.operation.AbstractGenericOperation;

import java.util.List;

/**
 * Operacija za pronalaženje projekcija.
 */
public class NadjiProjekcije extends AbstractGenericOperation {

	/**
	 * Lista projekcija namenjena za vracanje kontroleru preko Getter-a
	 */
    private List<Projekcija> list;

    /**
     * Proverava preuslove za izvršavanje operacije.
     *
     * @param param parametar operacije (Projekcija)
     * @throws Exception ukoliko dođe do greške prilikom provere preuslova
     */
    @Override
    protected void preconditions(Object param) throws Exception {
        if (param == null) throw new Exception("Parametar je null");

        Projekcija projekcija = (Projekcija) param;
        if (projekcija.getId() == null) throw new Exception("ID projekcije je null");
    }

    /**
     * Izvršava operaciju pronalaženja projekcija.
     *
     * @param param parametar operacije (Projekcija)
     * @throws Exception ukoliko dođe do greške prilikom izvršavanja operacije
     */
    @Override
    protected void executeOperation(Object param) throws Exception {
        list = repository.getAll((Projekcija) param);

        for (Projekcija projekcija : list) {
            Sala sala = (Sala) repository.getById(projekcija.getSala());
            Film film = (Film) repository.getById(projekcija.getFilm());
            projekcija.setSala(sala);
            projekcija.setFilm(film);
        }
    }

    /**
     * Vraća listu projekcija.
     *
     * @return lista projekcija
     */
    public List<Projekcija> getList() {
        return list;
    }
}
