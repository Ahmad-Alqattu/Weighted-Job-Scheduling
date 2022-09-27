package application;

import java.util.Arrays;
import java.util.Collections;
import java.util.Stack;

public class WeightedIntervalScheduling {
	 static int maxProfit = 0;
	/*
	 * A Binary Search based function to find the latest job (before current job)
	 * that doesn't conflict with current job. "index" is index of the current job.
	 * This function returns -1 if all projects before index conflict with it. The
	 * array projects[] is sorted in increasing order of finish time.
	 */
	static public int binarySearch(Project projects[], int index) {
		// Initialize 'lo' and 'hi' for Binary Search
		int lo = 0, hi = index - 1;
		System.out.println("+++++++++++++++++++");

		System.out.println("lo" + lo + " hi " + hi);

		// Perform binary Search iteratively
		while (lo <= hi) {
			int mid = (lo + hi) / 2;
			System.out.print("mid: " + mid);
			if (projects[mid].finish < projects[index].start) {
				System.out.println("f " + projects[mid].finish + "s " + projects[index].start);

				if (projects[mid + 1].finish < projects[index].start) {
					System.out.println("f " + projects[mid + 1].finish + "s " + projects[index].start);

					lo = mid + 1;
					System.out.print("mid::: " + mid);

				} else {
					// System.out.println(projects[mid].getId()+"mm "+projects[mid].getProfit()+"");
					// System.out.println(projects[index].getId()+"ms
					// "+projects[index].getProfit()+"");

					return mid;
				}
			} else
				hi = mid - 1;
		}

		return -1;
	}

	static int latestNonConflict(Project proj[], int i) {
		for (int j = i - 1; j >= 0; j--) {
			if (proj[j].finish < proj[i - 1].start)
				return j;
		}
		return -1;
	}

	// A recursive function that returns the maximum possible
	// profit from given array of jobs. The array of jobs must
	// be sorted according to finish time.
	Stack<Integer> findMaxProfit(Project proj[], int n)
	{
		Arrays.sort(proj, new ProjectComparator());

	    // Base case
		 int arry[] = new int[n]; 
	    // Find profit when current job is included
	    for (int i = 0; i<n; i++){
		     arry[i] = proj[i].profit;
		     System.out.println("gggg "+arry[i]);
	    }
	    for (int i = 1; i<n; i++){
	    for (int j =0; j<i; j++)
	    {
	        if (proj[j].finish < proj[i].start) {
	        	int inclProf =arry[j]+proj[i].profit;
	        	arry[i]= Math.max(inclProf,arry[i]);
			     System.out.println("oo "+arry[i]);

	        }
	
	    }

	    
	    }	
	     maxProfit = 0;
	    		for(int i = 1; i<n; i++) {
				     System.out.println("ggeegg "+maxProfit);
				     System.out.println("eegg "+arry[i]);

	    		    if (maxProfit < arry[i])
	    		        maxProfit = arry[i];
	    		}
	    		return FindingPerformedProject(proj,arry,maxProfit);


	}
	
	public static Stack<Integer>FindingPerformedProject(Project proj[], int t[],int max) {
		Stack<Integer> S = new Stack<Integer>();

		        
			     
		for (int i=t.length-1;  max > 0 && i>=0;i--) {
		     System.out.println("ii "+max);

		    if (max == t[i]||max == proj[i].profit) {
			     System.out.println("ll "+max);

		        S.push(proj[i].id);
		        max = max - proj[i].profit;
			     System.out.println("lgl "+proj[i].id);

	
		}
		}
		return S;
}

	static public Stack<Integer> schedule(Project projects[]) {
		// Sort projects according to finish time
		Arrays.sort(projects, new ProjectComparator());

		// Create an array to store solutions of subproblems.
		// table[i] stores the profit for projects till projects[i]
		// (including projects[i])
		int n = projects.length;
		int table[] = new int[n];
		table[0] = projects[0].profit;

		// Fill entries in M[] using recursive property
		for (int i = 1; i < n; i++) {
			// Find profit including the current job
			int inclProf = projects[i].profit;
			int l = binarySearch(projects, i);
			// System.out.println(i+"f "+inclProf);
			int F = 0;
			if (l != -1) {
				inclProf += table[l];
				F = 1;
			}
			// Store maximum of including and excluding
			if (inclProf > table[i - 1] && F == 1) {
		//		System.out.println(l + " " + projects[l].getId() + "l " + projects[l].getProfit());
			//	System.out.println(i + "" + projects[i].getId() + "i " + projects[i].getProfit());
			}
			// System.out.println(projects[i].getId()+" --"+table[i-1]);
			table[i] = Math.max(inclProf, table[i - 1]);

			// System.out.println(i+" "+table[i]);

		}
		maxProfit=table[n -1];
		return FindingPerformedProject(projects,table,maxProfit);
		 
	}

	
	
	
	
	
//	// The main function that returns the maximum possible
//	// profit from given array of projects
//	static public int schedules(Project projects[]) {
//		// Sort projects according to finish time
//		Arrays.sort(projects, new ProjectComparator());
//
//		// Create an array to store solutions of subproblems.
//		// table[i] stores the profit for projects till projects[i]
//		// (including projects[i])
//		int n = projects.length;
//		int table[] = new int[n];
//		table[0] = projects[0].profit;
//
//		// Fill entries in M[] using recursive property
//		byte position[][] = new byte[projects.length][n + 1];
//
//		// set position and fined maximum number of LEDs are lighted
//		int temp[][] = new int[n][n + 1];
//		int max = 0;
//		for (int i = 1; i < n; i++) {
//			temp[i][0] = 0;
//		}
//		for (int j = 1; j <= n; j++) {
//			temp[0][j] = 0;
//		}
//		System.out.print("   ");
//
//		for (int i = 1; i < n; i++) {
//			System.out.print(projects[i].id + "   |");
//		}
//		System.out.println();
//
//		for (int i = 1; i < n; i++) {
//			System.out.print(projects[i].id + " |");
//			for (int j = 1; j < n + 1; j++) {
//
//				if (projects[i].profit == j) {
//					temp[i][j] = temp[i - 1][j - 1] + 1;
//					position[i][j] = 1;
//					System.out.print(temp[i][j] + " " + tochar(position[i][j]) + " |");
//				} else if (temp[i][j - 1] > temp[i - 1][j]) {
//					temp[i][j] = temp[i][j - 1];
//					position[i][j] = 0;
//					System.out.print(temp[i][j] + " " + tochar(position[i][j]) + " |");
//
//				} else {
//					temp[i][j] = temp[i - 1][j];
//					position[i][j] = 2;
//					System.out.print(temp[i][j] + " " + tochar(position[i][j]) + " |");
//				}
//				if (temp[i][j] > max) {
//					max = temp[i][j];
//				}
//
//			}
//			System.out.println();
//
//		}
//
//		return table[n - 1];
//	}
//
//	static String tochar(byte c) {
//		if (c == 2)
//			return "\u2191";
//		else if (c == 1)
//			return "\u2196";
//		else if (c == 0)
//			return "\u2190";
//
//		return "\2191";
//
//	}
}
