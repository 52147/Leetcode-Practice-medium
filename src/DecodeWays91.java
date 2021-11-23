import java.util.Map;

/**
 * 91. Decode Ways
 * 
 * Q:
 *  - A message containing letters from A-Z can be encoded into numbers using the following mapping:
 *    
 *    - 'A' -> "1"
 *    - 'B' -> "2"
 *    ...
 *    - 'Z' -> "26"
 *    
 *   - To decode an encode message, all the digits must be grouped then mapped back into letters
 *     using the reverse of the mapping above (there may be multiple ways).
 *     - For example:
 *       "11106" can be mapped into:
 *       
 *       - "AAJF" with the grouping (1 1 10 6)
 *       - "KJF" with the grouping (11 10 6)
 *   
 *   - Noter that the grouping (1 11 06) is invalid because "06" can not be mapped into 'F' since "6" is different from "06".
 *   - Given a string s containing only digits, return the number of way to decode it.
 *   
 *   - The answer is guaranteed to fit in a 32-bit integer.
 *   
 * Example 1:
 * 
 *  Input: s = "12"
 *  Output: 2
 *  Explanation: "12" could be decoded as "AB" (1 2) or "L" (12.).
 *  
 * Example 2:
 * 
 *  Input: s = "226"
 *  Output: 3
 *  Explanation: "226" could be decoded as "BZ" (2 26), "VF" (22 6), or "BBF" (2 2 6).
 *  
 * Example 3:
 * 
 *  Input: s = "0"
 *  Output: 0
 *  Explanation: 
 *   - There is no character that is mapped to a number starting with 0.
 *   - The only valid mappings with 0 are 'J' -> "10" and 'T' -> "20", neither of which start with 0.
 *   - Hence, there are no valid ways to decode this since all digits need to be mapped.
 * 
 * Example 4:
 * 
 *   - Input: s = "06"
 *   - Output: 0
 *   - Explanation: "06" cannot be mapped to "F" because of the leading zero("6 is different from 06").
 *   
 * Constraints:
 * 
 *   - 1 <= s.length <= 100
 *   - s contains only digits and may contain leading zero(s).
 *   
 *   
 * Solution:
 *   
 *   - The most important point to understand in this problem is that at any given step when you are trying to decode a string of numbers
 *     it can either be a single digit decode e.g. 1 to A or a double digit decode e.g. 25 to Y.
 *     
 *   - As long as it's a valid decoding we move ahead to decode the rest of the string.
 *   
 *   - The subproblem could be thought of as number of ways decoding a substring.
 *   
 *   For example:
 *   s = "326"
 *   
 *   3 -> C
 *   2 -> B
 *   6 -> F
 *   
 *   - We did a single digit decode for all the digits.
 *   - Digits 1-9 can be mapped to from A-I.
 *   - Also keep in my zero '0' is also a single digit and it doesn't have any mapping by itself.
 *   
 *   32 -> x
 *   There is no mapping for "32", since double digit mapping are only between 11-26
 *   
 *   3 -> C
 *   26 -> Z
 *   
 *   The above diagram shows string "326" could be decoded into 2 ways.
 *   
 *   
 * Approach 1: Recursive Approach with Memoization  
 * 
 * 
 *  Intuition:
 *  
 *  - The problem deals with finding number of way of decoding a string.
 *  - What helps to crack the problem is to think why there would be many ways to decode a string.
 *  - The reason is simple since at any given point we either decode using 2 digits or single digit.
 *  - This choice while decoding can lead to different combinations.
 *  
 *  - If single digit decoding was allowed:
 *    - "326"
 *    - 3 -> C
 *    - 2 -> B
 *    - 6 -> F
 *    
 *   - If there was just single digit decode then there is only one choice to make each step.
 *    
 *    - 32 -> x
 *     - if no double digit decoding is allowed
 *    - 26 -> x
 *     - if no double digit decoding is allowed
 *     
 *   - If double digit decoding is also allowed:
 *     
 *     -3 -> C
 *      - When 2 digit decode is allowed, after decoding "3" we have 2 choice.
 *      - We can either decode "2" to "B" or "26" to "Z"
 *        
 *     -> 
 *      - This problem allows both single and 2 digit decode.
 *      - Thus, at every step we have 2 choices to make.
 *        1. Single digit decode
 *        2. 2 digit decode
 *        - This lead to more than one way of decoding a string.
 *        
 *    
 *  - Thus at any given time for a string we enter a recursion 
 *    after successfully decoding 2 digits to a single character or a single digit to a character.
 *  - This leads to multiple paths to decoding the entire string.
 *  - If a given paths leads to the end of the string this means we could successfully decode the string.
 *  - If at any point in the traversal we encounter digits which cannot be decoded, we backtrack from that path.
 *  
 *  326 -> 3 -> 2 -> 6 (V) 
 *           -> 26 (v)
 *           A successful path means successful decode. Thus there are 2 paths and hence 2 ways to decode "326".  
 *      
 *      -> 32 (x) "32" can not be mapped to a character. Only digits which lie in the range of 1 to 26 have a mapping.
 *      
 *  - In the following diagram we can see how the paths have to deal with similar subproblems.
 *  - Overlapping subproblems means we can reuse the answer.
 *  - Thus, we do memoization to solve this problem.
 *  
 *    2326 ->2 ->3 ->2 ->6 (v)
 *                 ->26(v)
 *             ->32(x)
 *             
 *        ->23 ->2 ->6 (v)
 *             ->26(v)
 *             
 *     Overlapping sub-problems:
 *              
 *      ->2 ->6 (v)
 *      ->26(v)
 *  
 *    - You may have noticed there are 4 successful paths and hence ways to decode string "2326".
 *    - What we also need to pay attention to is the re-work that is shown inside the 2 triangle markings.
 *    - After "23" was decoded both the triangles had to decode the same string i.e. "26". 
 *    
 *       
 * Algorithm:
 * 
 *  - 1. Enter recursion with the given string i.e. start with index 0.
 *  
 *  - 2. For the terminating case of the recursion we check for the end of the string.
 *  
 *  - 3. Every time we enter recursion it's for a substring of the original string.
 *       For any recursion if the first character is 0 then terminate that path by returning 0.
 *       Thus this path won't contribute to the number of ways.
 *       
 *  - 4. Memoization helps to reduce the complexity which would otherwise be exponential.    
 *       We check the dictionary memo to see if the result for the given substring already exists.
 *       
 *  - 5. If the result is already in memo we return the result.
 *       Otherwise the number of ways for the given string is determined by making a recursive
 *       call to the function with index + 1 for next substring string and index + 2 after checking for valid 2-digit decode.
 *       The result is always stored in memo with key as current index, for saving for future overlapping subproblems.      
 *       
 *       
 *     
 * 
 *
 */
public class DecodeWays91 {
	
	// Use HashMap to solve the overlapping problem.
	Map<Integer, Integer> memo = new HashMap<>();
	
	public int numDecodings(String s) {
		return recursiveWithMemo(0,s); // 0 is the starting index because that's the first index of the string.
	}
	
	private int recursiveWithMemo(int index, String str) {
		// Have we already seen this substring
		if(memo.containsKey(index)) {
			return memo.get(index); // return the answer I've already computed.
		}
		
		// If you reach the end of the string
		// Return 1 for success.
		if(index == str.length()) {
			return 1;
		}
		
		
		// If the string starts with a zero, it can't be decode
		if(str.charAt(index)== '0') {
			return 0;
		}
		
		//if we are at the last value of the array which is the length-1, so we don't need to worry about going out of the bounds.
		if (index == str.length()-1) {
			return 1;
		}
		
		// Recursive part:
		// create a variable call answer
		int ans = recursiveWithMemo(index+1 , str);
		
		// If we can use two letters as a decoding, we can use Integer.parseInt to 
		// take the substring from index we are currently looking at to 2 indices ahead. It's an exclusive range.
		if(Integer.parseInt(str.substring(index, index+2)) <= 26) {
			ans += recursiveWithMemo(index+2, str); // We have 2 digits encoding to use, so we can add the recursion to call the answer
		}
		
		// Save for memoization
		memo.put(index, ans);
		return ans;
		
	}
	
	
	

}

/**
 *  Complexity Analysis:
 *  
 *   -Time complexity:
 *    - O(N)
 *    - where N is length of the string.
 *    - Memoizatiom helps in pruning the recursion tree and hence decoding for an index only once.
 *    - Thus this solution is linear time complexity.
 *    
 *   - Space complexity:
 *     - O(N)
 *     - The dictionary used for memoization would take the space equal to the length of the string.
 *     - There would be an entry for index value.
 *     - The recursion stack would also be equal to the length of the string.
 *     
 */



