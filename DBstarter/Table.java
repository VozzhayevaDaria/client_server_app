import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Table {
    private String name;
    private ArrayList fields;
    private String someAddition;

    public Table(String name){
        this.name = name;
        fields = new ArrayList();
    }
    public void addField(String field){
        fields.add(field);
    }
    public void addSomeAddition(String addition){
        this.someAddition = addition;
    }
    public void createTable(Statement stm) throws SQLException {
        String concatenetedFields = String.join(",", fields);
        String query;
        if (someAddition == null){
            query = "CREATE TABLE " + name + "(" + concatenetedFields + ")";
        } else {
            query = "CREATE TABLE " + name + "(" + concatenetedFields + "," + someAddition + ")";
        }
        stm.executeQuery(query);
        System.out.println("Table " + name + " was successfully created");
    }

    public void insert(Statement stm, String value) throws SQLException {
        String query = "INSERT INTO " + name + " VALUES (" + value + ")";
        stm.executeQuery(query);
    }
}
