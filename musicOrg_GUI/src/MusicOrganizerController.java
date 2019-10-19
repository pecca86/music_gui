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
	private List<SoundClip> soundclips;
	
	private AlbumCommander albumCommander = new AlbumCommander();
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
	 */
	public void addNewAlbum(){ 
	    
	    try {
	        // prompts for new album name
            String albumName = view.promptForAlbumName();
            if ( albumName.isEmpty() ) albumName = "new album";
            
            // creates child and partent albums
            Album child = new Album(albumName);
            Album parent = view.getSelectedAlbum();
            
            // creates AlbumAdder and albumcommander
            AlbumAddClass albumAddClass = new AlbumAddClass(parent, child);
            AlbumRemoveClass removeAlbum = new AlbumRemoveClass(parent, child);
            
            // sets the parameters for albumcommander and executes:
            albumCommander.setCommand(albumAddClass);
            albumCommander.onAddButtonWasPushed();
            
            // updates the view
            view.onAlbumAdded(child);
	        
            
	    } catch ( NullPointerException ex ) {
            view.showMessage("No album created!");
            return;
        }
	}
	
	
	
	/**
	 * Removes an album from the Music Organizer, except the root album
	 */
	public void deleteAlbum(){ 
	    
	    // Get the selected album
	    Album selected = view.getSelectedAlbum();
	    
	    if ( selected == null) {
	        view.showMessage("No albums selected!");
	        return;
	    }
	    
	    // Get the parent of the selected album
	    Album parent = selected.getParent();
	    if ( parent == null )  {
	        view.showMessage("Album is not a subalbum!");
	        return;
	    }
	    
	    // Create a Album remove command
	    AlbumRemoveClass albumRm = new AlbumRemoveClass(parent, selected);
	    AlbumAddClass albumAddClass = new AlbumAddClass(parent, selected);
	    
	    // Set the command to the album commander and execute
	    albumCommander.setCommand(albumRm);
	    albumCommander.removeButtonWasPushed();
	    
	    // update view
	    view.onAlbumRemoved(selected);

	}
	
	
	/**
	 * Adds sound clips to an album
	 */
	public void addSoundClips(){ 
	    
	    try {
	            // Get the selected album
        	    Album album = view.getSelectedAlbum();
        	    if ( album == null ) {
        	        System.out.println("no album selected!");
        	        return;
        	    }
        	    
        	    // Choose file to add and put it in the SoundClip object
        	    JFileChooser j = new JFileChooser();
        	    j.showOpenDialog(view);
        	    File file = j.getSelectedFile();
        	    
        	    SoundClip sound = new SoundClip(file);
        	    
        	    // Create a new Sound adder command
        	    SoundAddCommand sAddCommand = new SoundAddCommand(album, sound);
        	    
        	    // Set the command to the albumcommander and execute
        	    albumCommander.setCommand(sAddCommand);
        	    albumCommander.onAddButtonWasPushed();
        	    
        	    // Update view
        	    view.onClipsUpdated();
        	    
        	    
	    } catch ( NullPointerException ex ) {
	        view.showMessage("No songs added!");
	        return;
	    }
	    
	}
	
	/**
	 * Removes sound clips from an album
	 */
	public void removeSoundClips(){
	    
	    // Get the selected album
	    Album album = view.getSelectedAlbum();
	    
	    // Check if album is selected and if it contains soundclips
	    if ( album == null ) return;
	    if ( album.getSoundClips() == null ) return;
	    
	    // Set the selected album's soundclip in to our soundclips arraylist
	    soundclips = album.getSoundClips();
	  
	    // Create a list where we put all the selected sound clips
	    List<SoundClip> viewSc = new ArrayList<SoundClip>();
	    viewSc = view.getSelectedSoundClips();
	    
	    // Create a new sound remove command, to where we send our selected album and selected soundclips
        SoundRemoveCommand soundRm = new SoundRemoveCommand(album, (ArrayList<SoundClip>) viewSc);
        albumCommander.setCommand(soundRm);
        albumCommander.removeButtonWasPushed();
	    
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
	}
	
	
	/**
	 * Undoes the recent action
	 */
	public void undoAction() {
	    albumCommander.undoButtonWasPushed();
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
