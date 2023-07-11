package domen;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Ocena implements GenericEntity {
    private long ocenaID;
    private int ocena;
    private Korisnik korisnik;
    private Film film;

    public Ocena() {
    }

    public Ocena(long ocenaID, int ocena, Korisnik korisnik, Film film) {
        this.ocena = ocena;
        this.ocenaID = ocenaID;
        this.korisnik = korisnik;
        this.film = film;
    }

    public int getOcena() {
        return ocena;
    }

    public void setOcena(int ocena) {
        this.ocena = ocena;
    }

    public Korisnik getKorisnik() {
        return korisnik;
    }

    public long getOcenaID() {
        return ocenaID;
    }

    public void setOcenaID(long ocenaID) {
        this.ocenaID = ocenaID;
    }

    public void setKorisnik(Korisnik korisnik) {
        this.korisnik = korisnik;
    }

    public Film getFilm() {
        return film;
    }

    public void setFilm(Film film) {
        this.film = film;
    }

    @Override
    public String toString() {
        return "Ocena{" +
                "ocenaID=" + ocenaID +
                ", ocena=" + ocena +
                ", korisnik=" + korisnik +
                ", film=" + film +
                '}';
    }

    @Override
    public String getTableName() {
        return "ocena";
    }

    @Override
    public String getColumnNames() {
        return "(ocenaid, ocena, korisnikid, filmid)";
    }

    @Override
    public String getColumnNamesWithoutId() {
        return "(ocena, korisnikid, filmid)";
    }

    @Override
    public String getValues() {
        return "ocenaid = " + ocenaID + ", ocena = " + ocena + ", korisnikid = " + korisnik.getId() + ", filmid = " + film.getId();
    }

    @Override
    public String getInsertValues() {
        return "(" + ocena + ", " + korisnik.getKorisnikID() + ", " + film.getFilmID() + ")";
    }

    @Override
    public void setId(long id) {
        this.ocenaID = id;
    }

    @Override
    public Long getId() {
        return ocenaID;
    }

    @Override
    public GenericEntity extractFromResultSet(ResultSet rs) throws SQLException {
        Ocena ocena = new Ocena();

        ocena.setId(rs.getLong("ocenaid"));
        ocena.setOcena(rs.getInt("ocena"));

        Korisnik korisnik = new Korisnik();
        korisnik.setId(rs.getLong("korisnikid"));

        Film film = new Film();
        film.setId(rs.getLong("filmid"));

        ocena.setFilm(film);
        ocena.setKorisnik(korisnik);

        return ocena;
    }

    @Override
    public String getWhereCondition() {
        return "(ocenaid = " + ocenaID + ")";
    }

    @Override
    public String AllDetails() {
        return "OCENA:\n" +
                "Film: " + film.getImeFilma() +"\n" +
                "Korisnik: " + korisnik.getIme() +"\n" +
                "Ocena: " + ocena;
    }
}
