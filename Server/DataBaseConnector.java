import CollectionFiles.CommandData;

import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import org.postgresql.Driver;

public class DataBaseConnector {
    private static final String URL = "jdbc:postgresql://pg/studs";
    private static final String USERNAME = "s367987";
    private static final String PASSWORD = "1I6o6L7H8qPAXV6X";

    public static void getConnection() throws SQLException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Connection connection;
        Class.forName("org.postgresql.Driver");
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            CommandData.stm = connection.createStatement();
            CommandData.stm1 = connection.createStatement();
        } catch (SQLException e) {
            System.out.println("Не удалось подключиться к базе данных");
            throw e;
        }
    }
}
