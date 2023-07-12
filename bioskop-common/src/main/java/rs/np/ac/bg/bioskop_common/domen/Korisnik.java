package rs.np.ac.bg.bioskop_common.domen;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import rs.np.ac.bg.bioskop_common.Util.DateParser;

/**
 * Predstavlja korisnika, klijenta
 */
public class Korisnik implements GenericEntity {
    /**
     * Privatni atribut ID korisnika
     */
    private long korisnikID;
    /**
     * Privatni atribut ime korisnika
     */
    private String ime;
    /**
     * Privatni atribut datum rodjenja korisnika
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
     * Vraca ID korisnika
     * @return ID koorisnika
     */
    public long getKorisnikID() {
        return korisnikID;
    }

    /**
     * Postavlja ID korisnika
     * @param korisnikID ID Korisnika
     */
    public void setKorisnikID(long korisnikID) {
        this.korisnikID = korisnikID;
    }

    /**
     * Vraca ime korisnika
     * @return Ime korisnika
     */
    public String getIme() {
        return ime;
    }

    /**
     * Postavlja ime korisnika
     * @param ime Ime korisnika
     */
    public void setIme(String ime) {
        this.ime = ime;
    }

    /**
     * Vraca datum rodjenja korisnika
     * @return datum rodjenja
     */
    public Date getDatumRodjenja() {
        return datumRodjenja;
    }

    /**
     * Postavlja datum rodjenja korisnika
     * @param datumRodjenja Datum rodjenja
     */
    public void setDatumRodjenja(Date datumRodjenja) {
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
        return "ime = '" + ime + "',datumrodjenja = '" + DateParser.toString(datumRodjenja) + "'";
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
        return "KORISNIK: \n"+
                "Ime: " + ime + "\n" +
                "Datum rodjenja: " + DateParser.toString(datumRodjenja);
    }
}

