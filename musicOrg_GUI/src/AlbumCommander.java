import java.io.File;

/**
 * Our album commander
 * @author pekka
 * @version 18.10.2019
 *
 */
public class AlbumCommander implements Command {
    
    Command slot;
    
    /**
     * Our constructor
     */
    public AlbumCommander() {};
    

    @Override
    public void execute() {
        slot.execute();
    }

    @Override
    public void undo() {
        // TODO Auto-generated method stub
        
    }
    
    
    /**
     * @param command set the Class of which method we want to execute
     */
    public void setCommand(Command command) {
        slot = command;
    }
    
    
    /**
     * Test method for class
     * @param args not in use
     */
    public static void main(String[] args) {
        
        Album album = new Album("gg");
        Album sub = new Album("DD");
        
        File f = new File("first sound clip");
        File ff = new File("second sound clip");
        
        SoundClip sc = new SoundClip(f);
        SoundClip sc2 = new SoundClip(ff);
        

        // ADDING ALBUM:
        
        AlbumAddClass albumAdder = new AlbumAddClass(album, sub);
        album.setCommand(albumAdder);
        album.execute();
        System.out.println(album.getAlbums());
        
        
        // REMOVING ALBUM:
        
        AlbumRemoveClass rm = new AlbumRemoveClass(album, sub);
        album.setCommand(rm);
        album.execute();
        System.out.println(album.getAlbums());
        
        // ADDING AGAIN
        
        album.setCommand(albumAdder);
        album.execute();
        System.out.println(album.getAlbums());
        
        // SONG ADDED:
        
        SoundAddCommand sAdd = new SoundAddCommand(album, sc);
        album.setCommand(sAdd);
        album.execute();
        System.out.println(album.getSoundClips()); // should print
        
        // SONG REMOVED:
        /*
        SoundRemoveCommand sRm = new SoundRemoveCommand(album, sc);
        album.setCommand(sRm);
        album.execute();
        System.out.println(album.getSoundClips());
        */
    }

}
