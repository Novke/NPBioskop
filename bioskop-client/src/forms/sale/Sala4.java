package forms.sale;

import domen.Projekcija;

import javax.swing.*;

public class Sala4 extends AbstractSala{
    private JPanel pnlMain;
    private JPanel pnlPlatno;
    private JPanel pnl1red;
    private JPanel pnl2;
    private JPanel pnl2red;
    private JPanel pnl3;
    private JPanel pnl3red;
    private JPanel pnl4;
    private JPanel pnl4red;
    private JPanel pnl5;
    private JPanel pnl5red;
    private JButton button11;
    private JButton button12;
    private JButton button21;
    private JButton button22;
    private JButton button31;
    private JButton button32;
    private JButton button33;
    private JButton button34;
    private JButton button41;
    private JButton button42;
    private JButton button43;
    private JButton button44;
    private JButton button51;
    private JButton button52;
    private JButton button53;
    private JButton button54;
    private JLabel lblBrojRez;
    private JCheckBox cbVip;
    private JButton btnRezervisi;
    private JButton btnIzadji;
    private JButton btnPlatno;

    @Override
    void setSvaMesta() {
        svaMesta = new JButton[5][];
        svaMesta[0] = new JButton[]{button11, button12};
        svaMesta[1] = new JButton[]{button21, button22};
        svaMesta[2] = new JButton[]{button31, button32, button33, button34};
        svaMesta[3] = new JButton[]{button41, button42, button43, button44};
        svaMesta[4] = new JButton[]{button51, button52, button53, button54};
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
        return pnlMain;
    }

    public Sala4(Projekcija projekcija) {
        this.projekcija = projekcija;

        try {
            prepareView();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
