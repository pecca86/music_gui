
public abstract class SoundDecorator extends MasterSound {
	
	MasterSound mastersound;
	
	@Override
	public String toString() {
		return this.getName();
	}

}
