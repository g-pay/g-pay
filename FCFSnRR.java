
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
		Scanner sc = new Scanner(System.in);
        System.out.println("Enter no of processes");
        int n = sc.nextInt();
        int at[] = new int[n];
        int bt[] = new int[n];
        int pid[] = new int[n];
        int ct[] = new int[n];
        int wt[] = new int[n];
        int tat[] = new int[n];
        int remtime[] = new int[n];
        int tq,total;
        float avgtat = 0,avgwt = 0;

        for (int i = 0; i < n; i++) {
            System.out.println("Process "+(i + 1) + " Arrival Time:");
            at[i] = sc.nextInt();
            System.out.println("Process "+(i + 1) + " Burst Time:");
            bt[i] = sc.nextInt();
            remtime[i] = bt[i];
            wt[i] = 0;
            pid[i] = i+1;
        }

        System.out.println("Enter Value of Time Quantum");
        tq = sc.nextInt();

        int rp=n;
        int time=0;
        int i=0;
        System.out.println("0");
        wt[0] = 0;
        while (rp!=0) {
            if (remtime[i] > tq) {
                remtime[i] = remtime[i] - tq;
                System.out.println(" | Process ["+(i+1)+"]  | ");
                time = time + tq;
                System.out.println(time);
            }
            else if(remtime[i] <= tq && remtime[i]>0)
            {
                time = time + remtime[i];
                remtime[i] = remtime[i] - remtime[i];
                System.out.println(" | Process ["+(i+1)+"]  | ");
                rp--;
                wt[i] = time - bt[i];
                System.out.println(time);
            }
            i++;
            if(i==n){
                i=0;
            }
        }
        // Finding completion times
        for (i = 0; i < n; i++) {
            if(i == 0)
            {
                ct[i] = at[i] + bt[i];
            }
            else
            {
                if(at[i] > ct[i-1])
                {
                    ct[i] = at[i] + bt[i];
                }
                else
                {
                    ct[i] = ct[i-1] + bt[i];
                }
                
                // tat[i] = bt[i] - wt[i];
            }
    

        }
        for (i = 0; i < n ; i++)
        {
            tat[i] = bt[i] + wt[i];
            avgwt += wt[i];
            avgtat +=tat[i];
        }
        System.out.println("\n PID ARRIVAL BURST  TAT WAITING");
        for (i = 0; i < n; i++) {
            System.out.println(pid[i] + " \t "+at[i] + "\t" + bt[i] + "\t" + tat[i] + "\t" + wt[i]);
        }
        System.out.println("\n Average Waiting Time: "+(avgwt/n));
        System.out.println("\n Average Turn Around Time: "+(avgtat/n));
        sc.close();

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
