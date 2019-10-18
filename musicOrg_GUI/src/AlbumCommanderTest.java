/**
 * 
 */

/**
 * @author pekka
 * @version 18.10.2019
 * Test class for our AlbumCommander
 */
public class AlbumCommanderTest {

    /**
     * @param args not in use
     */
    public static void main(String[] args) {
        
        AlbumCommander commander = new AlbumCommander();
        Album album = new Album("Rock");
        Album sub = new Album("Pop");
        
        AlbumAddClass addCom = new AlbumAddClass(album, sub);
        commander.setCommand(addCom);
        commander.execute();
        System.out.println(album.getAlbums());
        
        AlbumRemoveClass rmAlb = new AlbumRemoveClass(album, sub);
        commander.setCommand(rmAlb);
        commander.execute();
        System.out.println(album.getAlbums());
    }
}
