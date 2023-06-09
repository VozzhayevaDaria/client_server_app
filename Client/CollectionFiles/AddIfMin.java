package CollectionFiles;

import org.postgresql.util.PSQLException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
/**
 * The inheritor class of the {@link Command} that implements the 'add_if_min' command
 */
public class AddIfMin extends Command{
    @Override
    public ArrayList execute(CommandData data) throws SQLException {
        ArrayList messages = new ArrayList();
        String query;
        ResultSet rs;
        MusicBand element = data.getBand();
        query = "SELECT MIN(sales) FROM Albums";
        try {
            rs = CommandData.stm.executeQuery(query);
            rs.next();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        Integer minCount = Integer.valueOf(rs.getString(1));
        Integer myCount = (int) element.getBestAlbum().getSales();
        if (myCount <= minCount) {
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

            messages.add("The item has been added to the collection. To see what the collection looks like, write 'show'");
        } else {
            messages.add("The item is bigger then minimum. Element wasn't added to collection");
        }
        return messages;

    }
}
