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
     */
    public static void main(String[] args) {
        
        
        
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
        
    }

}
