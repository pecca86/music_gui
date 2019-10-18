
public class AlbumRemoveClass implements Command {
    
    Album album;
    Album subAlbum;
    
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
