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
	private SearchableAlbum flaggedSongs = new FlaggedSongsAlbum("Flagged songs");
	private List<SoundClip> soundclips;
	
	//MEMENTO ATTRIBUTES:
	private AlbumCommander albumCommander = new AlbumCommander();
	private AlbumCaretaker caretaker = new AlbumCaretaker();
	private MementoCommand mementoCommand;
	private boolean wasLastAddedAlbum = false;
	private boolean wasLastRemovedAlbum = false;
	
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
	
	public SearchableAlbum getSearchableAlbums() {
		return flaggedSongs;
	}
	
	
	/**
	 * Adds an album to the Music Organizer
	 */
	public void addNewAlbum() { 
	    
	    try {
	        // prompts for new album name
            String albumName = view.promptForAlbumName();
            if ( albumName.isEmpty() ) albumName = "new album";
            
            // creates child and partent albums
            Album child = new Album(albumName);
            Album parent = view.getSelectedAlbum();
            
            // creates AlbumAdder and albumcommander
            AlbumAddClass albumAddClass = new AlbumAddClass(parent, child);
            
            // sets the parameters for albumcommander and executes:
            albumCommander.setCommand(albumAddClass);
            albumCommander.onButtonWasPushed();
            
            
            // updates the view
            view.onAlbumAdded(child);
	        
            mementoCommand = new MementoCommand(parent);
            albumCommander.save(mementoCommand);
            
            // so our undo function knows which restore to use
            wasLastAddedAlbum = true;
            wasLastRemovedAlbum = false;
            
	    } catch ( NullPointerException ex ) {
            view.showMessage("No album created!");
            return;
        } catch (CloneNotSupportedException e) {
            System.out.println("Object not cloneable " + e);
        } 
	}
	
	
	
	/**
	 * Removes an album from the Music Organizer, except the root album
	 * @throws  
	 */
	public void deleteAlbum() { 
	    
	
	
		
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
	
	view.onAlbumRemoved(selected);
	
	wasLastAddedAlbum = false;
	wasLastRemovedAlbum = true;

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
        	    albumCommander.onButtonWasPushed();
        	    
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
        albumCommander.onButtonWasPushed();
	    
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
	
	
	/*
	public void undoAction() {
		Album a = view.getSelectedAlbum();
		a.undoLastAction();
		view.onClipsUpdated();
	}
	
	
	public void redoAction() {
		Album a = view.getSelectedAlbum();
		a.redoLastAction();
		view.onClipsUpdated();
	}
	*/
	
	
	/**
	 * Undoes the recent action
	 */
	public void undoAction() {
		try {
			albumCommander.restore(mementoCommand);
			
			Album a = mementoCommand.getAlbum();
			Album child = a.getChild();
			

			view.onAlbumAdded(child);
			mementoCommand = new MementoCommand(a);
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	/**
	 * Redoes the recent revoked action
	 */
	public void redoAction() {
	    System.out.println("Redoing");
	}
	
	
	/**
	 * Method for flagging selected song/s in the album
	 */
	public void setFlag() {
		System.out.println("Setting flags!");
		List<SoundClip> selectedSongs = new ArrayList<SoundClip>();
		
		selectedSongs = view.getSelectedSoundClips();
		for ( SoundClip sc : selectedSongs ) {
			if ( !sc.isFlagged()) {
				sc.setFlag();
				flaggedSongs.addTagged(sc);
			} else if ( sc.isFlagged() ) {
				sc.setFlag();
				flaggedSongs.removeTagged(sc);
			}
		}
		view.onClipsUpdated();
	}
	
	
	/**
	 * Calls the setGrade method in our SoundClip class and set the grade to the soundclip
	 * @param grade grade from 0-5, if grade remove we take in -1
	 */
	public void setGrade(int grade) {
		int userGrade = grade;
		List<SoundClip> selectedSongs = new ArrayList<SoundClip>();
		
		if ( grade < 0 || grade > 5) {
			view.showMessage("grading removed!");
			
			selectedSongs = view.getSelectedSoundClips();
			for ( SoundClip sc : selectedSongs ) {
				sc.setGrade(userGrade);
			}
			
			view.onClipsUpdated();
			return;
		}
		
		selectedSongs = view.getSelectedSoundClips();
		for ( SoundClip sc : selectedSongs ) {
			sc.setGrade(userGrade);
		}
		
		view.showMessage("grade was set to: " + grade);
		view.onClipsUpdated();
	}
	
	
	/**
	 * For testing out the class
	 * @param args not in use
	 */
	public static void main(String[] args) {
	    System.out.println("test");
	}
}
