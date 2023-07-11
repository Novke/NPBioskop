package forms;

import communication.Communication;
import datechooser.beans.DateChooserCombo;
import domen.Film;

import javax.swing.*;
import java.awt.event.*;

public class AddEditFilm extends JDialog {
    Film film;
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JPanel pnlDugmici;
    private JPanel pnlMain;
    private JPanel pnlIme;
    private JPanel pnl1;
    private JPanel pnlTrajanje;
    private JPanel pnl2;
    private JPanel pnlOcena;
    private JPanel pnl3;
    private JPanel pnlDatum;
    private JPanel pnlOpis;
    private JTextField txtIme;
    private JFormattedTextField txtTrajanje;
    private JLabel lblMin;
    private JFormattedTextField txtOcena;
    private JLabel lblDatum;
    private JTextArea txtOpis;
    private DateChooserCombo dateChooser = new DateChooserCombo();
    private ShowList showList;

    public AddEditFilm(Film film) {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        this.film = film;

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
        pnlDatum.add(dateChooser);

        if (film != null){
            popuniPolja();
        }
    }

    private void popuniPolja() {
        txtIme.setText(film.getImeFilma());
        //dateChooser.setSelectedDate(film.getPocetakPrikazivanja().getTime());
        txtOpis.setText(film.getOpis());
        txtTrajanje.setText(film.getTrajanje()+"");
        txtOcena.setText(film.getOcena()+"");
    }

    private void onOK() {
        try {
            if (film != null) {
                izmeniFilm();
            } else {
                napraviFilm();
            }

            dispose();
        } catch (Exception e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Sistem ne moze da zapamti film");
        }
    }

    private void izmeniFilm() throws Exception{
        fields2object();

        Communication.getInstance().editFilm(film);
        JOptionPane.showMessageDialog(this, "USPESNO");
    }

    private void napraviFilm() throws Exception {
        film = new Film();
        fields2object();

        Communication.getInstance().createFIlm(film);
        JOptionPane.showMessageDialog(this, "USPESNO");
    }

    private void fields2object() {
        film.setImeFilma(txtIme.getText());
        film.setTrajanje(Integer.parseInt(txtTrajanje.getText()));
        film.setOpis(txtOpis.getText());
        film.setOcena(Double.parseDouble(txtOcena.getText()));
        film.setPocetakPrikazivanja(dateChooser.getSelectedDate().getTime());
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    public static void main(String[] args) {
        AddEditFilm dialog = new AddEditFilm(null);
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }

    public void setParent(ShowList showList) {
        this.showList = showList;
    }
}
