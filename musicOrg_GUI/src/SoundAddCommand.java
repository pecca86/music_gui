
/**
 * @author pekka
 * @version 18.10.2019
 *
 */
public class SoundAddCommand implements Command {
    
    Album album;
    SoundClip sc;
    
    /**
     * @param album the parent album
     * @param sc the song which we want to add to the album
     */
    public SoundAddCommand(Album album, SoundClip sc) {
        this.album = album;
        this.sc = sc;
    }

    
    @Override
    public void execute() {
        System.out.println("sound clip added");
        album.addSong(sc);
    }
    
    
    @Override
    public void undo() {
        // TODO Auto-generated method stub
        
    }
}
