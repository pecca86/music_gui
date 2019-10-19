import java.io.File;
import java.util.List;

/**
 * Our album commander
 * @author pekka
 * @version 18.10.2019
 *
 */
public class AlbumCommander {
    
    
    List<Command> addCommands;
    List<Command> removeCommans;
    
    Command command;
    Command addCommand;
    Command removeCommand;
    Command undoCommand;
    
    /**
     * Our constructor
     */
    public AlbumCommander() {
        //
    }
    
    
    /**
     * @param c our command
     */
    public void setCommand(Command c) {
        command = c;
        //removeCommand = c;
    }
    

    /**
     * the method we call for when a button is pushed
     */
    public void onButtonWasPushed() {
        command.execute();
        undoCommand = command;
    }
    
    
    /**
     * The method we call for when the undo button is pushed
     * @param memento ff
     */
    public void undoButtonWasPushed(Command memento) {
        memento.undo();
    }
    
    
    /**
     * Test method for class
     * @param args not in use
     */
    public static void main(String[] args) {
        //
    }


    public void save(Command memento) {
        memento.execute();
    }


    public void restore(Command memento) {
        memento.undo();
    }
}
