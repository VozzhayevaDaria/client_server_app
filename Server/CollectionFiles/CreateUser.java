package CollectionFiles;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedList;
public class CreateUser extends Command {

    public ArrayList execute(CommandData data) throws SQLException {
        ArrayList messages = new ArrayList();
        String query = "SELECT password FROM USERS WHERE username='" + data.getUserName() + "'";
        ResultSet rs = CommandData.stm.executeQuery(query);
        if (rs.next()){ //user exists in DB
            messages.add("This email is already in use :(");
        } else { //user wasn't found
            query = "INSERT INTO Users VALUES('" + data.getUserName() + "','" + data.getPassword() + "')";
            Statement statement = CommandData.stm;
            try{
                statement.executeQuery(query);
            } catch (org.postgresql.util.PSQLException e){
                System.out.println("Created new user: " + data.getUserName());
            }
            messages.add("Nice to meet you! Type 'help' to view commands list");
        }
        return messages;
    }
}

