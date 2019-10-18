
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
     * 
     */
    public void undo();
}
