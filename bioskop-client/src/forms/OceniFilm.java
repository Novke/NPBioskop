package forms;

import communication.Communication;
import domen.*;
import tableModel.OcenaTM;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OceniFilm {

    List<GenericEntity> sviKorisnici = new ArrayList<>();
    List<Korisnik> prikazaniKorisnici = new ArrayList<>();
    private JPanel pnlMain;
    private JPanel pnlIzborKorisnika;
    private JLabel lblFiltriraj;
    private JTextField txtFilter;
    private JComboBox cbKorisnici;
    private JPanel pnl1;
    private JTable table;

    public OceniFilm() {
        initialize();
        prepareCombo();
        prepareView();
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2 && !e.isConsumed()) {
                    int selectedRow = table.getSelectedRow();

                    Film film = ((OcenaTM)table.getModel()).getKartaList().get(selectedRow).getProjekcija().getFilm();
                    if (cbKorisnici.getSelectedIndex()<=0) return;
                    Korisnik korisnik = prikazaniKorisnici.get(cbKorisnici.getSelectedIndex()-1);

                    otvoriDijalog(film, korisnik);
                    e.consume();
                }
            }
        });

        cbKorisnici.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                prepareView();
            }
        });

        txtFilter.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {
                prepareCombo();
                table.setModel(new DefaultTableModel());
            }
        });

    }

    public JPanel getMainPanel(){
        return pnlMain;
    }

    private void otvoriDijalog(Film film, Korisnik korisnik) {
        UnosOcene dialog = new UnosOcene(film, korisnik, this);
        dialog.pack();
        dialog.setVisible(true);
    }

    private void initialize() {
        pnlMain.setSize(600,400);

    }

    private void prepareCombo() {
        try {
            sviKorisnici = Communication.getInstance().getAllKorisnik();
            if (sviKorisnici == null || sviKorisnici.isEmpty()) throw new Exception();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Sistem ne moze da vrati korisnike");
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        prikazaniKorisnici.clear();
        String filter = txtFilter.getText();
        if (filter.isBlank()){
            for (GenericEntity entity : sviKorisnici){
                prikazaniKorisnici.add((Korisnik) entity);
            }
        } else {
            for (GenericEntity entity : sviKorisnici){
                Korisnik k = (Korisnik) entity;
                if (k.getIme().toLowerCase().contains(filter.toLowerCase())){
                    prikazaniKorisnici.add(k);
                }
            }
        }
        cbKorisnici.removeAllItems();
        cbKorisnici.addItem("");
        for (Korisnik k : prikazaniKorisnici){
            cbKorisnici.addItem(k.getIme());
        }
    }

    public void prepareView() {
        if (cbKorisnici.getSelectedIndex()<=0) return;
        Korisnik korisnik = prikazaniKorisnici.get(cbKorisnici.getSelectedIndex()-1);
        List<Karta> kartaListAll = nabaviKarte(korisnik);
        List<Ocena> ocenaList = nabaviOcene(korisnik);
        List<Karta> kartaList = new ArrayList<>();

        if (kartaListAll == null){
            JOptionPane.showMessageDialog(pnlMain, "Sistem ne moze da prikaze projekcije");
        }

        List<Karta> kartaListFiltered = new ArrayList<>();
        for (Karta k1 : kartaListAll){

            for (int i = 0;; i++){
                if (kartaListFiltered.isEmpty()) kartaListFiltered.add(k1);
                if (kartaListFiltered.get(i).getProjekcija().getFilm().getFilmID() == k1.getProjekcija().getFilm().getId()) break;
                if (i == kartaListFiltered.size()-1) {
                    kartaListFiltered.add(k1);
                    break;
                }
            }
        }


        for (Karta karta : kartaListFiltered){
            if (karta.getProjekcija().getPocetakProjekcije().before(new Date())){
                kartaList.add(karta);
            }
        }
        OcenaTM model = new OcenaTM(ocenaList,kartaList);
        table.setModel(model);
        table.setVisible(true);
    }

    private List<Ocena> nabaviOcene(Korisnik korisnik) {
        try {
            List<Ocena> ocenaList = Communication.getInstance().getAllOcena();
            List<Ocena> korisnikoveOcene = new ArrayList<>();

            for (Ocena o : ocenaList){
                if (o.getKorisnik().getId() == korisnik.getId()){
                    korisnikoveOcene.add(o);
                }
            }
            return korisnikoveOcene;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private List<Karta> nabaviKarte(Korisnik korisnik) {
        try {
            List<Karta> kartaList = Communication.getInstance().getAllKarte(korisnik);
            return kartaList;
        } catch (Exception e){
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("OceniFilm");
        frame.setContentPane(new OceniFilm().pnlMain);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
