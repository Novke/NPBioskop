package operation.Ocena;

import domen.Film;
import domen.Korisnik;
import domen.Ocena;
import operation.AbstractGenericOperation;

import java.util.ArrayList;
import java.util.List;

/**
 * Operacija koja vraća ocene za određeni film.
 */
public class VratiOceneFilma extends AbstractGenericOperation {

	/**
	 * Lista ocena namenjena za vracanje kontroleru preko Getter-a
	 */
    private List<Ocena> list = new ArrayList<>();

    /**
     * Proverava preuslove za izvršavanje operacije.
     *
     * @param param parametar operacije (Film)
     * @throws Exception ukoliko dođe do greške prilikom provere preuslova
     */
    @Override
    protected void preconditions(Object param) throws Exception {
        Film film = (Film) param;
        if (film.getId() == null) throw new Exception("ID filma je null");
    }

    /**
     * Izvršava operaciju vraćanja ocena za određeni film.
     *
     * @param param parametar operacije (Film)
     * @throws Exception ukoliko dođe do greške prilikom izvršavanja operacije
     */
    @Override
    protected void executeOperation(Object param) throws Exception {
        Film film = (Film)param;
        List<Ocena> sve = repository.getAll(new Ocena());

        for (Ocena o : sve){
            if (o.getFilm().getId() == film.getId()) list.add(o);
        }

        for (Ocena o : list){
            o.setFilm(film);
            o.setKorisnik((Korisnik) repository.getById(o.getKorisnik()));
        }
    }

    /**
     * Vraća listu ocena za film.
     *
     * @return lista ocena
     */
    public List<Ocena> getList() {
        return list;
    }
}
