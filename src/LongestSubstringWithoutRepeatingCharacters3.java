/**
 *  3. Longest Substring without Repeating Characters
 * 
 * Q:
 * Given a string s, find the length of the longest substring without repeating characters.
 * 
 * example 1:
 * 
 * - input s = "abcabcbb"
 * - output: 3
 * - explanation: The answer is "abc", with the length of 3.
 * 
 * Constraint :
 * 
 * - 0 <= s.length <=5*10^4
 * - s = consists of English letters, digits, symbols and spaces.
 * 
 * 
 * Solution:
 * 
 * Approach 1 : Brute Force (time limit exceeded) 
 * 
 *  
 * Intuition :
 * 
 *  - check all the substring one by one to see if it has no duplicate character.
 *  
 *
 * Algorithm:
 * 
 * 
 * -for example:
 * 
 *    s = "abcabcbb"
 *    
 *    abca
 *    
 *    - we can start from the character "a"
 *      and we forward to check all the substrings starting from it.
 *    - then we move forward to the second character.
 *    
 *    bca
 *    
 *    - and enumerate all the substrings starting from this character "b"
 *      
 *    ca
 *    
 *    - after this, we can move forward to next character "c"
 *      and keep enumerating the substrings, so on and so forth.
 *      
 *      
 * 
 * - Suppose we have a function boolean allUnique(String substring) which will return true if the characters in the substring are all unique, otherwise false.
 * - we can iterate through all the possible substrings of the given string s and call the function allUnique.
 * - If it turns out to be true, then we update our answer of the maximum length of substring without duplicate characters.
 * 
 *  1. enumerating method:
 * 
 *   - To enumerate all substrings of a given string, we enumerate the start and end indices of them.
 *   - Suppose the start and end indices are i and j.
 *   - Then we have 0<= i <= j <= n (here end index j is exclusive by convention).
 *   - Thus, using 2 nested loops with i from 0 to n-1 and j from i+1 to n, we can enumerate all the substrings of s.
 *  
 *  2. check Repetition method :   
 *   - To check if one string has duplicate characters, we can use a set.
 *   - we iterate through all the characters in the string and put then into the set one by one.
 *   - Before putting one character, we check of the set already contains it.
 *   - If so, we return false.
 *   - After the loop, we return true.       
 * 
 * - Direct access/ addressing table:
 *  - maps records with their corresponding unique keys using Arrays.
 *  - the key of records are used directly as indexes. 
 * 
 * 
 */
public class LongestSubstringWithoutRepeatingCharacters3 {
	
	public int lengthOfLongestSubstring(String s) {
		// s consists of English letters, digits, symbols and spaces.
		int n = s.length();
		
		int result= 0;
		
		//  use nested for loop to Enumerate all the substrings
		for (int i = 0; i < n; i++) {
			for (int j =i ; j < n ; j++) {
				
				// Check the substring and update the result
				if (checkRepition(s, i, j))
					result = Math.max(result,j - i + 1);
			}
		}
		return result;
	}
	
	private boolean checkRepition(String s, int start, int end) {
		// Declare a direct access table to record the occurrence of each character.
		int [] chars = new int [128];
		
		// all the characters can be mapped to an integer index.
	    // for example : the capital letter A is mapped to 65.
		for (int i = start; i <= end ; i++) {
			char c = s.charAt(i);
			chars[c]++;
			if(chars[c]>1) { 
				return false;
			}
		}
			
		return true;
		}
	}


/**
 * Time complexity:
 * 
 * - nested for loop : O(n^2)
 * - checkRepition method : O(n)
 * 
 * -> O(n^3) -> time limit exceeded
 *   - n is the length of the input string
 *   
 * Space complexity :
 * 
 * - we use a direct access table to check the substrings.
 * 
 * -> O(m) 
 *   - where m is the size of the table, which depends on the size of the porblem's charset. 
 *
 */

