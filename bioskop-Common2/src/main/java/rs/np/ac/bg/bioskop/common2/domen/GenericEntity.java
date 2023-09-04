package rs.np.ac.bg.bioskop.common2.domen;


import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Domenski interfejs koji implementiraju sve domenske klase projekta.
 * Sadrzi standardne metode za rad sa generickim repozitorijumom
 * @author Novica
 */
public interface GenericEntity extends Serializable {

    /**
     * Metoda koja vraca naziv tabele u bazi podataka kao string
     * @return naziv tabele
     */
    String getTableName();

    /**
     * Metoda koja vraca imena svih kolona date tabele u bazi kao string
     * @return Imena svih kolona date tabele
     */
    String getColumnNames();

    /**
     * Metoda koja vraca imena svih kolona izuzev ID kolone
     * @return String s imenima svih kolona izuzev ID kolone
     */
    String getColumnNamesWithoutId();

    /**
     * Metoda koja vraca nazive kolona u tabeli i vrednosti domenskog objekta koje im je
     * potrebno dodeliti kao novi unos u bazu
     * @return Vrednosti koje je potrebno dodeliti u tabeli baze i nazivi kolona baza u MySQL formatu
     */
    String getValues();

    /**
     * Metoda koja vraca deo koda za MySQL upit gde se specificiraju konkretne vrednosti za unos
     * @return deo INSERT naredbe za MySQL upit
     */

    String getInsertValues();

    /**
     * Setter a ID
     * @param id ID
     */
    void setId(long id);

    /**
     * Getter za ID
     * @return ID
     */
    public Long getId();

    /**
     * Metoda koja izvuce domenski objekat iz ResultSet-a
     * @param rs ResultSet iz baze
     * @return Dati domenski objekat
     * @throws SQLException Kada je nemoguce preveti ResultSet u domenski objekat
     */
    GenericEntity extractFromResultSet(ResultSet rs) throws SQLException;

    /**
     * Metoda koja vraca WHERE uslov za domenski objekat u obliku stringa pogodan za MySQL jezik
     * @return WHERE uslov za MySQL query
     */

    String getWhereCondition();

    /**
     * Metoda koja vraca sve podatke domenskog objekta za JDIalog
     * @return String pogodan za prikaz na JDIalog-u
     */
    String AllDetails();

}

