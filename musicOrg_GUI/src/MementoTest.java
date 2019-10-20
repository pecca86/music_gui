import java.io.File;

/**
 * 
 */

/**
 * @author pekka
 * @version 19.10.2019
 *
 */
public class MementoTest {

    /**
     * @param args not in use
     * @throws CloneNotSupportedException if object is not clonable
     */
    public static void main(String[] args) throws CloneNotSupportedException {
        
        
        
        Album album = new Album("rock");
        Album album2 = new Album("Pop");
        Album album3 = new Album("Heavy metal");
        Album album4 = new Album("Disco");
        
        File file = new File("torso");
        File file2 = new File("kiss - love gun");
        File file3 = new File("Metallica - one");
        SoundClip soundclip = new SoundClip(file);
        SoundClip soundclip2 = new SoundClip(file2);
        SoundClip soundclip3 = new SoundClip(file3);
        
        AlbumCaretaker caretaker = new AlbumCaretaker();

        // TESTING ALBUMS:
        album2.addSubAlbum(album2, album3);
        System.out.println(album2.getAlbums());
        caretaker.saveState(album2);
        
        album2.addSubAlbum(album2, album4);
        album2.addSubAlbum(album2, album);
        System.out.println(album2.getAlbums());
        
        caretaker.restoreState(album2);
        System.out.println(album2.getAlbums());
        
        // TESTING SOUNDCLIPS:
        album2.addSong(soundclip3);
        System.out.println(album2.getSoundClips());
        caretaker.saveState(album2);
        
        album2.addSong(soundclip2);
        album2.addSong(soundclip);
        System.out.println(album2.getSoundClips());
        
        caretaker.restoreState(album2);
        System.out.println(album2.getSoundClips());
        
        System.out.println("\n==============================\n");
        
        
        AlbumCommander commander = new AlbumCommander();
        AlbumAddClass addClass = new AlbumAddClass(album3, album4);
        AlbumRemoveClass rmClass = new AlbumRemoveClass(album3, album4);
        MementoCommand mc = new MementoCommand(album3);
        
        // adding album4 to album3
        commander.setCommand(addClass);
        commander.onButtonWasPushed();
        System.out.println(album3.getAlbums());
        System.out.println("\nsaving to memento...");
        commander.save(mc);
        System.out.println("");
        
        AlbumAddClass addClass2 = new AlbumAddClass(album3, album2);
        commander.setCommand(addClass2);
        commander.onButtonWasPushed();
        System.out.println(album3.getAlbums());
        
        AlbumAddClass addClass3 = new AlbumAddClass(album3, album);
        commander.setCommand(addClass3);
        commander.onButtonWasPushed();
        System.out.println(album3.getAlbums());
        
        commander.restore(mc);
        System.out.println("Final form: " + album3.getAlbums());
    }

}
