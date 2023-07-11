package operation.Korisnik;

import domen.Korisnik;
import operation.AbstractGenericOperation;

import java.util.Date;
import java.util.List;

public class KreiranjeKorisnika extends AbstractGenericOperation {
    @Override
    protected void preconditions(Object param) throws Exception {
        if (param == null) throw new Exception("Parametar je null");
        Korisnik korisnik = (Korisnik) param;
        if (korisnik.getIme().isBlank()) throw new Exception("Ime je prazno");
        if (korisnik.getDatumRodjenja().after(new Date())) throw new Exception("Los datum rodjenja");

        List<Korisnik> korisnikList = repository.getAll(new Korisnik());
        for (Korisnik k : korisnikList){
            if (k.getIme().trim().equals(korisnik.getIme().trim())) throw new Exception("Vec postoji korisnik s tim imenom!");
        }
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        repository.add((Korisnik)param);
    }
}
