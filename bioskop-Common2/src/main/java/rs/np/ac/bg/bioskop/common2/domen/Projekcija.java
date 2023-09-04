package rs.np.ac.bg.bioskop.common2.domen;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import rs.np.ac.bg.bioskop.common2.Util.DateParser;


/**
 * Klasa projekcije koja predstavlja filmsku projekciju u bioskopu.
 */
public class Projekcija implements GenericEntity {

    /**
     * ID projekcije.
     */
    private long projekcijaID;

    /**
     * Vrsta projekcije.
     */
    private String vrstaProjekcije;

    /**
     * Datum i vreme pocetka projekcije.
     */
    private Date pocetakProjekcije;

    /**
     * Sala u kojoj se odrzava projekcija.
     */
    private Sala sala;

    /**
     * Film koji se prikazuje na projekciji.
     */
    private Film film;

    /**
     * Konstruktor bez parametara.
     */
    public Projekcija() {
    }

    /**
     * Konstruktor sa parametrima.
     *
     * @param projekcijaID      ID projekcije
     * @param vrstaProjekcije   vrsta projekcije
     * @param pocetakProjekcije datum i vreme pocetka projekcije
     * @param sala              sala u kojoj se odrzava projekcija
     * @param film              film koji se prikazuje na projekciji
     */
    public Projekcija(long projekcijaID, String vrstaProjekcije, Date pocetakProjekcije, Sala sala, Film film) {
        this.projekcijaID = projekcijaID;
        this.vrstaProjekcije = vrstaProjekcije;
        this.pocetakProjekcije = pocetakProjekcije;
        this.sala = sala;
        this.film = film;
    }

    /**
     * Vraća ID projekcije.
     *
     * @return ID projekcije
     */
    public long getProjekcijaID() {
        return projekcijaID;
    }

    /**
     * Postavlja ID projekcije.
     *
     * @param projekcijaID ID projekcije
     * @throws IllegalArgumentException ako je ID projekcije manji od 0
     */
    public void setProjekcijaID(long projekcijaID) {
        if (projekcijaID < 0) throw new IllegalArgumentException();
        this.projekcijaID = projekcijaID;
    }

    /**
     * Vraća vrstu projekcije.
     *
     * @return vrsta projekcije
     */
    public String getVrstaProjekcije() {
        return vrstaProjekcije;
    }

    /**
     * Postavlja vrstu projekcije.
     *
     * @param vrstaProjekcije vrsta projekcije
     * @throws IllegalArgumentException ako je vrsta projekcije null ili prazan String
     */
    public void setVrstaProjekcije(String vrstaProjekcije) {
        if (vrstaProjekcije == null || vrstaProjekcije.isBlank()) throw new IllegalArgumentException();
        this.vrstaProjekcije = vrstaProjekcije;
    }

    /**
     * Vraća datum i vreme pocetka projekcije.
     *
     * @return datum i vreme pocetka projekcije
     */
    public Date getPocetakProjekcije() {
        return pocetakProjekcije;
    }

    /**
     * Postavlja datum i vreme pocetka projekcije.
     *
     * @param pocetakProjekcije datum i vreme pocetka projekcije
     * @throws IllegalArgumentException ako je pocetakProjekcije null
     */
    public void setPocetakProjekcije(Date pocetakProjekcije) {
        if (pocetakProjekcije == null) throw new IllegalArgumentException();
        this.pocetakProjekcije = pocetakProjekcije;
    }

    /**
     * Vraća salu u kojoj se odrzava projekcija.
     *
     * @return sala
     */
    public Sala getSala() {
        return sala;
    }

    /**
     * Postavlja salu u kojoj se odrzava projekcija.
     *
     * @param sala sala
     * @throws IllegalArgumentException ako je sala null ili broj sale nije u validnom opsegu
     */
    public void setSala(Sala sala) {
        if (sala == null) throw new IllegalArgumentException();
        if (sala.getBrojSale() < 1 || sala.getBrojSale() > 4) throw new IllegalArgumentException();
        this.sala = sala;
    }

    /**
     * Vraća film koji se prikazuje na projekciji.
     *
     * @return film
     */
    public Film getFilm() {
        return film;
    }

    /**
     * Postavlja film koji se prikazuje na projekciji.
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
        return "('" + vrstaProjekcije + "', '" + DateParser.timeToString(pocetakProjekcije) + "', " + sala.getBrojSale() + ", " + film.getFilmID() + ")";
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
        return "PROJEKCIJA:\n" +
                "Film: " + film.getImeFilma() + "\n" +
                "Sala: " + sala.getBrojSale() + "\n" +
                "Vrsta projekcije: " + vrstaProjekcije + "\n" +
                "Pocetak: " + DateParser.timeToString(pocetakProjekcije);
    }
}
