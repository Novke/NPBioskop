package operation.Karta;

import domen.Karta;
import operation.AbstractGenericOperation;

public class IzmenaKarte extends AbstractGenericOperation {
    @Override
    protected void preconditions(Object param) throws Exception {
        if (param==null) throw new Exception("Parametar je null");

        Karta karta = (Karta) param;

        if (karta.getKorisnik() == null) throw new Exception("Korisnik je null");
        if (karta.getMesto() <= 0) throw new Exception("Mesto je negativan broj");
        if (karta.getRed()<=0) throw new Exception("Red je negativan broj");
        if (karta.getProjekcija()==null) throw new Exception("Projekcija je null");
        if (karta.getTipKarte().isBlank()) throw new Exception("Tip karte je prazan string");

    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        repository.edit((Karta)param);
    }
}
