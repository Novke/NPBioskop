package forms;

import communication.Communication;
import domen.Film;
import domen.GenericEntity;
import domen.Korisnik;
import domen.Projekcija;
import tableModel.FilmTM;
import tableModel.KorisnikTM;
import tableModel.ProjekcijaTM;

import javax.swing.event.*;
import javax.swing.*;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import java.awt.event.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.*;

public class ShowList extends JDialog {

    private GenericEntity entity;
    private List<GenericEntity> list;
    private JPanel contentPane;
    private JButton buttonObrisi;
    private JButton buttonOK;
    private JTable table;
    private JPanel pnlBottom;
    private JPanel pnlCenter;
    private JPanel pnl1;
    private JLabel lblNaslov;
    private JButton buttonIzmeni;
    private TableModel model;

    public ShowList(GenericEntity entity) {
        this.entity = entity;
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonObrisi);

        prepareView();

        buttonObrisi.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onObrisi();
            }
        });

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOk();
            }
        });

        buttonIzmeni.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onIzmeni();
            }
        });

        table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    int selectedRow = table.getSelectedRow();
                    if (selectedRow == -1) {
                        buttonIzmeni.setEnabled(false);
                        buttonObrisi.setEnabled(false);
                    } else {
                        buttonIzmeni.setEnabled(true);
                        buttonObrisi.setEnabled(true);
                    }
                }
            }
        });

        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() >= 2 && !e.isConsumed()) {
                    int selectedRow = table.getSelectedRow();
                    String ispis = list.get(selectedRow).AllDetails();

                    JOptionPane.showMessageDialog(contentPane, ispis);
                    e.consume();
                }
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onOk();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOk();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);



    }

    public void prepareView() {
        buttonIzmeni.setEnabled(false);
        buttonObrisi.setEnabled(false);

        if (entity instanceof Korisnik){

            adaptToKorisnik();
            return;
        }
        if (entity instanceof Projekcija){
            
            adaptToProjekcija();
            return;
        }
        if (entity instanceof Film){
            
            adaptToFilm();
            return;
        }
    }

    private void adaptToFilm() {
        buttonIzmeni.setVisible(false);
        model = new FilmTM();
        try {
            list = Communication.getInstance().getAllFilm();
            sortirajFilmove();
            ((FilmTM)model).setFilmovi(list);
            table.setModel(model);
            lblNaslov.setText("SVI FILMOVI");
            setColumnWidth(1,150);
            setColumnWidth(5,100);
            pack();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Neuspenso izvlacenje filmova");
        }
    }

    private void adaptToProjekcija() {
        buttonIzmeni.setVisible(false);
        model = new ProjekcijaTM();
        try {
            list = Communication.getInstance().getAllProjekcija();
            sortirajProjekcije();
            ((ProjekcijaTM)model).setProjekcije(list);
            table.setModel(model);
            lblNaslov.setText("SVE PROJEKCIJE");
            setColumnWidth(1,150);

            pack();
        } catch (Exception e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Neuspesno izvlacenje projekcija");
            throw new RuntimeException("Neuspesno izvlacenje projekcija");
        }
    }

    private void sortirajProjekcije() {

        Collections.sort(list, new Comparator<GenericEntity>() {
            @Override
            public int compare(GenericEntity o1, GenericEntity o2) {
                Date date1 = ((Projekcija)o1).getPocetakProjekcije();
                Date date2 = ((Projekcija)o2).getPocetakProjekcije();
                return date1.compareTo(date2);
            }
        });

    }

    private void sortirajFilmove(){
        Collections.sort(list, new Comparator<GenericEntity>() {
            @Override
            public int compare(GenericEntity o1, GenericEntity o2) {
                Date date1 = ((Film)o1).getPocetakPrikazivanja();
                Date date2 = ((Film)o2).getPocetakPrikazivanja();
                return date1.compareTo(date2);
            }
        });
    }

    public void adaptToProjekcijaWeek(){
        List<GenericEntity> newList = new ArrayList<>();
        setColumnWidth(1,150);
        for (GenericEntity entity : list){
            if (isWithinNext7Days(((Projekcija)entity).getPocetakProjekcije())) newList.add(entity);
        }
        ((ProjekcijaTM)model).setProjekcije(newList);
        table.setModel(model);
        lblNaslov.setText("PROJEKCIJE U NAREDNIH 7 DANA");
        pack();
    }

    private boolean isWithinNext7Days(Date pocetakProjekcije) {
        LocalDate currentDate = LocalDate.now();
        LocalDateTime startDateTime = pocetakProjekcije.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        LocalDateTime now = LocalDateTime.of(currentDate, LocalTime.MIDNIGHT);
        LocalDateTime endDate = now.plusDays(7);

        return startDateTime.isBefore(endDate) && startDateTime.isAfter(now);
    }

    private void adaptToKorisnik() {
        model = new KorisnikTM();

        try {
            list = Communication.getInstance().getAllKorisnik();
            ((KorisnikTM)model).setKorisnici(list);
            table.setModel(model);
            lblNaslov.setText("SVI KORISNICI");
            setColumnWidth(1,35);

            pack();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Sistem ne moze da vrati korisnike");
            dispose();
        }

    }

    private void onObrisi(){
        int index = table.getSelectedRow();
        if (index<0) return;
        GenericEntity item = list.get(index);
        String greska = "";
        try {

            if (entity instanceof Korisnik) {
                greska = "korisnika";
                Communication.getInstance().deleteKorisnik((Korisnik) item);
                adaptToKorisnik();
            }
            if (entity instanceof Projekcija) {
                greska = "projekciju";
                Communication.getInstance().deleteProjekcija((Projekcija) item);
                adaptToProjekcija();
            }
            if (entity instanceof Film) {
                greska = "film";
                Communication.getInstance().deleteFilm((Film) item);
                adaptToFilm();
            }
            JOptionPane.showMessageDialog(this, "USPESNO!");

        } catch (Exception e){
            JOptionPane.showMessageDialog(this, "Sistem ne moze da obrise " + greska);
            e.printStackTrace();
        }
    }

    private void onOk() {
        dispose();
    }

    private void onIzmeni(){

        int index = table.getSelectedRow();
        if (index<0) return;
        GenericEntity item = list.get(index);

        try {

            if (entity instanceof Korisnik) {

                AddEditKorisnik dialog = new AddEditKorisnik((Korisnik) item);
                dialog.setParent(this);
                dialog.setVisible(true);
                dialog.pack();
            }
            if (entity instanceof Projekcija) {

                AddEditProjekcija dialog = new AddEditProjekcija((Projekcija) item);
                dialog.setParent(this);
                dialog.pack();
                dialog.setVisible(true);
            }
            if (entity instanceof Film) {
                AddEditFilm dialog = new AddEditFilm((Film)item);
                dialog.setParent(this);
                dialog.pack();
                dialog.setVisible(true);

            }


        } catch (Exception e){
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    private void setColumnWidth(int column, int width){
        TableColumnModel columnModel = table.getColumnModel();
        TableColumn firstColum = columnModel.getColumn(column-1);
        firstColum.setPreferredWidth(width);
    }

    public static void main(String[] args) {
        ShowList dialog = new ShowList(new Korisnik());
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
