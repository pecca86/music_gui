
/**
 * @author pekka
 * @version 18.10.2019
 *
 */
public class AlbumAddClass implements Command {
    
    Album album;
    Album subAlbum;

    
    /**
     * @param album our parent album
     * @param subAlbum the subalbum we want to add to our parent album
     */
    public AlbumAddClass(Album album, Album subAlbum) {
        this.album = album;
        this.subAlbum = subAlbum;
    }

    
    @Override
    public void execute() {
        System.out.println("Album added");
        
        album.addSubAlbum(album, subAlbum);
    }

    
    @Override
    public void undo() {
        System.out.println("Undoing add...");
        
        album.removeSubAlbum(subAlbum);
    }

}
