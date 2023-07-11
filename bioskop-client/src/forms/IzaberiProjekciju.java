package forms;

import communication.Communication;
import domen.GenericEntity;
import domen.Projekcija;
import forms.sale.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;

public class IzaberiProjekciju extends JDialog {
    private List<Projekcija> projekcijaList =new ArrayList<>();
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTable table;

    public IzaberiProjekciju() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        setTitle("PREDSTOJECE PROJEKCIJE");

        try {
            prepareView();


        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Sistem ne moze da nadje projekcije");
            throw new RuntimeException(e);
        }
        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                buttonOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() >= 2 && !e.isConsumed()) {
                    buttonOK();
                    e.consume();
                }
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

    private void buttonOK() {
        int selectedRow = table.getSelectedRow();

        Projekcija p = projekcijaList.get(selectedRow);

        if (brojSlobodnihMesta(p).equals("0")){
            JOptionPane.showMessageDialog(null, "NEMA SLOBODNIH MESTA");
            return;
        }

        JFrame frame = new JFrame();
        AbstractSala sala;

        switch ((int) p.getSala().getBrojSale()){
            case 1:
                sala = new Sala1(p);
                break;

            case 2:
                sala = new Sala2(p);
                break;

            case 3:
                sala = new Sala3(p);
                break;

            case 4:
                sala = new Sala4(p);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + (int) p.getSala().getBrojSale());
        }

        sala.setFrame(frame);
        frame.setAlwaysOnTop(true);
        frame.setVisible(true);
        dispose();
    }

    private void prepareView() throws Exception {
        contentPane.setPreferredSize(new Dimension(600, 300));

        List<GenericEntity> entityList = Communication.getInstance().getAllProjekcija();
        System.out.println("Izvuceno objekata: " + entityList.size());

        for (GenericEntity entity : entityList){
            Projekcija projekcija = (Projekcija) entity;
            if (projekcija.getPocetakProjekcije().after(new Date())){
                projekcijaList.add(projekcija);
            }
        }
        
        sortirajPoDatumu(projekcijaList);

        DefaultTableModel model = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Make all cells uneditable
            }
        };
        model.addColumn("Film");
        model.addColumn("Vreme");
        model.addColumn("Sala");
        model.addColumn("Vrsta projekcije");
        model.addColumn("Slobodnih mesta:");

        for (Projekcija projekcija : projekcijaList){
            dodajRed(projekcija, model);
        }
        table.setModel(model);
    }

    private void sortirajPoDatumu(List<Projekcija> projekcijaList) {
        Collections.sort(projekcijaList, new Comparator<Projekcija>() {
            @Override
            public int compare(Projekcija p1, Projekcija p2) {
                Date date1 = p1.getPocetakProjekcije();
                Date date2 = p2.getPocetakProjekcije();
                return date1.compareTo(date2);
            }
        });

    }

    private void dodajRed(Projekcija projekcija, DefaultTableModel model) {
        Object[] o = {  projekcija.getFilm().getImeFilma(),
                        projekcija.getPocetakProjekcije().toString().split("CEST")[0],
                        "Sala " + projekcija.getSala().getBrojSale(),
                        projekcija.getVrstaProjekcije(),
                        brojSlobodnihMesta(projekcija)};
        model.addRow(o);
    }

    private static String brojSlobodnihMesta(Projekcija projekcija)  {
        try {
            return (projekcija.getSala().getBrojSedista() - Communication.getInstance().getAllKarte(projekcija).size())+"";
        } catch (Exception e) {
            return "";
        }
    }

    private void onOK() {
        // add your code here
        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    public static void main(String[] args) {
        IzaberiProjekciju dialog = new IzaberiProjekciju();
        dialog.pack();
        dialog.setVisible(true);
//        System.exit(0);
    }
}
