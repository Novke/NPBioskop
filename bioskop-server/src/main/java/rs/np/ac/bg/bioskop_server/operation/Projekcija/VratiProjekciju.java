package rs.np.ac.bg.bioskop_server.operation.Projekcija;

import domen.Film;
import domen.Projekcija;
import domen.Sala;
import rs.np.ac.bg.bioskop_server.operation.AbstractGenericOperation;

/**
 * Operacija za vraćanje pojedinačne projekcije.
 */
public class VratiProjekciju extends AbstractGenericOperation {

	/**
	 * Instanca projekcije namenjena za vracanje kontroleru preko Getter-a
	 */
    private Projekcija projekcija;

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
     * Izvršava operaciju vraćanja pojedinačne projekcije.
     *
     * @param param parametar operacije (Projekcija)
     * @throws Exception ukoliko dođe do greške prilikom izvršavanja operacije
     */
    @Override
    protected void executeOperation(Object param) throws Exception {
        projekcija = (Projekcija) repository.getById((Projekcija) param);

        Sala s = (Sala) repository.getById(projekcija.getSala());
        Film f = (Film) repository.getById(projekcija.getFilm());
        projekcija.setSala(s);
        projekcija.setFilm(f);
    }

    /**
     * Vraća projekciju.
     *
     * @return projekcija
     */
    public Projekcija getProjekcija() {
        return projekcija;
    }
}
