package operation.Projekcija;

import domen.Projekcija;
import operation.AbstractGenericOperation;

public class ObrisiProjekciju extends AbstractGenericOperation {
    @Override
    protected void preconditions(Object param) throws Exception {
        Projekcija projekcija = (Projekcija) param;
        if (projekcija.getId() == null) throw new Exception("Projekcija nema ID, nemoguce brisanje!");
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        repository.delete((Projekcija)param);
    }
}
