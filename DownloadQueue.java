import java.util.LinkedList;
import java.util.Queue;
/*
 * Varun Batra
 * 110256128
 * This class creates an instance of DownloadQueue
 */
public class DownloadQueue {
	LinkedList<DownloadJob> r = new LinkedList<DownloadJob>();
	Queue<DownloadJob> q = r;// Queue is an interface must be implemented by linked list or vector
	/*
	 * @param downloadjob d
	 * add d to queue
	 */
	public void enqueue( DownloadJob d){
		this.r.addLast(d);
	}
	/*
	 * dequeue job
	 */
	public DownloadJob dequeue(){
		DownloadJob d = null;
	if(!r.isEmpty()){
		d= r.removeFirst();
	}
	return d;
	}
	/*
	 * check if queue is empty
	 */
	public boolean isEmpty(){
		return r.isEmpty();
	}
	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString(){
		DownloadJob d =null;
		int index=0;
		while(d!=r.getLast()){
			d=r.get(index);
			index++;
		}
		return "[ "+d.getId()+": "+ d.toString()+ "]";
	}
}
