package tableModel;

import domen.GenericEntity;
import domen.Projekcija;

import javax.swing.table.AbstractTableModel;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class ProjekcijaTM extends AbstractTableModel {

    List<GenericEntity> projekcije = new ArrayList<>();
    SimpleDateFormat sdf = new SimpleDateFormat("dd.MM HH:mm");

    public void setProjekcije(List<GenericEntity> projekcije) {
        this.projekcije = projekcije;
    }

    @Override
    public int getRowCount() {
        return projekcije.size();
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Projekcija p = (Projekcija) projekcije.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return p.getFilm().getImeFilma();
            case 1:
                return sdf.format(p.getPocetakProjekcije());
            case 2:
                return "Sala " + p.getSala().getBrojSale();
            case 3:
                return p.getVrstaProjekcije();
            default:
                return "";
        }
    }

    @Override
    public String getColumnName(int column) {
        String[] columns = {"Film", "Pocetak", "Sala", "Vrsta proj."};
        return columns[column];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return String.class;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }
}
