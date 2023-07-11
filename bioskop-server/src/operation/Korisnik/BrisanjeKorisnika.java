package operation.Korisnik;

import domen.Korisnik;
import operation.AbstractGenericOperation;

public class BrisanjeKorisnika extends AbstractGenericOperation {
    @Override
    protected void preconditions(Object param) throws Exception {
        Korisnik korisnik = (Korisnik) param;
        if (korisnik.getId() == null) throw new Exception("Korisnik nema ID, nemoguce brisanje");
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        repository.delete((Korisnik)param);
    }
}
