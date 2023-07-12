package rs.np.ac.bg.bioskop_server.operation.Karta;

import domen.Karta;
import domen.Korisnik;
import domen.Projekcija;
import rs.np.ac.bg.bioskop_server.operation.AbstractGenericOperation;

import java.util.ArrayList;
import java.util.List;

/**
 * Operacija koja vraća rezervacije za određenu projekciju.
 */
public class VratiRezervacije extends AbstractGenericOperation {
	
	/**
	 * Privatna lista karata namenjena za vracane preko Getter-a
	 */
    List<Karta> list = new ArrayList<>();

    /**
     * Proverava preuslove za izvršavanje operacije.
     *
     * @param param parametar operacije (Projekcija)
     * @throws Exception ukoliko dođe do greške prilikom provere preuslova
     */
    @Override
    protected void preconditions(Object param) throws Exception {
        if (param == null) throw new Exception("Parametar je null");
        Projekcija projekcija = (Projekcija) param;
        if (projekcija.getId() == null || projekcija.getId() == 0) throw new Exception("ID Projekcije je los");
    }

    /**
     * Izvršava operaciju vraćanja rezervacija za datu projekciju.
     *
     * @param param parametar operacije (Projekcija)
     * @throws Exception ukoliko dođe do greške prilikom izvršavanja operacije
     */
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

    /**
     * Vraća listu rezervacija za projekciju.
     *
     * @return lista rezervacija
     */
    public List<Karta> getList() {
        return list;
    }
}

