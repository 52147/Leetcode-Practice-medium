/**	
 * 
 * 276. Paint Fence
 * 
 *  Approach 3: Bottom-Up, Constant Space
 *  
 *  
 *   Intuition:
 *   
 *    - You may have noticed that our recurrence relation from the previous 2 approaches only cares about 2 steps below the current step.
 *      - For example, if we are trying to calculate totalWays[11], we only care about totalWays[9] and totalWays[10].
 *    - While we would have needed to calculate totalWays[3] through totalWays[8] as well, at the time of the actual calculation for totalWays[11],
 *      we no longer care about of the previous steps.
 *    - Therefore, instead of using O(n) space to store an array, we can improve to O(1) space by using 2 variables to store the results from the last 2 steps.
 *    
 *  Algorithm:
 *  
 *    - 1. 
 *      - Initialize 2 variables, twoPostBack and onePostBack, that represent the number of ways to paint the previous 2 posts.
 *      - Since we start iteration from post 3, twoPostBack initially represents the number of ways to paint the one post,
 *        and onePostBack initially represents the number of ways to paint 2 posts.
 *      
 *      - Set their values twoPostBack = k, onePostBack = k*k, because they are equivalent to our base cases.
 *      
 *    - 2.
 *      - Iterate n-2 times.
 *      - At each iteration, simulate moving i up by one.
 *      - Use the recurrence relation to calculate the number of ways for the current step and store it int the variable curr.
 *      - "Moving up" means twoPostBack will now refer to onePostBack, so update twoPostBack = onePostBack.
 *      - onePostBack will now refer to the current step, so update onePostBack to curr.
 *      
 *    - 3.
 *      - In the end, return onePostBack, since "moving up" after the last step would mean onePostBack is the number of ways to paint n fence posts. 
 * 
 *
 */
		
public class PaintFence276_3 {
	
	public int numWays(int n, int k) {
		if(n == 1) return k;
		
		// set the 2 variable to represent the 2 previous way to paint post
		int twoPostBack = k;
		int onePostBack = k*k;
		
		// Iterate start from the 3 post
		for(int i = 3; i <= n; i++) {
			
			// Store the result of recurrence relation in the variable curr
			int curr = (k-1)*(onePostBack + twoPostBack);
			// advance the twoPostBack to the position of onePostBack
			twoPostBack = onePostBack;
			// advance the onePostBack to the position of curr
			onePostBack = curr;
			
		}
		
		// Return onePostBack since the last step of the iteration means onePostBack is the number of ways to paint posts.
		return onePostBack;
		
	}

}

/**
 * Complexity analysis:
 * 
 *  - Time complexity:
 *   
 *    - O(n)
 *    - We only iterate from 3 to n once, each time doing O(1) work.
 *    
 *  - Space complexity:
 *  
 *    - O(1)
 *    - The only extra space we use are a few integer variable, which re independent of input size. 
 */

