
/**
 * @author pekka
 * @version 14.10.2019
 * Handles the saving and restoring of our album's state
 */
public class AlbumCaretaker {
    
    Object objMemento;
    
    
    /**
     * @param album the album which state we are saving
     */
    public void saveState(Album album) {
        objMemento = album.save();
    }
    
    
    /**
     * @param album the album which state we are restoring
     */
    public void restoreState(Album album) {
        album.restore(objMemento);
    }
}
