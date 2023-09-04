package rs.np.ac.bg.bioskop.common2.operacije;

import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * Klasa koja se koristi za slanje objekata preko mreže.
 */
public class Sender {
	/**
	 * Privatni atribut koji predstavlja Socket posiljaoca
	 */
    private Socket socket;

    /**
     * Konstruktor koji inicijalizuje Sender objekat sa datim socketom.
     *
     * @param socket socket preko kojeg će se vršiti slanje objekata
     */
    public Sender(Socket socket) {
        this.socket = socket;
    }

    /**
     * Metoda koja šalje objekat preko socket konekcije.
     *
     * @param object objekat koji se šalje
     * @throws Exception ukoliko dođe do greške prilikom slanja objekta
     */
    public void send(Object object) throws Exception {
        try {
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            out.writeObject(object);
            out.flush();
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new Exception("Error sending object!\n" + ex.getMessage());
        }
    }

    /**
     * Metoda koja zatvara socket konekciju.
     *
     * @throws Exception ukoliko dođe do greške prilikom zatvaranja socket konekcije
     */
    public void close() throws Exception {
        try {
            socket.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new Exception("Error closing socket connection!\n" + ex.getMessage());
        }
    }
}

