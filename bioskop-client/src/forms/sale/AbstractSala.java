package forms.sale;

import communication.Communication;
import domen.GenericEntity;
import domen.Karta;
import domen.Korisnik;
import domen.Projekcija;
import forms.IzaberiKorisnika;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public abstract class AbstractSala {
    protected Projekcija projekcija;
    private Korisnik korisnik;
    protected List<Karta> sveRezervacije = new ArrayList<>();
    protected List<Karta> sveKarte = new ArrayList<>();
    protected JFrame frame;
    protected JLabel absLblBrojRezervacija;
    protected JButton absBtnRezervisi;
    protected JButton absBtnIzadji;
    protected JCheckBox absCbVip;
    protected JButton absBtnPlatno;
    protected JButton[][] svaMesta;



    abstract void setSvaMesta();
    protected void nabaviSveRezervacije() throws Exception{
        sveRezervacije = Communication.getInstance().getAllKarte(projekcija);
    }
    protected void obojiDugmice() throws Exception{
        for (Karta k : sveRezervacije){
            JButton button = svaMesta[k.getRed()-1][k.getMesto()-1];
            button.setBackground(Color.RED);
            button.setEnabled(false);
        }
    }

    protected void prepareView() throws Exception{
        try {
            setteri();
            nabaviSveRezervacije();
            prepareDugmice();
            obojiDugmice();
            detalji();
        } catch (Exception e){
            JOptionPane.showMessageDialog(frame, "Sistem ne moze da prikaze podatke o izabranoj projekciji");
            frame.dispose();
            throw new RuntimeException(e);
        }
    }

    private void setteri() {
        setSvaMesta();
        setBrojRezervacijaLabel();
        setIzadjiButton();
        setRezervisiButton();
        setVipCheckbox();
        setBtnPlatno();
    }

    private void detalji() {
        absCbVip.setText("Zelim VIP karte");

        absLblBrojRezervacija.setText("Pritisnite na slobodno sediste");


        Border thickBorder = BorderFactory.createLineBorder(Color.BLACK, 3);

        absBtnRezervisi.setText("Rezervisi");
        absBtnRezervisi.setBackground(Color.LIGHT_GRAY.brighter());
        absBtnRezervisi.setPreferredSize(new Dimension(100, 40));
        absBtnRezervisi.setBorder(thickBorder);

        absBtnIzadji.setText("Izadji");
        absBtnIzadji.setBackground(Color.LIGHT_GRAY.brighter());
        absBtnIzadji.setPreferredSize(new Dimension(100, 40));
        absBtnIzadji.setBorder(thickBorder);

        absBtnPlatno.setText("PLATNO");
        absBtnPlatno.setBackground(Color.PINK);
        absBtnPlatno.setEnabled(false);
    }

    protected void prepareDugmice() {
        ActionListener buttonClickListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                JButton clickedButton = (JButton) e.getSource();
                int row = (int) clickedButton.getClientProperty("row");
                int column = (int) clickedButton.getClientProperty("column");

                System.out.println("Red: " + (row+1) + ", Kolona: " + (column+1));

                for (Karta k : sveRezervacije){
                    if (k.getRed() == row+1 && k.getMesto() == column+1) {
                        return;
                    }
                }

                for (Karta k : sveKarte){
                    if (k.getRed() == row+1 && k.getMesto() == column+1) {
                        clickedButton.setBackground(Color.GRAY);
                        sveKarte.remove(k);
                        updateBrojRezervacija();
                        return;
                    }
                }

                Karta novaKarta = new Karta();
                novaKarta.setMesto(column+1);
                novaKarta.setRed((row+1));
                novaKarta.setProjekcija(projekcija);

                sveKarte.add(novaKarta);
                clickedButton.setBackground(Color.GREEN);
                updateBrojRezervacija();
            }
        };

        ActionListener btnRezervisiListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    buttonRezervisi();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null , "Sistem ne moze da zapamti karte");
                }

            }
        };

        for (int i = 0; i < svaMesta.length; i++) {
            for (int j = 0; j < svaMesta[i].length; j++) {
                JButton button = svaMesta[i][j];

                button.putClientProperty("row", i);
                button.putClientProperty("column", j);
                button.addActionListener(buttonClickListener);
                button.setText((i+1) + ", " + (j+1));
                button.setBackground(Color.GRAY);
                button.setPreferredSize(new Dimension(80,80));
                button.setHorizontalAlignment(SwingConstants.RIGHT);
                button.setVerticalAlignment(SwingConstants.BOTTOM);
            }
        }

        absBtnRezervisi.addActionListener(btnRezervisiListener);
        absBtnIzadji.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                exit();
            }
        });
    }

    private void buttonRezervisi() throws Exception {
        if (sveKarte == null || sveKarte.isEmpty()) return;
        if (korisnik == null) {
            frame.setAlwaysOnTop(false);
            List<GenericEntity> sviKorisnici = Communication.getInstance().getAllKorisnik();
            JDialog dialog = new IzaberiKorisnika(this, sviKorisnici);
            dialog.setLocationRelativeTo(getMainPanel());
            dialog.pack();
            dialog.setVisible(true);
            updateBrojRezervacija();
            return;
        }

        for (Karta k:sveKarte){
            k.setKorisnik(korisnik);
            String tip = absCbVip.isSelected() ? "VIP" : "Classic";
            k.setTipKarte(tip);
        }
        Communication.getInstance().bookTickets(sveKarte);
        JOptionPane.showMessageDialog(null, prodateKarteToString());
        exit();
    }

    private Object prodateKarteToString() {
        String vrati = "";
        vrati+="U S P E S N O\n\n";
        Date atm = new Date();
        vrati+="\nDatum: " + atm.getDate() + "." + (atm.getMonth()+1);
        vrati+="\nVreme: " + atm.getHours() + ":" + (atm.getMinutes()<10 ? "0" + atm.getMinutes() : atm.getMinutes());
        vrati+="\nKorisnik: " + sveKarte.get(0).getKorisnik().getIme();
        vrati+="\nProjekcija: " + projekcija.getFilm().getImeFilma() + " - " + projekcija.getPocetakProjekcije().toString().split("CEST")[0];
        vrati+="\nSala: " + projekcija.getSala().getBrojSale();
        vrati+="\n-------------------------------------";
        vrati+="\n\nUkupno karata: " + sveKarte.size();
        vrati+="\n===========================";
        for (Karta k : sveKarte){
            vrati+="\n" + "Red: " + k.getRed() + ", Mesto: " + k.getMesto();
        }
        vrati+="\n-------------------------------------";
        vrati+="\n\nUKUPNA CENA: " + ((sveKarte.get(0).getTipKarte().equals("VIP")?1750:1000)*sveKarte.size()) + ",00 din";


        return  vrati;

    }

    private void exit() {
        frame.dispose();
    }

    private void updateBrojRezervacija() {
        int broj = sveKarte.size();
        String text = "";
        if (broj == 0) text ="Pritisnite na slobodna mesta";
        else text = "Ukupno: " + broj;

        if (korisnik!=null) text+= " (" + korisnik.getIme() + ")";

        absLblBrojRezervacija.setText(text);
    }
    public void setKorisnik(Korisnik korisnik){
        this.korisnik = korisnik;
        System.out.println("Postavljen korisnik: " + korisnik.getIme());
    }

    public void setFrame(JFrame frame){
        frame.setContentPane(getMainPanel());
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setTitle("Sala " + projekcija.getSala().getBrojSale() + " - " + projekcija.getFilm().getImeFilma() + " " + projekcija.getPocetakProjekcije().toString().split("CEST")[0]);
        frame.pack();
        this.frame = frame;

    }

    protected abstract void setBrojRezervacijaLabel();
    protected abstract void setRezervisiButton();
    protected abstract void setIzadjiButton();
    protected abstract void setVipCheckbox();
    protected abstract void setBtnPlatno();
    public abstract JPanel getMainPanel();

}
