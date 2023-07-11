package operation.Film;

import domen.Film;
import operation.AbstractGenericOperation;

import java.util.List;

public class VratiFIlm extends AbstractGenericOperation {

    Film film;


    @Override
    protected void preconditions(Object param) throws Exception {

        if (param == null) throw new Exception("Parametar je null");
        Film film = (Film) param;

        if (film.getId() == null) throw new Exception("ID filma je null");
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        film = (Film) repository.getById((Film)param);
    }
    public Film getFilm() {
        return film;
    }
}
