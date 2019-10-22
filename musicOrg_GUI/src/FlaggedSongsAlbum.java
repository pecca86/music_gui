import java.util.ArrayList;
import java.util.List;


/**
 * An album that keeps track of all the sound clips tagged with a flag
 * @author pekka
 *
 */
public class FlaggedSongsAlbum extends SearchableAlbum {
	
	
	/**
	 * Constructor
	 * @param albumName name of this album
	 */
	public FlaggedSongsAlbum(String albumName) {
		super(albumName);
	}


	private List<SoundClip> flaggedSongs = new ArrayList<SoundClip>();
	
	/**
	 * Adds the tagged soundclip to our array
	 */
	@Override
	public void addTagged(SoundClip soundclip) {
		System.out.println("Adding flag...");
		flaggedSongs.add(soundclip);
	}
	

	/**
	 * Removes the selected soundclip from our array
	 */
	@Override
	void removeTagged(SoundClip soundclip) {
		System.out.println("Removing flag...");
		flaggedSongs.remove(soundclip);
	}
	

	/**
	 * Gives all the stored sound clip inside this album
	 */
	@Override
	public List<SoundClip> getSoundClips() {
        ArrayList<SoundClip> found = new ArrayList<SoundClip>();
        for ( SoundClip sc : flaggedSongs ) {
            found.add(sc);
        }
        return found;
	}


	/**
	 * Gives the name of this album
	 */
	@Override
	public String toString() {
		return "Flagged Sound Clips";
	}
}
