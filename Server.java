/*
 * Varun Batra
 * 110256128
 * creates an instance of server
 */
public class Server {
	DownloadJob job;
	int number;
	public Server(int number){
		this.number = number;
	
		
	}
	/*
	 * @param Downloadjob d
	 * set server job to d
	 */
	public void addJob(DownloadJob d){// set server job to d
		job = d;
		
	}
	
	public String toString(){
		String x = "Server" + this.number+ " :[ "+job.toString()+ " total";
		return x;
		
	}
	/*
	 * return server id
	 */
	public int getnumber(){
		return this.number;
	}
	
	

}
