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
