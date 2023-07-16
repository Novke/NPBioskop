package rs.np.ac.bg.bioskop_common.domen;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Klasa ocena koja predstavlja subjektivnu ocenu stepena zadovoljstva korisnika filmom.
 */
public class Ocena implements GenericEntity {

    /**
     * ID ocene.
     */
    private long ocenaID;
    
    /**
     * Ocena koja je dodeljena filmu.
     */
    private int ocena;
    
    /**
     * Korisnik koji je dao ocenu.
     */
    private Korisnik korisnik;
    
    /**
     * Film koji je ocenjen.
     */
    private Film film;

    /**
     * Konstruktor bez parametara.
     */
    public Ocena() {
    }

    /**
     * Konstruktor sa parametrima.
     *
     * @param ocenaID   ID ocene
     * @param ocena     ocena
     * @param korisnik  korisnik koji je dao ocenu
     * @param film      film koji je ocenjen
     */
    public Ocena(long ocenaID, int ocena, Korisnik korisnik, Film film) {
        this.ocena = ocena;
        this.ocenaID = ocenaID;
        this.korisnik = korisnik;
        this.film = film;
    }

    /**
     * Vraća ocenu.
     *
     * @return ocena
     */
    public int getOcena() {
        return ocena;
    }

    /**
     * Postavlja ocenu.
     *
     * @param ocena ocena
     * @throws IllegalArgumentException Kada je prosledjena ocena van raspona 1-10
     */
    public void setOcena(int ocena) {
    	if (ocena < 1 || ocena > 10) throw new IllegalArgumentException();
        this.ocena = ocena;
    }

    /**
     * Vraća korisnika koji je dao ocenu.
     *
     * @return korisnik
     */
    public Korisnik getKorisnik() {
        return korisnik;
    }

    /**
     * Vraća ID ocene.
     *
     * @return ID ocene
     */
    public long getOcenaID() {
        return ocenaID;
    }

    /**
     * Postavlja ID ocene.
     *
     * @param ocenaID ID ocene
     * @throws IllegalArgumentException ako je ID ocene manji od 0
     */
    public void setOcenaID(long ocenaID) {
        if (ocenaID < 0) throw new IllegalArgumentException();
        this.ocenaID = ocenaID;
    }

    /**
     * Postavlja korisnika koji je dao ocenu.
     *
     * @param korisnik korisnik
     * @throws IllegalArgumentException ako je korisnik null
     */
    public void setKorisnik(Korisnik korisnik) {
        if (korisnik == null) throw new IllegalArgumentException();
        this.korisnik = korisnik;
    }

    /**
     * Vraća film koji je ocenjen.
     *
     * @return film
     */
    public Film getFilm() {
        return film;
    }

    /**
     * Postavlja film koji je ocenjen.
     *
     * @param film film
     * @throws IllegalArgumentException ako je film null
     */
    public void setFilm(Film film) {
        if (film == null) throw new IllegalArgumentException();
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
                "Film: " + film.getImeFilma() + "\n" +
                "Korisnik: " + korisnik.getIme() + "\n" +
                "Ocena: " + ocena;
    }
}
