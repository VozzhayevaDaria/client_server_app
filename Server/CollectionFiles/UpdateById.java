package CollectionFiles;


import org.postgresql.util.PSQLException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * The inheritor class of the {@link Command} that implements the 'update_by_id' command
 */
public class UpdateById extends Command{
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
              messages.add("Element was successfully updated");
          } else{
              messages.add("You don't have access to this element :(");
          }
      } else {
          messages.add("Element wasn't found");
      }
      return messages;
    }
  }
