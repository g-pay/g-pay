import java.util.Scanner;

public class SJFNPnRR{
	
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
	
	
	
    public static void main(String args[]) {
    	SJFNPnRR f = new SJFNPnRR();
    	int ch;
    	
    	System.out.println("1.SJFNP \n 2.Round Robin\n 3.EXIT \n");
    	System.out.println("Enter Choice:- ");
    	Scanner sc = new Scanner(System.in);
    	ch = sc.nextInt();
    	
    	while(ch!=3)
    	{
    		switch(ch) {
    		case 1 :   f.sjf();
    		break;
    		
    		case 2:f.rr();
    		break;
    		
    		}
    	}

    }
}
	


