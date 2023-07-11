package operation.Film;

import domen.Film;
import operation.AbstractGenericOperation;

public class KreirajFilm extends AbstractGenericOperation {
    @Override
    protected void preconditions(Object param) throws Exception {
        Film film = (Film) param;
        if (film.getOcena()<0 || film.getOcena()>10) throw new Exception("Ocena nije u redu");
        if (film.getImeFilma() == null || film.getImeFilma().isBlank()) throw new Exception("Ime filma nije OK");
        if (film.getTrajanje()<=0) throw new Exception("Trajanje filma nije ok");
        if (film.getPocetakPrikazivanja() == null) throw new Exception("Pocetak prikazivanja nije ok");
        if (film.getOpis() == null || film.getOpis().isBlank()) throw new Exception("Opis filma nije ok");
    }

    @Override
    protected void executeOperation(Object param) throws Exception {

        Film film = (Film) param;
        repository.add(film);
    }
}
