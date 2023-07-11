package operation.Karta;

import domen.*;
import operation.AbstractGenericOperation;

import java.util.ArrayList;
import java.util.List;

public class VratiListuKarata extends AbstractGenericOperation {

    List<Karta> list = new ArrayList<>();
    @Override
    protected void preconditions(Object param) throws Exception {
        if (param == null) throw new Exception("Parametar je null");

        Korisnik korinsik = (Korisnik) param;

        if (korinsik.getId() == null) throw new Exception("ID Korisnika je null");
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        List<Karta> sveKarte = repository.getAll(new Karta());
        Korisnik korinsik = (Korisnik) param;

        for (Karta karta: sveKarte){
            if (karta.getKorisnik().getId() == korinsik.getId()) list.add(karta);
        }

        for (Karta karta:list){
            Korisnik k = (Korisnik) repository.getById(karta.getKorisnik());
            Projekcija p = (Projekcija) repository.getById(karta.getProjekcija());
            p.setFilm((Film) repository.getById(p.getFilm()));
            p.setSala((Sala) repository.getById(p.getSala()));
            karta.setKorisnik(k);
            karta.setProjekcija(p);
        }
    }

    public List<Karta> getList() {
        return list;
    }
}
