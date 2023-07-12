package operation.Karta;

import domen.*;
import operation.AbstractGenericOperation;

import java.util.ArrayList;
import java.util.List;

/**
 * Operacija koja vraća listu karata za određenog korisnika.
 */
public class VratiListuKarata extends AbstractGenericOperation {

	/**
	 * Privatni atribut lista karata namenjena za vracanje kontroleru preko Getter-a 
	 */
    private List<Karta> list = new ArrayList<>();

    /**
     * Proverava preuslove za izvršavanje operacije.
     *
     * @param param objekat koji predstavlja korisnika
     * @throws Exception ukoliko parametar nije validan
     */
    @Override
    protected void preconditions(Object param) throws Exception {
        if (param == null) throw new Exception("Parametar je null");

        Korisnik korisnik = (Korisnik) param;

        if (korisnik.getId() == null) throw new Exception("ID Korisnika je null");
    }

    /**
     * Izvršava operaciju vraćanja liste karata za korisnika.
     *
     * @param param objekat koji predstavlja korisnika
     * @throws Exception ukoliko dođe do greške prilikom izvršavanja operacije
     */
    @Override
    protected void executeOperation(Object param) throws Exception {
        List<Karta> sveKarte = repository.getAll(new Karta());
        Korisnik korisnik = (Korisnik) param;

        for (Karta karta : sveKarte) {
            if (karta.getKorisnik().getId() == korisnik.getId()) {
                list.add(karta);
            }
        }

        for (Karta karta : list) {
            Korisnik k = (Korisnik) repository.getById(karta.getKorisnik());
            Projekcija p = (Projekcija) repository.getById(karta.getProjekcija());
            p.setFilm((Film) repository.getById(p.getFilm()));
            p.setSala((Sala) repository.getById(p.getSala()));
            karta.setKorisnik(k);
            karta.setProjekcija(p); 
        }
    }

    /**
     * Vraća listu karata za korisnika.
     *
     * @return lista karata
     */
    public List<Karta> getList() {
        return list;
    }
}
