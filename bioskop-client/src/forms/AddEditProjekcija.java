package forms;

import communication.Communication;
import datechooser.beans.DateChooserCombo;
import domen.Film;
import domen.GenericEntity;
import domen.Projekcija;
import domen.Sala;

import javax.swing.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class AddEditProjekcija extends JDialog {

    private Projekcija projekcija;
    List<Film> filmList = new ArrayList<>();
    private ShowList parent;
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JPanel pnlCenter;
    private JPanel pnlBottom;
    private JLabel lblNaslov;
    private JPanel pnl2;
    private JPanel pnlIzborFilma;
    private JComboBox cmbFilm;
    private JPanel pnl3;
    private JPanel pnlIzborDatuma;
    private JLabel txtDatum;
    private JPanel pnl4;
    private JPanel pnlIzborVremena;
    private JLabel lblVreme;
    private JTextField txtSati;
    private JLabel lblDveTacke;
    private JTextField txtMinuti;
    private JPanel pnl5;
    private JLabel lblSala;
    private JComboBox cmbSala;
    private JLabel lblFilm;
    private JPanel pnlVrstaProjekcije;
    private JComboBox cmbVrstaProjekcije;
    private DateChooserCombo dateChooserCombo = new DateChooserCombo();

    public AddEditProjekcija(Projekcija projekcija) {
        this.projekcija = projekcija;
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        try {
            prepareView();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Nemoguce izvlacenje filmova!\n"+e.getMessage());
            dispose();
            return;
        }


        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

    }

    private void prepareView() throws Exception {
        setSize(300, 285);
        setResizable(false);
        dateChooserCombo.setDateFormat(new SimpleDateFormat("dd.MM.yyyy"));
        pnlIzborDatuma.add(dateChooserCombo);
        List<GenericEntity> entityList = Communication.getInstance().getAllFilm();
        cmbFilm.removeAllItems();
        cmbFilm.addItem("");
        for (GenericEntity entity : entityList){
            Film film = (Film) entity;
            filmList.add(film);
            cmbFilm.addItem(film.getImeFilma());
        }

        if (projekcija!= null){
            popuniPolja();
        } else {

        }
    }

    private void popuniPolja() {
        lblNaslov.setText("Izmeni projekciju");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(projekcija.getPocetakProjekcije());
        dateChooserCombo.setSelectedDate(calendar);
        txtSati.setText(calendar.get(Calendar.HOUR_OF_DAY)+"");
        txtMinuti.setText(calendar.get(Calendar.MINUTE)+"");
        cmbSala.setSelectedIndex((int) projekcija.getSala().getBrojSale());

        int vrstaProj;
        switch (projekcija.getVrstaProjekcije()){
            case "35mm":
                vrstaProj =1;
                break;
            case "IMAX":
                vrstaProj=2;
                break;
            case "3D":
                vrstaProj=3;
                break;
            default:
                vrstaProj=0;
        }
        cmbVrstaProjekcije.setSelectedIndex(vrstaProj);
    }

    private void onOK() {
        try {
            if (projekcija == null) kreirajProjekciju();
            else {
                izmeniProjekciju();
            }
            dispose();
        } catch (Exception e){
            e.printStackTrace();
            projekcija = null;
            JOptionPane.showMessageDialog(this, "Sistem ne moze da kreira novu projekciju");
        }
        
    }

    private void izmeniProjekciju() throws Exception {
        throw new Exception("Nije moguce menjati projekciju");
    }

    private void kreirajProjekciju() throws Exception {
        projekcija = new Projekcija();
        polja2projekcija();
        Communication.getInstance().createProjekcija(projekcija);
        JOptionPane.showMessageDialog(this, "USPESNO!");
    }

    private void polja2projekcija() throws Exception {
        Sala s = new Sala();
        if (cmbSala.getSelectedIndex()==0) throw new Exception("Nije odabrana sala za projekciju");
        s.setBrojSale(cmbSala.getSelectedIndex());
        projekcija.setSala(s);
        if (cmbFilm.getSelectedIndex()==0) throw new Exception("Nije odabran film");
        projekcija.setFilm(filmList.get(cmbFilm.getSelectedIndex()-1));
        projekcija.setVrstaProjekcije(cmbVrstaProjekcije.getSelectedItem().toString());

        int sat = Integer.parseInt(txtSati.getText());
        int min = Integer.parseInt(txtMinuti.getText());

        System.out.println("Izabrano vreme: " + sat + ":" + min);
        Calendar calendar = dateChooserCombo.getSelectedDate();
        Date date = calendar.getTime();
        date.setHours(sat);
        date.setMinutes(min);
        date.setSeconds(0);
        System.out.println("Konacni date: " + date);
        projekcija.setPocetakProjekcije(date);

    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    public static void main(String[] args) {
        AddEditProjekcija dialog = new AddEditProjekcija(null);
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }

    public void setParent(ShowList showList) {
        this.parent = parent;
    }
}
