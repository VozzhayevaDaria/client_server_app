package CollectionFiles;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * The inheritor class of the {@link Command} that implements the 'FilterLessThanBestAlbum' command
 */
public class FilterLessThanBestAlbum extends Command{
    @Override
    public ArrayList execute(CommandData data) throws SQLException {
        ArrayList messages = new ArrayList();
        messages.add("Bands whose best album sales are less than " + data.getArgs()[0].toString() + ":");
        String username;
        String id;
        String name;
        String x;
        String y;
        String date;
        String numberOfParticipants;
        String singlesCount;
        String albumsCount;
        String genre;
        String albumName;
        String albumSales;
        String query;
        ResultSet rsBands;
        ResultSet rsAlbum;
        query = "SELECT * FROM MusicBands";
        rsBands = CommandData.stm.executeQuery(query);
        query = "SELECT * FROM Albums";
        rsAlbum = CommandData.stm1.executeQuery(query);

        while (rsBands.next() && rsAlbum.next()){
            username = rsBands.getString(1);
            id = rsBands.getString(2);
            name = rsBands.getString(3);
            x = rsBands.getString(4);
            y = rsBands.getString(5);
            date = rsBands.getString(6);
            numberOfParticipants = rsBands.getString(7);
            singlesCount = rsBands.getString(8);
            albumsCount = rsBands.getString(9);
            genre = MusicGenre.getGenreName(rsBands.getString(10));
            albumName = rsAlbum.getString(2);
            albumSales = rsAlbum.getString(3);

            messages.add("------>" + name + "<------"  + "\n"
                    + "id: " + id  + "\n"
                    + "coordinates: (" + x + ", " + y + ")\n"
                    + "creation date: " + date  + "\n"
                    + "number of participants: " + numberOfParticipants + "\n"
                    + "singles count: " + singlesCount + "\n"
                    + "albums count: " + albumsCount + "\n"
                    + "genre: " + genre  + "\n"
                    + "best album: " + albumName  + "\n"
                    + "best album sales: " + albumSales);

        }
        return messages;
    }
}
