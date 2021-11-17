/**
 * 2. Add Two Numbers (Linked list)(Medium)
 * 
 *  Q:
 *  - Given two non-empty linked lists representing 2 non-negative integers.
 *  - The digits are stored in reverse order, and each of nodes contains a single digit.
 *  - Add the 2 numbers and return the sum as a linked list.
 *  
 *  ex 1:
 *  
 *  first linked list: 2 -> 4 -> 3
 *  second linked list: 5-> 6 -> 4
 *  
 *  output: 7 -> 0 -> 8
 *  
 *  explanation: 342+465 = 807
 *  
 *  ex 2 : 
 *  
 *  first linked list : 0
 *  second linked list : 0
 *  
 *  output : 0
 *  
 *  Solution:
 *  
 * - Intuition:
 *       
 *  first linked list :
 *  
 *  l1 ->2 ->4 ->3 -> null
 *  
 *  second linked list :
 *  
 *  l2 -> 5 -> 6 -> 4 -> null
 *  
 *  output :
 *  
 *  result -> 7 -> 0 -> 8 -> null
 *  
 * - Algorithm:
 *  
 *  - summing the least-significant digits, which is the head of l1 and l2.
 *       
 *       -Since each digit is the range of 0~9, summing 2 digits may "overflow".
 *        for example:
 *        
 *         5+7 =12
 *         -> in the case, set the current digit to 2 and bring over the carry = 1 to the next iteration.
 *   	   -> carry must be either 0 or 1
 *   	
 *  the least-significant digits:
 *    - the digit with the lowest exponent value, located in the rightmost position.
 *    - for example:
 *       2516 -> 6 is the least significant digit  
 *       
 *  Test case :
 *   1.
 *    
 *   l1 = [0,1]      
 *   l2 = [0,1,2]
 *   When on list is longer than the other.
 *   
 *   2.
 *   
 *   l1 = []
 *   l2 = [0,1]
 *   When one list is null, which means an empty list.
 *   
 *   3.
 *   
 *   l1 = [9,9]
 *   l2 = [1]
 *   The sum can have an carry of one at the end, which is easy to forget.       
 *
 */


/**
 * 
 * Definition for singly-linked list.
 * 
 * public class ListNode{
 * 	   int val;
 *     ListNode next;
 *     ListNode{
 *     
 *     }
 *     ListNOde(int val){
 *     this.val = val;}
 *     ListNode(int val, ListNode next){
 *     this.val = val;
 *     this.next = next;
 *     }
 * }
 *
 */
public class AddTwoNumbers2 {
	
/**
 * Intuition :
 *  
 *  first linked list :
 *  
 *  pointer : p =l1
 *  
 *  l1 ->2 ->4 ->3 -> null
 *  
 *  second linked list :
 *  
 *  pointer : q=l2
 *  
 *  l2 -> 5 -> 6 -> 4 -> null
 *  
 *  output :
 *  
 *  pointer : current = dummyHead
 *  
 *  dummyHead -> 7 -> 0 -> 8 -> null
*/
	
	public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
		// Initialize current node to dummy head of the returning list.
		ListNode dummyHead = new ListNode(0); // initialize carry to 0.
		
		// Initial p and q to head of l1 and l2.
		ListNode p =l1, q =l2, current = dummyHead;		
		
		int carry = 0;
		
		// loop through lists l1 and l2 until reach both ends.
		while(p != null|| q != null) {
			
			// Set x to node p's value.
			// if p has reach the end of l1, set to 0.
			int x = (p != null) ? p.val : 0;
			
			//Set y to node q's value.
			// if q has reach the end of l2, set to 0.
			int y = (q != null) ? q.val : 0;
			
			// Set sum = x + y + carry
			int sum = carry + x + y;
			
			// Update carry = sum/10
			carry = sum/10;
			
			// Create a new node with digit value of (sum mod 10)
			// and set it to current node's next
			// then advance current node to next.
			current.next = new ListNode(sum % 10);
			current = current.next; 
			
			// Advance p and q
			if(p!= null)
				p = p.next; // iterator existing linked list's node(not add new node) -> p node = p node's next 
			if(q!= null)
				q = q.next;
		}
		
		// the last carry :
		// Check if carry = 1, if so append a new node with digit 1 to the returning list.
		if(carry > 0) {
			current.next = new ListNode(1);
		}
		
		// Return dummyHead's next node.
		return dummyHead.next;	
			
		}
		
	}

/**
 * Complexity Analysis:
 * 
 * -Time complexity :
 *   - O(max(m,n))
 *   - Assume that m and n represents the length l1 and l2
 *   - the algorithm above iterates at most mas(m,n) times.
 *   
 * - Space complexity : 
 *   - O(max(m,n))
 *   - the length of the new list is at most man(m.n)+1  
 *   
 */
	
	
	


