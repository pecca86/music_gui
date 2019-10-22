
public class Flag extends SoundDecorator {
	
	boolean b = false;
	String currentName;
	
	public Flag(MasterSound mastersound, boolean b, String name) {
		this.mastersound = mastersound;
		this.b = b;
		this.currentName = name;
	}
	
	@Override
	public String getName() {
		if (b) {
			return this.currentName;
		}
		return mastersound.getName() + " F"; 
	}
}
