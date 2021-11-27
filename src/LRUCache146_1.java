import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 146. LRU Cache
 * 
 * - Least recently used (LRU)
 *   
 *   - Discards the lease recently used items first.
 *   - This algorithm requires keeping track of what was used when, which is expensive if one wants to make sure the algorithm 
 *     always discard the least recently used item.
 *   - General implementations of this technique require keeping "age bits" for cache-lines and track the "Least Recently Used" cache-line based on age-bits.
 *   - In such an implementation, every time a cache-line is used, the age of  all other cache-lines changes.
 *   
 *   The access sequence for the below example is A->B->C->D->E->F
 *   
 *    - ABCD get installed in the blocks with sequence numbers (increment 1 for each new access)
 *      and when E is accessed, it is a miss and it need to be installed in one of the blocks.
 *    - According to the LRU Algorithm, since A has the lowest Rank(A(0)), E will replace A.
 *    - In the second to last step, D is accessed and therefore the sequence number is updated.  
 *   
 *    - LRU like many other replacement policies, can be characterized using a state transition field in vector space,
 *      which decides the dynamic cache state changes similar to how an electromagnetic field determines the movement of a charged particle placed in it.  
 *   
 *   Access sequence:
 *   
 *   A->B->C->D->E->F
 *   
 *   | A(0) |      |      |      |
 *   
 *   | A(0) | B(1) |      |      | 
 *  
 *   | A(0) | B(1) | C(2) |      |
 *    
 *   | A(0) | B(1) | C(2) | D(3) | 
 *   
 *   | E(4) | B(1) | C(2) | D(3) |   Access E(miss)-> E replace with A, because A is the least recently used item
 * 
 *   | E(4) | B(1) | C(2) | D(5) |   Access D -> update the D sequence number to 5
 *   
 *   | E(4) | F(6) | C(2) | D(5) |   Access F(miss) -> F replace B, because B is the lest recently used item
 *   
 *   
 * 
 * Q:
 *  - Design a data structure that follows the constraints of a Least Recently Used (LRU) cache.
 *  
 *  - implement the LRUCache class:
 *  
 *     - LRUCache(int capacity):
 *     
 *       - Initialize the LRU cache with positive size capacity
 *       
 *     - int get(int key):
 *     
 *       - Return the value of the key if the key exists, otherwise return -1.
 *       
 *     - void put(int key, int value):
 *     
 *       - Update the value of the key if the key exists.
 *       - Otherwise, add the key-value pair to the cache.
 *       - If the number of keys exceeds the capacity from this operation, evict the least recently used key. 
 *       
 *   - The function get and put must each run in O(1) average time complexity.
 *   
 *   
 * ex 1:
 * 
 *   - Input :
 *     
 *     ["LRUCache", "put", "put", "get", "put", "get", "put", "get", "get", "get"]
 *     
 *     [[2],[1, 1], [2, 2], [1], [3, 3], [2], [4, 4], [1], [3], [4]]
 *     
 *   - Output:
 *   
 *     [null, null, null, 1 , null, -1, null, -1, 3, 4]
 *     
 *     
 *   - Explanation:
 *   
 *     LRUCache lRUCache = new LRUCache(2);
 *     lRUCache.put(1, 1); // cache is {1 = 1}
 *     lRUCache.put(2, 2); // cache is {1 = 1 , 2 = 2}
 *     lRUCahce.get(1); // return 1
 *     lRUCache.put(3, 3); //LRU key was 2, evicts key 2, cache is {1=1, 3=3}
 *     lRUCache.get(2); // return -1 (not found)
 *     lRUCache.put(4, 4); //LRU key was 1, evicts key 1, cache is {4=4, 3=3}
 *     lRUCache.get(1); // return -1 (not found)
 *     lRUCache.get(3); // return 3
 *     lRUCache.get(4); // return 4
 *     
 *  Constraints:
 *   
 *   1 <= capacity <= 100
 *   0 <= key <= 10^4
 *   0 <= value <= 10^5
 *   At most 2*10^5 calls will be made to get and put 
 *   
 *  Solution:
 *  
 *   Approach 1: Ordered dictionary
 *   
 *     Intuition:
 *      
 *      - We're asked to implement the structure which provides the following operation in O(1) time:
 *        - Get the key/ Check if the key exists
 *        - Put the key
 *        - Delete the first added key
 *      - The first 2 operation in O(1) time are provide by the standard hashmap, and the last one- by linked list.
 *      
 *        - There is a structure called ordered dictionary, it combines behind both hashmap and linked list.
 *        - In Python this structure is called OrderedDict and in Java LinkedHashMap
 *        - Let's use LinkedHashMap here.
 *     
 *   
 * 
 *
 */
public class LRUCache146_1 extends LinkedHashMap<Integer, Integer>{
	
	private int capacity;
	
	public LRUCache146_1(int capacity) {
		super(capacity, 0.75F, true); 
		// Map implementations in Java have default load factor of 0.75 
		// which means that they rehash whole map when size > 0.75*capacity.
		this.capacity = capacity;
	}
	
	public int get (int key) {
		return super.getOrDefault(key,-1); 
		// Returns the value to the key is mapped, or defaultValue if this map contains no mapping for the key.
	}
	
	public void put(int key, int value) {
		super.put(key, value);
	}
	
	@Override
	// override the base-class(LinkedHashMap class) method
	// Use the removeEldestEntry method of the LinkedHashMap class in this class
	protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
		return size() > capacity;
	}

}
/**
 * Your LRUCache object will be instantiated and called as such:
 * 
 *  LRUCache obj = new LRUCache(capacity);
 *  int param_1 = obj.get(key);
 *  obj.put(key, value);
 * 
 */

/**
 *  Complexity Analysis:
 *  
 *   - Time complexity:
 *     - O(1)
 *     - both for put and get since all operations with ordered dictionary:
 *       get/in/set/move_to_end/popitem (get/ containsKey/ put/ remove) are done in a constant time.
 *       
 *   - Space complexity:
 *     - O(capacity)
 *     - since the space is used only for an ordered dictionary with at most capacity + 1 elements/    
 *     
 * 
 */







