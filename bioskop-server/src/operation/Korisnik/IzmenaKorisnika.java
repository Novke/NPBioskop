package operation.Korisnik;

import domen.Korisnik;
import operation.AbstractGenericOperation;

import java.util.Date;

public class IzmenaKorisnika extends AbstractGenericOperation {
    @Override
    protected void preconditions(Object param) throws Exception {
        if (param == null) throw new Exception("Parametar je null");
        Korisnik korisnik = (Korisnik) param;
        if (korisnik.getIme().isBlank()) throw new Exception("Ime je prazno");
        if (korisnik.getDatumRodjenja().after(new Date())) throw new Exception("Los datum rodjenja");
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        repository.edit((Korisnik)param);
    }
}
