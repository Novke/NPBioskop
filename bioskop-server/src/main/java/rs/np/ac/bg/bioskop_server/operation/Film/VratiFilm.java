package rs.np.ac.bg.bioskop_server.operation.Film;

import rs.np.ac.bg.bioskop_common.domen.Film;
import rs.np.ac.bg.bioskop_server.operation.AbstractGenericOperation;

/**
 * Operacija za vraćanje filma na osnovu ID-ja.
 */
public class VratiFilm extends AbstractGenericOperation {

	/**
	 * Privatni atribut film koji ce se vratiti preko Getter-a
	 */
    private Film film;

    /**
     * {@inheritDoc}
     * <p>
     * Proverava da li je parametar null i da li film ima ID vrednost.
     * </p>
     *
     * @param param Parametar koji predstavlja film za vraćanje
     * @throws Exception ako je parametar null ili ako film nema ID vrednost
     */
    @Override
    protected void preconditions(Object param) throws Exception {
        if (param == null) {
            throw new Exception("Parametar je null");
        }
        Film film = (Film) param;
        if (film.getId() == null) {
            throw new Exception("ID filma je null");
        }
    }

    /**
     * {@inheritDoc}
     * <p>
     * Izvršava operaciju vraćanja filma na osnovu ID-ja iz repozitorijuma.
     * </p>
     *
     * @param param Parametar koji predstavlja film za vraćanje
     * @throws Exception ako dođe do greške prilikom izvršavanja operacije
     */
    @Override
    protected void executeOperation(Object param) throws Exception {
        film = (Film) repository.getById((Film) param);
    }

    /**
     * Metoda za dobijanje vraćenog filma.
     *
     * @return Film koji je vraćen
     */
    public Film getFilm() {
        return film;
    }
}

