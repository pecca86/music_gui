
public class SoundAddCommand implements Command {
    
    Album album;
    SoundClip sc;
    
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
