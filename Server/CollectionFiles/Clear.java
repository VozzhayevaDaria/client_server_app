package CollectionFiles;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
/**
 * The inheritor class of the {@link Command} that implements the 'Clear' command
 */
public class Clear extends Command{
    @Override
    public ArrayList execute(CommandData data) throws SQLException {
        ArrayList messages = new ArrayList<>();
        String query;
        ResultSet rs;
        query = "DELETE FROM Albums WHERE albumId IN (SELECT albumId FROM MusicBands WHERE username='" + data.getUserName() + "')";
        try {
            CommandData.stm.executeQuery(query);
        } catch (SQLException e) {
            System.out.println("Albums of user " + data.getUserName() + " has been cleared");
        }
        query = "DELETE FROM MusicBands WHERE username='" + data.getUserName() + "'";
        try {
            CommandData.stm.executeQuery(query);
        } catch (SQLException e) {
            System.out.println("MusicBands of user " + data.getUserName() + " has been cleared");
        }
        messages.add("Your items has been deleted from collection");
        return messages;
    }
}
