package forms;

import domen.GenericEntity;
import domen.Karta;
import domen.Korisnik;
import domen.Ocena;
import forms.sale.AbstractSala;
import tableModel.OcenaTM;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class IzaberiKorisnika extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField txtFilter;
    private JComboBox cmbKorisnici;
    private JPanel pnlCentar;
    private JPanel pnlBottom;
    AbstractSala sala;
    List<GenericEntity> sviKorisnici;
    List<GenericEntity> prikazaniKorisnici = new ArrayList<>();

    public IzaberiKorisnika(AbstractSala sala, List<GenericEntity> korisnici) {
        this.sala = sala;
        this.sviKorisnici = korisnici;
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);


        prepareCombo();
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

    }

    private void prepareCombo() {
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
        cmbKorisnici.removeAllItems();
        cmbKorisnici.addItem("");
        for (GenericEntity entity : prikazaniKorisnici){
            cmbKorisnici.addItem(((Korisnik)entity).getIme());
        }
    }

    private void onOK() {
        if (cmbKorisnici.getSelectedIndex()<=0) return;
        Korisnik korisnik = (Korisnik) prikazaniKorisnici.get(cmbKorisnici.getSelectedIndex()-1);
        sala.setKorisnik(korisnik);

        dispose();

    }

    private void onCancel() {
        dispose();
    }
}
