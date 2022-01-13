/**
 * 
 * 56. Merge Intervals
 * 
 *  Q:
 *   - Given an array of intervals where intervals[i] = [start i, end i ], merge all overlapping intervals,
 *     and return an array of the non-overlapping intervals that cover all the intervals in the input.
 *     
 *  Example 1:
 *  
 *   Input: intervals = [[1,3], [2,6], [8, 10], [15, 18]] 
 *   Output: [[1,6], [8, 10], [15, 18]]
 *   
 *   Explanation:
 *    Since intervals [1,3] and [2,6] overlaps, merge them into [1,6].
 *  
 *  Example 2:  
 *  
 *   Input: intervals = [[1,4], [4,5]]
 *   Output: [[1,5]]
 *   
 *   Explanation:
 *    Intervals [1,4] and [4, 5] are considered overlapping.
 *    
 * Solution:
 * 
 *    Approach 1: Connected Components
 *    
 *     Intuition:
 *     
 *     - If we draw a graph (with intervals as nodes) that contains undirected edges between all pairs of intervals that overlap, 
 *       then all intervals in each connected component of the graph can be merged into a single interval.
 *       
 *     Algorithm:
 *     
 *     - With the above intuition in mind, we can represent the graph as an adjacency list,
 *       inserting directed edges in both directions to simulate undirected edges.
 *       
 *     
 *    
 *    
 *   
 *    
 * 
 *
 */
public class MergeIntervals56_1 {

}
