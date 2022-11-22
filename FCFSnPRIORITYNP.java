import java.util.Arrays;
import java.util.Scanner;

public class FCFSnPRIORITYNP {
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
	
	
	public void priority() {
		System.out.println("*** Priority Scheduling ***");

        System.out.print("Enter Number of Process: ");
        Scanner sc = new Scanner(System.in);
        int numberOfProcess = sc.nextInt();
        int ar[] = new int[numberOfProcess];
        int ct[] = new int[numberOfProcess];
        String process[] = new String[numberOfProcess];

        int p = 1;
        for (int i = 0; i < numberOfProcess; i++) {
            process[i] = "P" + p;
            p++;
        }

        System.out.println(Arrays.toString(process));

        System.out.print("Enter Burst Time for " + numberOfProcess + " process: ");

        int burstTime[] = new int[numberOfProcess];
        for (int i = 0; i< numberOfProcess; i++) {
            burstTime[i] = sc.nextInt();
        }

        System.out.println(Arrays.toString(burstTime));
        System.out.print("Enter Arrival Time for " + numberOfProcess + " process: ");
        for (int i = 0; i< numberOfProcess; i++) {
            ar[i] = sc.nextInt();
        }
        
        System.out.print("Enter Priority for " + numberOfProcess + " process: ");

        int priority[] = new int[numberOfProcess];
        for (int i = 0; i < numberOfProcess; i++) {
            priority[i] = sc.nextInt();
        }

        System.out.println(Arrays.toString(priority));

int temp;
String temp2;
for (int i = 0; i < numberOfProcess - 1; i++) {
      for (int j = 0; j < numberOfProcess - 1; j++) {
            if (priority[j] > priority[j + 1]) {
                   temp = priority[j];
                    priority[j] = priority[j + 1];
                    priority[j + 1] = temp;

                    temp = burstTime[j];
                    burstTime[j] = burstTime[j + 1];
                    burstTime[j + 1] = temp;

                    temp2 = process[j];
                    process[j] = process[j + 1];
                    process[j + 1] = temp2;
                    
                    temp = ar[j];
                    ar[j] = ar[j + 1];
                    ar[j + 1] = temp;

                }
            }
        }

ct[0] = burstTime[0] + ar[0];
for(int i = 1; i < numberOfProcess; i++) {
    ct[i] = ct[i - 1] + burstTime[i];
}

        int TAT[] = new int[numberOfProcess];
        int waitingTime[] = new int[numberOfProcess];


        for (int i = 0; i <numberOfProcess; i++) {
            TAT[i] = ct[i] - ar[i];
            waitingTime[i] = TAT[i] - burstTime[i];
        }

        int totalWT = 0;
        int totalTAT = 0;
        double avgWT;
        double avgTAT;

        System.out.println("Process  AT   BT         PRIORTY      CT       WT        TAT");
        for (int i = 0; i<numberOfProcess; i++) {

            System.out.println(process[i] + "          " +ar[i] +"      "+ burstTime[i] + "       "+priority[i]+"           "+ct[i]+"       " +( waitingTime[i]) + "         " + (TAT[i]));
            totalTAT +=  TAT[i];
            totalWT += waitingTime[i];

        }

        avgWT = totalWT / (double) numberOfProcess;
        avgTAT = totalTAT / (double) numberOfProcess;

        System.out.println("\n Average Wating Time: " + avgWT);
        System.out.println(" Average Turn Around Time: " + avgTAT);

	}
	
	
 public static void main(String[] args) {
 	FCFSnPRIORITY f = new FCFSnPRIORITY();
 	int ch;
 	
 	System.out.println("1.FCFS \n 2.PRIORITY\n 3.EXIT \n");
 	System.out.println("Enter Choice:- ");
 	Scanner sc = new Scanner(System.in);
 	ch = sc.nextInt();
 	
 	while(ch!=3)
 	{
 		switch(ch) {
 		case 1 :   f.fcfs();
 		break;
 		
 		case 2:f.priority();
 		break;
 		
 		}
 	}

 }

}

