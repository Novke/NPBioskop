package operation.Ocena;

import Controller.Controller;
import domen.Film;
import domen.Karta;
import domen.Korisnik;
import domen.Ocena;
import operation.AbstractGenericOperation;

import java.util.ArrayList;
import java.util.List;

public class VratiOcene extends AbstractGenericOperation {

    List<Ocena> list = new ArrayList<>();
    @Override
    protected void preconditions(Object param) throws Exception {
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        list = repository.getAll(new Ocena());

        for (Ocena o : list){
            o.setFilm((Film) repository.getById(o.getFilm()));
            o.setKorisnik((Korisnik) repository.getById(o.getKorisnik()));
        }
    }

    public List<Ocena> getList() {
        return list;
    }
}
