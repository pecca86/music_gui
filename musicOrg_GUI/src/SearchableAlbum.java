import java.io.File;
import java.util.List;


public abstract class SearchableAlbum extends Album {
	

	
	public SearchableAlbum(String albumName) {
		super(albumName);
		// TODO Auto-generated constructor stub
	}

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
	
	
	public static void main(String[] args) {
		File f = new File("file!");
		SoundClip sc = new SoundClip(f);
		Album a = new Album("Rioc");
		
		a.addSong(sc);
		
		SearchableAlbum flaggedAlbum = new FlaggedSongsAlbum("Flagged Album");
		System.out.println(a.getSoundClips());
		System.out.println("Flagged: " + flaggedAlbum.getSoundClips());
	}

}
