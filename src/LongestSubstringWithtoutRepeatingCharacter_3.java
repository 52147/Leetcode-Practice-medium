import java.util.Map;
import java.util.HashMap;

/**
 * Approach 3: Sliding window optimized (using HashMap)
 * 
 * - The previous approach requires at most 2n steps.
 * - In fact, it could be optimized to require only n steps.
 * 
 * - Instead of using a set to tell id a character exists or not, we could define a mapping of the characters to its index.
 * - Then we can skip the characters immediately when we found a repeated character.
 * 
 * - The reason is that if s[j] have a duplicate in the range [i,j) with index j'.
 * - we don't need to increase i little by little.
 * - we can skip all the elements in the range [i, j'] and let i to be j'+1. 
 *
 */
public class LongestSubstringWithtoutRepeatingCharacter_3 {
	
	public int lengthOfLongestSubstring(String s) {
		int n = s.length(), ans = 0;
		
		Map<Character, Integer>map = new HashMap<>(); // current index of the character
		
		// extend the range[i,j]
		// for example : abcdeaf -> s[5] a is duplicate
		// first loop: j=0 ; i= 0 ; ans =1 ; a:1
		// second loop: j=1 ; i=0 ; ans =1-0+1 =2 ; b:2
		// third loop: j=2 ; i=0 ; ans = 2-0+1 =3 ; c:3
		// fourth loop: j =3 ; i=0 ; ans =3-0+1 =4; d:4
		// fifth loop: j =4 ; i=0 ; ans = 4-0+1 =5 ; e:5
		// sixth loop : j =5 ; i=1 ; ans = 5-1+1 =5 ; a: j+1 = 5+1 =6
		// seventh loop: j=6 ; i=1 ; ans = 6-1+1 = 6 ; f:7
		for(int j = 0, i = 0; j < n ;j++) {
			if(map.containsKey(s.charAt(j))){// if contains the same key in the map
				i = Math.max(map.get(s.charAt(j)),i); // (1, 0) -> 1>0 -> i = 1
			}
			ans = Math.max(ans, j-i+1);// 5-1+1 = 5 -> ans = 5
			map.put(s.charAt(j),j+1); 
			// map: first loop -> a: 1, second loop -> b:2
			// third loop-> c: 3, fourth loop -> d:4, fifth loop: e:5
			// sixth loop -> a become  6 ; ans = 5
			// seventh loop -> f:7 ; ans = 6
		}
	
		return ans;	
		
	}

}
/**
 * Complexity Analysis:
 * 
 * - Time complexity:
 *   - O(n).
 *   - indez j will iterate n times.
 * 
 * - Space complexity(HashMap):
 *   - O(min(m,n))
 *   - We need O(k) space for checking a substring has no duplicate characters, 
 *   - where k is the size of the Set. 
 *   - The size of the Set is upper bounded by the size of the string n and the size of the charset/alphabet m.
 *   - (same as the previous approach)
 *   
 * - Space complexity(Table):
 *   - O(m)
 *   - m is the size of the charset.     
 *  
 */

