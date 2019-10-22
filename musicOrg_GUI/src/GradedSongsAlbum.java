import java.util.ArrayList;
import java.util.List;

/**
 * An album that keeps track of all songs graded 4 or more
 * @author pekka
 *
 */
public class GradedSongsAlbum extends SearchableAlbum {
	
	private List<SoundClip> gradedSongs = new ArrayList<SoundClip>();

	
	/**
	 * Constructor
	 * @param albumName name of this album
	 */
	public GradedSongsAlbum(String albumName) {
		super(albumName);
	}

	
	/**
	 * Adds the tagged soundclip to our array
	 */
	@Override
	void addTagged(SoundClip soundclip) {
		System.out.println("Adding grade...");
		gradedSongs.add(soundclip);
	}

	
	/**
	 * Removes the selected soundclip from our array
	 */
	@Override
	void removeTagged(SoundClip soundclip) {
		System.out.println("Removing grade...");
		gradedSongs.remove(soundclip);
	}

	
	/**
	 * Gives all the stored sound clip inside this album
	 */
	@Override
	public List<SoundClip> getSoundClips() {
		ArrayList<SoundClip> found = new ArrayList<SoundClip>();
        for ( SoundClip sc : gradedSongs ) {
        	//if (!sc.isGraded()) continue;
            found.add(sc);
        }
        return found;
	}

	
	/**
	 * Gives the name of this album
	 */
	@Override
	public String toString() {
		return "Great Sound Clips";
	}
}
