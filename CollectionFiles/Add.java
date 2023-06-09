package CollectionFiles;

import org.postgresql.util.PSQLException;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

/**
 * The inheritor class of the {@link Command} that implements the 'add' command
 */
public class Add extends Command {

    public ArrayList execute(CommandData data) throws SQLException {
        String query;
        MusicBand element = data.getBand();
        query = "INSERT INTO Albums VALUES(" + String.valueOf(element.getId()) + ",'" + element.getBestAlbum().getName()
                + "'," + String.valueOf(element.getBestAlbum().getSales()) + ")";
        try {
            CommandData.stm.executeQuery(query);
        } catch (PSQLException e) {
            System.out.println("New Album was added");
        }
        String[] date = element.getCreationDate().toString().split("-");
        query = "INSERT INTO MusicBands VALUES("
                + "'" + data.getUserName() + "',"
                + String.valueOf(element.getId()) + ","
                + "'" + element.getName() + "',"
                + String.valueOf(Math.round(element.getCoordinates().getX())) + ","
                + String.valueOf(Math.round(element.getCoordinates().getY())) + ","
                + "'" + date[0] + date[1] + date[2] + "',"
                + String.valueOf(element.getNumberOfParticipants()) + ","
                + String.valueOf(element.getSinglesCount()) + ","
                + String.valueOf(element.getAlbumsCount()) + ","
                + MusicGenre.getGenreId(element.getGenre()) + ","
                + String.valueOf(element.getId()) + ")";
        try {
            CommandData.stm.executeQuery(query);
        } catch (Exception e) {
            System.out.println("New Band was added");
        }
        ArrayList messages = new ArrayList();

        messages.add("The item has been added to the collection. To see what the collection looks like, write 'show'");
        return messages;

    }
}

