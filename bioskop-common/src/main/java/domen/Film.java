package domen;

import Util.DateParser;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

/**
 * Predstavlja film.
 */
public class Film implements GenericEntity {

    /**
     * ID filma.
     */
    private long filmID;
    
    /**
     * Ime filma.
     */
    private String imeFilma;
    
    /**
     * Ocena filma.
     */
    private double ocena;
    
    /**
     * Trajanje filma u minutima.
     */
    private int trajanje;
    
    /**
     * Opis filma.
     */
    private String opis;
    
    /**
     * Datum pocetka prikazivanja filma.
     */
    private Date pocetakPrikazivanja;

    /**
     * Konstruktor bez parametara.
     */
    public Film() {
    }

    /**
     * Konstruktor sa parametrima.
     *
     * @param filmID                ID filma
     * @param imeFilma              ime filma
     * @param ocena                 ocena filma
     * @param trajanje              trajanje filma u minutima
     * @param opis                  opis filma
     * @param pocetakPrikazivanja   datum pocetka prikazivanja filma
     */
    public Film(long filmID, String imeFilma, double ocena, int trajanje, String opis, Date pocetakPrikazivanja) {
        this.filmID = filmID;
        this.imeFilma = imeFilma;
        this.ocena = ocena;
        this.trajanje = trajanje;
        this.opis = opis;
        this.pocetakPrikazivanja = pocetakPrikazivanja;
    }

    /**
     * Vraća ID filma.
     *
     * @return ID filma
     */
    public long getFilmID() {
        return filmID;
    }

    /**
     * Postavlja ID filma.
     *
     * @param filmID ID filma
     */
    public void setFilmID(long filmID) {
        this.filmID = filmID;
    }

    /**
     * Vraća ime filma.
     *
     * @return ime filma
     */
    public String getImeFilma() {
        return imeFilma;
    }

    /**
     * Postavlja ime filma.
     *
     * @param imeFilma ime filma
     */
    public void setImeFilma(String imeFilma) {
        this.imeFilma = imeFilma;
    }

    /**
     * Vraća ocenu filma.
     *
     * @return ocena filma
     */
    public double getOcena() {
        return ocena;
    }

    /**
     * Postavlja ocenu filma.
     *
     * @param ocena ocena filma
     */
    public void setOcena(double ocena) {
        this.ocena = ocena;
    }

    /**
     * Vraća trajanje filma u minutima.
     *
     * @return trajanje filma
     */
    public int getTrajanje() {
        return trajanje;
    }

    /**
     * Postavlja trajanje filma u minutima.
     *
     * @param trajanje trajanje filma
     */
    public void setTrajanje(int trajanje) {
        this.trajanje = trajanje;
    }

    /**
     * Vraća opis filma.
     *
     * @return opis filma
     */
    public String getOpis() {
        return opis;
    }

    /**
     * Postavlja opis filma.
     *
     * @param opis opis filma
     */
    public void setOpis(String opis) {
        this.opis = opis;
    }

    /**
     * Vraća datum pocetka prikazivanja filma.
     *
     * @return datum pocetka prikazivanja filma
     */
    public Date getPocetakPrikazivanja() {
        return pocetakPrikazivanja;
    }

    /**
     * Postavlja datum pocetka prikazivanja filma.
     *
     * @param pocetakPrikazivanja datum pocetka prikazivanja filma
     */
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
        return "('" + imeFilma + "', " + ocena + ", " + trajanje + ", '" + opis + "', '" + DateParser.toString(pocetakPrikazivanja) + "')";
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
