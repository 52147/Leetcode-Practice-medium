import java.util.Arrays;
import java.util.LinkedList;

/**
 * 
 * Approach 2: Sorting
 * Intuition:
 * 
 * - If we sort the intervals by their start value, 
 *   then each set of intervals that can be merged will appear as a contiguous "run" in the sorted list.
 *   
 * Algorithm:
 * 
 * - First, we sort the list as described.
 * - Then, we insert the first interval into our merged list and continue considering each interval in turn as follows:
 *   
 *   - If the current interval begins after the previous interval ends,
 *     then they do not overlap and we can append the current interval to merged.
 *     - Otherwise, they do overlap, and we merge them by updating the end of the previous interval
 *       if it is less than the end of the current interval.
 *  
 *       
 *   - A simple proof by contradiction shows that this algorithm always produces the correct answer.
 *   
 *       - First, suppose that the algorithm at some point fails to merge two intervals that should be merged.
 *         - This would imply that there exists some triple of indices i, j and k in a list of intervals ints
 *           such that i < j < k and (ints[i], ints[k] ) can be merged,
 *           but neither (ints[i], ints[j]) nor (ints[j], int[k]) can be merged.
 *         
 *         - From this scenario follow several inequalities:
 *         
 *           ints[i].end < ints[j].start
 *           ints[j].end < ints[k].start
 *           ints[i].end >= ints[k].start
 *           
 *           - We can chain these inequalities
 *             (along with the following inequality, implied by the well-formedness of the intervals: ints[j].start <= ints[j].end)
 *             to demonstrate a contradiction:
 *             
 *              ints[i].end < ints[j].start <= ints[j].end < ints[k].start
 *                                             ints[i].end >= ints[k].start
 *            
 *              - Therefore, all mergeable intervals must occur in a contiguous run of the sorted list.                               
 *   
 *   [(1,9), (2, 5), (19, 20), (10, 11), (12, 20), (0, 3), (0, 1), (0, 2)]
 *   
 *   -> sort
 *   
 *   [(0, 3), (0, 1), (0, 2), (1,9), (2, 5), (10, 11), (12, 20), (19, 20)]
 *   
 *   Consider the example above, where the intervals are sorted, 
 *   and then all mergeable intervals from contiguous blocks.
 *   
 * 
 * 
 *
 */
public class MergeInterval56_2 {
	
	public int[][]merge(int[][] intervals){
		
		// 1. sort given array
		// compare the first element in the 2 array, which represent the start point 
		// => the intervals will be sorted from smallest to largest
		// sorting -> O(n log n)
		Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0])); 
		
		
		// 2. create a linked list to add the merged interval in.
		LinkedList<int[]> merged = new LinkedList<>();
		
		// 3. iterate through all of the intervals in the intervals arrays.
		// for loop -> O(N)
		for(int[] interval: intervals) {
			// if the linked list is empty or the last item its ending position is before where i start at
			if(merged.isEmpty() || merged.getLast()[1] < interval[0]) {
				merged.add(interval);
			}else { // overlap
				merged.getLast()[1] = Math.max(merged.getLast()[1], interval[1]);
			}
		}
		
		return merged.toArray(new int[merged.size()][]); // return 2 dimensional array
	}
	
	
	
	

}


/**
 * Complexity analysis:
 * 
 * Time complexity: 
 *  - O(nlogn)
 *  - Other than the sort invocation, we do a simple linear scan of the list,
 *    so the runtime is dominated by the O(nlogn) complexity of sorting.
 *    
 *  Space complexity: O(log N) or (O(n))
 *   - O(n) : n(the input size)-> the number of intervals that we're comparing.
 *   - If we can sort intervals in place, we do not need more than constant additional space
 *     , although the sorting itself takes O(log n) space.
 *   - Otherwise, we must allocate linear space to store a copy of intervals and sort that.  
 *                    
 * 
 * 
 */
