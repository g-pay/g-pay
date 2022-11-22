import java.util.Arrays;
import java.util.Scanner;

public class FCFSnPRIORITYNP {
	public void fcfs() {
	       System.out.println("Enter the number of process");
	        Scanner in = new Scanner(System.in);
	        int numberOfProcesses = in.nextInt();
	        int avgw=0;
	        int pid[] = new int[numberOfProcesses];
	        int bt[] = new int[numberOfProcesses];
	        int ar[] = new int[numberOfProcesses];
	        int ct[] = new int[numberOfProcesses];
	        int ta[] = new int[numberOfProcesses];
	        int wt[] = new int[numberOfProcesses];

	        for(int i = 0; i < numberOfProcesses; i++) {
	            System.out.println("Enter process " + (i+1) + " arrival time: ");
	            ar[i] = in.nextInt();
	            System.out.println("Enter process " + (i+1) + " brust time: ");
	            bt[i] = in.nextInt();
	            pid[i] = i+1;
	        }
	        int temp;
	        for (int i = 0; i < numberOfProcesses; i++) {
	            for (int j = i+1; j < numberOfProcesses; j++) {

	                if(ar[i] > ar[j]) {
	                    temp = ar[i];
	                    ar[i] = ar[j];
	                    ar[j] = temp;

	                    temp = pid[i];
	                    pid[i] = pid[j];
	                    pid[j] = temp;
	                    
	                    temp = bt[i];
	                    bt[i] = bt[j];
	                    bt[j] = temp;
	                }
	            }
	        }

	        
	        ct[0] = bt[0] + ar[0];
	        for(int i = 1; i < numberOfProcesses; i++) {
	            ct[i] = ct[i - 1] + bt[i];
	        }
	        for(int i = 0; i < numberOfProcesses; i++) {
	            ta[i] = ct[i] - ar[i];
	            wt[i] = ta[i] - bt[i];
	        }
	       
	        
	        System.out.println("Process\t\tAT\t\tBT\t\tCT\t\tTAT\t\tWT");
	        for(int i = 0; i < numberOfProcesses; i++) {
	            System.out.println(pid[i]+"\t\t\t" + ar[i] + "\t\t" + bt[i]+ "\t\t" + ct[i]+ "\t\t" + ta[i]+ "\t\t" + wt[i]);
	        }

	        System.out.println("gantt chart: ");
	        for(int i = 0; i < numberOfProcesses; i++) {
	            System.out.print("P" + pid[i] +" ");
	        }
	        
	        for(int i = 0; i < ar.length; i++) {    	
	            avgw+=wt[i];          
	        }
	        
	 System.out.print("agw " +avgw/numberOfProcesses);
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
            if (priority[j] < priority[j + 1]) {
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

