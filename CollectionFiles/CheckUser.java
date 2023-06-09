package CollectionFiles;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
public class CheckUser extends Command {

    public ArrayList execute(CommandData data) throws SQLException {
        ArrayList messages = new ArrayList();
        String query = "SELECT password FROM USERS WHERE username='" + data.getUserName() + "'";
        ResultSet rs = CommandData.stm.executeQuery(query);
        if (rs.next()){ //user exists in DB
            String pas = rs.getString(1);
            if (pas.equals(data.getPassword())){
                messages.add("Welcome back, sweetie)");
            } else {
                messages.add("Password is wrong");
            }
        } else { //user wasn't found
            messages.add("Can't find anybody with this email :(");
        }
        return messages;
    }
}

