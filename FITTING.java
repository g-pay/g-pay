import java.io.*;

import java.util.*;

public class FITTING {

	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		int nb, np;

		System.out.println("Enter no. of blocks: ");
		nb = sc.nextInt();

		System.out.println("Enter no. of processes: ");
		np = sc.nextInt();
		int blockSize[] = new int[nb];

		System.out.println("Enter block sizes: ");
		for (int i = 0; i < nb; i++) {
			blockSize[i] = sc.nextInt();
		}
		int processSize[] = new int[np];
		System.out.println("Enter sizes of processes: ");
		for (int i = 0; i < np; i++) {
			processSize[i] = sc.nextInt();
		}

		while (true) {
			System.out.println("Menu:");
			System.out.println(
					"\n1.First Fit \n2.Best Fit \n3.WorstFit \n4.Next Fit\n5.Exit");
			System.out.println("Enter Your Choice:");
			int ch = sc.nextInt();
			switch (ch) {
			case 1:
				firstFit(blockSize, nb, processSize, np);
				break;
			case 2:
				bestFit(blockSize, nb, processSize, np);
				break;
			case 3:
				worstFit(blockSize, nb, processSize, np);
				break;
			case 4:
				nextFit(blockSize, nb, processSize, np);
				break;
			case 5:
				System.exit(0);
				break;
			}// end of swith
		} // enf of while
	}// end of main

	static void firstFit(int blockSize[], int m, int processSize[], int n) {
// Stores block id of the
// block allocated to a process
		int allocation[] = new int[n];

// Initially no block is assigned to any process
		for (int i = 0; i < allocation.length; i++)
			allocation[i] = -1;

// pick each process and find suitable blocks
// according to its size ad assign to it
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (blockSize[j] >= processSize[i]) {
					// allocate block j to p[i] process
					allocation[i] = j;

					// Reduce available memory in this block.
					blockSize[j] -= processSize[i];

					break;
				}
			}
		}

		System.out.println("\nProcess No.\tProcess Size\tBlock no.");
		for (int i = 0; i < n; i++) {
			System.out.print(" " + (i + 1) + "\t\t" + processSize[i] + "\t\t");
			if (allocation[i] != -1)
				System.out.print(allocation[i] + 1);
			else
				System.out.print("Not Allocated");
			System.out.println();
		}
	}

	static void bestFit(int blockSize[], int m, int processSize[], int n) {
// Stores block id of the block allocated to a
// process
		int allocation[] = new int[n];

// Initially no block is assigned to any process
		for (int i = 0; i < allocation.length; i++)
			allocation[i] = -1;

// pick each process and find suitable blocks
// according to its size ad assign to it
		for (int i = 0; i < n; i++) {
// Find the best fit block for current process
			int bestIdx = -1;
			for (int j = 0; j < m; j++) {
				if (blockSize[j] >= processSize[i]) {
					if (bestIdx == -1)
						bestIdx = j;
					else if (blockSize[bestIdx] > blockSize[j])
						bestIdx = j;
				}
			}

// If we could find a block for current process
			if (bestIdx != -1) {
// allocate block j to p[i] process
				allocation[i] = bestIdx;

// Reduce available memory in this block.
				blockSize[bestIdx] -= processSize[i];
			}
		}

		System.out.println("\nProcess No.\tProcess Size\tBlock no.");
		for (int i = 0; i < n; i++) {
			System.out.print(" " + (i + 1) + "\t\t" + processSize[i] + "\t\t");
			if (allocation[i] != -1)
				System.out.print(allocation[i] + 1);
			else
				System.out.print("Not Allocated");
			System.out.println();
		}
	}

	static void worstFit(int blockSize[], int m, int processSize[],
			int n)
{
// Stores block id of the block allocated to a
// process
int allocation[] = new int[n];

// Initially no block is assigned to any process
for (int i = 0; i < allocation.length; i++)
allocation[i] = -1;

// pick each process and find suitable blocks
// according to its size ad assign to it
for (int i=0; i<n; i++)
{
// Find the best fit block for current process
int wstIdx = -1;
for (int j=0; j<m; j++)
{
if (blockSize[j] >= processSize[i])
{
if (wstIdx == -1)
wstIdx = j;
else if (blockSize[wstIdx] < blockSize[j])
wstIdx = j;
}
}

// If we could find a block for current process
if (wstIdx != -1)
{
// allocate block j to p[i] process
allocation[i] = wstIdx;

// Reduce available memory in this block.
blockSize[wstIdx] -= processSize[i];
}
}

System.out.println("\nProcess No.\tProcess Size\tBlock no.");
for (int i = 0; i < n; i++)
{
System.out.print(" " + (i+1) + "\t\t" + processSize[i] + "\t\t");
if (allocation[i] != -1)
System.out.print(allocation[i] + 1);
else
System.out.print("Not Allocated");
System.out.println();
}
}



	static void nextFit(int blockSize[], int m, int processSize[], int n) {
        // Stores block id of the block allocated to a
        // process
        int allocation[] = new int[n], j = 0;
 
        // Initially no block is assigned to any process
        Arrays.fill(allocation, -1);
 
        // pick each process and find suitable blocks
        // according to its size ad assign to it
        for (int i = 0; i < n; i++) {
 
            // Do not start from beginning
            int count =0;
            while (j < m) {
                count++;    //makes sure that for every process we traverse through entire array maximum once only.This avoids the problem of going into infinite loop if memory is not available
                if (blockSize[j] >= processSize[i]) {
 
                    // allocate block j to p[i] process
                    allocation[i] = j;
 
                    // Reduce available memory in this block.
                    blockSize[j] -= processSize[i];
 
                    break;
                }
 
                // mod m will help in traversing the blocks from
                // starting block after we reach the end.
                j = (j + 1) % m;
            }
        }
 
        System.out.print("\nProcess No.\tProcess Size\tBlock no.\n");
        for (int i = 0; i < n; i++) {
            System.out.print( i + 1 + "\t\t" + processSize[i]
                    + "\t\t");
            if (allocation[i] != -1) {
                System.out.print(allocation[i] + 1);
            } else {
                System.out.print("Not Allocated");
            }
            System.out.println("");
        }
    }
}
