/**
 * Proverava preuslove za izvršavanje operacije kreiranja karata.
 *
 * @param param objekat koji predstavlja listu karata
 * @throws Exception ukoliko dođe do greške prilikom provere preuslova
 */
@Override
protected void preconditions(Object param) throws Exception {
    if (param == null) {
        throw new Exception("Parametar je null");
    }

    List<Karta> list = (List<Karta>) param;

    if (list.isEmpty()) {
        throw new Exception("Nije prosleđena nijedna karta");
    }

    Projekcija p = list.get(0).getProjekcija();

    for (Karta karta : list) {
        if (karta.getKorisnik() == null) {
            throw new Exception("Korisnik na karti je null");
        }
        if (karta.getMesto() < 0) {
            throw new Exception("Broj mesta na karti je negativan");
        }
        if (karta.getRed() < 0) {
            throw new Exception("Broj reda na karti je negativan");
        }
        if (karta.getProjekcija() == null) {
            throw new Exception("Projekcija na karti je null");
        }
        if (karta.getTipKarte().isBlank()) {
            throw new Exception("Tip karte na karti je prazan");
        }
    }

    // Provera da li su zauzeta mesta
    List<Karta> sveKarte = repository.getAll(new Karta());
    List<Karta> postojeceKarte = new ArrayList<>();
    for (Karta k : sveKarte) {
        if (k.getProjekcija().getId() == p.getId()) {
            postojeceKarte.add(k);
        }
    }

    String exception = "";
    for (Karta novaKarta : list) {
        for (Karta staraKarta : postojeceKarte) {
            if (staraKarta.getRed() == novaKarta.getRed() &&
                    staraKarta.getMesto() == novaKarta.getMesto()) {
                exception += "Mesto " + staraKarta.getMesto() + " u redu " + staraKarta.getRed() + " je zauzeto!\n";
            }
        }
    }
    if (!exception.isBlank()) {
        throw new Exception(exception);
    }
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
