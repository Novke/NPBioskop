package tableModel;

import communication.Communication;
import domen.Film;
import domen.GenericEntity;
import domen.Ocena;

import javax.swing.table.AbstractTableModel;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class FilmTM extends AbstractTableModel {

    List<GenericEntity> filmovi = new ArrayList<>();
    SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");

    public void setFilmovi(List<GenericEntity> filmovi) {
        this.filmovi = filmovi;
    }

    @Override
    public int getRowCount() {
        return filmovi.size();
    }

    @Override
    public int getColumnCount() {
        return 5;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Film f = (Film) filmovi.get(rowIndex);
        switch (columnIndex){
            case 0:
                return f.getImeFilma();
            case 1:
                return f.getTrajanje() + " min";
            case 2:
                return f.getOcena();
            case 3:
                try {
                    List<Ocena> ocene = Communication.getInstance().getOcene(f);
                    double ukupno = 0;
                    for (Ocena o: ocene){
                        ukupno+=o.getOcena();
                    }
                    if (ukupno == 0) throw new Exception();
                    return ukupno/ocene.size();
                } catch (Exception e) {
                    return "/";
                }
            case 4:
                return sdf.format(f.getPocetakPrikazivanja());
            default:
                return "";
        }
    }

    @Override
    public String getColumnName(int column) {
        String[] columns = {"Ime", "Trajanje", "IMDB", "Ocena korisnika", "Premijera"};
        return columns[column];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return /*columnIndex == 2 ? Double.class :*/ String.class;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }
}
