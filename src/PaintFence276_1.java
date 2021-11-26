import java.util.HashMap;
/**
 * 
 * 276. Paint Fence
 *  Q. 
 *   - You are painting a fence of n posts with k different colors.
 *   - You must paint the posts following these rules:
 *   
 *    - Every post must be painted exactly one color.
 *    - There cannot be three or more consecutive posts with the same color.
 *  
 *   - Given the 2 integers n and k, return the number of ways you can paint the fence.
 *   
 *  Example 1:
 *  
 *   Input: n = 3, k = 2
 *  
 *   3 posts 2 color:
 *  
 *  	- 1. RRG
 *  	- 2. RGR
 *  	- 3. RGG
 *   	- 4. GRR
 *   	- 5. GRG
 *   	- 6. GGR
 *   
 *   -> Output: 6
 *    Explanation:
 *     - All the possibilities are shown.
 *     - Note that painting all the posts red or all the posts green is invalid 
 *       because there cannot be 3 posts in a row with the same color.
 *  
 *  Example 2:
 *   
 *   Input: n = 1, k = 1
 *   Output: 1
 *   
 *  Example 3:
 *   
 *   Input: n = 7, k = 2
 *   Output: 42     
 *    
 * Constraints:
 *  - 1 <= n <= 50
 *  - 1 <= k <= 10^5
 *  - The testcases are generated such that the answer is in the range [0, 2^31 -1] for the given n and k
 *  
 *  
 *  Solution:
 *  
 *   Overview:
 *    
 *    - Realizing this is Dynamic Programming Problem
 *      
 *      - There are 2 parts to this problem that tell us it can be solved with dynamic programming.
 *      
 *        1.
 *        - First, the question is asking for the "number of ways" to do something.
 *        
 *        2.
 *        - Second, we need to make decisions that may depend on previously made decisions.
 *          - In this problem, we need to decide what color we should paint a given post, which may change depending on previous decisions.
 *          - For example:
 *            - If we paint the first two posts the same color, then we are not allowed to paint the third post the same color.
 *         
 *        - Both of these things are characteristic of dynamic programming problems.
 *    
 *    
 *    - A Framework to solve Dynamic Programming problem
 *      
 *      - A dynamic programming algorithm typically has 3 components.
 *      - Learning these components is extremely valuable, as most dynamic programming problems can be solved this way.
 *      
 *        1.
 *        - First, we need some function or array that represents the answer to the problem for a given state.
 *        - For this problem, let's say that we have a function "totalWays", where "totalWays(i)" returns the number of ways to paint i posts.
 *        - Because we only have one argument, this is a one-dimensional dynamic programming problem.
 *        
 *        2.
 *        - Second, we need a way to transition between state, such as "totalWay(3)" and "totalWays(4)".
 *        - This is called a recurrence relation and figuring it out is usually the hardest part of solving a problem with dynamic programming.
 *        - We'll talk about the recurrence relation for this problem below.
 *        
 *        3.
 *        - The third component is establishing base cases.
 *        - If we have 1 post, there are k ways to paint it.
 *        - If we have 2 post, then there are k*k ways to paint it(since we are allowed to paint have 2 posts in a row be the same color).
 *        - Therefore, totalWays(1) = k, totalWays(2) = k*k.
 *        
 *    - Finding the Recurrence Relation
 *      
 *      - We know the values for totalWays(1) and totalWays(2), now we need a formula for totalWays(i), where 3<= i<= n.
 *      - Let's think about how many ways there are to paint the i-th post.
 *      
 *      - We have 2 options:
 *        
 *        1.
 *        - Use a different color than the previous post.
 *        - If we use a different color, then there are "k-1" colors for us to use.
 *        - This means there are (k-1)*totalWays(i-1) ways to paint the i-th post a different color than the (i-1)-th post.
 *        
 *        2.
 *        - Use the same color as the previous post.
 *        - There is only one color for us to use so there are 1*totalWays(i-1) ways to paint the i-th post 
 *          the same color as the (i-1)-th post.
 *        - However, we have the added restriction of not being allowed to paint 3 posts in a row the same color.
 *        - Therefore, we can paint the i-th post the same color as the (i-1)-th post
 *          only if the (i-1)-th post is a different color than the (i-2)-th post.
 *                 
 *        - So, how many ways are there to paint the (i-1)-th post a different color than the (i-2)-th post?
 *        - As stated in the first option, there are (k-1)*totalWays(i-1) ways to paint the i-th post a difference color than the (i-1)-th post,
 *          so that means there are 1*(k-1)*totalWays(i-2) ways to paint the (i-1)-th post a different color than the (i-2)-th post.
 *          
 *     - Adding these 2 senarios together gives totalWays(i) = (k-1)*totalWays(i-1) + (k-1)*totalWays(i-2), which can be simplified to:
 *       
 *       -> totalWays(i) = (k-1) * (totalWays(i-1) + totalWays(i-2))
 *       
 *       -> This is our recurrence relation which we can use to solve the problem from the base case. 
 *       
 *               
 *  Approach 1: Top-Down Dynamic Programming (Recursion + Memoization)
 *  
 *   Intuition:
 *    
 *    - Top-down dynamic programming starts from the top and works it way down to the base cases.
 *    - Typically, this is implemented with recursion and then efficient using memoization.
 *    
 *    - Memoization refers to storing the results of expensive function calls to avoid duplicate computations.
 *   
 *    - We can implement the function totalWay(i) as follows-
 *      
 *      1.
 *      - first, check for the base cases we defined above totalWays(1) = k, totalWay(2) = k*k.
 *      - If i >= 3, use our recurrence relation: totalWay(i) = (k-1)*(totalWays(i-1) + totalWays(i-2)).
 *      
 *      - However, we will run into a major problem - repeated computation.
 *      - If we call totalWays(5), that function call will also call totalWays(4) and totalWays(3).
 *      - The totalWays(4) call will call totalWays(3) again.
 *      - as illustrated below, we are calculating totalWays(3) twice.
 *      
 *      
 *                            5
 *                            /\
 *                          (3)  4
 *                          /\  /\
 *                         1 2  2 (3)
 *                                 /\
 *                                1  2 
 *                        
 *                       -> We have to calculate totalWays(3) 2 times.
 *                       
 *      - This may not seen like a big deal with i=5, but imagine if we called totalWays(6).
 *      - This entire tree would be 1 child, and we would have to call totalWays(4) twice.
 *      - As n increases, the size of the tree grows exponentially - imagine how expensive a call such that value in memory.
 *      - Next time we need to call totalWays(i), we can refer to the value stored in memory 
 *        instead of having to call the function again and going through the repeated computations.
 *        
 *  Algorithm:
 *   
 *   - 1. Define a hash map memo, where memo[i] represents the number of ways you can paint i fence posts.
 *   
 *   - 2. Define a function totalWays where totalWay(i) will determine the number of ways you can paint i fence posts.
 *   
 *   - 3. 
 *     - In the function totalWays, first check for the base cases.
 *     - return k if i == 1, and return k*k if i == 2.
 *     - Next, check if the argument i has already been calculated and stored in memo.
 *     - If so, return memo[i].
 *     - Otherwise, use recurrence relation to calculate memo[i], and then return memo[i].    
 *        
 *   - 4. Simply call and return totalWays(n).            
 *               
 *          
 */
public class PaintFence276_1 {
	// Use HashMap to store the previously computed results.
	private HashMap<Integer, Integer> memo = new HashMap<Integer, Integer>();
	
	private int totalWays(int i, int k) {
		
		// Base cases:
		if(i == 1) return k; // 1 post has the k ways to paint
		if(i == 2) return k*k; // 2 posts have the k*k ways to paint 
		
		// Check if we have already calculated totalWays(i)
		if(memo.containsKey(i)) {
			return memo.get(i);			
		}
		
		// Use the recurrence relation to calculate totalWays(i)
		memo.put(i, (k-1)*(totalWays(i-1, k) + totalWays(i-2, k)));
		
		return memo.get(i); 
	}
	
	public int numWays(int n, int k) {
		return totalWays(n, k);
	}

}

/**
 * Extra notes:
 * 
 *  - For this approach, we are using a hash map as our data structure to memoize function calls.
 *  - We could also use array since the calls to "totalWays" are very well defined(between 1 and n).
 *  
 *  - However, a hash map is used for most top-down dynamic programming solutions, as there will often be multiple function arguments,
 *    the arguments might not be integers, or a variety of other reasons that require a hash map instead of an array.
 *  
 *  - Although using an array is slightly more efficient, using a hash map here is a good practice that can be applied to other problems.   
 * 
 */

/**
 * Complexity Analysis:
 *  
 *  - Time complexity:
 *  
 *    - O(N)
 *    - totalWays get calls with each index from n to 3.
 *    - Because of our memoization, each call will only take O(1) time.
 *    
 *  - Space complexity:
 *  
 *    - O(N)
 *    - The extra space used by this algorithm is the recursion call stack.
 *      - For example:
 *        - totalWays(50) will call totalWays(49), which calls totalWays(48) etc,
 *          all the way down until base cases at totalWays(1) and totalWays(2).
 *          
 *    - In addition, our hash map memo will be of size n at the end, since we populate it with every index from n to 3.      
 *          
 * 
 */
