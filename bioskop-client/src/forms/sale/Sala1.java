package forms.sale;

import domen.Film;
import domen.Korisnik;
import domen.Projekcija;
import domen.Sala;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Sala1 extends AbstractSala{
    private JPanel mainPnl;
    private JPanel pnlPlatno;
    private JPanel pnl1;
    private JPanel pnl1red;
    private JPanel pnl2;
    private JPanel pnl2red;
    private JPanel pnl3;
    private JPanel pnl3red;
    private JPanel pnl4;
    private JPanel pnl4red;
    private JPanel pnl5;
    private JButton button11;
    private JButton button12;
    private JButton button13;
    private JButton button14;
    private JButton button15;
    private JButton button16;
    private JButton button21;
    private JButton button22;
    private JButton button23;
    private JButton button24;
    private JButton button25;
    private JButton button31;
    private JButton button32;
    private JButton button33;
    private JButton button34;
    private JButton button35;
    private JButton button36;
    private JButton button41;
    private JButton button42;
    private JButton button43;
    private JButton button44;
    private JButton button45;
    private JPanel pnl5red;
    private JButton button51;
    private JButton button52;
    private JButton button53;
    private JButton button54;
    private JButton button55;
    private JButton button56;
    private JButton btnPlatno;
    private JPanel pnlDno;
    private JLabel lblSuma;
    private JPanel pnl6;
    private JButton btnRezervisi;
    private JButton btnIzadji;
    private JCheckBox cbVip;

    public Sala1(Projekcija projekcija) {
        this.projekcija = projekcija;

        try {
            prepareView();
        } catch (Exception e) {
            e.printStackTrace();

        }

//        lblSuma.addMouseListener(new MouseAdapter() {
//            @Override
//            public void mouseClicked(MouseEvent e) {
//                test();
//                e.consume();
//            }
//
//        });

    }

    private void test() {

    }

    public static void main(String[] args) {
        Projekcija p = new Projekcija();
        p.setId(7);
        p.setSala(new Sala(1, 44));
        Film film = new Film();
        film.setImeFilma("Ime filma");
        p.setFilm(film);
        JFrame frame = new JFrame("Sala 1 - MAIN");
        AbstractSala sala = new Sala1(p);
        sala.setFrame(frame);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    @Override
    void setSvaMesta() {
        svaMesta = new JButton[5][];
        svaMesta[0] = new JButton[]{button11, button12, button13, button14, button15, button16};
        svaMesta[1] = new JButton[]{button21, button22, button23, button24, button25};
        svaMesta[2] = new JButton[]{button31, button32, button33, button34, button35, button36};
        svaMesta[3] = new JButton[]{button41, button42, button43, button44, button45};
        svaMesta[4] = new JButton[]{button51, button52, button53, button54, button55, button56};


    }

    @Override
    protected void setBrojRezervacijaLabel() {
        absLblBrojRezervacija = lblSuma;
    }

    @Override
    protected void setRezervisiButton() {
        absBtnRezervisi = btnRezervisi;
    }

    @Override
    protected void setIzadjiButton() {
        absBtnIzadji = btnIzadji;
    }

    @Override
    protected void setVipCheckbox() {
        absCbVip = cbVip;
    }

    @Override
    protected void setBtnPlatno() {absBtnPlatno = btnPlatno;
    }

    @Override
    public JPanel getMainPanel() {
        return mainPnl;
    }


}
