
import java.util.Arrays;
import java.util.Scanner;

public class PRIORITYNPnRR {

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
		for (int i = 0; i < numberOfProcess; i++) {
			burstTime[i] = sc.nextInt();
		}

		System.out.println(Arrays.toString(burstTime));
		System.out.print("Enter Arrival Time for " + numberOfProcess + " process: ");
		for (int i = 0; i < numberOfProcess; i++) {
			ar[i] = sc.nextInt();
		}

		System.out.print("Enter Priority for " + numberOfProcess + " process: ");

		int priority[] = new int[numberOfProcess];
		for (int i = 0; i < numberOfProcess; i++) {
			priority[i] = sc.nextInt();
		}

		System.out.println(Arrays.toString(priority));

// Sorting process &amp; burst time by priority
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
		for (int i = 1; i < numberOfProcess; i++) {
			ct[i] = ct[i - 1] + burstTime[i];
		}

		int TAT[] = new int[numberOfProcess];
		int waitingTime[] = new int[numberOfProcess];

// Calculating Waiting Time &amp; Turn Around Time
		for (int i = 0; i < numberOfProcess; i++) {
			TAT[i] = ct[i] - ar[i];
			waitingTime[i] = TAT[i] - burstTime[i];
		}

		int totalWT = 0;
		int totalTAT = 0;
		double avgWT;
		double avgTAT;

		System.out.println("Process  AT   BT         PRIORTY      CT       WT        TAT");
		for (int i = 0; i < numberOfProcess; i++) {

			System.out.println(process[i] + "          " + ar[i] + "      " + burstTime[i] + "       " + priority[i]
					+ "           " + ct[i] + "       " + (waitingTime[i]) + "         " + (TAT[i]));
			totalTAT += TAT[i];
			totalWT += waitingTime[i];

		}

		avgWT = totalWT / (double) numberOfProcess;
		avgTAT = totalTAT / (double) numberOfProcess;

		System.out.println("\n Average Wating Time: " + avgWT);
		System.out.println(" Average Turn Around Time: " + avgTAT);

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
		PRIORITYNPnRR f = new PRIORITYNPnRR();
		int ch;

		System.out.println("1.Priority \n 2.Round Robin\n 3.EXIT \n");
		System.out.println("Enter Choice:- ");
		Scanner sc = new Scanner(System.in);
		ch = sc.nextInt();

		while (ch != 3) {
			switch (ch) {
			case 1:
				f.priority();
				break;

			case 2:
				f.rr();
				break;

			}
		}

	}

}
