package CollectionFiles;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * abstract class ancestor for all classes of commands
 */
public abstract class Command {
    public abstract ArrayList execute(CommandData data) throws SQLException;
}
