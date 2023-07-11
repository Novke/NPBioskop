package form;

import server.Server;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class Console {

    private Server server;
    private Thread serverThread;
    private Properties properties = new Properties();
    private JPanel panel1;
    private JButton btnStart;
    private JButton btnStop;
    private JLabel lblUrl;
    private JLabel lblUser;
    private JLabel lblPass;
    private JLabel lblStatus;

    public Console() {
        btnStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buttonStart();
            }
        });

        btnStop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buttonStop();
            }
        });


        lblUrl.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    otvoriDijalog("url");
                }
            }
        });

        lblUser.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2){
                    otvoriDijalog("username");
                }
            }
        });

        lblPass.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2){
                    otvoriDijalog("password");
                }
            }
        });
    }

    private void otvoriDijalog(String property) {
        if (server != null) {
            JOptionPane.showMessageDialog(panel1, "Zaustaviti server prvo");
            return;
        }

        String currentValue = properties.getProperty(property);
        String newValue = JOptionPane.showInputDialog(panel1, "Enter new value for " + property, currentValue);

        if (newValue != null) {
            properties.setProperty(property, newValue);
            savePropertiesToFile();
            prepareView();
        }
    }

    private void savePropertiesToFile() {
        try {
            FileOutputStream outputStream = new FileOutputStream("config/dbconfig.properties");
            properties.store(outputStream, null);
            outputStream.close();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            throw new RuntimeException(e);
        }
    }

    private void buttonStart() {
        server = new Server();
        serverThread = new Thread(new Runnable() {
            @Override
            public void run() {
                server.startServer();
            }
        });
        serverThread.start();

        btnStart.setEnabled(false);
        btnStop.setEnabled(true);
        lblStatus.setText("Status: Running");
    }

    private void buttonStop(){
        if (server != null) {

//            try {
//                serverThread.join();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }

            try {
                server.stopServer();
            } catch (IOException e) {
                e.printStackTrace();
            }

            server = null;
            serverThread = null;
        }

        btnStart.setEnabled(true);
        btnStop.setEnabled(false);
        lblStatus.setText("Status: Stopped");
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Console");
        Console console = new Console();
        frame.setContentPane(console.panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        console.prepareView();

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    void prepareView() {
        try {
            properties.load(new FileInputStream("config/dbconfig.properties"));
            setLabels(properties);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            throw new RuntimeException(e);
        }


    }

    private void setLabels(Properties properties) {
        String url = properties.getProperty("url");
        String username = properties.getProperty("username");
        String password = properties.getProperty("password");

        lblUrl.setText("URL = " + url);
        lblUser.setText("USERNAME = " + username);
        lblPass.setText("PASSWORD = " + password);
    }

    public Properties getProperties() {
        return properties;
    }
}
