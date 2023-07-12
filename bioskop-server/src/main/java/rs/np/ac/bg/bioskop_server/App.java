package rs.np.ac.bg.bioskop_server;

import rs.np.ac.bg.bioskop_server.server.Server;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        Server server = new Server();
        server.startServer();
    }
}
