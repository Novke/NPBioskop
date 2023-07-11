package forms.sale;

import domen.Projekcija;
import domen.Sala;

import javax.swing.*;

public class Sala3 extends AbstractSala{
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
    private JPanel pnl7;
    private JPanel pnl7red;
    private JButton button11;
    private JButton button12;
    private JButton button13;
    private JButton button14;
    private JButton button15;
    private JButton button16;
    private JButton button17;
    private JButton btnPlatno;
    private JButton button21;
    private JButton button22;
    private JButton button23;
    private JButton button24;
    private JButton button25;
    private JButton button26;
    private JButton button27;
    private JButton button31;
    private JButton button32;
    private JButton button33;
    private JButton button34;
    private JButton button35;
    private JButton button36;
    private JButton button37;
    private JButton button41;
    private JButton button42;
    private JButton button43;
    private JButton button44;
    private JButton button45;
    private JButton button46;
    private JButton button47;
    private JButton button51;
    private JButton button52;
    private JButton button53;
    private JButton button54;
    private JButton button55;
    private JButton button56;
    private JButton button57;
    private JButton button61;
    private JButton button62;
    private JButton button63;
    private JButton button64;
    private JButton button65;
    private JButton button66;
    private JButton button67;
    private JButton button71;
    private JButton button72;
    private JButton button73;
    private JCheckBox cbVip;
    private JButton btnRezervisi;
    private JButton btnIzadji;
    private JLabel lblBrojRez;

    public Sala3(Projekcija projekcija) {
        this.projekcija = projekcija;

        try {
            prepareView();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        JFrame frame = new JFrame("Sala3");
        Projekcija p = new Projekcija();
        p.setSala(new Sala(3,20));
        frame.setContentPane(new Sala3(p).mainPnl);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    @Override
    void setSvaMesta() {
        svaMesta = new JButton[7][];
        svaMesta[0] = new JButton[]{button11, button12, button13, button14, button15, button16, button17};
        svaMesta[1] = new JButton[]{button21, button22, button23, button24, button25, button26, button27};
        svaMesta[2] = new JButton[]{button31, button32, button33, button34, button35, button36, button37};
        svaMesta[3] = new JButton[]{button41, button42, button43, button44, button45, button46, button47};
        svaMesta[4] = new JButton[]{button51, button52, button53, button54, button55, button56, button57};
        svaMesta[5] = new JButton[]{button61, button62, button63, button64, button65, button66, button67};
        svaMesta[6] = new JButton[]{button71, button72, button73};

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
