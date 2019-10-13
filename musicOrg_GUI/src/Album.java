/**
 * 
 */

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author pekka
 * @version 17.9.2019
 *
 */
public class Album implements Iterable<SoundClip>{
    
    private Album parentAlbum;
    private Album childAlbum;
    private final List<Album> subAlbums = new ArrayList<Album>();
    private List<SoundClip> sounds = new ArrayList<SoundClip>();
    private String category;
    private String albumName;
    
    private List<SoundClip> soundsOld = new ArrayList<SoundClip>();
    private List<SoundClip> soundsNew = new ArrayList<SoundClip>();
    
    /**
     * Album constructor
     * @param albumName the name of the album
     */
    public Album(String albumName) {
        this.albumName = albumName;
    }
    
    
    
    
    /**
     * Adds a sub album into our existing album
     * @param parent the sub album's parent album
     * @param subAlbum sub album
     */
    public void addSubAlbum(Album parent, Album subAlbum) {
        subAlbum.setParent(parent);
        parent.setChild(subAlbum);
        subAlbums.add(subAlbum);
    }
    
    
    /**
     * @param parent set's the parent
     */
    public void setParent(Album parent) {
        this.parentAlbum = parent;
    }
    
    
    /**
     * Enables us to remove all the songs from the parents child albums
     * @param child child album to the parent
     */
    public void setChild(Album child) {
        this.childAlbum = child;
    }
    
    
    
    /**
     * @return parent albums
     */
    public Album getParent() {
        return parentAlbum;
    }
    
    
    /**
     * Removes a sub album from our existing album
     * @param subAlbum sub album
     */
    public void removeSubAlbum(Album subAlbum) {
        subAlbums.remove(subAlbum);
    }
    
    
    public void undoLastAction() {
    	sounds = soundsOld;
    }
    
    public void redoLastAction() {
    	sounds = soundsNew;
    }
    
    /**
     * @param sound Object we want to add to our sub album and all its parent albums
     */
    public void addSong(SoundClip sound) {
    	soundsOld = this.getSoundClips();
    	
        sounds.add(sound);
        
        if ( parentAlbum != null ) {
            parentAlbum.addSong(sound);
        }
        
        soundsNew = this.getSoundClips();
    }
    
    
    /**
     * @param sound Object we want to remove from our album and all its child albums
     */
    public void removeSong(SoundClip sound) {
    	soundsOld = this.getSoundClips();
    	
        sounds.remove(sound);

        for ( Album sub : subAlbums ) {
            sub.removeSong(sound);
        }
        
        soundsNew = this.getSoundClips();
    }
    
    
    /**
     * @return the size of our array
     */
    public int getSize() {
        return sounds.size();
    }
    
    
    /**
     * @param category sets album category
     */
    public void setCategory(String category) {
        this.category = category;
    }
    
    
    /**
     * @return the category label
     */
    public String getCategory() {
        return this.category;
    }
    
    
    /**
     * @param name set album name
     */
    public void setName(String name) {
        this.albumName = name;
    }
    
    
    /**
     * @return the name of the album
     */
    public String getName() {
        return this.albumName;
    }
    
    
    /**
     * Prints the album name
     */
    public void printAlbumName() {
        System.out.println(getName());
    }
    
    
    @Override
    public String toString() {
        return getName();
    }
    
    /**
     * Iterator for going trought all our songs
     * @return an iterator for our songs
     */
    @Override
    public Iterator<SoundClip> iterator() {
        return sounds.iterator();
    }
    
    
    
    /**
     * @return The albums soundclips in an arraylist
     */
    public ArrayList<SoundClip> getSoundClips() {
        ArrayList<SoundClip> found = new ArrayList<SoundClip>();
        for ( SoundClip sc : sounds ) {
            found.add(sc);
        }
        return found;
    }
    
    /**
     * @return The albums soundclips in an arraylist
     */
    public ArrayList<SoundClip> getSoundClipsOld() {
        ArrayList<SoundClip> found = new ArrayList<SoundClip>();
        for ( SoundClip sc : soundsOld ) {
            found.add(sc);
        }
        return found;
    }
    
    
    /**
     * @return all the sub albums from the given album
     */
    public ArrayList<Album> getAlbums() {
        ArrayList<Album> found = new ArrayList<Album>();
        for ( Album a : subAlbums ) {
            found.add(a);
        }
        return found;
    }
    
    
    /**
     * A main method so we can test out the funtions of the class
     * @param args not in use
     */
    public static void main(String[] args) {
        
    	Album a = new Album("Rock");
    	
    	File file = new File("gey.avi");
    	SoundClip sc = new SoundClip(file);
    	
    	
    	
    	
    	// TESTING:
    	a.addSong(sc);
    	System.out.println(a.getSoundClips().toString());
    	System.out.println("old: " + a.getSoundClips().toString());
    	
    	a.removeSong(sc);
    	System.out.println(a.getSoundClips().toString());
    	System.out.println("old: " + a.getSoundClips().toString());
    	
    	a.undoLastAction();
    	System.out.println(a.getSoundClips().toString());
    	System.out.println("old: " + a.getSoundClips().toString());
    	
    	a.redoLastAction();
    	System.out.println(a.getSoundClips().toString());
        
    }
}
