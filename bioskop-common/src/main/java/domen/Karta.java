package domen;

import Util.DateParser;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Karta implements GenericEntity {

    private long kartaID;
    private String tipKarte;
    private int red;
    private int mesto;
    private Projekcija projekcija;

    private Korisnik korisnik;

    public Karta() {
    }
    public Karta(long kartaID, String tipKarte, int red, int mesto, Projekcija projekcija, Korisnik korisnik) {
        this.kartaID = kartaID;
        this.tipKarte = tipKarte;
        this.red = red;
        this.mesto = mesto;
        this.projekcija = projekcija;
        this.korisnik = korisnik;
    }

    public long getKartaID() {
        return kartaID;
    }

    public void setKartaID(long kartaID) {
        this.kartaID = kartaID;
    }

    public String getTipKarte() {
        return tipKarte;
    }

    public void setTipKarte(String tipKarte) {
        this.tipKarte = tipKarte;
    }

    public int getRed() {
        return red;
    }

    public void setRed(int red) {
        this.red = red;
    }

    public int getMesto() {
        return mesto;
    }

    public void setMesto(int mesto) {
        this.mesto = mesto;
    }

    public Projekcija getProjekcija() {
        return projekcija;
    }

    public void setProjekcija(Projekcija projekcija) {
        this.projekcija = projekcija;
    }

    public Korisnik getKorisnik() {
        return korisnik;
    }

    public void setKorisnik(Korisnik korisnik) {
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
        return "KARTA:\n"+
                "Korisnik: " + korisnik.getIme() + "\n" +
                "Film: " + projekcija.getFilm().getImeFilma() +"\n"+
                "Sala " + projekcija.getSala().getBrojSale() + "\n"+
                "Pocetak: " + DateParser.timeToString(projekcija.getPocetakProjekcije())+"\n"+
                "Red: " + red + ", Mesto: " + mesto;
    }
}
