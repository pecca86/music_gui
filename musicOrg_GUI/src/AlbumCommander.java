import java.io.File;
import java.util.List;

/**
 * Our album commander
 * @author pekka
 * @version 18.10.2019
 *
 */
public class AlbumCommander {
    
    Command command;
    Command undoCommand;
    List<Command> addCommands;
    List<Command> removeCommans;
    Command addCommand;
    Command removeCommand;
    
    /**
     * Our constructor
     */
    public AlbumCommander() {
        //
    }
    

    /**
     * the method we call for when a button is pushed
     */
    public void onAddButtonWasPushed() {
        addCommand.execute();
        undoCommand = addCommand;
    }
    
    
    /**
     * When the remove button is pushed
     */
    public void removeButtonWasPushed() {
        removeCommand.execute();
        undoCommand = removeCommand;
    }

    
    /**
     * The method we call for when the undo button is pushed
     */
    public void undoButtonWasPushed() {
        undoCommand.undo();
    }
    
    
    /**
     * @param c set the Class of which method we want to execute
     */
    public void setCommand(Command c) {
        addCommand = c;
        removeCommand = c;
        undoCommand = c;
    }
    
    
    /**
     * Test method for class
     * @param args not in use
     */
    public static void main(String[] args) {
        //
    }

}
