package forms;

import communication.Communication;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.net.ConnectException;
import java.util.HashMap;

import static com.sun.java.accessibility.util.AWTEventMonitor.addKeyListener;

public class Login {

    static JFrame frame;
    private JButton btnLogin;
    private JPanel pnlMain;
    private JPanel pnlButtonBar;
    private JLabel lblTitle;
    private JPanel pnlInner;
    private JPanel pnlUser;
    private JPanel pnlPass;
    private JTextField txtUser;
    private JLabel lblPass;
    private JLabel lblUser;
    private JPasswordField txtPass;

    public static void main(String[] args) {
        frame = new JFrame("Login");
        frame.setContentPane(new Login().pnlMain);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setSize(500,180);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
    }

    public Login() {
    btnLogin.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            loginButton();
        }

    });

        txtPass.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    // Handle the Enter key event here
                    System.out.println("Enter key pressed");
                    loginButton();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });




    }

    private void loginButton() {
        String user = txtUser.getText();
        String pass = txtPass.getText();

        if (user.isBlank() || pass.isEmpty()){
        JOptionPane.showMessageDialog(frame, "UNESI USER I PASS");
            return;
        }

        HashMap<Integer, String> map = new HashMap<Integer, String>();
        map.put(1, user);
        map.put(2, pass);

        try {
            boolean successfulLogin = Communication.getInstance().login(map);
            if (successfulLogin){
                ControlView cv = new ControlView();
                ControlView.show(cv);

                frame.dispose();
            } else {

                JOptionPane.showMessageDialog(frame, "LOS USER / PASS");
            }

        } catch (ConnectException ce){
            JOptionPane.showMessageDialog(frame, "SERVER DOWN");
        }
        catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}
