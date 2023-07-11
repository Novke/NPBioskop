package operation.Film;

import domen.Film;
import operation.AbstractGenericOperation;

public class ObrisiFilm extends AbstractGenericOperation {
    @Override
    protected void preconditions(Object param) throws Exception {
        Film film = (Film) param;
        if (film.getId() == null) throw new Exception("Film nema ID, nemoguce brisanje!");
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        repository.delete((Film)param);

    }
}
