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
public class Album implements Iterable<SoundClip>, Cloneable{
    
    // COMMAND PATTERN START
    
    Command command;
    
    // COMMAND PATTERN END
    
    private Album parentAlbum;
    private Album childAlbum;



    private final List<Album> subAlbums = new ArrayList<Album>();
    private List<SoundClip> sounds = new ArrayList<SoundClip>();
    private String category;
    private String albumName;
    
    
    /**
     * Album constructor
     * @param albumName the name of the album
     */
    public Album(String albumName) {
        this.albumName = albumName;
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
     * @return child album
     */
    public Album getChild() {
        return childAlbum;
    }
    
    
    /**
     * Adds a sub album into our existing album
     * @param parent the sub album's parent album
     * @param subAlbum sub album
     */
    public void addSubAlbum(Album parent, Album subAlbum) {

        
        //caretaker.saveState(this);
        
        subAlbum.setParent(parent);
        parent.setChild(subAlbum);
        subAlbums.add(subAlbum);
        
    }
    
    
    /**
     * Removes a sub album from our existing album
     * @param subAlbum sub album
     */
    public void removeSubAlbum(Album subAlbum) {
        subAlbums.remove(subAlbum);
    }
    
    
    /**
     * @param sound Object we want to add to our sub album and all its parent albums
     */
    public void addSong(SoundClip sound) {
        sounds.add(sound);
        
        if ( parentAlbum != null ) {
            parentAlbum.addSong(sound);
        }
    }

    
    
    /**
     * @param sound Object we want to remove from our album and all its child albums
     */
    public void removeSong(SoundClip sound) {
        sounds.remove(sound);

        for ( Album sub : subAlbums ) {
            sub.removeSong(sound);
        }
    }
    
    
    /**
     * @return the size of our array
     */
    public int getSize() {
        return sounds.size();
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
    
    
    /**
     * Gives the name of the album
     */
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
    public List<SoundClip> getSoundClips() {
        ArrayList<SoundClip> found = new ArrayList<SoundClip>();
        for ( SoundClip sc : sounds ) {
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
     * @param mementoAlbums a new set of sub albums
     * Deletes all the old sub albums from the subAlbum array and replaces them with a new set of subAlbums
     */
    public void setAlbums(List<Album> mementoAlbums) {
        subAlbums.removeAll(subAlbums);
        for ( Album a : mementoAlbums ) {
            this.addSubAlbum(this, a);
        }
    }
    
    
    /**
     * Makes a clone out of our album
     */
    @Override
    public Album clone() throws CloneNotSupportedException {
        
        Album cloneAlbum = new Album("clone");
        Album myAlbum = this;
        
        ArrayList<Album> myList = new ArrayList<Album>();
        
        myList = myAlbum.getAlbums();
        
        for ( Album a : myList ) {
            cloneAlbum.addSubAlbum(cloneAlbum, a);
        }
        return cloneAlbum;
        
    }
    
    
    /**
     * A main method so we can test out the funtions of the class
     * @param args not in use
     * @throws CloneNotSupportedException ll
     */
    public static void main(String[] args) throws CloneNotSupportedException {
        //
    }
    
    
    
    /**
     * 
     * @author pekka
     * @version 19.10.2019
     * Memento object for storing our ablum state
     */
    private class Memento {
        
        List<Album> mementoSub = new ArrayList<Album>();
        List<SoundClip> mementoSounds = new ArrayList<SoundClip>();
        
        
        /**
         * Our memento constructor
         * @param subAlbums memento arraylist of sub albums
         * @param sounds memento array lsit of soundclips
         */
        public Memento(List<Album> subAlbums, List<SoundClip> sounds) {
            this.mementoSub = subAlbums;
            this.mementoSounds = sounds;
        }
    }
    
    
    /**
     * Save the state of our current object into a memento
     * @return Memento object where we have saved the current objects state
     */
    public Memento save() {
        
        // TODO: check if necessary..
        List<Album>saveAlb = new ArrayList<Album>();
        saveAlb = this.getAlbums();
        
        List<SoundClip>saveSound = new ArrayList<SoundClip>();
        saveSound = this.getSoundClips();
        
        // Puts the arrays we want to save into our memento object
        return new Memento(saveAlb, saveSound);
    }
    
    
    /**
     * Restores to our last state
     * @param objMemento a momento object where we have stored our resent state
     */
    public void restore(Object objMemento) {
        Memento memento = (Memento) objMemento;
    	Album a = new Album("Rock");
    	
    	File file = new File("gary.avi");
    	SoundClip sc = new SoundClip(file);
    }
}
