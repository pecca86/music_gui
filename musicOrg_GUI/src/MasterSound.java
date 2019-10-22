
public abstract class MasterSound {
	protected String fileName = "Empty file name";
	
	public String getName() {
		return fileName;
	}
	
	public void setName(String name) {
		this.fileName = name;
	}
	
	@Override
	public String toString() {
		return fileName;
	}
}
