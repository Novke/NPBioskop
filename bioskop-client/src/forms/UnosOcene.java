package forms;

import communication.Communication;
import domen.Film;
import domen.Korisnik;
import domen.Ocena;
import domen.Projekcija;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class UnosOcene extends JDialog {
    int izbor;
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JPanel pnl10;
    private JPanel pnlDown;
    private JPanel pnlUp;
    private JPanel pnl123;
    private JPanel pnl789;
    private JPanel pnl456;
    private JButton btn10;
    private JButton btn1;
    private JButton btn2;
    private JButton btn3;
    private JButton btn4;
    private JButton btn5;
    private JButton btn6;
    private JButton btn7;
    private JButton btn8;
    private JButton btn9;

    Film film;
    Korisnik korisnik;
    OceniFilm oceniFilm;

    public UnosOcene(Film film, Korisnik korisnik, OceniFilm oceniFilm) {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        this.film=film;
        this.korisnik = korisnik;
        this.oceniFilm=oceniFilm;

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

        btn1.addActionListener(dugme);
        btn2.addActionListener(dugme);
        btn3.addActionListener(dugme);
        btn4.addActionListener(dugme);
        btn5.addActionListener(dugme);
        btn6.addActionListener(dugme);
        btn7.addActionListener(dugme);
        btn8.addActionListener(dugme);
        btn9.addActionListener(dugme);
        btn10.addActionListener(dugme);

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

    private void onOK() {
        Ocena ocena = new Ocena();
        ocena.setKorisnik(korisnik);
        ocena.setFilm(film);
        ocena.setOcena(izbor);
        try {
            Communication.getInstance().insertOcena(ocena);
            oceniFilm.prepareView();
        } catch (Exception e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(contentPane, "Sistem ne moze da zapamti ocenu");
        }

        dispose();
    }

    private void onCancel() {
        dispose();
    }


    ActionListener dugme = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton pressedButton = (JButton) e.getSource();
            izbor = Integer.parseInt(pressedButton.getText());
            osposobiDugmice();
            pressedButton.setEnabled(false);
        }
    };

    private void osposobiDugmice() {
        btn1.setEnabled(true);
        btn2.setEnabled(true);
        btn3.setEnabled(true);
        btn4.setEnabled(true);
        btn5.setEnabled(true);
        btn6.setEnabled(true);
        btn7.setEnabled(true);
        btn8.setEnabled(true);
        btn9.setEnabled(true);
        btn10.setEnabled(true);
        buttonOK.setEnabled(true);
    }
}
