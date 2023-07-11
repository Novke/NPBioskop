package forms;

import domen.Film;
import domen.Korisnik;
import domen.Projekcija;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControlView {
    private static JFrame frame;
    private JPanel pnlMain;
    private JMenuBar menuBar;
    private JPanel pnl1;
    private JPanel pnl2;
    private JButton btnBook;
    private JButton btnRate;
    private JButton btnLogout;
    private JButton btnProjekcije;

    private final JMenu menuNew = new JMenu(" New ");
    private final JMenuItem menuItemNewKorisnik = new JMenuItem("Registruj korisnika");
    private final JMenuItem menuItemNewProjekcija = new JMenuItem("Zakazi projekciju");
    private final JMenuItem menuItemNewFilm = new JMenuItem("Dodaj film");
    private final JMenu menuShow = new JMenu("Prikazi");
    private final JMenuItem menuItemShowKorisnik = new JMenuItem("Korisnike");
    private final JMenuItem menuItemShowFilm = new JMenuItem("Filmove");
    private final JMenuItem menuItemShowProjekcija = new JMenuItem("Projekcije");

    public static void main(String[] args) {
        ControlView view = new ControlView();
        show(view);
    }

    public static void show(ControlView view) {
        frame = new JFrame("Kontrolna Tabla");
        frame.setContentPane(view.pnlMain);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        view.prepareView();
        frame.pack();
        frame.setVisible(true);
    }

    private void prepareView() {
        prepareMenuBar();
        frame.setSize(1000,600);
        frame.setLocationRelativeTo(null);

    }

    private void prepareMenuBar() {
        frame.setJMenuBar(menuBar);

        menuBar.add(menuNew);
        menuBar.add(menuShow);

        menuNew.add(menuItemNewKorisnik);
        menuNew.add(menuItemNewProjekcija);
        menuNew.add(menuItemNewFilm);

        menuShow.add(menuItemShowKorisnik);
        menuShow.add(menuItemShowProjekcija);
        menuShow.add(menuItemShowFilm);
    }

    public ControlView(){

        btnRate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame("OceniFilm");
                frame.setContentPane(new OceniFilm().getMainPanel());
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.setLocationRelativeTo(null);
                frame.pack();
                frame.setVisible(true);

            }
        });

        btnBook.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                IzaberiProjekciju dialog = new IzaberiProjekciju();
                dialog.setLocationRelativeTo(null);
                dialog.pack();
                dialog.setVisible(true);
            }
        });

        btnProjekcije.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JDialog dialog = new ShowList(new Projekcija());
                dialog.setLocationRelativeTo(pnlMain);
                ((ShowList)dialog).adaptToProjekcijaWeek();
                dialog.setVisible(true);

            }
        });

        btnLogout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int choice = JOptionPane.showConfirmDialog(null, "Da li ste sigurni?", "ZBOGOM :(", JOptionPane.YES_NO_OPTION);
                if (choice == JOptionPane.YES_OPTION) {
                    System.exit(1);
                } else {
                    JOptionPane.getRootFrame().dispose();
                }

            }
        });

        menuItemNewProjekcija.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JDialog dialog = new AddEditProjekcija(null);
                dialog.setLocationRelativeTo(pnlMain);
                dialog.pack();
                dialog.setVisible(true);
            }
        });

        menuItemNewKorisnik.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JDialog dialog = new AddEditKorisnik(null);
                dialog.setLocationRelativeTo(pnlMain);
                dialog.pack();
                dialog.setVisible(true);
            }
        });

        menuItemNewFilm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JDialog dialog = new AddEditFilm(null);
                dialog.setLocationRelativeTo(null);
                dialog.pack();
                dialog.setVisible(true);
            }
        });

        menuItemShowKorisnik.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JDialog dialog = new ShowList(new Korisnik());
                dialog.setLocationRelativeTo(pnlMain);
                dialog.setVisible(true);
            }
        });

        menuItemShowFilm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                JDialog dialog = new ShowList(new Film());
                dialog.setLocationRelativeTo(pnlMain);
                dialog.setVisible(true);
            }
        });

        menuItemShowProjekcija.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                JDialog dialog = new ShowList(new Projekcija());
                dialog.setLocationRelativeTo(pnlMain);
                dialog.setVisible(true);
            }
        });

    }

}
