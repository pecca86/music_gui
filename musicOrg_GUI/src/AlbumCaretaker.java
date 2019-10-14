
public class AlbumCaretaker {
    
    Object objMemento;
    
    
    public void saveState(Album album) {
        objMemento = album.save();
    }
    
    public void restoreState(Album album) {
        album.restore(objMemento);
    }
}
