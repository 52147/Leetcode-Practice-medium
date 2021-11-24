/**
 * 
 * 91. Decode Ways 
 * 
 *  Approach 2: Iterative Approach
 *  
 *   - The iterative approach might be a little bit less intuitive.
 *   - We use an array for DP to store the results for subproblems.
 *   - A cell with index i of the dp array is used to store the number of decode ways for substring of s from index 0 to index i-1.
 *   
 *   - We initialize the starting 2 indices of the dp array.
 *   - It's similar to relay race where the first runner is given a baton to be passes to the subsequent runners.
 *   - The first 2 indices of the dp array hold a baton.
 *   - As we iterate the dp array from left to right this baton 
 *     which signifies the number of ways of decoding is passed to the next index or not depending on whether the decode is possible.
 *  
 *  
 *  - Dynamic programming(DP): Dynamic programming solves subproblems non recursively by recording answers in a table.
 *  
 *  - dp[i] can get the baton from 2 other previous indices, either i-1 or i-2.
 *  - 2 previous indices are involved since both single and 2 digit decodes are possible.
 *  
 *  - Unlike the relay race we don't get only one baton in the end.
 *  - The batons add up as we pass on.
 *  - If someone has one baton, they can provide a copy of it to everyone who comes to them with a success.
 *  - Thus, leading to number of ways of reaching the end.
 *  
 *  - DP Array:
 *  
 *    s = "326"
 *    
 *   1.
 *    
 *    - Initialize the DP array.    
 *    | 1 | 1 |  |  |
 *          v  v  v
 *         (3)(2)(6)
 *         
 *   2.
 *         
 *   - Finding the number ways of decoding the substring "32" what we would store in DP[i]
 *             i       
 *    | 1 | 1 |  |  |
 *          v  v  v
 *         (3)(2)(6)         
 *    
 *   3.
 *    
 *    - Single Digit Decode:
 *      - "2" is a valid single digit decode.
 *      - Thus whatever is in DP[i-1] we add to DP[i].
 *      - This means if there are N ways of reaching DP[i-1].
 *      - Now those N ways also lead to DP[i]
 *          --> +1    
 *    | 1 | 1 | 1 |  | 
 *          v   v   v
 *         (3) (2) (6)
 *         
 *         
 *    4.
 *    
 *    - Double Digit Decode:
 *      - "32" is not valid decode.
 *      - Thus the decode paths that lead to DP[i-2] do not lead to DP[i].
 *      - This is an example of how the baton at DP[i-2] is not passed on to DP[i].
 *      
 *          
 *      ------>+1(x)  
 *    | 1 | 1 | 1 |  |     
 *          v   v   v
 *              x
 *          (3  2)  6
 *          
 *  - dp[i] = Number of ways of decoding substring s[:i].
 *  - So we might say dp[i] = dp[i-1] + dp[i-2], which is not always true for this decode ways problem.
 *  - As shown in the above diagram, only when the decode is possible we add the results of the previous indices.
 *  - Thus, in this race we don't just pass the baton.
 *  - The baton is passed to the next index or not depending on possibility of the decode.
 *  
 *           
 *           
 *  Algorithm:
 *  
 *  - 1. If the string s is empty or null we return the result as 0.
 *  
 *  - 2. Initialize dp array. dp[0] = 1 to provide the baton to be passed.
 *  
 *  - 3. If the first character of the string is 0 then no decode is possible hence initialize dp[1] to 0,
 *       otherwise the first character is valid to pass on the baton, dp[1] = 1.
 *       
 *  - 4. Iterate the dp array starting at index 2.
 *       The index i of dp is the i -th character of the string s, that is character at index i-1 of s.
 *       
 *  - 5. 
 *      - We check if valid single digit decode is possible.
 *      - This just means the character at index s[i-1] is non-zero.
 *      - Since we do not have a decoding for 0.
 *      - If the valid single digit decoding is possible then we add dp[i-1] to dp[i].
 *      - Since all the ways up to (i-1)-th character now lead up to i-th character too.
 *  
 *  - 6.
 *      - We check if valid 2 digit decode is possible.
 *      - This means the substring s[i-2] s[i-1] is between 10 to 26.
 *      - If the valid 2 digit decoding is possible then we add dp[i-2] to dp[i].
 *      
 *  - 7. Once we reach the end of the dp array we would have the number of ways of decoding string s. *      
 *       
 *          
 */
public class DecodeWays91_2 {
	
	public int numDecodings(String s) {
		// Create a DP array to store the subproblem results
		int[] dp = new int[s.length() + 1]; // n+1 to include that empty string possibility
		dp[0] = 1;
		
		// Ways to decode a string of size 1 is 1.
		// Unless the string is '0'.
		// '0' doesn't have a single digit decode.
		// this can prevent the index is out of bounds because there is at least 1 item in the string.
		
		// this is equal to below if statement
		/**
		 * if (s.charAt(0) == '0'){
		 *  return 0;
		 * }
		 */
		dp[1] = s.charAt(0) == '0'? 0 : 1; // DP at 1 represents the first character in the string
		
		
		// Iterate through the string
		// i = 2 ->  the second character in the string
		for (int i = 2 ; i < dp.length; i++) {
			// Check if successful single digit decode is possible.
			// if the current item at i is not 0, then we can add it to the solution before me.
			if(s.charAt(i-1) != '0') {
				dp[i] = dp[i-1];
			}
			
			// Check if successful 2 digit decode is possible.
			int twoDigit = Integer.valueOf(s.substring(i-2, i));
			if (twoDigit >= 10 && twoDigit <= 26) {
				dp[i] += dp [i-2];
			}
			
		}
		
		return dp[s.length()];
		
		
	}

}

/**
 *  Complexity Analysis:
 *   
 *   - Time Complexity:
 *     - O(N)
 *     - where N is length of the string.
 *     - We iterate the length of dp array which is N+1
 *     
 *   - Space Complexity: 
 *     - O(N)
 *     - The length of the DP array. 
 *     
 *  
 */






