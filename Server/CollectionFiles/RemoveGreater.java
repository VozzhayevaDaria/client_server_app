package CollectionFiles;


import javax.xml.transform.Result;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
/**
 * The inheritor class of the {@link Command} that implements the 'remove_greater' command
 */
public class RemoveGreater extends Command{
    @Override
    public ArrayList execute(CommandData data) {
        ArrayList messages = new ArrayList();
        String query;
        ResultSet rs;
        String numOfParticipants = String.valueOf(data.getBand().getNumberOfParticipants());
        query = "DELETE FROM Albums WHERE albumId IN (SELECT id FROM MusicBands WHERE " +
                "numberOfParticipants>" + numOfParticipants + " AND username='" + data.getUserName() + "')";
        try {
            rs = CommandData.stm.executeQuery(query);
        } catch (SQLException e) {}
        query = "DELETE FROM MusicBands WHERE numberOfParticipants>" + numOfParticipants + " AND username='" + data.getUserName() + "'";
        try {
            rs = CommandData.stm.executeQuery(query);
        } catch (SQLException e) {}
        messages.add("Elements was successfully deleted");
        return messages;
    }
}
