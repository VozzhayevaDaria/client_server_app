package CollectionFiles;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * The inheritor class of the {@link Command} that implements the 'remo_by_id' command
 */
public class RemoveById extends Command{
    @Override
    public ArrayList execute(CommandData data) throws SQLException {
        ArrayList messages = new ArrayList<>();
        String query;
        ResultSet rs;
        String id = String.valueOf(data.getArgs()[0]);
        query = "SELECT username FROM MusicBands WHERE id=" + id;
        rs = CommandData.stm.executeQuery(query);
        if (rs.next()){
            String owner = rs.getString(1);
            if (owner.equals(data.getUserName())){
                query = "DELETE FROM Albums WHERE albumId=" + id;
                try {
                    rs = CommandData.stm.executeQuery(query);
                } catch (SQLException e) {}
                query = "DELETE FROM MusicBands WHERE id=" + id;
                try {
                    rs = CommandData.stm.executeQuery(query);
                } catch (SQLException e) {}
                messages.add("Element was successfully deleted");
            } else{
                messages.add("You don't have access to this element :(");
            }
        } else {
            messages.add("Element wasn't found");
        }
        return messages;
    }
}
