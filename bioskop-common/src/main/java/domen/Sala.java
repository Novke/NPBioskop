package domen;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Sala implements GenericEntity {

    private long brojSale;
    private int brojSedista;

    public Sala() {
    }

    public Sala(long brojSale, int brojSedista) {
        this.brojSale = brojSale;
        this.brojSedista = brojSedista;
    }

    public long getBrojSale() {
        return brojSale;
    }

    public void setBrojSale(long brojSale) {
        this.brojSale = brojSale;
    }

    public int getBrojSedista() {
        return brojSedista;
    }

    public void setBrojSedista(int brojSedista) {
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
        return "(" + brojSale + ", " + brojSedista +")";
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
        return "(brojSale = " + brojSale + ")";
    }

    @Override
    public String AllDetails() {
        return "Sala " + brojSale + "\nKapacitet: " + brojSedista;
    }
}
