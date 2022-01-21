import java.util.List;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

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
 *     - Then, to determine which connected component each node is it,
 *       we perform graph traversals from arbitrary unvisited nodes until all nodes have been visited.
 *     - To do this efficiently, we store visited nodes in a Set, allowing for constant time containment checks and insertion.
 *     - Finally, we consider each connected component, merging all of its intervals by constructing a new Interval with start equal to
 *       the minimum start among them and end equal to the maximum end.
 *       
 *     - This algorithm is correct simply because it is basically the brute force solution.
 *     - We compare every interval to every other interval,
 *       so we know exactly which interval overlap.
 *     - The reason for the connected component search is that 2 intervals may not directly overlap,
 *       but might overlap indirectly via a third interval.
 *     - See the example below to see this more clearly.
 *     
 *            (1, 5)   (6, 10)      (15, 17)
 *                  \ /                 |
 *                 (4,7)             (16, 20)
 *     
 *     - Although (1,5) and (6, 10) do not directly overlap, either would overlap with the other if first merged with (4,7).
 *     - There are 2 connected components, so if we merge their nodes,
 *       we expect to get the following 2 merged intervals:
 *       
 *       (1, 10), (15, 20)
 *       
 *       
 *     
 *    
 *    
 *   
 *    
 * 
 *
 */
class MergeIntervals56_1 {
    private Map<int[], List<int[]>> graph;
    private Map<Integer, List<int[]>> nodesInComp;
    private Set<int[]> visited;

    // return whether two intervals overlap (inclusive)
    private boolean overlap(int[] a, int[] b) {
        return a[0] <= b[1] && b[0] <= a[1];
    }

    // build a graph where an undirected edge between intervals u and v exists
    // iff u and v overlap.
    private void buildGraph(int[][] intervals) {
        graph = new HashMap<>();
        for (int[] interval : intervals) {
            graph.put(interval, new LinkedList<>());
        }

        for (int[] interval1 : intervals) {
            for (int[] interval2 : intervals) {
                if (overlap(interval1, interval2)) {
                    graph.get(interval1).add(interval2);
                    graph.get(interval2).add(interval1);
                }
            }
        }
    }

    // merges all of the nodes in this connected component into one interval.
    private int[] mergeNodes(List<int[]> nodes) {
        int minStart = nodes.get(0)[0];
        for (int[] node : nodes) {
            minStart = Math.min(minStart, node[0]);
        }

        int maxEnd = nodes.get(0)[1];
        for (int[] node : nodes) {
            maxEnd = Math.max(maxEnd, node[1]);
        }

        return new int[] {minStart, maxEnd};
    }

    // use depth-first search to mark all nodes in the same connected component
    // with the same integer.
    private void markComponentDFS(int[] start, int compNumber) {
        Stack<int[]> stack = new Stack<>();
        stack.add(start);

        while (!stack.isEmpty()) {
            int[] node = stack.pop();
            if (!visited.contains(node)) {
                visited.add(node);

                if (nodesInComp.get(compNumber) == null) {
                    nodesInComp.put(compNumber, new LinkedList<>());
                }
                nodesInComp.get(compNumber).add(node);

                for (int[] child : graph.get(node)) {
                    stack.add(child);
                }
            }
        }
    }

    // gets the connected components of the interval overlap graph.
    private void buildComponents(int[][] intervals) {
        nodesInComp = new HashMap<>();
        visited = new HashSet<>();
        int compNumber = 0;

        for (int[] interval : intervals) {
            if (!visited.contains(interval)) {
                markComponentDFS(interval, compNumber);
                compNumber++;
            }
        }
    }

    public int[][] merge(int[][] intervals) {
        buildGraph(intervals);
        buildComponents(intervals);

        // for each component, merge all intervals into one interval.
        List<int[]> merged = new LinkedList<>();
        for (int comp = 0; comp < nodesInComp.size(); comp++) {
            merged.add(mergeNodes(nodesInComp.get(comp)));
        }

        return merged.toArray(new int[merged.size()][]);
    }
}

/**
 * Complexity Analysis:
 * 
 *  - Time complexity:
 *    - O(n^2)
 *    - building the graph costs O(V+E) = O(V) + O(E) = O(n) + O(n^2) = O(n^2) time, 
 *      as in the worst case all intervals are mutually overlapping.
 *    - Traversing the graph has the same cost (although it might appear higher at first) 
 *      because our visited set guarantees that each node will be visited exactly once.
 *    - Finally, because each node is part of exactly one component,
 *      the merge step cost O(V) = O(n) time.
 *      
 *    - This all adds up as follows:
 *      O(n^2) + O(n^2) + O(N) = O(n^2)
 *      
 *  - Space complexity: O(n^2)
 *  
 *    - As previous mentioned, in the worst case, all intervals are mutually overlapping,
 *      so there will be an edge for every pair of intervals.
 *    - Therefore, the memory footprint is quadratic in the input size.
 *     
 * 
 */







