package rs.np.ac.bg.bioskop_common.domen;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import rs.np.ac.bg.bioskop_common.Util.DateParser;

/**
 * Predstavlja korisnika, klijenta.
 */
public class Korisnik implements GenericEntity {

    /**
     * Privatni atribut ID korisnika.
     */
    private long korisnikID;
    
    /**
     * Privatni atribut ime korisnika.
     */
    private String ime;
    
    /**
     * Privatni atribut datum rođenja korisnika.
     */
    private Date datumRodjenja;

    /**
     * Konstruktor bez parametara.
     */
    public Korisnik() {
    }

    /**
     * Konstruktor sa parametrima.
     *
     * @param korisnikID     ID korisnika
     * @param ime            ime korisnika
     * @param datumRodjenja  datum rođenja korisnika
     */
    public Korisnik(long korisnikID, String ime, Date datumRodjenja) {
        this.korisnikID = korisnikID;
        this.ime = ime;
        this.datumRodjenja = datumRodjenja;
    }

    /**
     * Vraća ID korisnika.
     *
     * @return ID korisnika
     */
    public long getKorisnikID() {
        return korisnikID;
    }

    /**
     * Postavlja ID korisnika.
     *
     * @param korisnikID ID korisnika
     * @throws IllegalArgumentException ako je ID korisnika manji od 0
     */
    public void setKorisnikID(long korisnikID) {
        if (korisnikID < 0) throw new IllegalArgumentException();
        this.korisnikID = korisnikID;
    }

    /**
     * Vraća ime korisnika.
     *
     * @return ime korisnika
     */
    public String getIme() {
        return ime;
    }

    /**
     * Postavlja ime korisnika.
     *
     * @param ime ime korisnika
     * @throws IllegalArgumentException ako je ime korisnika null ili prazan String
     */
    public void setIme(String ime) {
        if (ime == null || ime.isBlank()) throw new IllegalArgumentException();
        this.ime = ime;
    }

    /**
     * Vraća datum rođenja korisnika.
     *
     * @return datum rođenja
     */
    public Date getDatumRodjenja() {
        return datumRodjenja;
    }

    /**
     * Postavlja datum rođenja korisnika.
     *
     * @param datumRodjenja datum rođenja
     * @throws IllegalArgumentException ako je datum rođenja u budućnosti
     */
    public void setDatumRodjenja(Date datumRodjenja) {
        if (datumRodjenja.after(new Date())) throw new IllegalArgumentException();
        this.datumRodjenja = datumRodjenja;
    }

    @Override
    public String toString() {
        return "Korisnik{" +
                "korisnikID=" + korisnikID +
                ", ime='" + ime + '\'' +
                ", datumRodjenja=" + datumRodjenja +
                '}';
    }

    @Override
    public String getTableName() {
        return "korisnik";
    }

    @Override
    public String getColumnNames() {
        return "(korisnikid, ime, datumrodjenja)";
    }

    @Override
    public String getColumnNamesWithoutId() {
        return "(ime, datumrodjenja)";
    }

    @Override
    public String getValues() {
        return "ime = '" + ime + "', datumrodjenja = '" + DateParser.toString(datumRodjenja) + "'";
    }

    @Override
    public String getInsertValues() {
        return "('" + ime + "', '" + DateParser.toString(datumRodjenja) + "')";
    }

    @Override
    public void setId(long id) {
        this.korisnikID = id;
    }

    @Override
    public Long getId() {
        return korisnikID;
    }

    @Override
    public GenericEntity extractFromResultSet(ResultSet rs) throws SQLException {
        Korisnik korisnik = new Korisnik();

        korisnik.setKorisnikID(rs.getLong("korisnikid"));
        korisnik.setIme(rs.getString("ime"));
        korisnik.setDatumRodjenja(rs.getDate("datumrodjenja"));

        return korisnik;
    }

    @Override
    public String getWhereCondition() {
        return "(korisnikid = " + korisnikID + ")";
    }

    @Override
    public String AllDetails() {
        return "KORISNIK:\n" +
                "Ime: " + ime + "\n" +
                "Datum rođenja: " + DateParser.toString(datumRodjenja);
    }
}
