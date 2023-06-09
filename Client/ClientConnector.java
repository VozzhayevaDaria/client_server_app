import java.net.DatagramSocket;
import java.net.SocketException;

public class ClientConnector {
    static DatagramSocket startWork(int port) {
        DatagramSocket soc;
        try {
            soc = new DatagramSocket(port);
        } catch (SocketException e) {
            throw new RuntimeException(e);
        }
        return soc;
    }
}
