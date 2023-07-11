package domen;

import Util.DateParser;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class Film implements GenericEntity {

    private long filmID;
    private String imeFilma;
    private double ocena;
    private int trajanje;
    private String opis;
    private Date pocetakPrikazivanja;

    public Film() {
    }

    public Film(long filmID, String imeFilma, double ocena, int trajanje, String opis, Date pocetakPrikazivanja) {
        this.filmID = filmID;
        this.imeFilma = imeFilma;
        this.ocena = ocena;
        this.trajanje = trajanje;
        this.opis = opis;
        this.pocetakPrikazivanja = pocetakPrikazivanja;
    }

    public long getFilmID() {
        return filmID;
    }

    public void setFilmID(long filmID) {
        this.filmID = filmID;
    }

    public String getImeFilma() {
        return imeFilma;
    }

    public void setImeFilma(String imeFilma) {
        this.imeFilma = imeFilma;
    }

    public double getOcena() {
        return ocena;
    }

    public void setOcena(double ocena) {
        this.ocena = ocena;
    }

    public int getTrajanje() {
        return trajanje;
    }

    public void setTrajanje(int trajanje) {
        this.trajanje = trajanje;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public Date getPocetakPrikazivanja() {
        return pocetakPrikazivanja;
    }

    public void setPocetakPrikazivanja(Date pocetakPrikazivanja) {
        this.pocetakPrikazivanja = pocetakPrikazivanja;
    }

    @Override
    public String toString() {
        return "Film{" +
                "filmID=" + filmID +
                ", imeFilma='" + imeFilma + '\'' +
                ", ocena=" + ocena +
                ", trajanje=" + trajanje +
                ", opis='" + opis + '\'' +
                ", pocetakPrikazivanja=" + pocetakPrikazivanja +
                '}';
    }

    @Override
    public String getTableName() {
        return "film";
    }

    @Override
    public String getColumnNames() {
        return "(filmid, imefilma, ocena, trajanje, opis, pocetakprikazivanja)";
    }

    @Override
    public String getColumnNamesWithoutId() {
        return "(imefilma, ocena, trajanje, opis, pocetakprikazivanja)";
    }

    @Override
    public String getValues() {
        return "filmId = " + filmID + ", imefilma = '" + imeFilma + "', ocena = " + ocena + ", trajanje = " + trajanje + ", opis = '" + opis + "', pocetakprikazivanja = '" + DateParser.toString(pocetakPrikazivanja) + "'";
    }

    @Override
    public String getInsertValues() {
        return "(' " + imeFilma + "', " + ocena + ", " + trajanje + ", '" + opis + "', '" + DateParser.toString(pocetakPrikazivanja) + "')";
    }

    @Override
    public void setId(long id) {
        this.filmID = id;
    }

    @Override
    public Long getId() {
        return filmID;
    }

    @Override
    public GenericEntity extractFromResultSet(ResultSet resultSet) throws SQLException {
        Film film = new Film();

        film.setFilmID(resultSet.getLong("filmid"));
        film.setImeFilma(resultSet.getString("imeFilma"));
        film.setOcena(resultSet.getDouble("ocena"));
        film.setTrajanje(resultSet.getInt("trajanje"));
        film.setOpis(resultSet.getString("opis"));
        film.setPocetakPrikazivanja(resultSet.getDate("pocetakPrikazivanja"));

        return film;
    }

    @Override
    public String getWhereCondition() {
        return "(filmId = " + filmID + ")";
    }

    @Override
    public String AllDetails() {
        return "FILM:\n" +
                "Naziv: " + imeFilma + "\n" +
                "Trajanje: " + trajanje + "min\n" +
                "IMDB ocena: " + ocena + "\n" +
                "Premijera: " + DateParser.toString(pocetakPrikazivanja) +
                "\n" + opis;
    }
}
