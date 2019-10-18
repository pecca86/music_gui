import java.util.ArrayList;
import java.util.List;

public class SoundRemoveCommand implements Command {
    
    Album album;
    //SoundClip sc;
    List<SoundClip> scList = new ArrayList<SoundClip>();
    
    /**
     * @param album our album
     * @param scArray Array of sound clips
     */
    public SoundRemoveCommand(Album album, ArrayList<SoundClip> scArray) {
        this.album = album;
        //this.sc = sc;
        this.scList = scArray;
    }

    
    @Override
    public void execute() {
        System.out.println("sound clip removed");
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
