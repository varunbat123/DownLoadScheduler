import java.util.Scanner;
/*
 * Varun Batra
 * 110256128
 */
public class DownloadManager {
	/*
	 * simulate
	 */
	public void run(int numberOfServers,double serverSpeed, double simulationTime, double probPremium, double probRegular){
		DownloadScheduler d = new DownloadScheduler();
		d.createServers(numberOfServers);
		d.DownloadSimulate(numberOfServers,serverSpeed,simulationTime,probPremium,probRegular);
		d.Average();
		
	
	}
	/*
	 * Ask user to enter parameters
	 */
	public void menu(){
		DownloadManager manager = new DownloadManager();
		Scanner scan = new Scanner(System.in);
		int numberOfServers=0;
		double serverSpeed=0;
		double simulationTime=0;
		double probPremium=0;
		double probRegular=0;
		try{
			System.out.println("Please enter a number of servers");
			
		
		
		
			numberOfServers= scan.nextInt();
			if(numberOfServers<0){
				throw new IllegalArgumentException();
			}
		
		System.out.println("Please enter a download speed");
		
			serverSpeed= scan.nextInt();
			if(serverSpeed<0){
				throw new IllegalArgumentException();
			}
		
		System.out.println("Please enter a length of time");
		
			simulationTime= scan.nextInt();
			if(simulationTime<0){
				throw new IllegalArgumentException();
			}
		
		System.out.println("Please enter a probability of premium job per timestep");
	
			probPremium= scan.nextDouble();
			if(probPremium>1||probPremium<0){
				throw new IllegalArgumentException();
			}
		
		System.out.println("Please enter a probability of regular job per timestep");
		
			probRegular= scan.nextDouble();
			if(probRegular>1||probRegular<0){
				throw new IllegalArgumentException();
			}
			
		
		manager.run(numberOfServers,serverSpeed,simulationTime, probPremium, probRegular);
	}
		catch (IllegalArgumentException e){
			System.out.println("Please enter a valid number");
			manager.menu();
		}
	}
	public static void main(String[]args){
		DownloadManager manager = new DownloadManager();
		Scanner scan = new Scanner(System.in);
		manager.menu();
	}

}
