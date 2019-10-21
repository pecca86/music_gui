import java.io.File;

/**
 * SoundClip is a class representing a digital
 * sound clip file on disk.
 */
public class SoundClip {

	private final File file;
	private boolean isFlagged = false;
	
	/**
	 * Make a SoundClip from a file.
	 * Requires file != null.
	 * @param file the sound clip file
	 */
	public SoundClip(File file) {
		assert file != null;
		this.file = file;
	}
	

	/**
	 * @return the file containing this sound clip.
	 */
	public File getFile() {
		return file;
	}
	
	
	@Override
    public String toString(){
		if (isFlagged) {
			return file.getName() + " F";
		}
		return file.getName();
	}
	
	
	@Override
	public boolean equals(Object obj) {
		return 
			obj instanceof SoundClip
			&& ((SoundClip)obj).file.equals(file);
	}
	
	
	@Override
	public int hashCode() {
		return file.hashCode();
	}
	
	
	/**
	 * Sets a flag on the selected sound clips
	 */
	public void setFlag() {
		if (isFlagged) {
			isFlagged = false;
		} else
		isFlagged = true;
	}
	
	
	/**
	 * Checks if the sound clip is flagged or not
	 * @return true if it is, else false
	 */
	public boolean isFlagged() {
		return isFlagged;
	}
	
	
	/**
	 * For testing out our class
	 * @param args not in use
	 * 
	 */
	public static void main(String[] args) {
		File file1 = new File("fy fan!");
		SoundClip sc = new SoundClip(file1);
		
		System.out.println(sc.toString());
		System.out.println(sc.isFlagged);
		
		sc.setFlag();
		System.out.println(sc.toString());
		System.out.println(sc.isFlagged);
		
		sc.setFlag();
		System.out.println(sc.toString());
		System.out.println(sc.isFlagged);
	}
	
}
