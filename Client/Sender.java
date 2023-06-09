import CollectionFiles.CommandData;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class Sender {
    public void send(CommandData data, ArrayList messages, DatagramSocket soc) throws IOException {
        ObjectMapper mapper = new YAMLMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        String serialisedMessages = mapper.writeValueAsString(messages);
        mapper.registerModule(new JavaTimeModule());
        ArrayList<String> packetsList = Sender.MakePocketsList(serialisedMessages);
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        RecursieveSender sender = new RecursieveSender(packetsList, data.getClientAdress(), data.getClientPort(), soc);
        forkJoinPool.invoke(sender);
    }

    public class RecursieveSender extends RecursiveTask{
        ArrayList<String> list;
        InetAddress adress;
        int port;
        DatagramSocket soc;

        public RecursieveSender(ArrayList list, InetAddress adress, int port, DatagramSocket soc){
            this.list = list;
            this.adress = adress;
            this.port = port;
            this.soc = soc;
        }

        @Override
        protected RecursieveSender compute() {
            if (list.size() == 0){
                return null;
            }
            DatagramPacket datagramPacket = new DatagramPacket(list.get(0).getBytes(), list.get(0).getBytes().length, adress, port);
            try {
                soc.send(datagramPacket);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            list.remove(0);
            RecursieveSender newSender = new RecursieveSender(list, adress, port, soc);
            newSender.fork();
            return (RecursieveSender) newSender.join();
        }
    }


    public static ArrayList MakePocketsList(String str){
        ArrayList list = new ArrayList<>();
        int end_index = 0;
        int start_index = 0;
        Integer left_num = 0;
        Integer right_num = 1;
        while(end_index <= str.length()){
            int current_len = 100 - (left_num.toString().length() + right_num.toString().length()) - 2;
            end_index = start_index + current_len + 1;
            if (end_index > str.length()){
                list.add(left_num.toString() + ":" + str.substring(start_index, str.length()) + ":0");
            } else {
                list.add(left_num.toString() + ":" + str.substring(start_index, end_index) + ":" + right_num.toString());
            }
            start_index = end_index;
            left_num++;
            right_num++;
        }
        return list;
    }
}

