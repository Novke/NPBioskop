package forms.sale;

import domen.Projekcija;

import javax.swing.*;
import java.awt.*;

public class Sala2 extends AbstractSala{
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
    private JPanel pnl5red;
    private JPanel pnl6;
    private JPanel pnl6red;
    private JButton btnPlatno;
    private JPanel pnl7;
    private JPanel pnl7red;
    private JButton btn11;
    private JButton btn12;
    private JButton btn13;
    private JButton btn21;
    private JButton btn22;
    private JButton btn23;
    private JButton btn24;
    private JButton btn31;
    private JButton btn32;
    private JButton btn33;
    private JButton btn34;
    private JButton btn35;
    private JButton btn41;
    private JButton btn42;
    private JButton btn43;
    private JButton btn44;
    private JButton btn45;
    private JButton btn46;
    private JButton btn51;
    private JButton btn52;
    private JButton btn53;
    private JButton btn54;
    private JButton btn55;
    private JButton btn56;
    private JButton btn61;
    private JButton btn62;
    private JButton btn63;
    private JButton btn64;
    private JButton btn65;
    private JButton btn66;
    private JButton btn71;
    private JButton btn72;
    private JButton btn73;
    private JButton btn74;
    private JButton btnRezervisi;
    private JButton btnIzadji;
    private JCheckBox cbVip;
    private JLabel lblBrojRez;

    public Sala2(Projekcija projekcija) {
        this.projekcija = projekcija;

        try {
            prepareView();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    void setSvaMesta() {
        svaMesta = new JButton[7][];


        svaMesta = new JButton[7][];
        svaMesta[0] = new JButton[]{btn11, btn12, btn13};
        svaMesta[1] = new JButton[]{btn21, btn22, btn23, btn24};
        svaMesta[2] = new JButton[]{btn31, btn32, btn33,btn34, btn35};
        svaMesta[3] = new JButton[]{btn41, btn42, btn43, btn44, btn45, btn46};
        svaMesta[4] = new JButton[]{btn51, btn52, btn53, btn54, btn55, btn56};
        svaMesta[5] = new JButton[]{btn61, btn62, btn63, btn64, btn65, btn66};
        svaMesta[6] = new JButton[]{btn71, btn72, btn73, btn74};

    }

    @Override
    protected void setBrojRezervacijaLabel() {
        absLblBrojRezervacija = lblBrojRez;
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
