package CollectionFiles;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * The inheritor class of the {@link Command} that implements the 'Help' command
 */
public class Help extends Command {
    @Override
    public ArrayList execute(CommandData data) {
        ArrayList messages = new ArrayList();
        messages.add("help : output Help for available commands");
        messages.add("info : output to the standard output stream information about the collection (type, initialization date, number of elements, etc.)");
        messages.add("show : output to the standard output stream all elements of the collection in a string representation");
        messages.add("add : add a new element to the collection");
        messages.add("update_by_id *id* : update the value of the element a collection whose id is equal to the specified");
        messages.add("remove_by_id *id* : remove an item from the collection by its id");
        messages.add("clear : Clear the collection");
        messages.add("add_if_min : add a new element to the collection if its value is less than that of the smallest element of this collection");
        messages.add("remove_greater : remove from the collection all elements exceeding the specified");
        messages.add("sort : sort the collection in natural order");
        messages.add("filter_less_than_best_album *digit* : print musicbands, whose best album sales are less than digit");
        return messages;
    }
}
