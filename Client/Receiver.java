import CollectionFiles.CommandData;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import CollectionFiles.CommandData;

public class Receiver {
    public CommandData getData(DatagramSocket soc, ExecutorService pool) throws IOException, ExecutionException, InterruptedException {
        Callable task = new Callable() {
            @Override
            public CommandData call() throws Exception {
                byte[] buffer = new byte[1024];
                DatagramPacket datagramPacket = new DatagramPacket(buffer, buffer.length);
                soc.receive(datagramPacket);
                InetAddress clientAddress = datagramPacket.getAddress();
                int clientPort = datagramPacket.getPort();
                String SerialisedData = new String(datagramPacket.getData(), 0, datagramPacket.getLength());
                CommandData deserialisedData = Receiver.deserialiseData(SerialisedData);
                deserialisedData.SetClientInform(clientAddress, clientPort);
                System.out.println("Got command: " + deserialisedData.CommandName);
                return deserialisedData;
            }
        };
        Future<CommandData> result = pool.submit(task);
        return result.get();
    }

    public static CommandData deserialiseData(String data) throws JsonProcessingException {
        ObjectMapper mapper = new YAMLMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.registerModule(new JavaTimeModule());
        CommandData deserialisedData = mapper.readValue(data, CommandData.class);
        return deserialisedData;
    }
}
