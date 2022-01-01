/**
 * 19. Remove Nth Node from end of list
 * 
 * Approach 2: One pass algorithm
 * 
 *  Algorithm:
 *  
 *  - The above algorithm could be optimized to one pass.
 *  - Instead of one pointer, we could use 2 pointers.
 *  
 *  - The first pointer advances the list by n+1 steps from the beginning,
 *    while the second pointer starts from the beginning of the list.
 *    
 *  - Now, both pointers are exactly separated by n nodes apart. 
 *  - We maintain this constant gap by advancing both pointers together
 *    until the first pointer arrives past the last node.
 *    
 *  - The second pointer will be pointing at the n th node counting from the last.
 *  - We relink the next pointer of the node referenced by the second pointer to the node's next next node.
 *  
 *    Maintaining N = 2 nodes apart between first and second pointer:
 *    
 *     Remove the n th element from end of a list:
 *    
 *    D(second, first) -> 1(head) -> 2 -> 3 -> 4 -> 5 -> null
 *    
 *    D(second) -> 1(head) -> 2 -> 3(first) -> 4 -> 5 -> null
 *    
 *    D -> 1(head) -> 2 -> 3(second) -> 4 -> 5 -> null(first)
 *
 *    D -> 1(head) -> 2 -> 3(second) -> 4(x) -> 5 -> null(first)
 *    
 *    D -> 1(head) -> 2 -> 3(second) ->         5 -> null(first)
 *    
 */

// Definition for singly- linked list:
class ListNode3{
	int val;
	ListNode3 next;
	ListNode3(){}
	ListNode3(int val){
		this.val = val;
	}
	ListNode3(int val, ListNode3 next){ 
		this.val = val;
		this.next = next;
	}
}
public class RemoveNthNodeFromEndOfList19_2 {
	public ListNode3 removeNthFromEnd(ListNode3 head, int n) {
		ListNode3 dummy = new ListNode3(0);
		
		dummy.next = head;
		
		ListNode3 first = dummy;
		ListNode3 second  = dummy;
		
		// Advance the first pointer to the previous node we want to remove.
		
		for(int i = 0; i <= n ; i ++) {
			first = first.next;
		}
		
		// Move the first pointer to the null (not the end of the node!)
		// And move the second pointer to the first node at before
		// first move to null traversal 2 node -> second move 2 node
		// -> maintaining the gap because we want the second pointer at the place that first pointer at before.
		while(first != null) {
			first = first.next;
			second = second.next;
			}
		
		second.next = second.next.next;
		return dummy.next;
		
		
		}
	
	
}

/**
 * Complexity Analysis:
 * 
 *  - Time complexity: O(L)
 *    - The algorithm makes one traversal of the list of L nodes.
 *    - Therefore time complexity is O(L)
 *    
 *  - Space complexity: O(1)
 *    - We only used constant extra space.
 *  
 * 
 */

