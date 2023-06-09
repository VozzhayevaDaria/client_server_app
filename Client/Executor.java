import CollectionFiles.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

public class Executor{
    HashMap<String, Command> commandNames = new HashMap<>();
    public Executor(){
        commandNames.put("help", new Help());
        commandNames.put("info", new Info());
        commandNames.put("show", new Show());
        commandNames.put("add", new Add());
        commandNames.put("update_by_id", new UpdateById());
        commandNames.put("remove_by_id", new RemoveById());
        commandNames.put("clear", new Clear());
        commandNames.put("add_if_min", new AddIfMin());
        commandNames.put("remove_greater", new RemoveGreater());
        commandNames.put("sort", new Sort());
        commandNames.put("filter_less_than_best_album", new FilterLessThanBestAlbum());
        commandNames.put("CheckUser", new CheckUser());
        commandNames.put("CreateUser", new CreateUser());
    }



    public ArrayList execute(CommandData data) throws IOException, SQLException {
        //File file = new File("D:\\j_ws\\Lab_6_server\\src\\main\\java\\BandsInput.yaml");
        File file = new File("Input.yaml");
        LinkedList<MusicBand> collection = new LinkedList<>();
        //LinkedList<MusicBand> collection = this.readFile(file);
        ArrayList messages = this.commandNames.get(data.getCommandName()).execute(data);
        this.writeFile(collection, file);
        return messages;
    }
    public LinkedList readFile(File file) throws IOException {
        LinkedList<MusicBand> collection = new LinkedList<>();
        ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());
        CollectionType listType = objectMapper.getTypeFactory().constructCollectionType(LinkedList.class, MusicBand.class);
        objectMapper.registerModule(new JavaTimeModule());
        collection = objectMapper.readValue(file, listType);
        return collection;
    }
    public void writeFile(LinkedList list, File file) throws IOException {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        mapper.registerModule(new JavaTimeModule());
        mapper.writeValue(file, list);
    }
}
