/**
 * 
 * 423. Reconstruct Original Digits From English
 * 
 * Q:
 * - Given a string s containing an out-of-order English representation of digits 0-9,
 *   return the digits in ascending order.
 *   
 * Example 1:
 * 
 * Input: s = "owoztneoer"
 * Output: "012"
 * 
 * Example 2:
 * 
 * Input: s = "fviefuro"
 * Output: "45"
 * 
 * Constraints:
 * 
 * 1 <= s.lemgth <= 10^5
 * s[i] is one of the characters ["e", "g", "f", "i", "h", "o", "n", "s", "r", "u", "t", "w", "v", "x", "z"]
 * s is guaranteed to be valid.
 * 
 * 
 * Approach 1: Hashmap
 * 
 * Intuition:
 * 
 * - The naive approach would be to construct as man "zero"s as it's possible 
 *   from letters available in the input string, then as many "one"s as it's possible, etc.
 * - The problem is that letters "o", "n", "e" could be present as well in the another numbers
 *   that means that the straightforward approach could be misleading.
 *   
 *   
 *   twonine  => "one" could be constructed from the input string but it's misleading.
 *   
 *   - Hence the idea is to look for something unique.
 *   - One could notice that all even numbers contain each a unique letter:
 *     
 *     - Letter "z" is present only in "zero"
 *     - Letter "w" is present only in "two"
 *     - Letter "u" is present only in "four"
 *     - Letter "x" is present only in "six"
 *     - Letter "g" is present only in "eight"
 *     
 *     -> Hence there is a good way to count even numbers.
 *     
 *  - That is actually the key how to count 3s, 5s, 7s since some letters are present only 
 *    in one odd and one even number(and all even numbers has already been counted):
 *    
 *    - Letter "h" is present only in "three" and "eight"
 *    - Letter "f" is present only in "five" and "four"
 *    - Letter "s" is present only in "seven" and "six"
 *    
 * - Now one needs to count 9s and 1s only, and the logic is basically the same:
 *   
 *    - Letter "i" is present in "nine", "five", "six", and "eight"
 *    - Letter "n" is present in "one", "seven", and "nine".
 *    
 *    
 * implementation:
 * 
 *   onetwothreefourfivesixseveneightninezero
 *   
 *   ->
 *   
 *   - compute count:
 *   
 *      {'e':9, 'o':4, 'n':4, 'i':4, 't':3, 'r':3, 'h':2, 'f':2, 'v':2, 's': 2, 'w':1, 'u':1, 'x' :1, 'g': 1, 'z':1}
 *      
 *      output:
 *      
 *        "0123456789"
 *      
 *      
 *   - compute out:
 *      
 *      1. there is only 1 letter "z" in the input string => there is only one 0
 *      2. there is only 1 letter "w" in the input string => there is only one 2
 *      3. there is only 1 letter "u" in the input string => there is only one 4
 *      4. there is only 1 letter "x" in the input string => there is only one 6
 *      5. there is only 1 letter "g" in the input string => there is only one 8
 *      
 *      6. there are 2 letters "h" in the input string and 1 is taken by 8 => there is only one 3
 *      7. there are 2 letters "f" in the input string and 1 is taken by 4 => there is only one 5
 *      8. there are 2 letters "s" in the input string and 1 is taken by 6 => there is only one 7
 *      
 *      9. there are 4 letters "i" in the input string, 1 is taken by 5, one is taken by 6, and one is taken by 8 
 *         => there is only one 9
 *      10. there are 4 letters "n" in the input string, 1 is taken by 7, and two is taken by 9
 *         => there is only one 0
 *         
 *             
 *         
 * 
 *   
 *
 */
public class ReconstructOriginalDigitsFromEnglish423 {
	
	public String originalDigits(String s) {
		// building hashmap letter -> its frequency
		char[] count = new char[26 + (int) 'a'];
		
		for(char letter: s.toCharArray())
			count[letter]++;
	
	
	
	// building hashmap digit -> its frequency
	int[] out = new int[10];
	
	// letter "z" is present only in "zero"
	out[0] = count['z'];
	
	// letter "w" is present only in "two"
	out[2] = count['w'];
	
	// letter "u" is present only in "four"
	out[4] = count['u'];
	
	// letter "x" is present only in "six"
	out[6] = count['x'];
	
	// letter "g" is present only in "eight"
	out[8] = count['g'];
	
	// letter "h" is present only in "three" and "eight"
	out[3] = count['h'] - out[8];
	
	// letter "f" is present only in "five" and "four"
	out[5] = count['f'] - out[4];
	
	// letter "s" is present only in "seven" and "six"
	out[7] = count['s'] - out[6];
	
	// letter "i" is present in "nine", "five", "six", and "eight"
	out[9] = count['i'] - out[5] - out[6] - out[8];
	
	// letter "n" is present in "one", "nine", and "seven"
	out[1] = count['n'] - out[7] - 2*out[9];
	
	
	// building output string
	StringBuilder output = new StringBuilder();
	
	for(int i = 0; i < 10; i++)
		for(int j = 0; j < out[i]; j++)
			output.append(i);
	
	return output.toString();
}

}

/**
 * Complexity analysis:
 * 
 *  - Time complexity:
 *    - O(N)
 *      where N is a number of characters in the input string.
 *    - O(N) time is needed to compute hashmap count "letter -> its frequency in the input string".
 *    - Then we deal with a data structure out which contains 10 elements only and 
 *    all operations are done in a constant time.
 *    
 *  - Space complexity:
 *    - O(1)
 *    - count contains constant number of elements since input string contains only lowercase English letters,
 *      and out contains not more that 10 elements. 
 * 
 * 
 */






