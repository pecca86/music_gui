
public class AlbumAddClass implements Command {
    
    Album album;
    Album subAlbum;
    
    public AlbumAddClass(Album album, Album subAlbum) {
        this.album = album;
        this.subAlbum = subAlbum;
    }

    @Override
    public void execute() {
        // TODO Auto-generated method stub
        album.addSubAlbum(album, subAlbum);
        
    }

    @Override
    public void undo() {
        // TODO Auto-generated method stub
        
    }

}
