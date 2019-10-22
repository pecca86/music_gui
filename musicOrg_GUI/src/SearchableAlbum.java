import java.io.File;
import java.util.List;

/**
 * Abstract class for our searchable albums
 * @author pekka
 *
 */
public abstract class SearchableAlbum extends Album {
	

	/**
	 * Constructor
	 * @param albumName the name of our searchable album
	 */
	public SearchableAlbum(String albumName) {
		super(albumName);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Initates the album
	 */
	final void initiateAlbum() {
		addTagged(null);
		removeTagged(null);
		getSoundClips();
		toString();
	}
	
	abstract void addTagged(SoundClip soundclip);
	abstract void removeTagged(SoundClip soundclip);
	@Override
	public
	abstract List<SoundClip> getSoundClips();
	@Override
	public abstract String toString();
	
}
