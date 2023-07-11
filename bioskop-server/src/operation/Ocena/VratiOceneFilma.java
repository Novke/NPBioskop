package operation.Ocena;

import domen.Film;
import domen.Korisnik;
import domen.Ocena;
import operation.AbstractGenericOperation;

import java.util.ArrayList;
import java.util.List;

public class VratiOceneFilma extends AbstractGenericOperation {

    List<Ocena> list = new ArrayList<>();
    @Override
    protected void preconditions(Object param) throws Exception {
        Film film = (Film) param;
        if (film.getId() == null) throw new Exception("ID filma je null");
    }

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

    public List<Ocena> getList() {
        return list;
    }
}
