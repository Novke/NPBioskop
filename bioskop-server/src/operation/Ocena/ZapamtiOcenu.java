package operation.Ocena;

import domen.Ocena;
import operation.AbstractGenericOperation;

import java.util.List;

/**
 * Operacija koja služi za dodavanje ili izmenu ocene.
 */
public class ZapamtiOcenu extends AbstractGenericOperation {

    /**
     * Proverava preuslove za izvršavanje operacije.
     *
     * @param param parametar operacije (Ocena)
     * @throws Exception ukoliko dođe do greške prilikom provere preuslova
     */
    @Override
    protected void preconditions(Object param) throws Exception {
        if (param == null) throw new Exception("Parametar je null");

        Ocena ocena = (Ocena) param;

        if (ocena.getFilm() == null) throw new Exception("Film je null");
        if (ocena.getKorisnik() == null) throw new Exception("Korisnik je null");
        if (ocena.getOcena() < 1 || ocena.getOcena() > 10) throw new Exception("Ocena nije u intervalu 1-10");
    }

    /**
     * Izvršava operaciju dodavanja ili izmene ocene.
     *
     * @param param parametar operacije (Ocena)
     * @throws Exception ukoliko dođe do greške prilikom izvršavanja operacije
     */
    @Override
    protected void executeOperation(Object param) throws Exception {
        Ocena ocena = (Ocena) param;

        List<Ocena> sveOcene = repository.getAll(new Ocena());
        for (Ocena o : sveOcene) {
            if (o.getFilm().getId() == ocena.getFilm().getId() &&
                    o.getKorisnik().getId() == ocena.getKorisnik().getId()) {
                ocena.setId(o.getId());
                repository.edit(ocena);
                return;
            }
        }

        repository.add(ocena);
    }
}
