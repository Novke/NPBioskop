package rs.np.ac.bg.bioskop_server.operation.Film;

import domen.Film;
import rs.np.ac.bg.bioskop_server.operation.AbstractGenericOperation;

import java.util.List;

/**
 * Operacija za pronalaženje svih filmova.
 */
public class NadjiFilmove extends AbstractGenericOperation {

	/**
	 * Privatni atribut lista filmova koji ce se vracati preko Getter-a
	 */
    private List<Film> list;

    /**
     * {@inheritDoc}
     */
    @Override
    protected void preconditions(Object param) throws Exception {
        // Nema prethodnih uslova za ovu operaciju
    }

    /**
     * {@inheritDoc}
     * <p>
     * Izvršava operaciju pronalaženja svih filmova i čuva rezultat u listu.
     * </p>
     *
     * @param param Parametar (nije potreban za ovu operaciju)
     * @throws Exception ako dođe do greške prilikom izvršavanja operacije
     */
    @Override
    protected void executeOperation(Object param) throws Exception {
        list = repository.getAll(new Film());
    }

    /**
     * Vraća listu pronađenih filmova.
     *
     * @return Lista pronađenih filmova
     */
    public List<Film> getList() {
        return list;
    }
}

