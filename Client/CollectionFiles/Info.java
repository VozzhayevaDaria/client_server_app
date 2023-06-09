package CollectionFiles;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * The inheritor class of the {@link Command} that implements the 'Info' command
 */
public class Info extends Command {
     @Override
    public ArrayList execute(CommandData data) throws SQLException {
         String query;
         ResultSet rs;
         ArrayList messages = new ArrayList();
         messages.add("Objects type: MusicBand");

         query = "SELECT COUNT(*) FROM MusicBands WHERE username='" + data.getUserName() + "'";
         rs = CommandData.stm.executeQuery(query);
         rs.next();
         String countYours = rs.getString(1);

         query = "SELECT COUNT(*) FROM MusicBands";
         rs = CommandData.stm.executeQuery(query);
         rs.next();
         String countAll = rs.getString(1);
         messages.add("Items count: " + countAll);
         messages.add("Your items count: " + countYours);
         return messages;
    }
}
