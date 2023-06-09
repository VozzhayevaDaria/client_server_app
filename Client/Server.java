import CollectionFiles.CommandData;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.DatagramSocket;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.sql.*;

public class Server {
    public static void main(String[] args) throws IOException, InterruptedException, ExecutionException, SQLException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        final int SERVERPORT = 1256;
        int THREADSCOUNT = 5;
        DatagramSocket soc = ClientConnector.startWork(SERVERPORT);
        if (soc != null) {
            Receiver receiver = new Receiver();
            Executor executor = new Executor();
            Sender sender = new Sender();
            DataBaseConnector.getConnection();
            ExecutorService pool = Executors.newFixedThreadPool(THREADSCOUNT);
            System.out.println("Server started work!");
            while (true) {
                CommandData data = receiver.getData(soc, pool);
                ArrayList messagesForClient = executor.execute(data);
                sender.send(data, messagesForClient, soc);
            }
        } else {
            System.out.println("Server hasn't started work :(");
        }
    }
}
