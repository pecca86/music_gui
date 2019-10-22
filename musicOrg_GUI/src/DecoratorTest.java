import java.io.File;

/**
 * 
 */

/**
 * @author pekka
 *
 */
public class DecoratorTest {

	/**
	 * @param args fs 
	 */
	public static void main(String[] args) {
		
		File fille = new File("pitt");
		
		MasterSound ms = new SoundClip(fille);
		System.out.println(ms.toString());
		
		ms = new Flag(ms, true, "ffff");
		System.out.println(ms.toString());
		
	}

}
