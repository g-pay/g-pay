
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
