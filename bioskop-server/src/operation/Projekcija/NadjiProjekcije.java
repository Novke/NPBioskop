package operation.Projekcija;

import domen.Film;
import domen.Projekcija;
import domen.Sala;
import operation.AbstractGenericOperation;

import java.util.List;

public class NadjiProjekcije extends AbstractGenericOperation {

    List<Projekcija> list;
    @Override
    protected void preconditions(Object param) throws Exception {
        if (param == null) throw new Exception("Parametar je null");

        Projekcija projekcija = (Projekcija) param;
        if (projekcija.getId() == null) throw new Exception("ID projekcije je null");
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        list = repository.getAll((Projekcija)param);

        for (Projekcija projekcija: list){
            Sala s = (Sala) repository.getById(projekcija.getSala());
            Film f = (Film) repository.getById(projekcija.getFilm());
            projekcija.setSala(s);
            projekcija.setFilm(f);
        }
    }

    public List<Projekcija> getList() {
        return list;
    }
}
