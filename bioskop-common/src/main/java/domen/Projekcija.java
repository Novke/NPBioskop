package domen;

import Util.DateParser;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

public class Projekcija implements GenericEntity {

    private long projekcijaID;
    private String vrstaProjekcije;
    private Date pocetakProjekcije;
    private Sala sala;
    private Film film;
    public Projekcija() {
    }
    public Projekcija(long projekcijaID, String vrstaProjekcije, Date pocetakProjekcije, Sala sala, Film film) {
        this.projekcijaID = projekcijaID;
        this.vrstaProjekcije = vrstaProjekcije;
        this.pocetakProjekcije = pocetakProjekcije;
        this.sala = sala;
        this.film = film;
    }



    public long getProjekcijaID() {
        return projekcijaID;
    }

    public void setProjekcijaID(long projekcijaID) {
        this.projekcijaID = projekcijaID;
    }

    public String getVrstaProjekcije() {
        return vrstaProjekcije;
    }

    public void setVrstaProjekcije(String vrstaProjekcije) {
        this.vrstaProjekcije = vrstaProjekcije;
    }

    public Date getPocetakProjekcije() {
        return pocetakProjekcije;
    }

    public void setPocetakProjekcije(Date pocetakProjekcije) {
        this.pocetakProjekcije = pocetakProjekcije;
    }

    public Sala getSala() {
        return sala;
    }

    public void setSala(Sala sala) {
        this.sala = sala;
    }

    public Film getFilm() {
        return film;
    }

    public void setFilm(Film film) {
        this.film = film;
    }

    @Override
    public String toString() {
        return "Projekcija{" +
                "projekcijaID=" + projekcijaID +
                ", vrstaProjekcije='" + vrstaProjekcije + '\'' +
                ", pocetakProjekcije=" + pocetakProjekcije +
                ", sala=" + sala +
                ", film=" + film +
                '}';
    }

    @Override
    public String getTableName() {
        return "projekcija";
    }

    @Override
    public String getColumnNames() {
        return "(projekcijaid, vrstaprojekcije, pocetakprojekcije, salaid, filmid)";
    }

    @Override
    public String getColumnNamesWithoutId() {
        return "(vrstaprojekcije, pocetakprojekcije, salaid, filmid)";
    }

    @Override
    public String getValues() {
        return "projekcijaid = " + projekcijaID + ", vrstaprojekcije = '" + vrstaProjekcije + "', pocetakprojekcije = '" + DateParser.timeToString(pocetakProjekcije)
                + "', salaid = " + sala.getId() + ", filmid = " + film.getId();
    }

    @Override
    public String getInsertValues() {
        return "('" + vrstaProjekcije + "', '" + DateParser.timeToString(pocetakProjekcije) + "', " + sala.getBrojSale() + ", " + film.getFilmID() +")";
    }

    @Override
    public void setId(long id) {
        this.projekcijaID = id;
    }

    @Override
    public Long getId() {
        return projekcijaID;
    }

    @Override
    public GenericEntity extractFromResultSet(ResultSet rs) throws SQLException {
        Projekcija projekcija = new Projekcija();

        projekcija.setId(rs.getLong("projekcijaid"));
        projekcija.setVrstaProjekcije(rs.getString("vrstaprojekcije"));

        Timestamp pocetakProjekcijeTimestamp = rs.getTimestamp("pocetakprojekcije");
        Date pocetakProjekcije = new Date(pocetakProjekcijeTimestamp.getTime());
        projekcija.setPocetakProjekcije(pocetakProjekcije);

        Film film = new Film();
        film.setId(rs.getLong("filmid"));
        Sala sala = new Sala();
        sala.setId(rs.getLong("salaid"));

        projekcija.setFilm(film);
        projekcija.setSala(sala);

        return projekcija;
    }

    @Override
    public String getWhereCondition() {
        return "(projekcijaid = " + projekcijaID + ")";
    }

    @Override
    public String AllDetails() {
        return "PROJEKCIJA: \n"+
                "Film: " + film.getImeFilma() +"\n" +
                "Sala " + sala.getBrojSale() +"\n"+
                "Vrsta projekcije: " + vrstaProjekcije +"\n" +
                "Pocetak: " + DateParser.timeToString(pocetakProjekcije);
    }
}