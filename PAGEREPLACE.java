package single;


import java.util.*;
class FIFO
{
    public static void main(String args[]) {
    	int n;
    	Scanner sc = new Scanner(System.in);
    	System.out.println("Enter Total No. of Pages: ");
    	n= sc.nextInt();
        int pages[] = new int[n];
        int capacity = 3;
      
        Queue<Integer> queue = new LinkedList<>() ;
        HashSet<Integer> set = new HashSet<>(capacity);
        
        System.out.println("Enter pages: ");
        for(int i=0;i<n;i++) {
        	
        	pages[i] =  sc.nextInt();
        }
        int PF = 0;
        for (int i=0; i<n; i++){
            // Check If the memory holds fewer pages than capacity
            if (set.size() < capacity){
                // Push pages in the set until
                //  the size of set reaches its maximum capacity.
                if (!set.contains(pages[i])){
                    set.add(pages[i]);
                    // increment page fault
                    PF++;
                    // Insert the page into the queue
                    queue.add(pages[i]);
                }
            }
            else {
                //  If the current page is not already present in memory
                if (!set.contains(pages[i]))
                {
                    //Pop the topmost page from the queue and,
                    // Replace it with the current page
                    int val = queue.peek();
                    queue.poll();
                    set.remove(val);
                    set.add(pages[i]);
                    queue.add(pages[i]);
                    //Increment the page faults(PF) and return PF
                    PF++;
                }
            }
        }
        // Print the count of page faults
        System.out.println("The total number of page faults are: "+PF);
    }
}






package single;

import java.util.*;

public class LRU {
	int p[], n, fr[], m, fs[], index, k, l, flag1 = 0, flag2 = 0, pf = 0, framesize = 3, i, j;
	Scanner src = new Scanner(System.in);

	void read() {
		System.out.println("Enter Total No. of Pages :");
		n = src.nextInt();
		p = new int[n];
		System.out.println("Enter the Pages : ");
		for (int i = 0; i < n; i++)
			p[i] = src.nextInt();
		System.out.println("Enter the Number of frames : ");
		m = src.nextInt();
		fr = new int[m];
		fs = new int[m];
	}

	void display() {
		System.out.println("\n");
		for (i = 0; i < m; i++) {
			if (fr[i] == -1)
				System.out.println("[ ]");
			else
				System.out.println("[" + fr[i] + "]");
		}
	}

	void lru() {
		for (i = 0; i < m; i++) {
			fr[i] = -1;
		}
		for (j = 0; j < n; j++) {
			flag1 = 0;
			flag2 = 0;
			for (i = 0; i < m; i++) {
				if (fr[i] == p[j]) {
					flag1 = 1;
					flag2 = 1;
					break;
				}
			}
			if (flag1 == 0) {
				for (i = 0; i < m; i++) {
					if (fr[i] == -1) {
						fr[i] = p[j];
						flag2 = 1;
						break;
					}
				}
			}
			if (flag2 == 0) {
				for (i = 0; i < 3; i++)
					fs[i] = 0;
				for (k = j - 1, l = 1; l <= framesize - 1; l++, k--) {
					for (i = 0; i < 3; i++) {
						if (fr[i] == p[k])
							fs[i] = 1;
					}
				}
				for (i = 0; i < 3; i++) {
					if (fs[i] == 0)
						index = i;
				}
				fr[index] = p[j];
				pf++;
			}
			System.out.print("Page : " + p[j]);
			display();
		}
		System.out.println("\n Number of page fault:" + pf);
	}

	public static void main(String args[]) {
		LRU a = new LRU();
		a.read();
		a.lru();
		a.display();
	}
}





package single;

import java.util.*;

public class OPTIMAL {

	public static void main(String args[])

	{
		Scanner sc = new Scanner(System.in);

		int numberOfFrames, numberOfPages, flag1, flag2, flag3, i, j, k, pos = 0, max;

		int faults = 0;
		
		int temp[] = new int[10];

		System.out.println("Enter number of Frames: ");

		numberOfFrames = sc.nextInt();

		int frame[] = new int[numberOfFrames];

		System.out.println("Enter number of Pages: ");

		numberOfPages = sc.nextInt();

		int pages[] = new int[numberOfPages];

		System.out.println("Enter the pages: ");

		for (i = 0; i < numberOfPages; i++)

			pages[i] = sc.nextInt();

		for (i = 0; i < numberOfFrames; i++)

			frame[i] = -1;

		for (i = 0; i < numberOfPages; ++i) {

			flag1 = flag2 = 0;

			for (j = 0; j < numberOfFrames; ++j) {

				if (frame[j] == pages[i]) {

					flag1 = flag2 = 1;

					break;

				}

			}

			if (flag1 == 0) {

				for (j = 0; j < numberOfFrames; ++j) {

					if (frame[j] == -1) {

						faults++;

						frame[j] = pages[i];

						flag2 = 1;

						break;

					}

				}

			}

			if (flag2 == 0) {

				flag3 = 0;

				for (j = 0; j < numberOfFrames; ++j) {

					temp[j] = -1;

					for (k = i + 1; k < numberOfPages; ++k) {

						if (frame[j] == pages[k]) {

							temp[j] = k;

							break;

						}

					}

				}

				for (j = 0; j < numberOfFrames; ++j) {

					if (temp[j] == -1) {

						pos = j;

						flag3 = 1;

						break;

					}

				}

				if (flag3 == 0) {

					max = temp[0];

					pos = 0;

					for (j = 1; j < numberOfFrames; ++j) {

						if (temp[j] > max) {

							max = temp[j];

							pos = j;

						}

					}

				}

				frame[pos] = pages[i];

				faults++;

			}


			for (j = 0; j < numberOfFrames; ++j) {

				System.out.print("\t" + frame[j]);

			}

		}

		System.out.println("\n\nTotal Page Faults: " + faults);

	}
}
