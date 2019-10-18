import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 * @version 30.9.2019
 *
 */
public class MusicOrganizerController {

	private MusicOrganizerWindow view;
	private SoundClipBlockingQueue queue;
	private Album root;
	private Album album;
	private List<SoundClip> soundclips;
	
	private AlbumCommander albumCommander;
	/**
	 * Controller for the MusicOrganizer view
	 */
	public MusicOrganizerController() {
		
		// TODO: Create the root album for all sound clips
		root = new Album("All Sound Clips");
		
		// Create the View in Model-View-Controller
		view = new MusicOrganizerWindow(this);
		
		// Create the blocking queue
		queue = new SoundClipBlockingQueue();
		
		// Create a separate thread for the sound clip player and start it
		(new Thread(new SoundClipPlayer(queue))).start();
	}

	/**
	 * Load the sound clips found in all subfolders of a path on disk. If path is not
	 * an actual folder on disk, has no effect.
	 * @param path path to file
	 * @return a set of sound clips
	 */
	public Set<SoundClip> loadSoundClips(String path) {
		Set<SoundClip> clips = SoundClipLoader.loadSoundClips(path);
		// TODO: Add the loaded sound clips to the root album
		for ( SoundClip sc : clips ) {
		    root.addSong(sc);
		}
		return clips;
	}
	
	/**
	 * Returns the root album
	 * @return the root album
	 */
	public Album getRootAlbum(){
		return root;
	}
	
	
	/**
	 * Adds an album to the Music Organizer
	 * @param selected album
	 */
	public void addNewAlbum(Album selected){ 
	    
	    try {
	        // prompts for new album name
            String albumName = view.promptForAlbumName();
            if ( albumName.isEmpty() ) albumName = "new album";
            
            // creates child and partent albums
            Album child = new Album(albumName);
            Album parent = view.getSelectedAlbum();
            
            // creates AlbumAdder and albumcommander
            AlbumAddClass albumAddClass = new AlbumAddClass(parent, child);
            albumCommander = new AlbumCommander();
            
            // sets the parameters for albumcommander and executes:
            albumCommander.setCommand(albumAddClass);
            albumCommander.execute();
            
            // updates the view
            view.onAlbumAdded(child);
	        
            
	    } catch ( NullPointerException ex ) {
            view.showMessage("No album created!");
            return;
        }
	}
	
	
	
	/**
	 * Removes an album from the Music Organizer
	 */
	public void deleteAlbum(){ 
	    album = view.getSelectedAlbum();
	    
	    if ( album == null ) {
	        System.out.println("no albums selected");
	        return;
	    }
	    
	    album.removeSubAlbum(album);
	    view.onAlbumRemoved(album);
	}
	
	
	/**
	 * Adds sound clips to an album
	 */
	public void addSoundClips(){ 
	    
	    try {
        	    Album a = view.getSelectedAlbum();
        	    if ( a == null ) {
        	        System.out.println("no album selected!");
        	        return;
        	    }
        	    
        	    JFileChooser j = new JFileChooser();
        	    j.showOpenDialog(view);
        	    File file = j.getSelectedFile();
        	    
        	    SoundClip sound = new SoundClip(file);
        	    a.addSong(sound);
        	    view.onClipsUpdated();
        	    
        	    
	    } catch ( NullPointerException ex ) {
	        view.showMessage("No songs added!");
	        return;
	    }
	    
	}
	
	/**
	 * Removes sound clips from an album
	 */
	public void removeSoundClips(){ //TODO Update parameters if needed
	    //
	    Album a = view.getSelectedAlbum();
	    
	    if ( a == null ) return;
	    if ( a.getSoundClips() == null ) return;
	    
	    soundclips = a.getSoundClips();
	  
	    List<SoundClip> viewSc = view.getSelectedSoundClips();
	    
	    // TODO: Hit någon bättre lösning...
	    try {
        	    for ( SoundClip sc : soundclips ) {
        	        if ( viewSc.get(0).equals(sc) ) a.removeSong(sc);
        	    }
	    } catch ( java.lang.IndexOutOfBoundsException ex ) {
	        System.out.println("index out of bounds!");
	        return;
	    }
	    
	    view.onClipsUpdated();
	           
	}
	
	/**
	 * Puts the selected sound clips on the queue and lets
	 * the sound clip player thread play them. Essentially, when
	 * this method is called, the selected sound clips in the 
	 * SoundClipTable are played.
	 */
	public void playSoundClips(){
		List<SoundClip> l = view.getSelectedSoundClips();
		for(int i=0;i<l.size();i++)
			queue.enqueue(l.get(i));
		
		album = view.getSelectedAlbum();
	}
	
	
	/**
	 * Undoes the recent action
	 */
	public void undoAction() {
	    System.out.println("Undoing");
	    Album a = view.getSelectedAlbum();
	    a.undoAction();
	    view.onClipsUpdated();
	    
	    view.onAlbumAdded(a.getChild());
	}
	
	
	/**
	 * Redoes the recent revoked action
	 */
	public void redoAction() {
	    System.out.println("Redoing");
	}
	
	
	/**
	 * For testing out the class
	 * @param args not in use
	 */
	public static void main(String[] args) {
	    System.out.println("test");
	}
}
