/**
 * 
 * 236. Lowest Common Ancestor of a Binary Tree
 * 
 *  - Given a binary tree, find the lowest common ancestor(LCA) of 2 given nodes in the tree.
 *  - According to the definition of LCA on wikipedia: 
 *    "The lowest common ancestor is defined between 2 nodes p and q as the lowest node in T that has both p and q as decendants
 *     (where we allow a node to be a descendant of itself)"
 *     
 *     Example 1:
 *     
 *            3
 *            /\
 *           5  1
 *          /\  /\
 *         6  2 0 8
 *            /\
 *           7  4
 *           
 *           Input: root = [3, 5, 1, 6, 2, 0, 8, null, null, 7, 4], p = 5, q = 1
 *           Output: 3
 *           Explanation: The LCA of nodes 5 and 1 is 3.
 *           
 *      Example 2:
 *      
 *            3
 *            /\
 *           5  1
 *          /\  /\
 *         6  2 0 8
 *            /\
 *           7  4
 *           
 *          Input: root = [3, 5, 1, 6, 2, 0, 8, null, null, 7, 4], p = 5, q = 4
 *          Output: 5
 *          Explanation: The LCA of nodes 5 and 4 is 5, since a node can be a descendant of itself
 *                       according to the LCA definition.
 *       
 *                        
 *       Example 3:
 *       
 *          Input: root = [1, 2], p = 1, q = 2
 *          Output: 1
 *          
 *   Constraints:
 *   
 *     - The number of nodes in the tree is in the range [2, 10^5]
 *     - -10^9 <= Node.val <= 10^9
 *     - All Node.val are unique.
 *     - p != q
 *     - p and q will exist in the tree
 *     
 *     
 *  Solution:
 *  
 *     - First the given nodes p and q are to be searched in a binary tree
 *       and then their lowest common ancestor is to be found.
 *     - We can resort to a normal tree traversal to search for the 2 nodes.
 *     - Once we reach the desired nodes p and q, we can backtrack and find the lowest common ancestor.
 *                    
 *                        1
 *                    /      \
 *                   2         3
 *                  /\       /   \ 
 *                 4  5     6     7
 *                /\  /\    /\    /\
 *               8 9 10 11 12 13 14 15 
 *               
 *           Lowest Common Ancestor for Node 9 and Node 11 is Node 2
 *           
 *   Approach 1: Recursive Approach
 *   
 *   Intuition:
 *   
 *   - The approach is pretty intuitive.
 *   - Traverse the tree in depth first manner.
 *   - The moment you encounter either of the nodes p or q, return some boolean flag.
 *   - The flag helps to determine if we found the required nodes in any of the paths.
 *   
 *   - The least common ancestor would then be the node for which both the subtree recursions return a True flag.
 *   - It can also be the node which itself is one of p or q and for which one of the subtree recursions returns a True flag.
 *   
 *   - Let us look at the formal algorithm based on this idea.
 *   
 *  Algorithm:
 *  
 *   1. Start traversing the tree from the root node.
 *   2. If the current node itself is one of p or q,
 *      we would mark a variable mid as True and continue the search for
 *      the other node in the left and right branches.
 *   3. If either of the left or the right branch returns True,
 *      this means one of the 2 nodes was found below.
 *   4. If at any point in the traversal, any 2 of the 3 flags left, right, or mid become True,
 *      this means we have found the lowest common ancestor for the nodes p and q.   
 *    
 *    
 *    Let us look at a sample tree and we search for the lowest common ancestor of 2 nodes 9 and 11 in the tree.
 * 
 *                        1
 *                    /      \
 *                   2         3
 *                  /\       /   \ 
 *                 4  5     6     7
 *                /\  /\    /\    /\
 *               8 9 10 11 12 13 14 15
 *               
 *                    
 *    1 --> 2 --> 4 -->8
 *    Backtrack 8 --> 4
 *    
 *    4--> 9 (one node found, return true)
 *    Backtrack 9-->4-->2
 *    
 *    2 --> 5 --> 10
 *    Backtrack 10 --> 5
 *    
 *    5 --> 11 (Another node found, return true)
 *    backtrack 11 --> 5 --> 2
 *    
 *    2 is the node where we have left = True and right = True and hence it is the lowest common ancestor.
 *    
 *    
 *           
 */

// Definition for a binary tree node.
class TreeNode{
	int val;
	TreeNode left;
	TreeNode right;
	TreeNode(int x){
		val = x;
	}
	
}
public class LowestCommonAncestorOfABinaryTree236_1 {
	
	
	private TreeNode ans;
	
	public LowestCommonAncestorOfABinaryTree236_1() {
		// variable to store LCA node.
		this.ans = null;
	}
	
	private boolean recurseTree(TreeNode currentNode, TreeNode p, TreeNode q) {
		
		// If reached the end of a branch, return false.
		if(currentNode == null) {
			return false;
		}
		
		// Left Recursion. If left recursion return true, set left = 1 else 0
		int left = this.recurseTree(currentNode.left, p, q) ? 1 : 0;
		
		// Right Recursion
		int right = this.recurseTree(currentNode.right, p, q) ? 1: 0;
		
		// If the current node is one of p or q
		int mid = (currentNode == p || currentNode == q) ? 1 :0;
		
		// If any 2 of the flags left, right or mid becomew True
		if(mid + left + right >= 2) {
			this.ans = currentNode;
		}
		
		// Return true if any 1 of the 3 bool values is True
		return(mid + left + right > 0);
				
				
	}
	
	public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
		// Traverse the tree
		this.recurseTree(root, p, q);
		return this.ans;
	}

}


/**
 * Complexity Analysis:
 * 
 * - Time complexity: 
 *   - O(N)
 *   - where N is the number of nodes in the binary tree.
 *   - In the worst case we might be visiting all the nodes of the binary tree.
 *   
 * - Space complexity:
 *   - O(N)
 *   - This is because the maximum amount of space utilized by the recursion stack
 *     would be N since the height of a skewed binary tree could be N.  
 *   
 */



