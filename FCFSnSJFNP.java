
import java.util.Scanner;


public class FCFSnSJFNP {
	
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
	
	
    public static void main(String[] args) {
    	FCFSnSJFNP f = new FCFSnSJFNP();
    	int ch;
    	
    	System.out.println("1.FCFS \n 2.SJF\n 3.EXIT \n");
    	System.out.println("Enter Choice:- ");
    	Scanner sc = new Scanner(System.in);
    	ch = sc.nextInt();
    	
    	while(ch!=3)
    	{
    		switch(ch) {
    		case 1 :   f.fcfs();
    		break;
    		
    		case 2:f.sjf();
    		break;
    		
    		}
    	}

    }

}