import java.io.File;

/**
 * SoundClip is a class representing a digital
 * sound clip file on disk.
 */
public class SoundClip extends MasterSound {

	private final File file;
	private boolean isFlagged = false;
	private int grade = -1;
	private boolean isGraded = false;
	private MasterSound ms;
	
	
	
	/**
	 * Make a SoundClip from a file.
	 * Requires file != null.
	 * @param file the sound clip file
	 */
	public SoundClip(File file) {
		assert file != null;
		this.file = file;
		this.fileName = file.getName();
		ms = this;
	}
	
	
	/**
	 * @return the file containing this sound clip.
	 */
	public File getFile() {
		return file;
	}
	
	
	/**
	 * Prints the object to string using our Decorator pattern
	 */
	@Override
    public String toString(){
		if ( grade != -1) {
			return ms.getName() + " " + grade;
		}
		return ms.getName();
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
	 * Calls creates a new decorator object that produces the tag "F" if flagged
	 */
	public void setFlag() {
		if (isFlagged) {
			ms = new Flag(ms, true, file.getName());
			isFlagged = false;
		} else {
			ms = new Flag(ms, false, file.getName());
			isFlagged = true;
		}
	}
	
	
	/**
	 * Checks if the sound clip is flagged or not
	 * @return true if it is, else false
	 */
	public boolean isFlagged() {
		return isFlagged;
	}
	
	
	/**
	 * Checks if the given sound clip is graded or not
	 * @return false if not, else true
	 */
	public boolean isGraded() {
		return isGraded;
	}
	
	
	/**
	 * Sets the grade of the file from 0 to 5
	 * @param grade grade 0 to 5, if graded anything else, it will default to -1
	 */
	public void setGrade(int grade) {
		if ( grade < 0 || grade > 5 ) {
			this.grade = -1;
			isGraded = false;
			return;
		}
		this.grade = grade;
		isGraded = true;
	}
	
	
	/**
	 * Gets the grade of the specifik file
	 * @return the grade of the file
	 */
	public int getGrade() {
		return this.grade;
	}
	
	
	/**
	 * For testing out our class
	 * @param args not in use
	 * 
	 */
	public static void main(String[] args) {
		File file1 = new File("Holy Diver");
		SoundClip sc = new SoundClip(file1);
	}
}
