
import java.util.Arrays;
import java.util.Scanner;

public class SJFNPnPRIORITY{
	
	void sjf() {
		Scanner sc = new Scanner(System.in);
        System.out.println ("enter no of process:");
        int n = sc.nextInt();
        int pid[] = new int[n];
        int at[] = new int[n];
        int bt[] = new int[n];
        int ct[] = new int[n];
        int ta[] = new int[n];
        int wt[] = new int[n];
        int f[] = new int[n];

        int st=0, tot=0;
        float avgwt=0, avgta=0;

        for(int i=0;i<n;i++)
        {
            System.out.println ("enter process " + (i+1) + " arrival time:");
            at[i] = sc.nextInt();
            System.out.println ("enter process " + (i+1) + " brust time:");
            bt[i] = sc.nextInt();
            pid[i] = i+1;
            f[i] = 0;
        }


        while(true)
        {
            int c=n, min = 999999;

            if (tot == n)
                break;

            for (int i=0; i<n; i++)
            {

                if ((at[i] <= st) && (f[i] == 0) && (bt[i]<min))
                {
                    min=bt[i];
                    c=i;
                }
            }
            if (c==n)
                st++;
            else
            {
                ct[c]=st+bt[c];
                st+=bt[c];
                ta[c]=ct[c]-at[c];
                wt[c]=ta[c]-bt[c];
                f[c]=1;
                pid[tot] = c + 1;
                tot++;
            }
        }

        System.out.println("\npid  arrival brust  complete turn waiting");
        for(int i=0;i<n;i++)
        {
            avgwt+= wt[i];
            avgta+= ta[i];
            System.out.println(pid[i]+"\t\t"+at[i]+"\t\t"+bt[i]+"\t\t"+ct[i]+"\t\t"+ta[i]+"\t\t"+wt[i]);
        }
        System.out.println ("\naverage tat is "+ (float)(avgta/n));
        System.out.println ("average wt is "+ (float)(avgwt/n));
        sc.close();
        for(int i=0;i<n;i++)
        {
            System.out.print(pid[i] + " ");
        }
	
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
	
	
	
    public static void main(String args[]) {
    	SJFNPnPRIORITY f = new SJFNPnPRIORITY();
    	int ch;
    	
    	System.out.println("1.SJFNP \n 2.PRIORITY \n 3.EXIT \n");
    	System.out.println("Enter Choice:- ");
    	Scanner sc = new Scanner(System.in);
    	ch = sc.nextInt();
    	
    	while(ch!=3)
    	{
    		switch(ch) {
    		case 1 :   f.sjf();
    		break;
    		
    		case 2:f.priority();
    		break;
    		
    		}
    	}

    }
}
	


