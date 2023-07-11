package tableModel;

import communication.Communication;
import domen.GenericEntity;
import domen.Korisnik;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class KorisnikTM extends AbstractTableModel {

    List<GenericEntity> korisnici = new ArrayList<>();

    public void setKorisnici(List<GenericEntity> korisnici) {
        this.korisnici = korisnici;
    }


    @Override
    public int getRowCount() {
        return korisnici.size();
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Korisnik k = (Korisnik) korisnici.get(rowIndex);

        switch (columnIndex){
            case 0:
                return k.getId();
            case 1:
                return k.getIme();
            case 2:
                return k.getDatumRodjenja();
            case 3:
                try {
                    return Communication.getInstance().getAktivneKarte(k).size();
                } catch (Exception e) {
                    return "/";
                }
            default:
                return "/";
        }
    }

    @Override
    public String getColumnName(int column) {
        String[] columns = {"ID", "Ime", "Datum Rodjenja", "Aktivne karte"};
        return columns[column];
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        Class[] niz = {Long.class, String.class, Date.class, Integer.class};
        return niz[columnIndex];
    }
}
