/**
 * A concrete decorator in our decorator pattern
 * @author pekka
 *
 */
public class Flag extends SoundDecorator {
	
	boolean b = false;
	String currentName;
	
	/**
	 * Our constructor
	 * @param mastersound the object we want to decorate
	 * @param b if it's already decorated or not
	 * @param name The name of the object
	 */
	public Flag(MasterSound mastersound, boolean b, String name) {
		this.mastersound = mastersound;
		this.b = b;
		this.currentName = name;
	}
	
	/**
	 * Return the name plus a decorator add on
	 */
	@Override
	public String getName() {
		if (b) {
			return this.currentName;
		}
		return mastersound.getName() + " F"; 
	}
}
