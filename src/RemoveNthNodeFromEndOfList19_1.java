/**
 * 
 * 19. Remove Nth Node from end of list
 * 
 * - Given the head of a linked list,
 *   remove the n th node from the end of the list and return its head.
 *   
 *   
 * - Example 1:
 * 
 *   1->2->3->4->5
 *   1->2->3->   5
 *   
 *   Input: head = [1,2,3,4,5], n = 2
 *   Output: [1,2,3,5]
 *   
 * - Example 2:
 *   
 *   Input: head = [1], n = 1
 *   Output: []
 * 
 * - Example 3:
 *   
 *   Input: head = [1,2], n = 1
 *   Output: [1]
 *   
 * 
 * - Constraints:
 * 
 *   - The number of nodes in the list is sz.
 *   - 1 <= sz <= 30
 *   - 0 <= Node.val <= 100
 *   - 1 <= n <= sz
 *   
 *   
 * Solution:
 * 
 *  Summary:
 *  - The article is for beginners.
 *  - It introduces the following idea:
 *    Linked List traversal and removal of nth element from the end.
 *    
 *  Approach 1: 2 pass algorithm
 *  
 *    Intuition:
 *    
 *    - We notice that the problem could be simply reduced to another one:
 *      
 *      - Remove the (L-n + 1) th node from the beginning in the list,
 *        where L is the list length.
 *      - This problem is easy to solve once we found list length L.
 *      
 *    Algorithm:
 *    
 *    - First we will add auxiliary "dummy" node,
 *      which points to the list head.
 *    - The dummy node is used  to simplify some corner cases
 *      such as a list with only one node, or removing the head of the list.
 *      
 *    - On the first pass, we find the list length L.
 *    - Then we set a pointer to the dummy node and start to move it through the list
 *      till it comes to the (L-n) th node.
 *    - We relink next pointer of the (L-n) th node to the (L-n + 2) node and we are done.
 *    
 *    
 *    D-> 1 -> 2 -> 3 -> 4 -> null
 *    
 *    Input: 2  -> remove the (L-n) node from the end -> 4-2 = 2
 *    
 *    D-> 1 -> 2 ->      4 -> null 
 *    
 *    remove the (L-n) node from the end = remove the (L-n + 1) node from the start       
 *        
 *      
 *   
 *
 */
// Definition for singly- linked list:
class ListNode2{
	int val;
	ListNode2 next;
	ListNode2(){}
	ListNode2(int val){
		this.val = val;
	}
	ListNode2(int val, ListNode2 next){
		this.val = val;
		this.next = next;
		
	}
}
public class RemoveNthNodeFromEndOfList19_1 {
	
	public ListNode2 removeNthFromEnd(ListNode2 head, int n) {
	
	ListNode2 dummy = new ListNode2(0);
	dummy.next = head;
	int length = 0;
	ListNode2 first = head;
	
	// Find the length of the list
	while(first != null) {
		length ++;
		first = first.next;
	}
	
	length -= n; // L-n
	first = dummy;
	
	// Find the node before the node we want to remove
	while(length > 0) {
		length--;
		first = first.next;
	}
	
	// Link the L-n th node to L-n +2 node
	first.next = first.next.next;
	
	return dummy.next; // return dummy.next because we need to consider if the list has only one node.
	
	
	
	
	


}}

/**
 * Complexity Analysis:
 * 
 * - Time complexity: O(L)
 * 
 *   - The algorithm makes 2 traversal of the list,
 *     1. first to calculate list length L
 *     2. and second to find the (L-n) th node.
 *     
 *     -> There are 2L - n operations and time complexity is O(L)
 *     
 * - Space complexity: O(1)
 * 
 *   - We only used constant extra space.  
 */



