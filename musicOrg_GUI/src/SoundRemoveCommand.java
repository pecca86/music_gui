import java.util.ArrayList;
import java.util.List;

/**
 * @author pekka
 * @version 18.10.2019
 * Command for removing songs
 */
public class SoundRemoveCommand implements Command {
    
    Album album;
    List<SoundClip> scList = new ArrayList<SoundClip>();
    
    /**
     * @param album our album
     * @param scArray Array of selected sound clips
     */
    public SoundRemoveCommand(Album album, ArrayList<SoundClip> scArray) {
        this.album = album;
        this.scList = scArray;
    }

    
    @Override
    public void execute() {
        System.out.println("sound clip removed");
        
        // Iterate trough our albums all songs and if we find the selected songs we remove them
        for ( int i = 0; i < scList.size(); i++ ) {
            List<SoundClip> ourList = album.getSoundClips();
            
            for ( SoundClip song : ourList ) {
                if ( scList.get(i).equals(song) ) {
                    album.removeSong(song);
                }
            }
        }
    }
    
    
    @Override
    public void undo() {
        // TODO Auto-generated method stub
        
    }
}
