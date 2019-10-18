
/**
 * @author pekka
 * @version 18.10.2019
 *
 */
public class AlbumRemoveClass implements Command {
    
    Album album;
    Album subAlbum;
    
    /**
     * @param album the parent album
     * @param subAlbum the album we want to remove from our partent album
     */
    public AlbumRemoveClass(Album album, Album subAlbum) {
        this.album = album;
        this.subAlbum = subAlbum;
    }

    @Override
    public void execute() {
        System.out.println("Album removed");
        album.removeSubAlbum(subAlbum);
    }

    @Override
    public void undo() {
        // TODO Auto-generated method stub
        
    }

}
