import java.util.ArrayList;
import java.util.List;



public class FlaggedSongsAlbum extends SearchableAlbum {
	
	private String name;
	
	public FlaggedSongsAlbum(String albumName) {
		super(albumName);
		// TODO Auto-generated constructor stub
	}


	private List<SoundClip> flaggedSongs = new ArrayList<SoundClip>();
	
	@Override
	public void addTagged(SoundClip soundclip) {
		System.out.println("Adding flag...");
		flaggedSongs.add(soundclip);
	}
	

	@Override
	void removeTagged(SoundClip soundclip) {
		System.out.println("Removing flag...");
		flaggedSongs.remove(soundclip);
	}
	

	@Override

	public List<SoundClip> getSoundClips() {
        ArrayList<SoundClip> found = new ArrayList<SoundClip>();
        for ( SoundClip sc : flaggedSongs ) {
            found.add(sc);
        }
        return found;
	}


	@Override
	public String toString() {
		return "Flagged Sound Clips";
	}
}
