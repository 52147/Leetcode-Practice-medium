/**
 *  3. Longest substring without repeating characters
 *  
 *  Sol:
 *  
 *  Approach 2:
 *  
 *  Sliding window :
 *  
 *  - the brute force approach is very straightforward.
 *  - But it is too slow.
 *  - We need to optimize it.
 * 
 *  - In the brute force approach , we repeatedly check a substring to see if it has a duplicate character.
 *  - But it is unnecessary.
 *  - If a substring sij from index i to i-1 is already checked to have no duplicate characters.
 *  - we only need to check if s[j] is already in the substring sij.
 *  - to check if a character is already in the substring, we can scan the substring, which leads to an O(n^2) algorithm.
 *  - but we can do better.
 *  
 *  - By using HashSet as a sliding window, checking if a character in the current can be done in O(1).
 *  - A sliding window is an abstract concept commonly used in array/string problem.
 *  - A window is a range of elements in the array/string which usually defined by the start and end indices, 
 *  - i.e.[i,j) (left-closed, right-open).
 *  - A sliding window is a window "slides" its two boundaries to the certain direction.
 *  - for example, if we slide [i,j) to the right by 1 element, then it becomes [i+1,j+1) (left-closed, right-open).
 *  
 *  
 *   - we use HashSet to store the characters in current window [i,j)(j = i initially).
 *   - Then we slide the index j to the right.
 *   - if it is not in the HashSet, we slide j further.
 *   - Doing so until s[j] is already in the HashSet.
 *   - At this point, we found the maximum size of substrings without duplicate characters start with index i.
 *   - if we do this for all i, we get our answer. 
 * 
 * 
 *  for example:
 *  
 *   s = "abcabcbb"
 *   
 *   abc
 *     - we are enumerating the substrings starting from the first character a.
 *     - and we know that the current substring "abc" does not have the repeating characters
 *     
 *   abca  
 *     - then we move forward to the next substring "abca"
 *     - and we know that it has duplicate character "a"
 *     - we need to further enumerating the rest of the substrings starting with the character "a",
 *       but because  "abca" already has duplicate characters
 *     - we do not need to check the rest of the substrings any more.
 *     
 *   bca
 *     - and we can directly move forward to enumerate the substring starting from the second character "b"
 *     - and because previously when enumerating the substrings starting from the first character "a"
 *     - we have already checked the "b" and "bc" parts and we know that they did not have duplicate characters.
 *     - so we do not need to check there parts again and we can directly check the substring "bca"
 *       
 *     - and if it has duplicate characters
 *     - we do not need to further check the rest of the substrings starting with "b"
 *     - and we can just begin to enumerate the substring starting with the third character
 *     
 *    bcab   
 *     - in this case , the substring "bca" does not have the duplicate characters
 *     - so we can further check the next substring start with "b" so on and so forth.
 *       
 *
 */
public class LongestSubstringWithoutRepeatingCharacters3_1 {
	
	public int lengthOfLongestSubstring(String s) {
		// declare a direct access table to record how many times each character appears in the sliding window.
		// so we declare the table as an int array.
		// and all the characters can be encoded into integers.
		// The size of the table is set to 128
		// which can cover all the possible characters
		int[] chars = new int [128];
		
		// we use a left pointer to contract the window
		int left = 0;
		// and a right pointer to extend the window
		// and initially, these pointer are all set to 0.
		int right = 0;
		
		// we use a variable to check our final result.
		int res = 0;
		
		// Sliding window:
		// if the right boundary does not reach s.length, we can keep extending the window.
		while (right <s.length()) {
			
			// we firstly obtain the character at the right boundary.
			char r = s.charAt(right);
			// and we can add one more time for this character's occurrence.
			chars[r]++;
			
			// contracting the window :
			// if the character at the right boundary appears more than once,
			// we need to keep contracting the window.
			while (chars[r]>1) {
				
				// To contracting the window, we should get the character at the left boundary.
				// and to reduce it's occurrence.				
				char l = s.charAt(left);
				chars[l]--;
				
				// Then move forward the left pointer.
				left++;
			}
			
			// After contracting the window, we can make sure that there is no duplicate character in the current window.
			// so we can update our result using the current window's length
			res = Math.max(res, right - left + 1);
			
			// At last, we can keep extending the window.
			right++; 
		}
		return res;
	}

}

/**
 * Complexity Analysis :
 * 
 * - Time complexity :
 *  - O(2n) = O(n).
 *  - In the worst case each character will be visited twice by i and j.
 *  
 * - Space complexity:
 *  - O(min(m,n))
 *  - Same as the previous approach.
 *  - we need O(k) space for the sliding window, where k is the size of the Set.
 *  - The size of the Set is upper bounded by the size of tge string n and the size of the charset/alphabet m. 
 * 
 * 
 * 
 */
