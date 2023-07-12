package rs.np.ac.bg.bioskop_server.operation.Film;

import domen.Film;
import rs.np.ac.bg.bioskop_server.operation.AbstractGenericOperation;

/**
 * Operacija za brisanje filma.
 */
public class ObrisiFilm extends AbstractGenericOperation {

    /**
     * {@inheritDoc}
     * <p>
     * Proverava da li film ima ID vrednost.
     * </p>
     *
     * @param param Parametar koji predstavlja film koji se briše
     * @throws Exception ako film nema ID vrednost
     */
    @Override
    protected void preconditions(Object param) throws Exception {
        Film film = (Film) param;
        if (film.getId() == null) {
            throw new Exception("Film nema ID, nemoguće brisanje!");
        }
    }

    /**
     * {@inheritDoc}
     * <p>
     * Izvršava operaciju brisanja filma iz repozitorijuma.
     * </p>
     *
     * @param param Parametar koji predstavlja film koji se briše
     * @throws Exception ako dođe do greške prilikom izvršavanja operacije
     */
    @Override
    protected void executeOperation(Object param) throws Exception {
        repository.delete((Film) param);
    }
}
