package tableModel;

import domen.Karta;
import domen.Ocena;

import javax.swing.table.AbstractTableModel;
import java.util.*;

public class OcenaTM extends AbstractTableModel {
    List<Ocena> ocenaList;
    List<Karta> kartaList;

    public OcenaTM(List<Ocena> ocenaList, List<Karta> kartaList) {
        this.ocenaList = ocenaList;
        this.kartaList = kartaList;
    }

    public List<Ocena> getOcenaList() {
        return ocenaList;
    }

    public List<Karta> getKartaList() {
        return kartaList;
    }

    @Override
    public int getRowCount() {
        return kartaList.size();
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex){
            case 0:
                return kartaList.get(rowIndex).getProjekcija().getFilm().getImeFilma();
            case 1:
                return kartaList.get(rowIndex).getProjekcija().getPocetakProjekcije();
            case 2:
                long filmId = kartaList.get(rowIndex).getProjekcija().getFilm().getId();
                for (Ocena o: ocenaList){
                    if (o.getFilm().getId()==filmId) return o.getOcena();
                }
                return "/";
            default:
                return "";
        }
    }

    @Override
    public String getColumnName(int column) {
        String[] columns = {"Film", "Gledano", "Ocenio"};
        return columns[column];
    }

//    @Override
//    public Class<?> getColumnClass(int columnIndex) {
//        Class[] columns = {String.class, String.class, Double.class};
//        return columns[columnIndex];
//    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }
}
