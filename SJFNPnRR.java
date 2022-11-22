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

  
	
	
    void rr() {Scanner sc = new Scanner(System.in);
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
	


