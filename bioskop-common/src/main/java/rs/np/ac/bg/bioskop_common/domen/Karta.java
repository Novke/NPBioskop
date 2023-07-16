package rs.np.ac.bg.bioskop_common.domen;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

import rs.np.ac.bg.bioskop_common.Util.DateParser;

/**
 * Klasa karte koja predstavlja ulaznicu za projekciju.
 */
public class Karta implements GenericEntity {

    /**
     * ID karte.
     */
    private long kartaID;
    
    /**
     * Tip karte.
     */
    private String tipKarte;
    
    /**
     * Red na kojem se nalazi mesto na karti.
     */
    private int red;
    
    /**
     * Mesto na karti.
     */
    private int mesto;
    
    /**
     * Projekcija na kojoj je karta kupljena.
     */
    private Projekcija projekcija;

    /**
     * Korisnik koji je kupio kartu.
     */
    private Korisnik korisnik;

    /**
     * Konstruktor bez parametara.
     */
    public Karta() {
    }
    
    /**
     * Konstruktor sa parametrima.
     *
     * @param kartaID       ID karte
     * @param tipKarte      tip karte
     * @param red           red na kojem se nalazi mesto na karti
     * @param mesto         mesto na karti
     * @param projekcija    projekcija na kojoj je karta kupljena
     * @param korisnik      korisnik koji je kupio kartu
     */
    public Karta(long kartaID, String tipKarte, int red, int mesto, Projekcija projekcija, Korisnik korisnik) {
        this.kartaID = kartaID;
        this.tipKarte = tipKarte;
        this.red = red;
        this.mesto = mesto;
        this.projekcija = projekcija;
        this.korisnik = korisnik;
    }

    /**
     * Vraća ID karte.
     *
     * @return ID karte
     */
    public long getKartaID() {
        return kartaID;
    }

    /**
     * Postavlja ID karte.
     *
     * @param kartaID ID karte
     * @throws IllegalArgumentException Ako je ID karte negativan broj
     */
    public void setKartaID(long kartaID) {
    	if (kartaID < 0) throw new IllegalArgumentException();
        this.kartaID = kartaID;
    }

    /**
     * Vraća tip karte.
     *
     * @return tip karte
     */
    public String getTipKarte() {
        return tipKarte;
    }

    /**
     * Postavlja tip karte.
     *
     * @param tipKarte tip karte
     * @throws IllegalArgumentException ako je tip karte null ili prazan String
     */
    public void setTipKarte(String tipKarte) {
    	if (tipKarte == null || tipKarte.isBlank()) throw new IllegalArgumentException();
        this.tipKarte = tipKarte;
    }

    /**
     * Vraća red na kojem se nalazi mesto na karti.
     *
     * @return red na karti
     */
    public int getRed() {
        return red;
    }

    /**
     * Postavlja red na kojem se nalazi mesto na karti.
     *
     * @param red red na karti
     * @throws IllegalArgumentException ako je red manji od 0
     */
    public void setRed(int red) {
    	if (red < 0) throw new IllegalArgumentException();
        this.red = red;
    }

    /**
     * Vraća mesto na karti.
     *
     * @return mesto na karti
     */
    public int getMesto() {
        return mesto;
    }

    /**
     * Postavlja mesto na karti.
     *
     * @param mesto mesto na karti
     * @throws IllegalArgumentException ako je mesto manje od 0
     */
    public void setMesto(int mesto) {
    	if (mesto < 0) throw new IllegalArgumentException();
        this.mesto = mesto;
    }

    /**
     * Vraća projekciju na kojoj je karta kupljena.
     *
     * @return projekcija
     */
    public Projekcija getProjekcija() {
        return projekcija;
    }

    /**
     * Postavlja projekciju na kojoj je karta kupljena.
     *
     * @param projekcija projekcija
     * @throws IllegalArgumentException ako je projekcija null
     */
    public void setProjekcija(Projekcija projekcija) {
    	if (projekcija == null) throw new IllegalArgumentException();
        this.projekcija = projekcija;
    }

    /**
     * Vraća korisnika koji je kupio kartu.
     *
     * @return korisnik
     */
    public Korisnik getKorisnik() {
        return korisnik;
    }

    /**
     * Postavlja korisnika koji je kupio kartu.
     *
     * @param korisnik korisnik
     * @throws IllegalArgumentException ako je korisnik null
     */
    public void setKorisnik(Korisnik korisnik) {
    	if (korisnik == null) throw new IllegalArgumentException();
        this.korisnik = korisnik;
    }

    @Override
    public String toString() {
        return "Karta{" +
                "kartaID=" + kartaID +
                ", tipKarte='" + tipKarte + '\'' +
                ", red=" + red +
                ", mesto=" + mesto +
                ", projekcija=" + projekcija +
                ", korisnik=" + korisnik +
                '}';
    }

    @Override
    public String getTableName() {
        return "karta";
    }

    @Override
    public String getColumnNames() {
        return "(kartaid, tipkarte, red, mesto, korisnikid, projekcijaid)";
    }

    @Override
    public String getColumnNamesWithoutId() {
        return "(tipkarte, red, mesto, korisnikid, projekcijaid)";
    }

    @Override
    public String getValues() {
        return "kartaid = " + kartaID + ", tipkarte = '" + tipKarte + "', red = " + red + ", mesto = " + mesto + ", korisnikid = " + korisnik.getId()
                + ", projekcijaid = " + projekcija.getId();
    }

    @Override
    public String getInsertValues() {
        return "('" + tipKarte + "', " + red + ", " + mesto + ", " + korisnik.getKorisnikID() + ", " + projekcija.getProjekcijaID() + ")";
    }

    @Override
    public void setId(long id) {
        this.kartaID = id;
    }

    @Override
    public Long getId() {
        return kartaID;
    }

    @Override
    public GenericEntity extractFromResultSet(ResultSet rs) throws SQLException {
        Karta karta = new Karta();

        karta.setKartaID(rs.getLong("kartaid"));
        karta.setMesto(rs.getInt("mesto"));
        karta.setRed(rs.getInt("red"));

        Korisnik korisnik = new Korisnik();
        korisnik.setId(rs.getLong("korisnikid"));
        Projekcija projekcija = new Projekcija();
        projekcija.setId(rs.getLong("projekcijaid"));

        karta.setKorisnik(korisnik);
        karta.setProjekcija(projekcija);

        return karta;
    }

    @Override
    public String getWhereCondition() {
        return "(kartaid = " + kartaID + ")";
    }

    @Override
    public String AllDetails() {
        return "KARTA:\n" +
                "Korisnik: " + korisnik.getIme() + "\n" +
                "Film: " + projekcija.getFilm().getImeFilma() + "\n" +
                "Sala " + projekcija.getSala().getBrojSale() + "\n" +
                "Pocetak: " + DateParser.timeToString(projekcija.getPocetakProjekcije()) + "\n" +
                "Red: " + red + ", Mesto: " + mesto;
    }
}
