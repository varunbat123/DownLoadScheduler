
/*
 * Varun Batra
 * 110256128
 * varun.batra@stonybrook.edu
 * This class implements a DownloadJob object
 */
public class DownloadJob {
	private int downloadSize;
	private int downloadSizeRemaining;
	private int timeRequested;
	private boolean isPremium;
	private int id;
	/*
	 * Constructs a DownloadJob object
	 *@param downloadSize
	 *size of job
	 *@ param id
	 *id of job
	 *@param isPremium
	 *job premium or regular
	 *@param timeRequested
	 *time of job request
	 */
	public DownloadJob(int downloadSize,int id, boolean isPremium, int timeRequested){
		this.id=id;
		this.downloadSize = downloadSize;
		this.isPremium = isPremium;
		this.timeRequested = timeRequested;
	
		
	}
	/*
	 * Returns job id
	 */
	public int getId(){
		return this.id;
		
	}
	/*
	 * Returns job timeRequested
	 */
	public int getTimeRequested(){
		return this.timeRequested;
	}
/*
 * (non-Javadoc)
 * @see java.lang.Object#toString()
 */
	public String toString(){
		String x = "Job: " + this.id+ "Size: "+ this.downloadSize;
		return x;
		
	}
	/*
	 * Returns size of this job in string
	 */
	public String getSize(){
		return this.downloadSize+"mb" + " total";
	}
	/*
	 * returns this size
	 */
	public double getDownloadSize()
	{
		return this.downloadSize;
	}
	/*
	 * Return
	 * premium or regular
	 */
	public String getPremium(){
		String x ="Regular";
		if (this.isPremium){
			x="Premium";
		}
		return x;
		
	}
	/*
	 * return 
	 * this is premium
	 */
	public boolean isPremium(){
		return this.isPremium;
	}
	


}
