import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

 
public class SJFPnRR {

	void sjfp() throws NumberFormatException, IOException {
		  BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	      int n;
	      System.out.println("Please enter the number of Processes: ");
	       n = Integer.parseInt(br.readLine());
	       int proc[][] = new int[n + 1][4];//proc[][0] is the AT array,[][1] - RT,[][2] - WT,[][3] - TT
	       for(int i = 1; i <= n; i++)
	       {
	      System.out.println("Please enter the Arrival Time for Process " + i + ": ");
	      proc[i][0] = Integer.parseInt(br.readLine());
	      System.out.println("Please enter the Burst Time for Process " + i + ": ");
	      proc[i][1] = Integer.parseInt(br.readLine());
	     }
	       System.out.println();
	     
	       //Calculation of Total Time and Initialization of Time Chart array
	     int total_time = 0;
	     for(int i = 1; i <= n; i++)
	     {
	      total_time += proc[i][1];
	     }
	     int time_chart[] = new int[total_time];
	     
	     for(int i = 0; i < total_time; i++)
	     {
	      //Selection of shortest process which has arrived
	      int sel_proc = 0;
	      int min = 99999;
	      for(int j = 1; j <= n; j++)
	      {
	       if(proc[j][0] <= i)//Condition to check if Process has arrived
	       {
	        if(proc[j][1] < min && proc[j][1] != 0)
	        {
	         min = proc[j][1];
	         sel_proc = j;
	        }
	       }
	      }
	      
	      //Assign selected process to current time in the Chart
	      time_chart[i] = sel_proc;
	      
	      //Decrement Remaining Time of selected process by 1 since it has been assigned the CPU for 1 unit of time
	      proc[sel_proc][1]--;
	      
	      //WT and TT Calculation
	      for(int j = 1; j <= n; j++)
	      {
	       if(proc[j][0] <= i)
	       {
	        if(proc[j][1] != 0)
	        {
	         proc[j][3]++;//If process has arrived and it has not already completed execution its TT is incremented by 1
	            if(j != sel_proc)//If the process has not been currently assigned the CPU and has arrived its WT is incremented by 1
	             proc[j][2]++;
	        }
	        else if(j == sel_proc)//This is a special case in which the process has been assigned CPU and has completed its execution
	         proc[j][3]++;
	       }
	      }
	      
	      //Printing the Time Chart
	      if(i != 0)
	      {
	       if(sel_proc != time_chart[i - 1])
	        //If the CPU has been assigned to a different Process we need to print the current value of time and the name of 
	        //the new Process
	       {
	        System.out.print("--" + i + "--P" + sel_proc);
	       }
	      }
	      else//If the current time is 0 i.e the printing has just started we need to print the name of the First selected Process
	       System.out.print(i + "--P" + sel_proc);
	      if(i == total_time - 1)//All the process names have been printed now we have to print the time at which execution ends
	       System.out.print("--" + (i + 1));
	      
	     }
	     System.out.println();
	     System.out.println();
	     
	     //Printing the WT and TT for each Process
	     System.out.println("P\t WT \t TT ");
	     for(int i = 1; i <= n; i++)
	     {
	      System.out.printf("%d\t%2dms\t%2dms",i,proc[i][2],proc[i][3]);
	      System.out.println();
	     }
	     
	     System.out.println();
	     
	     //Printing the average WT & TT
	     float WT = 0,TT = 0;
	     for(int i = 1; i <= n; i++)
	     {
	      WT += proc[i][2];
	      TT += proc[i][3];
	     }
	     WT /= n;
	     TT /= n;
	     System.out.println("The Average WT is: " + WT + "ms");
	     System.out.println("The Average TT is: " + TT + "ms");
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

	public static void main(String args[]) throws NumberFormatException, IOException {
		SJFPnRR f = new SJFPnRR();
		int ch;

		System.out.println("1.SJFP \n 2.Round Robin\n 3.EXIT \n");
		System.out.println("Enter Choice:- ");
		Scanner sc = new Scanner(System.in);
		ch = sc.nextInt();

		while (ch != 3) {
			switch (ch) {
			case 1:
				f.sjfp();
				break;

			case 2:
				f.rr();
				break;

			}
		}

	}
}
