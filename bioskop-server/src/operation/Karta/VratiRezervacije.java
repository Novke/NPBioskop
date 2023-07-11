package operation.Karta;

import domen.Karta;
import domen.Korisnik;
import domen.Projekcija;
import operation.AbstractGenericOperation;

import java.util.ArrayList;
import java.util.List;

public class VratiRezervacije extends AbstractGenericOperation {
    List<Karta> list = new ArrayList<>();
    @Override
    protected void preconditions(Object param) throws Exception {
        if (param == null) throw new Exception("Parametar je null");
        Projekcija projekcija = (Projekcija) param;
        if (projekcija.getId()==null || projekcija.getId()==0) throw new Exception("ID Projekcije je los");
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        Projekcija projekcija = (Projekcija) param;
        List<Karta> kartaList = repository.getAll(new Karta());

        for (Karta k: kartaList){
            if (k.getProjekcija().getId() == projekcija.getId()){
                k.setProjekcija(projekcija);
                k.setKorisnik((Korisnik) repository.getById(k.getKorisnik()));

                list.add(k);
            }
        }
    }

    public List<Karta> getList() {
        return list;
    }
}
