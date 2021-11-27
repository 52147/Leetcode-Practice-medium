import java.util.Map;

/**
 * 
 * 146. LRUCache
 *  
 *  Approach 2: HashMap + DoubleLinkedList
 *  
 *  Intuition:
 *  
 *   - The problem can be solved with a hashmap that keeps track of the keys and its value in the double linked list.
 *   - The result in O(1) time for put and get operations arnd allows to removw the first added node in O(1) time as well.
 *   
 *   head -> key1 : value1 -> key2 : value2 -> tail
 *        <-               <-               <-
 *   
 *   key1 -> value1
 *   key2 -> value2
 *   
 *    - One advantage of double linked list is that the node can remove itself without other reference.
 *    - In addition, it takes constant time to add and remove nodes from the head or tail.
 *    
 *    - One particularity about the double linked list implemented here is that there are pseudo head and pseudo tail to mark the boundary,
 *      so that we don't need to check the null node during the update.
 *      
 *      Easy to add the node even in the empty list
 *      
 *      head -> tail
 *           <-
 *       
 *
 *
 */
public class LRUCache146_2 {

	class DLinkedNode {
		int key;
		int value;
		DLinkedNode prev;
		DLinkedNode next;
	}

	private void addNode(DLinkedNode node) {
		/**
		 * Always add the new node right after head.
		 */

		node.prev = head;
		node.next = head.next;

		head.next.prev = node;
		head.next = node;

	}

	private void removeNode(DLinkedNode node) {
		/**
		 * Remove an existing node from the linked list.
		 */
		DLinkedNode prev = node.prev;
		DLinkedNode next = node.next;

		prev.next = next;
		next.prev = prev;
	}

	private void moveToHead(DLinkedNode node) {
		/**
		 * Move certain node in between to the node.
		 */
		removeNode(node);
		addNode(node);
	}

	private DLinkedNode popTail() {

		/**
		 * Pop the current tail.
		 */
		DLinkedNode res = tail.prev;

		removeNode(res);
		return res;
	}

	private Map<Integer, DLinkedNode> cache = new HashMap<>();

	private int size;
	private int capacity;
	private DLinkedNode head, tail;

	public LRUCache146_2(int capacity) {
		this.size = 0;
		this.capacity = capacity;
		head = new DLinkedNode();
		// head.prev = null;

		tail = new DLinkedNode();
		// tial.next = null;

		head.next = tail;
		tail.prev = head;
	}

	public int get(int key) {
		DLinkedNode node = cache.get(key);
		if (node == null)
			return -1;

		// move the accessed node to the head.
		moveToHead(node);

		return node.value;
	}

	public void put(int key, int value) {
		DLinkedNode node = cache.get(key);

		if (node == null) {

			DLinkedNode newNode = new DLinkedNode();
			newNode.key = key;
			newNode.value = value;

			cache.put(key, newNode);
			addNode(newNode);

			++size;

			if (size > capacity) {
				// pop the tail
				DLinkedNode tail = popTail();
				cache.remove(tail.key);
				--size;
			}
		} else {
			// update the value.
			node.value = value;
			moveToHead(node);
		}
	}
}
