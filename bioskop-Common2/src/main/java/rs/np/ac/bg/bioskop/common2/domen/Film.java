package rs.np.ac.bg.bioskop.common2.domen;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import rs.np.ac.bg.bioskop.common2.Util.DateParser;


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
     * @throws IllegalArgumentException ako je ID manji od 0
     */
    public void setFilmID(long filmID) {
    	if (filmID < 0) throw new IllegalArgumentException("ID filma ne sme biti manji od 0");
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
     * @throws IllegalArgumentException ako je ime filma null ili prazan String
     */
    public void setImeFilma(String imeFilma) {
    	if (imeFilma == null || imeFilma.isBlank()) throw new IllegalArgumentException("Ime filma ne sme biti null ili prazan");
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
     * @throws IllegalArgumentException ako je ocena manja od 0 ili veća od 10
     */
    public void setOcena(double ocena) {
    	if (ocena < 0 || ocena > 10) throw new IllegalArgumentException("Ocena filma mora biti između 0 i 10");
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
     * @throws IllegalArgumentException ako je trajanje manje ili jednako 0
     */
    public void setTrajanje(int trajanje) {
    	if (trajanje <= 0) throw new IllegalArgumentException("Trajanje filma mora biti veće od 0");
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
     * @throws IllegalArgumentException ako je opis filma null ili prazan String
     */
    public void setOpis(String opis) {
    	if (opis == null || opis.isBlank()) throw new IllegalArgumentException("Opis filma ne sme biti null ili prazan");
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
     * @throws IllegalArgumentException ako je datum pocetka prikazivanja null
     */
    public void setPocetakPrikazivanja(Date pocetakPrikazivanja) {
    	if (pocetakPrikazivanja == null) throw new IllegalArgumentException("Datum pocetka prikazivanja filma ne sme biti null");
        this.pocetakPrikazivanja = pocetakPrikazivanja;
    }

    /**
     * {@inheritDoc}
     */
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

    /**
     * {@inheritDoc}
     */
    @Override
    public String getTableName() {
        return "film";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getColumnNames() {
        return "(filmid, imefilma, ocena, trajanje, opis, pocetakprikazivanja)";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getColumnNamesWithoutId() {
        return "(imefilma, ocena, trajanje, opis, pocetakprikazivanja)";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getValues() {
        return "filmId = " + filmID + ", imefilma = '" + imeFilma + "', ocena = " + ocena + ", trajanje = " + trajanje + ", opis = '" + opis + "', pocetakprikazivanja = '" + DateParser.toString(pocetakPrikazivanja) + "'";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getInsertValues() {
        return "('" + imeFilma + "', " + ocena + ", " + trajanje + ", '" + opis + "', '" + DateParser.toString(pocetakPrikazivanja) + "')";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setId(long id) {
        this.filmID = id;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Long getId() {
        return filmID;
    }

    /**
     * {@inheritDoc}
     */
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

    /**
     * {@inheritDoc}
     */
    @Override
    public String getWhereCondition() {
        return "(filmId = " + filmID + ")";
    }

    /**
     * {@inheritDoc}
     */
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
