package operacije;

import java.io.ObjectInputStream;
import java.net.Socket;

/**
 * Klasa koja se koristi za primanje objekata preko mreže.
 */
public class Receiver {
	
	/**
	 * Privatni atribut instanca tipa socket
	 */
    private Socket socket;

    /**
     * Konstruktor koji inicijalizuje Receiver objekat sa zadatim socketom.
     *
     * @param socket socket preko kojeg se vrši komunikacija
     */
    public Receiver(Socket socket) {
        this.socket = socket;
    }

    /**
     * Metoda koja vrši primanje objekta preko mreže.
     *
     * @return primljeni objekat
     * @throws Exception ukoliko dođe do greške prilikom primanja objekta
     */
    public Object receive() throws Exception {
        try {
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
            return in.readObject();
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new Exception("Error receiving object!\n" + ex.getMessage());
        }
    }
}

