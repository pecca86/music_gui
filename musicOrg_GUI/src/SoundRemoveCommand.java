
public class SoundRemoveCommand implements Command {
    
    Album album;
    SoundClip sc;
    
    /**
     * @param album our album
     * @param sc the song we want to remove from the album
     */
    public SoundRemoveCommand(Album album, SoundClip sc) {
        this.album = album;
        this.sc = sc;
    }

    
    @Override
    public void execute() {
        System.out.println("sound clip removed");
        album.removeSong(sc);
    }
    
    
    @Override
    public void undo() {
        // TODO Auto-generated method stub
        
    }

}
