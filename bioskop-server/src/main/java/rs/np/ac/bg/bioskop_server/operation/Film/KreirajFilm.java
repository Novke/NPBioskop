package rs.np.ac.bg.bioskop_server.operation.Film;

import rs.np.ac.bg.bioskop_common.domen.Film;
import rs.np.ac.bg.bioskop_server.operation.AbstractGenericOperation;

/**
 * Operacija za kreiranje filma.
 */
public class KreirajFilm extends AbstractGenericOperation {

    /**
     * {@inheritDoc}
     * <p>
     * Prethodni uslovi:
     * - Ocena filma mora biti u opsegu od 0 do 10.
     * - Ime filma ne sme biti prazno ili sadržati samo razmake.
     * - Trajanje filma mora biti veće od 0.
     * - Datum početka prikazivanja filma ne sme biti null.
     * - Opis filma ne sme biti prazan ili sadržati samo razmake.
     * </p>
     *
     * @param param Film koji se kreira
     * @throws Exception ako neki od prethodnih uslova nije ispunjen
     */
    @Override
    protected void preconditions(Object param) throws Exception {
        Film film = (Film) param;
        if (film.getOcena() < 0 || film.getOcena() > 10)
            throw new Exception("Ocena nije u redu");
        if (film.getImeFilma() == null || film.getImeFilma().isBlank())
            throw new Exception("Ime filma nije OK");
        if (film.getTrajanje() <= 0)
            throw new Exception("Trajanje filma nije ok");
        if (film.getPocetakPrikazivanja() == null)
            throw new Exception("Pocetak prikazivanja nije ok");
        if (film.getOpis() == null || film.getOpis().isBlank())
            throw new Exception("Opis filma nije ok");
    }

    /**
     * {@inheritDoc}
     * <p>
     * Izvršava operaciju dodavanja filma u repozitorijum.
     * </p>
     *
     * @param param Film koji se dodaje
     * @throws Exception ako dođe do greške prilikom dodavanja filma
     */
    @Override
    protected void executeOperation(Object param) throws Exception {
        Film film = (Film) param;
        repository.add(film);
    }
}
