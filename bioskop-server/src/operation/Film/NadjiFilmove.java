package operation.Film;

import domen.Film;
import operation.AbstractGenericOperation;

import java.util.List;

public class NadjiFilmove extends AbstractGenericOperation {

    List<Film> list;


    @Override
    protected void preconditions(Object param) throws Exception {
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        list = repository.getAll(new Film());
    }
    public List<Film> getList() {
        return list;
    }
}
