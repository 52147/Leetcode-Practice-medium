/**
 * 
 * 276. Paint Fence
 * 
 *  Approach 2: Bottom-Up Dynamic Programming (Tabulation)
 *  
 *  
 *  Intuition:
 *  
 *   - Bottom-up dynamic programming is also know as tabulation and is done iteratively.
 *   - Instead of using a function like top-down, 
 *     let's use an array totalWays instead, where totalWays[i] represents the number of ways you can paint i fence posts.
 *   
 *   - As the name suggests, we now start at the bottom and work our way up to the top(n).
 *   - Initialize the base cases totalWays[1] = k, totalWays[2] = k*k, and then iterate from 3 to n, 
 *     using the recurrence relation to populate totalWays.
 *       
 *   - Bottom-up algorithms are generally considered superior to top-down algorithms.
 *   - Typically, a top-down implementation will use more space and take longer than the equivalent bottom-up approach.
 *   
 *  Algorithm:
 *  
 *   - 1. 
 *     - Define an array totalWays of length n+1, where totalWays[i] represents the number of ways you can paint i fence posts.
 *     - Initialize totalWays[1] = k and totalWays[2] = k*k.
 *     
 *   - 2.
 *     - Iterate from 3 to n, using the recurrence relation to populate totalWays: totalWays[i] = (k-1) * (totalWays[i-1] + totalWays[i-2]).
 *     
 *   - 3.
 *     - At the end, return totalWays[n].  
 * 
 *
 */
public class PaintFence276_2 {
	
	public int numWays(int n, int k) {
		
		
		// Base cases for the problem to avoid index out of bound issues.
		if(n == 1) return k;
		if(n == 2) return k*k;
		
		// Create a Array to represent  the number of ways to paint posts.
		// the length will be n+1, because we start the index from 1~n
		int totalWays[] = new int[n+1]; 
		
		totalWays[1] = k;
		totalWays[2] = k*k;
		
		// Iterative the recurrence relation and put each answer in the array
		for(int i = 3 ; i <= n; i++) {
			totalWays[i] =(k-1)*(totalWays[i-1] + totalWays[i-2]);
		}
		
		// Return the result of index n (posts) in toatalWays array.
		return totalWays[n];
		
		
	}	

}

/**
 * Complexity Analysis:
 * 
 *  - Time complexity:
 *  
 *    - O(n)
 *    - We only iterative from 3 to n once, where each iterative requires O(1) time.
 *   
 *  - Space complexity:
 *  
 *    - O(n)
 *    - We need to use an array "totalWays", where totalWays.length scales linearly with b.  
 *    
 */
