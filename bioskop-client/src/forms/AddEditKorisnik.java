package forms;

import communication.Communication;
import datechooser.beans.DateChooserCombo;
import domen.Korisnik;

import javax.swing.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class AddEditKorisnik extends JDialog {

    Korisnik korisnik;
    ShowList parent;
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JPanel pnlCenter;
    private JPanel pnlBottom;
    private JLabel lblNaslov;
    private JPanel pnl1;
    private JPanel pnlIme;
    private JLabel lblIme;
    private JTextField txtIme;
    private JPanel pnlDatum;
    private JLabel lblDatum;
    private JButton btnDelete;
    private DateChooserCombo dateChooserCombo = new DateChooserCombo();

    public AddEditKorisnik(Korisnik korisnik) {
        this.korisnik = korisnik;
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        prepareView();

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

        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onDelete();
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

    private void prepareView() {
        setSize(300, 285);
        dateChooserCombo.setDateFormat(new SimpleDateFormat("dd.MM.yyyy"));
        pnlDatum.add(dateChooserCombo);


        if (korisnik!=null){
            popuniPolja();
            btnDelete.setVisible(true);
        } else {

        }
    }

    private void popuniPolja() {
        lblNaslov.setText("Izmeni korisnika");
        txtIme.setText(korisnik.getIme());
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(korisnik.getDatumRodjenja());
        dateChooserCombo.setSelectedDate(calendar);
    }

    private void onOK() {
        if (korisnik==null){
            napraviKorisnika();
        } else {
            izmeniKorisnika();
            parent.prepareView();
        }
        dispose();
    }

    private void onDelete(){
        try {
            Communication.getInstance().deleteKarte(korisnik);
            JOptionPane.showMessageDialog(this, "USPESNO");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Sistem ne moze da obrise karte");
            throw new RuntimeException(e);
        }
    }

    private void izmeniKorisnika() {
        korisnik.setIme(txtIme.getText());
        korisnik.setDatumRodjenja(dateChooserCombo.getSelectedDate().getTime());
        try {
        Communication.getInstance().editKorisnik(korisnik);

    } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Sistem ne moze da zapamti korisnika");
            e.printStackTrace();
        return;
    }
        JOptionPane.showMessageDialog(this, "USPESNO");
    }

    private void napraviKorisnika() {
        korisnik = new Korisnik();
        korisnik.setIme(txtIme.getText());
        korisnik.setDatumRodjenja(dateChooserCombo.getSelectedDate().getTime());
        try {
            Communication.getInstance().createKorisnik(korisnik);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Sistem ne moze da zapamti korisnika");
            e.printStackTrace();
            return;
        }
        JOptionPane.showMessageDialog(this, "USPESNO");
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    public void setParent(ShowList parent) {
        this.parent = parent;
    }

    public static void main(String[] args) {
        AddEditKorisnik dialog = new AddEditKorisnik(null);
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
