
/**
 * @author pekka
 * @version 18.10.2019
 *
 */
public interface Command {
    
    
    /**
     * invokes the execute method of the command we are calling
     */
    public void execute();
    
    
    /**
     * Undoes the last action made
     */
    public void undo();
    
}
