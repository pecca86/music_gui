import java.util.ArrayList;
import java.util.List;

public class MementoCommand implements Command {
    
    private Memento storedState;
    
    private Album myAlbum;
    
    
    /**
     * @param album the album which state we want to store
     * @throws CloneNotSupportedException If we fail to make a clone out of the Album
     */
    public MementoCommand(Album album) throws CloneNotSupportedException {
        myAlbum = album;
    }
    
    
    private class Memento {
        
        // variables
        Album mementoAlbum;
        
        /**
         * 
         * @param album the album which state we want to store
         */
        public Memento(Album album) {
            try {
                Album memAlb = album.clone();
                this.mementoAlbum = memAlb;
            } catch (CloneNotSupportedException e) {
                System.out.println("Clone not supported! " + e);
            }
        }
    }

    @Override
    public void execute() {
        storedState = new Memento(myAlbum);
        
        Album test = storedState.mementoAlbum;
        System.out.println("now storing album '" + test + "' with sub albums: "  + test.getAlbums());
    }

    @Override
    public void undo() {
        
        Album cloneAlbum = storedState.mementoAlbum;
        
        //TODO: Smarter solution
        // Copies albums from our cloned Album array to the album
        List<Album> mementoAlbums = new ArrayList<Album>();
        mementoAlbums = cloneAlbum.getAlbums();
        
        myAlbum.setAlbums(mementoAlbums);
        
        System.out.println("Memento albums restored: " + myAlbum.getAlbums());
    }
}
