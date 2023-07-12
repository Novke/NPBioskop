package rs.np.ac.bg.bioskop_server.operation.Karta;

import domen.Karta;
import domen.Projekcija;
import rs.np.ac.bg.bioskop_server.operation.AbstractGenericOperation;

import java.util.ArrayList;
import java.util.List;

/**
 * Operacija za kreiranje karata.
 */
public class KreiranjeKarte extends AbstractGenericOperation {

    /**
     * Proverava preuslove za izvršavanje operacije.
     *
     * @param param objekat koji predstavlja listu karata
     * @throws Exception ukoliko dođe do greške prilikom provere preuslova
     */
    @Override
    protected void preconditions(Object param) throws Exception {
        if (param == null) throw new Exception("Parametar je null");

        List<Karta> list = (List<Karta>) param;

        if (list.isEmpty()) throw new Exception("0 karata prosleđeno");

        Projekcija p = list.get(0).getProjekcija();

        for (Karta karta : list) {
            if (karta.getKorisnik() == null) throw new Exception("Korisnik je null");
            if (karta.getMesto() < 0) throw new Exception("Mesto je negativan broj");
            if (karta.getRed() < 0) throw new Exception("Red je negativan broj");
            if (karta.getProjekcija() == null) throw new Exception("Projekcija je null");
            if (karta.getTipKarte().isBlank()) throw new Exception("Tip karte je prazan string");
        }

        // Provera da li su zauzeta mesta
        List<Karta> sveKarte = repository.getAll(new Karta());
        List<Karta> postojeceKarte = new ArrayList<>();
        for (Karta k : sveKarte) {
            if (k.getProjekcija().getId() == p.getId()) postojeceKarte.add(k);
        }

        String exception = "";
        for (Karta novaKarta : list) {
            for (Karta staraKarta : postojeceKarte) {
                if (staraKarta.getRed() == novaKarta.getRed() &&
                        staraKarta.getMesto() == novaKarta.getMesto()) {
                    exception += "Mesto " + staraKarta.getMesto() + " u " + staraKarta.getRed() + ". redu je zauzeto!\n";
                }
            }
        }
        if (!exception.isBlank()) throw new Exception(exception);
    }

    /**
     * Izvršava operaciju kreiranja karata.
     *
     * @param param objekat koji predstavlja listu karata
     * @throws Exception ukoliko dođe do greške prilikom izvršavanja operacije
     */
    @Override
    protected void executeOperation(Object param) throws Exception {
        List<Karta> list = (List<Karta>) param;
        for (Karta karta : list) {
            repository.add(karta);
        }
    }
}
