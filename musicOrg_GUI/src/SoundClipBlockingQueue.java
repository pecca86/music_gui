import java.util.LinkedList;
import java.util.List;

/**
 * @version 30.9.2019
 *
 */
public class SoundClipBlockingQueue {

	private List<SoundClip> queue = new LinkedList<SoundClip>();
	
	// Add a SoundClip to the queue, and notify all
	// threads waiting for SoundClips on the queue
	/**
	 * @param item sound clip
	 */
	public synchronized void enqueue(SoundClip item){
		queue.add(item);
	    notifyAll();
	}
	
	// Remove a SoundClip from the queue. 
	// If the queue is empty, the invoking thread will wait until
	// there is a SoundClip in the queue, and then remove it from
	// the queue.
	/**
	 * @return queue without the object we want
	 * @throws InterruptedException exception
	 */
	public synchronized SoundClip dequeue() throws InterruptedException{
		while(queue.size() == 0){
			wait();
		}
	    return queue.remove(0);
	}
}
