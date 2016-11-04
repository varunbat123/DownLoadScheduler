import java.util.LinkedList;
import java.util.Queue;
/*
 * Varun Batra
 * 110256128
 * varun.batra@stonybrook.edu
 * this class implements a downloadscheduler object
 */
public class DownloadScheduler {
	int idCount=0;
	private int currentTime;
	private int simulationEndTime;
	private int downloadSpeed;
	int numberOfServers=150;
	int totalJobs=0;// jobs served
	int totalData=0;// data served
	int totalPremium=0;// premium jobs served
	int totalRegular=0;// regular data served
	int premiumData=0;// premium data served
	int regularData=0;// regular data served
	double averagePremiumWaitTime=0;
	double averageRegularWaitTime=0;
	Server[] servers = new Server[numberOfServers]; // array of servers

	/*
	 * Creates servers
	 * @param int numberOfServers
	 * number of servers
	 */
public void createServers(int numberOfServers){
for(int i =0;i<numberOfServers;i++){
	servers[i]= new Server(i+1);
	
}
}
	
	
/*
 * simulate downloading
 * @param int numberofServers
 * number of servers
 * @param double serverSpeed
 * download speed
 * @param double simulationTime
 * time of simulation
 * @param double probpremium
 * probability of premium file
 * @ param double probRegular
 * probability of regular file	
 */
public  void DownloadSimulate(int numberOfServers,double serverSpeed, double simulationTime, double probPremium, double probRegular)	{
	DownloadRandomizer random = new DownloadRandomizer(probPremium, probRegular);
DownloadQueue regularQ = new DownloadQueue();
DownloadQueue premiumQ = new DownloadQueue();


System.out.println("-----------------Simulation Starting----------------");
for( currentTime=0; currentTime< simulationTime; currentTime++){
	System.out.println("TimeStamp: "+ currentTime);
	
	int x = random.getRegular();// regular file size
	int y = random.getPremium(); //premium file size file size
	if(x!=-1){// -1 if no file
		idCount++;
		DownloadJob d = new DownloadJob(x, idCount,false, currentTime); // new regular job
		System.out.println("New Regular Job: "+ d.toString());
		
		regularQ.enqueue(d);
		
	}
	 else{
		 System.out.println("New Regular Job: n/a");
	 }
 if(y!=-1){
		idCount++;
		DownloadJob d = new DownloadJob(y, idCount,true,currentTime);// new premium job
		System.out.println("New Premium Job :" + d.toString());
		premiumQ.enqueue(d);
		
		
	}
 else{
	 System.out.println("New Premium Job: n/a");
 }
System.out.println( "RegularQueue "+queueStatus(regularQ));
System.out.println("PremiumQueue " +queueStatus(premiumQ));

for(int i =0; i< numberOfServers; i++){// check each server update statistics
	
	
	if(!premiumQ.isEmpty()){// if premium queue is not empty
		DownloadJob d = servers[i].job;// current job
		double z=0;// size left
		String completedJob="";
		if(servers[i].job!=null){// if job is not null
			if(servers[i].job.getDownloadSize()-((currentTime-servers[i].job.getTimeRequested())*serverSpeed)<=0.0){// if job is finished downloading
				completedJob+="Job"+"#:"+servers[i].job.getId()+ "completed, "+ servers[i].job.getPremium()+", "+ servers[i].job.getSize()+" served"+" Total wait time: "+ (currentTime-servers[i].job.getTimeRequested());
				totalJobs++;// increment jobs served
				totalData+=servers[i].job.getDownloadSize();//increment data served by current jobs data
				if(servers[i].job.isPremium()){
					averagePremiumWaitTime+=(currentTime-servers[i].job.getTimeRequested());
					totalPremium++;
					premiumData+=servers[i].job.getDownloadSize();}
				else{
					totalRegular++;
					regularData+=servers[i].job.getDownloadSize();
					averageRegularWaitTime+=(currentTime-servers[i].job.getTimeRequested());
				}
				
				d = premiumQ.dequeue();
				z =d.getDownloadSize()-((currentTime-d.getTimeRequested())*serverSpeed);
				servers[i].addJob(d);
		
	
		System.out.println("Server "+servers[i].getnumber()+":[  "+ d.getId()+": "+d.getSize()+", "+z + " remaining, " +"Request Time: "+d.getTimeRequested()+ ", " +d.getPremium());
		System.out.println(completedJob);
		}
			else{// if job is still downloading
				z =d.getDownloadSize()-((currentTime-d.getTimeRequested())*serverSpeed);
				System.out.println("Server "+servers[i].getnumber()+":[  "+ d.getId()+": "+d.getSize()+" "+z + " remaining, " +"Request Time: "+d.getTimeRequested() + ", "+ d.getPremium());
				// print out its current status
			}
			
			
		}
		else{// if server is null
			d = premiumQ.dequeue();
			servers[i].addJob(d);
			z =d.getDownloadSize()-((currentTime-d.getTimeRequested())*serverSpeed);
			
			
			System.out.println("Server "+servers[i].getnumber()+":[  "+ d.getId()+ ": "+d.getSize()+" "+z + " remaining, " +"Request Time: "+d.getTimeRequested()+ ", " +d.getPremium());
			// add new job
		}
	
		
	}
	else if (premiumQ.isEmpty()&&!regularQ.isEmpty()){
		
		DownloadJob d = servers[i].job;
		double z=0;
		String completedJob="";
		if(servers[i].job!=null){
			if(servers[i].job.getDownloadSize()-((currentTime-servers[i].job.getTimeRequested())*serverSpeed)<=0.0){// if job is finished
				
				totalJobs++;
				totalData+=servers[i].job.getDownloadSize();
				if(servers[i].job.isPremium()){
					averagePremiumWaitTime+=(currentTime-servers[i].job.getTimeRequested());
					totalPremium++;
					premiumData+=servers[i].job.getDownloadSize();}
					else{
						averageRegularWaitTime+=(currentTime-servers[i].job.getTimeRequested());
						totalRegular++;
						regularData+=servers[i].job.getDownloadSize();
					}
				completedJob+="Job"+"#:"+servers[i].job.getId()+ "completed, "+ servers[i].job.getPremium()+", "+ servers[i].job.getSize()+" served"+" Total wait time: "+ (currentTime-servers[i].job.getTimeRequested());
				 d = regularQ.dequeue();// dequeue from regular queue
				 z =d.getDownloadSize()-((currentTime-d.getTimeRequested())*serverSpeed);
				servers[i].addJob(d);
		
	
	System.out.println("Server "+servers[i].getnumber()+":[  "+d.getId()+": "+d.getSize()+" "+z + " remaining, " +"Request Time: "+d.getTimeRequested()+ ", "+ d.getPremium());
	System.out.println(completedJob);
		}
			else{// if still downloading
				z =d.getDownloadSize()-((currentTime-d.getTimeRequested())*serverSpeed);
				System.out.println("Server "+servers[i].getnumber()+":[  "+d.getId()+": "+d.getSize()+" "+z + " remaining, " +"Request Time: "+d.getTimeRequested()+ ", "+ d.getPremium());
				// print job out
			}
			
			
		}
		else{// server empty
			d = regularQ.dequeue();
			servers[i].addJob(d);
			z =d.getDownloadSize()-((currentTime-d.getTimeRequested())*serverSpeed);
			
			
			System.out.println("Server "+servers[i].getnumber()+":[  "+d.getId()+": "+d.getSize()+" "+z + " remaining, " +"Request Time: "+d.getTimeRequested()+ ", "+ d.getPremium());
			// add new job to server print it
		}
	
		
	}
	else if (!premiumQ.isEmpty()&&!regularQ.isEmpty()){ 
		DownloadJob d = servers[i].job;
		String completedJob="";
		double z=0;
		if(servers[i].job!=null){
			if(servers[i].job.getDownloadSize()-((currentTime-servers[i].job.getTimeRequested())*serverSpeed)<=0.0){
				totalJobs++;
				totalData+=servers[i].job.getDownloadSize();
				if(servers[i].job.isPremium()){
					averagePremiumWaitTime+=(currentTime-servers[i].job.getTimeRequested());
					totalPremium++;
					premiumData+=servers[i].job.getDownloadSize();}
					else{
						averageRegularWaitTime+=(currentTime-servers[i].job.getTimeRequested());
						totalRegular++;
						regularData+=servers[i].job.getDownloadSize();
					}
				completedJob="Job"+"#:"+servers[i].job.getId()+ "completed, "+ servers[i].job.getPremium()+", "+ servers[i].job.getSize()+" served"+" Total wait time: "+ (currentTime-servers[i].job.getTimeRequested()); 
				d = premiumQ.dequeue();
				z =d.getDownloadSize()-((currentTime-d.getTimeRequested())*serverSpeed);
				servers[i].addJob(d);
		
	
	System.out.println("Server "+servers[i].getnumber()+":[  "+d.getId()+": "+d.getSize()+" "+z + " remaining, " +"Request Time: "+d.getTimeRequested()+ ", "+ d.getPremium());
	System.out.println(completedJob);
		}
			else{
				z =d.getDownloadSize()-((currentTime-d.getTimeRequested())*serverSpeed);
				System.out.println("Server "+servers[i].getnumber()+":[  "+d.getId()+": "+d.getSize()+" "+z + " remaining, " +"Request Time: "+d.getTimeRequested()+ ", "+ d.getPremium());
				
			}
			
			
		}
		else{
			d = premiumQ.dequeue();
			servers[i].addJob(d);
			z =d.getDownloadSize()-((currentTime-d.getTimeRequested())*serverSpeed);
			
			
			System.out.println("Server "+servers[i].getnumber()+":[  "+d.getId()+": "+d.getSize()+" "+z + " remaining, " +"Request Time: "+d.getTimeRequested()+ ", "+ d.getPremium());
			
		}
	
	}
	else if (premiumQ.isEmpty()&&regularQ.isEmpty()){// both queues empty
		DownloadJob d = servers[i].job;
		double z=0;
			if(servers[i].job!=null){
				z =d.getDownloadSize()-((currentTime-d.getTimeRequested())*serverSpeed);
				System.out.println("Server "+servers[i].getnumber()+":[  "+d.getId()+": "+d.getSize()+" "+z + " remaining, " +"Request Time: "+d.getTimeRequested()+ ", "+ d.getPremium());
			}
			else{
		System.out.println("Server "+ servers[i].getnumber()+ " idle");
		// print out current status of each job in server array
			}
	}

}

 System.out.println("------------------------------------------------------");
 
}

}
public boolean isQueueEmpty(Queue<DownloadJob> q){
	boolean x = false;
	if(!q.isEmpty()){
	x = true;
	}
	return x;
	
}
/*
 * @param DownloadQueue
 * return status of Queue
 */
public String queueStatus(DownloadQueue q){
	String x ="";
	if(q.isEmpty()){
		x+="empty";
	
	}
	else{
		x+=q.toString();
	}
	return x;
	
}
/*
 * Average the data served
 */
public void Average(){
	System.out.println("Simulation Ended: ");
	System.out.println();
	System.out.println("Total Jobs Served: " + totalJobs);
	System.out.println("Total Premium Jobs Served: "+ totalPremium);
	System.out.println("Total Regular Jobs Served: "+ totalRegular);
	System.out.println("Total Data Served: "+ totalData+ "Mb");
	System.out.println("Total Premium Data Served: "+ premiumData+ "Mb");
	System.out.println("Total Regular Data Served: "+ regularData+ "Mb");
	System.out.println(averageRegularWaitTime);
	averagePremiumWaitTime=averagePremiumWaitTime/totalPremium;
	averageRegularWaitTime=averageRegularWaitTime/totalRegular;
	System.out.println("Average Premium Wait Time: "+ averagePremiumWaitTime);
	System.out.println("Average Regular Wait Time: "+ averageRegularWaitTime);
	System.out.println("-------------------"+" Thank You For Running The Simulator"+"---------------");
}

public static void main(String[] args){
	DownloadScheduler d = new DownloadScheduler();
	d.createServers(9);
	d.DownloadSimulate(9, 100, 8, .9, .8);
	d.Average();
}

}
