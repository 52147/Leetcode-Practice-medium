/**
 * 
 * Sliding window :
 * 
 *  for example:
 *  
 *   s = "abcabcbb"
 *   
 *   abc
 *     - we are enumerating the substrings starting from the first character a.
 *     and we know that the current substring "abc" does not have the repeating characters
 *   abca  
 *     - then we move forward to the next substring "abca"
 *     and we know that it has duplicate character "a"
 *     - we need to further enumerating the rest of the substrings starting with the character "a",
 *       but because  "abca" already has duplicate characters
 *     - we do not need to check the rest of the substrings any more.
 *   bca
 *     - and we can directly move forward to enumerate  the substring starting fron the second character "b"
 *     - and because previously when enumerating the substrings starting from the first character "a"
 *     - we have already checked the "b" and "bc" parts and we know that they did not have duplicate characters.
 *     - so we do not need to check there parts again and we can directly check the substring "bca"
 *       
 *     - and if it has duplicate characters
 *     - we do not need to further check the rest of the substrings starting with "b"
 *     - and we can just begin to enumerate the substring starting with the third character
 *    bcab   
 *     - in this case , the substring "bca" does not have the duplicate characters
 *     - so we can further cjeck the next substring start with "b" so on and so forth.
 *       
 *
 */
public class LongestSubstringWithoutRepeatingCharacters3_1 {

}
