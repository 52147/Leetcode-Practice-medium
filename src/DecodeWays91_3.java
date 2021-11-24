/**
 * 
 * 91. Decode Ways
 * 
 * Approach 3: Iterative, Constant Space
 * 
 *  Intuition:
 *  
 *   - In Approach 2 we are using an array dp to save the results for future.
 *   - As we move ahead character by character of the given string, we look back only 2 steps.
 *   - For calculating dp[i] we need to know dp[i-1] and dp[i-2] only.
 *   - Thus, we can easily cut down our O(N) space requirement to O(1) by using only 2 variables to store the last 2 results.
 *   
 *
 */
public class DecodeWays91_3 {
	
	public int numDecodings(String s) {
		if (s.charAt(0) == '0') {
			return 0;
		}
		
		int n = s.length();
		int twoBack = 1;
		int oneBack = 1;
		for (int i = 1; i<n; i++) {
			int current = 0; // create a current variable
			
			if(s.charAt(i)!= '0') {
				current = oneBack; 
				// current will go up by oneBack 
				// if we can append this digit we are at to the one behind me.
			}
			int twoDigit = Integer.parseInt(s.substring(i-1, i+1));
			
			if(twoDigit>= 10 && twoDigit <= 26) {
				current += twoBack; // current will go up by twoBack if we can use 2 digit value as the encoding.
			}
			
			
			// Update the pointers			
			twoBack = oneBack; // Whatever onBack is will be the new value of twoBack
			oneBack = current; // Whatever current is will be the value of oneBack
		}
		
		return oneBack;
	}

}

/**
 * Complexity Analysis:
 * 
 *  - Time Complexity: 
 *    - O(N)  -> for loop
 *    - where N is length of the string.
 *    - We're essentially doing the same work as what were in Approach 2, 
 *      except this time we're throwing away calculation results when we no longer need them.
 *      
 *  - Space Complexity:
 *    - O(1) -> 2 constant variable
 *    - Instead of a dp array, we're simply using 2 variables.    
 * 
 */



