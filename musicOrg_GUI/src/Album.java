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
public class Album implements Iterable<SoundClip>, Command {
    
    // COMMAND PATTERN START
    
    Command slot;
    
    // COMMAND PATTERN END
    
    private Album parentAlbum;
    private Album childAlbum;
    private List<Album> subAlbums = new ArrayList<Album>();
    private List<SoundClip> sounds = new ArrayList<SoundClip>();
    private String albumName;

    //TODO undo method:
    AlbumCaretaker caretaker = new AlbumCaretaker();
    
    /**
     * Our memento class for storing old states
     * @author pekka
     * @version 12.10.2019
     *
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
     * Tries to undo the last action taken
     */
    public void undoAction() {
        caretaker.restoreState(this);
    }
    
    
    /**
     * Save the state of our current object into a memento
     * @return Memento object where we have saved the current objects state
     */
    public Memento save() {
        
        List<Album>saveAlb = new ArrayList<Album>();
        saveAlb = this.getAlbums();
        
        List<SoundClip>saveSound = new ArrayList<SoundClip>();
        saveSound = this.getSoundClips();
        
        
        return new Memento(saveAlb, saveSound);
    }
    
    
    /**
     * Restores to our last state
     * @param objMemento a momento object where we have stored our resent state
     */
    public void restore(Object objMemento) {
        Memento memento = (Memento) objMemento;
        
        
        subAlbums = memento.mementoSub;
        sounds = memento.mementoSounds;
    }
    
    // COMMAND PATTERN METHODS:
    
    
    @Override
    public void execute() {
        slot.execute();
    }


    @Override
    public void undo() {
        // TODO Auto-generated method stub
        
    }
    
    public void setCommand(Command command) {
        slot = command;
    }
    

    // END OF COMMAND PATTERN METHODS:
    
    
    
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
        caretaker.saveState(this);
        
        subAlbums.remove(subAlbum);
    }
    
    /**
     * @param sound Object we want to add to our sub album and all its parent albums
     */
    public void addSong(SoundClip sound) {
        caretaker.saveState(this);
        
        sounds.add(sound);
        
        if ( parentAlbum != null ) {
            parentAlbum.addSong(sound);
        }
    }

    
    
    /**
     * @param sound Object we want to remove from our album and all its child albums
     */
    public void removeSong(SoundClip sound) {
        caretaker.saveState(this);
        
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
    public ArrayList<SoundClip> getSoundClips() {
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
     * A main method so we can test out the funtions of the class
     * @param args not in use
     */
    public static void main(String[] args) {
        
        //
        
    }

}
