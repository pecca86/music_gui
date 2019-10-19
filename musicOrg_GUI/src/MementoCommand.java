import java.util.ArrayList;
import java.util.List;

public class MementoCommand implements Command {
    
    private Memento storedState;
    
    private Album album;
    
    
    /**
     * @param album the album which state we want to store
     */
    public MementoCommand(Album album) {
        this.album = album;
    }
    
    
    private class Memento {
        
        // variables
        Album mementoAlbum;
        
        public Memento(Album album) {
            this.mementoAlbum = album;
            System.out.println("Albums stored: " + mementoAlbum.getAlbums());
        }
    }


    @Override
    public void undo() {
        Album restored = storedState.mementoAlbum;
        album = restored;
        System.out.println("albums restored: " + restored.getAlbums());
    }
    

    @Override
    public void execute() {
        Album store = album;
        storedState = new Memento(store);
        
        Album test = storedState.mementoAlbum;
        System.out.println("now storing: " + test);
    }
}
