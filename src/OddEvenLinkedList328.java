/**
 * 
 * 328. Odd Even Linked List
 * 
 * Q:
 * 
 * - Given the head of a singly linked list, group all the nodes 
 *   with odd indices together followed by the node with even indices, 
 *   and return the reordered list.
 *   
 * - The first node is considered odd, and the second node is even, and so on.
 * 
 * - Note that the relative order inside both the even and odd groups 
 *   should remain as it was in the input.
 * - You must solve the problem in O(1) extra space complexity and O(n) time complexity.
 * 
 * Example 1:
 * 
 * 1 -> 2 -> 3 -> 4 -> 5
 * 
 * 1 -> 3 -> 5 -> 2 -> 4
 * 
 * Input: head = [1, 2, 3, 4, 5]
 * Output: [1, 3, 5, 2, 4]
 * 
 * 
 * Example 2:
 * 
 * 2 -> 1 -> 3 -> 5 -> 6 -> 4 -> 7
 * 
 * 2 -> 3 -> 6 -> 7 -> 1 -> 5 -> 4
 * 
 * Input : head = [2, 1, 3, 5, 6, 4, 7]
 * Output: [2, 3, 6, 7, 1, 5, 4]
 * 
 * Constraints:
 * 
 *  n == number of nodes in the linked list
 *  0 <= n <= 10^4
 *  -10^6 <= Node.val <= 10^6
 *  
 *  
 *  Solution:
 *  
 *  Intuition:
 *  
 *  - Put the odd nodes in a linked list and the even nodes in another.
 *  - Then link the evenList to the tail of the oddList.
 *  
 *  Algorithm:
 *  
 *  - The solution is very intuitive.
 *  - But it is not trivial to write a concise and bug-free code.
 *  
 *  - A well-formed LinkedList need 2 pointers head and tail to support operations at both ends.
 *  
 *  - The variables head and odd are the head pointer and tail pointer of one LinkedList we call oddList;
 *    the variables evenHead and even are the head pointer and tail pointer of another LinkedList we call evenList.
 *    
 *  - The algorithm traverses the original LinkedList and put the odd nodes into the oddList and the even nodes to the evenList.
 *  - To traverse a LinkedList we need at least one pointer as an iterator for the current node.
 *  - But here the pointers odd and even not only serve as the tail pointers but also
 *    act as the iterators of the original list.
 *    
 *  - The best way of solving any linked list problem is to visualize it either in your mind or on a piece of paper.
 *  - An illustration of our algorithm is following:
 *  
 *  Step 0:
 *  
 *  odd: 1
 *  evenHead: 2
 *  even: 2
 *  
 *  head -> 1 -> 2 -> 3 -> 4 -> 5 -> null
 *  
 *  
 *  Step 1:
 *  
 *  odd: 3
 *  even: 4
 *  
 *  head -> 1 -> 3 -> 4 -> 5 -> null
 *  
 *  evenHead -> 2 -> 4
 *  
 *  Step 2:
 *  odd: 5
 *  even: null
 *  
 *  head -> 1 -> 3 -> 5 -> null
 *  evenHead -> 2 -> 4 -> null
 *  
 *  
 *  Final:
 *  
 *  odd: 5
 *  evenHead: 2
 *  even: null
 *  
 *  head -> 1 -> 3 -> 5 -> 2 -> 4 -> null 
 *
 */

// Definition for singly-linked list.
class ListNode328{
	int val;
	ListNode328 next;
	ListNode328(){}
	
	ListNode328(int val){
		this.val = val;
		}
	
	ListNode328(int val, ListNode328 next){
		this.val = val;
		this.next = next;
	}
}
public class OddEvenLinkedList328 {
	
	public ListNode328 oddEvenList(ListNode328 head) {
		
		// 1. check the list is empty
		if(head == null)
			return null;
		
		// 2. Set the pointer old, even, evenHead
		ListNode328 odd = head, even = head.next, evenHead = even;
		
		// 3. Split the odd index and even index node into 2 list
		while(even != null && even.next != null) {
			odd.next = even.next;
			odd = odd.next;
			even.next = odd.next;
			even = even.next;
		}
		
		// 4. relink the 2 list together
		odd.next = evenHead; 
		
		// 5. return the new sorted list
		return head;
		
	}

}

/**
 * Complexity Analysis:
 * 
 *    - Time complexity: 
 *      O(N)
 *      There are total n nodes and we visit each node once.
 *    
 *    - Space complexity:
 *      O(1)
 *      All we need is the four pointers.
 */
