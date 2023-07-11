package operation.Korisnik;

import domen.Korisnik;
import operation.AbstractGenericOperation;

import java.util.List;

public class NadjiKorisnike extends AbstractGenericOperation {
    private List<Korisnik> list;
    @Override
    protected void preconditions(Object param) throws Exception {

    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        list = repository.getAll(new Korisnik());
    }

    public List<Korisnik> getList(){
        return list;
    }
}
