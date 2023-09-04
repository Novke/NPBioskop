package rs.np.ac.bg.bioskop.common2.domen;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Klasa Sala koja predstavlja salu u bioskopu namenjenu za gledanje filmova.
 */
public class Sala implements GenericEntity {

    /**
     * Broj sale.
     */
    private long brojSale;

    /**
     * Broj sedišta u sali.
     */
    private int brojSedista;

    /**
     * Konstruktor bez parametara.
     */
    public Sala() {
    }

    /**
     * Konstruktor sa parametrima.
     *
     * @param brojSale    broj sale
     * @param brojSedista broj sedišta u sali
     */
    public Sala(long brojSale, int brojSedista) {
        this.brojSale = brojSale;
        this.brojSedista = brojSedista;
    }

    /**
     * Vraća broj sale.
     *
     * @return broj sale
     */
    public long getBrojSale() {
        return brojSale;
    }

    /**
     * Postavlja broj sale.
     *
     * @param brojSale broj sale
     * @throws IllegalArgumentException ako je brojSale manji od 1 ili veći od 4
     */
    public void setBrojSale(long brojSale) {
        if (brojSale < 1 || brojSale > 4) throw new IllegalArgumentException();
        this.brojSale = brojSale;
    }

    /**
     * Vraća broj sedišta u sali.
     *
     * @return broj sedišta
     */
    public int getBrojSedista() {
        return brojSedista;
    }

    /**
     * Postavlja broj sedišta u sali.
     *
     * @param brojSedista broj sedišta
     * @throws IllegalArgumentException ako je brojSedista manji od 0
     */
    public void setBrojSedista(int brojSedista) {
        if (brojSedista < 0) throw new IllegalArgumentException();
        this.brojSedista = brojSedista;
    }

    @Override
    public String toString() {
        return "Sala{" +
                "brojSale=" + brojSale +
                ", brojSedista=" + brojSedista +
                '}';
    }

    @Override
    public String getTableName() {
        return "sala";
    }

    @Override
    public String getColumnNames() {
        return "(brojsale, brojsedista)";
    }

    @Override
    public String getColumnNamesWithoutId() {
        return "(brojsale, brojsedista)";
    }

    @Override
    public String getValues() {
        return "brojSale = " + brojSale + ", brojSedista = " + brojSedista;
    }


    @Override
    public String getInsertValues() {
        return "(" + brojSale + ", " + brojSedista + ")";
    }

    @Override
    public void setId(long id) {
        this.brojSale = id;
    }

    @Override
    public Long getId() {
        return brojSale;
    }

    @Override
    public GenericEntity extractFromResultSet(ResultSet rs) throws SQLException {
        Sala sala = new Sala();

        sala.setBrojSale(rs.getLong("brojsale"));
        sala.setBrojSedista(rs.getInt("brojsedista"));

        return sala;
    }

    @Override
    public String getWhereCondition() {
        return "(brojsale = " + brojSale + ")";
    }

    @Override
    public String AllDetails() {
        return "Sala " + brojSale + "\nKapacitet: " + brojSedista;
    }
}
