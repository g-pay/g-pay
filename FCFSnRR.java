
import java.util.Scanner;


public class FCFSnRR {
	
	public void fcfs() {
	      Scanner sc = new Scanner(System.in);
System.out.println("enter no of process: ");
int n = sc.nextInt();
int pid[] = new int[n];   // process ids
int ar[] = new int[n];     // arrival times
int bt[] = new int[n];     // burst or execution times
int ct[] = new int[n];     // completion times
int ta[] = new int[n];     // turn around times
int wt[] = new int[n];     // waiting times
int temp;
float avgwt=0,avgta=0;
 
for(int i = 0; i < n; i++)
{
System.out.println("enter process " + (i+1) + " arrival time: ");
ar[i] = sc.nextInt();
System.out.println("enter process " + (i+1) + " brust time: ");
bt[i] = sc.nextInt();
pid[i] = i+1;
}
 
//sorting according to arrival times
for(int i = 0 ; i <n; i++)
{
for(int  j=0;  j < n-(i+1) ; j++)
{
if( ar[j] > ar[j+1] )
{
temp = ar[j];
ar[j] = ar[j+1];
ar[j+1] = temp;
temp = bt[j];
bt[j] = bt[j+1];
bt[j+1] = temp;
temp = pid[j];
pid[j] = pid[j+1];
pid[j+1] = temp;
}
}
}
// finding completion times
for(int  i = 0 ; i < n; i++)
{
if( i == 0)
{
ct[i] = ar[i] + bt[i];
}
else
{
if( ar[i] > ct[i-1])
{
ct[i] = ar[i] + bt[i];
}
else
ct[i] = ct[i-1] + bt[i];
}
ta[i] = ct[i] - ar[i] ;          // turnaround time= completion time- arrival time
wt[i] = ta[i] - bt[i] ;          // waiting time= turnaround time- burst time
avgwt += wt[i] ;               // total waiting time
avgta += ta[i] ;               // total turnaround time
}
System.out.println("\npid  arrival  brust  complete turn waiting");
for(int  i = 0 ; i< n;  i++)
{
System.out.println(pid[i] + "  \t " + ar[i] + "\t" + bt[i] + "\t" + ct[i] + "\t" + ta[i] + "\t"  + wt[i] ) ;
}
sc.close();
System.out.println("\naverage waiting time: "+ (avgwt/n));     // printing average waiting time.
System.out.println("average turnaround time:"+(avgta/n));    // printing average turnaround time.

	    }
	
	
	void rr() {
		Scanner inp = new Scanner(System.in);
		int n, tq, timer = 0, maxProccessIndex = 0;
		float avgWait = 0, avgTT = 0;
		System.out.print("\nEnter the time quanta : ");
		tq = inp.nextInt();
		System.out.print("\nEnter the number of processes : ");
		n = inp.nextInt();
		int arrival[] = new int[n];
		int burst[] = new int[n];
		int wait[] = new int[n];
		int turn[] = new int[n];
		int queue[] = new int[n];
		int temp_burst[] = new int[n];
		boolean complete[] = new boolean[n];
		System.out.print("\nEnter the arrival time of the processes : ");
		for (int i = 0; i < n; i++)
			arrival[i] = inp.nextInt();
		System.out.print("\nEnter the burst time of the processes : ");
		for (int i = 0; i < n; i++) {
			burst[i] = inp.nextInt();
			temp_burst[i] = burst[i];
		}
		for (int i = 0; i < n; i++) {
			complete[i] = false;
			queue[i] = 0;
		}
		while (timer < arrival[0])
			timer++;
		queue[0] = 1;
		while (true) {
			boolean flag = true;
			for (int i = 0; i < n; i++) {
				if (temp_burst[i] != 0) {
					flag = false;
					break;
				}
			}
			if (flag)
				break;
			for (int i = 0; (i < n) && (queue[i] != 0); i++) {
				int ctr = 0;
				while ((ctr < tq) && (temp_burst[queue[0] - 1] > 0)) {
					temp_burst[queue[0] - 1] -= 1;
					timer += 1;
					ctr++;
					checkNewArrival(timer, arrival, n, maxProccessIndex, queue);
				}
				if ((temp_burst[queue[0] - 1] == 0) && (complete[queue[0] - 1] == false)) {
					turn[queue[0] - 1] = timer;
					complete[queue[0] - 1] = true;
				}
				boolean idle = true;
				if (queue[n - 1] == 0) {
					for (int k = 0; k < n && queue[k] != 0; k++) {
						if (complete[queue[k] - 1] == false) {
							idle = false;
						}
					}
				} else
					idle = false;
				if (idle) {
					timer++;
					checkNewArrival(timer, arrival, n, maxProccessIndex, queue);
				}
				queueMaintainence(queue, n);
			}
		}
		for (int i = 0; i < n; i++) {
			turn[i] = turn[i] - arrival[i];
			wait[i] = turn[i] - burst[i];
		}
		System.out.print("\nProgram No.\tArrival Time\tBurst Time\tWait Time\tTurnAroundTime" + "\n");
		for (int i = 0; i < n; i++) {
			System.out.print(
					i + 1 + "\t\t" + arrival[i] + "\t\t" + burst[i] + "\t\t" + wait[i] + "\t\t" + turn[i] + "\n");
		}
		for (int i = 0; i < n; i++) {
			avgWait += wait[i];
			avgTT += turn[i];
		}
		System.out.print("\nAverage wait time : " + (avgWait / n) + "\nAverage Turn Around Time : " + (avgTT / n));
	}

	public static void queueUpdation(int queue[], int timer, int arrival[], int n, int maxProccessIndex) {
		int zeroIndex = -1;
		for (int i = 0; i < n; i++) {
			if (queue[i] == 0) {
				zeroIndex = i;
				break;
			}
		}
		if (zeroIndex == -1)
			return;
		queue[zeroIndex] = maxProccessIndex + 1;
	}

	public static void checkNewArrival(int timer, int arrival[], int n, int maxProccessIndex, int queue[]) {
		if (timer <= arrival[n - 1]) {
			boolean newArrival = false;
			for (int j = (maxProccessIndex + 1); j < n; j++) {
				if (arrival[j] <= timer) {
					if (maxProccessIndex < j) {
						maxProccessIndex = j;
						newArrival = true;
					}
				}
			}
			if (newArrival)
				queueUpdation(queue, timer, arrival, n, maxProccessIndex);
		}
	}

	public static void queueMaintainence(int queue[], int n) {
		for (int i = 0; (i < n - 1) && (queue[i + 1] != 0); i++) {
			int temp = queue[i];
			queue[i] = queue[i + 1];
			queue[i + 1] = temp;
		}
	}
	
	
	
    public static void main(String[] args) {
    	FCFSnRR f = new FCFSnRR();
    	int ch;
    	
    	System.out.println("1.FCFS \n 2.Round Robin\n 3.EXIT \n");
    	System.out.println("Enter Choice:- ");
    	Scanner sc = new Scanner(System.in);
    	ch = sc.nextInt();
    	
    	while(ch!=3)
    	{
    		switch(ch) {
    		case 1 :   f.fcfs();
    		break;
    		
    		case 2:f.rr();
    		break;
    		
    		}
    	}

    }

}
