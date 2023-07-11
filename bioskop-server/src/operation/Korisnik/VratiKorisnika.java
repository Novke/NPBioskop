package operation.Korisnik;

import domen.Korisnik;
import operation.AbstractGenericOperation;

public class VratiKorisnika extends AbstractGenericOperation {
    private Korisnik korisnik;
    @Override
    protected void preconditions(Object param) throws Exception {
        if (param == null) throw new Exception("Parametar je null");
        Korisnik korisnik = (Korisnik) param;
        if (korisnik.getId() == null) throw new Exception("ID je null, nemoguce pretrazivanje");
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        korisnik = (Korisnik) repository.getById((Korisnik)param);
    }

    public Korisnik getKorisnik(){
        return korisnik;
    }
}
