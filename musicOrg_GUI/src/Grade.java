
public class Grade extends SoundDecorator {
	
	int grade = 11;
	String CurrentName;

	public Grade(MasterSound mastersound, int grade, String name) {
		this.mastersound = mastersound;
		this.grade = grade;
		this.CurrentName = name;
	}
	
	@Override
	public String getName() {
		return this.CurrentName + " " + grade;
		//return mastersound.getName() + " " + grade;
	}
	
	
	public void setGrade(int grade) {
		this.grade = grade;
	}
}
