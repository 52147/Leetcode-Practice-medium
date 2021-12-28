/**
 * 622. Design Circular Queue
 * 
 * Q:
 *   - Design your implementation of the circular queue.
 *   - The circular queue is a linear data structure in which the operations are performed
 *     based on FIFO(First in First out) principle and
 *     the last position is connected back to the first position to make a circle.
 *   - It is also called "Ring Buffer".
 *   
 *   - One of the benefits of the circular queue is that we can make use of the spaces
 *     in front of the queue.
 *   - In a normal queue, once the queue becomes full,
 *     we cannot insert the next element even if there is a space in front of the queue.
 *   - But using the circular queue, we can use the space to store new values.
 *   
 *   Implementation the MyCircularQueue class:
 *   
 *     - MyCircularQueue(k) 
 *       Initializes the object with the size of the queue to be k.
 *       
 *     - int Front() 
 *       Gets the front item from the queue. If the queue is empty, return -1.
 *       
 *     - int Rear() 
 *       Gets the last item from the queue. If the queue is empty, return -1.
 *       
 *     - boolean enQueue(int value) 
 *       Inserts an element into the circular queue. Return true if the operation is successful.
 *       
 *     - boolean deQueue() 
 *       Deletes an elements from the circular queue. Return true if the operation is successful.
 *       
 *     - boolean isEmpty() 
 *       Checks whether the circular queue is empty or not.
 *       
 *     - boolean isFull() 
 *       Checks whether the circular queue is full or not.
 *     
 *  - Example 1:
 *  
 *  Input:
 *  ["MyCircularQueue", "enQueue", "enQueue", "enQueue", "enQueue", "Rear", "isFull", "deQueue", "enQueue", "Rear"]
 *  
 *  [[3], [1], [2], [3], [4], [], [], [], [4], []]
 *  
 *  Output:
 *  [null, true, true, true, false, 3, true, true, true, 4]
 *  
 *  Explanation:
 *  
 *  MyCircularQueue myCircularQueue = new MyCircularQueue(3);
 *  
 *  myCircularQueue.enQueue(1); // return True
 *  myCircularQueue.enQueue(2); // return True
 *  myCircularQueue.enQueue(3); // return True
 *  myCircularQueue.enQueue(4); // return True
 *  
 *  myCircularQueue.Rear(); // return 3
 *  myCircularQueue.isFull(); // return True
 *  myCircylarQueue.deQueue(); // return True
 *  
 *  myCircularQueue.enQueue(4); // return True
 *  myCircularQueue.Rear(); // return 4
 *  
 *  Constraints:
 *  
 *   1 <= k <= 1000
 *   0 <= value <= 1000
 *   At most 3000 calls will be made to enQueue, deQueue, Front, Rear, isEmpty, and isFull.
 * 
 * 
 * Solution:
 * 
 * Approach 1: Array
 * 
 *  Intuition:
 *  
 *  - Based on the description of the problem, an intuitive data structure that
 *    meets all the requirements 
 *    would be a ring where the head and the tail are adjacent to each other.
 *    
 *  - However, there does not exist a ring data structure in any programming language.
 *  - A similar data structure at our disposal is the one called Array
 *    which is a collection of elements that reside continuously in one dimensional space.
 *    
 *  - In this case, to build a circular queue, we could form a virtual ring structure with the Array,
 *    via the manipulation of index.  
 *    
 *  - Given a fixed size array, any of the elements could be considered as a head in a queue.
 *  - As long as we know the length of the queue, we then can instantly locate its tail,
 *    based on the following formula:
 *    
 *    tailIndex = (headIndex + count - 1) mod capacity
 *    
 *      - where the variable capacity is the size of the array, 
 *        the count is the length of the queue and the headIndex and 
 *        tailIndex are the indice of head and tail elements respectively in the array.
 *        
 *        
 *  - Here we showcase a few example how circular queue could reside in a fixed size array.
 *    
 *    
 *       Array capacity = 5
 *       Queue size = 3
 *       
 *       Head     Tail
 *       |   |   |   |   |   |
 *         0   1   2   3   4
 *         
 *         
 *            Head    Tail
 *       |   |   |   |   |   |
 *         0   1   2   3   4
 *         
 *             
 *       Tail         Head     
 *       |   |   |   |   |   |
 *         0   1   2   3   4
 *
 *
 * Algorithm:
 * 
 *  - The procedure to design a data structure lies essentially on
 *    how we design the attributes within the data structure.
 *   
 *  - One of the traits of a good design is to have as less attributes as possible,
 *    which arguably could bring several benefits.
 *    
 *    - Less attributes usually implies little or no redundancy among the attributes.
 *    - The less redundant the attributes are, the simpler the manipulation logic,
 *      which eventually could be less error-prone.
 *    - Less attributes also requires less space and therefore it could also bring efficiency
 *      to the runtime performance.
 *      
 *  - However, it is not advisable to seek for the minimum set of attributes.
 *  - Sometimes, a bit of redundancy could help with the time complexity.
 *  - After all, like many other problems, we are trying to strike a balance between the space and the time.
 *  
 *  
 *  - Following the above principle, here we give a list of attributes and 
 *    the thoughts behind each attribute.
 *    
 *    -1. queue:
 *     - a fixed size array to hold the elements for the circular queue.
 *     
 *    -2. count:
 *     - the current length of the circular queue, i.e. the number of elements in the circular queue.
 *     - Together with the headIndex, we could locate the current tail elements in the circular queue,
 *       based on the formula we gave previously.
 *     - Therefore, we choose not to add another attribute for the index of tail.
 *     
 *    -3. capacity:
 *     - the capacity of the circular queue, i.e. the maximum number of elements that can be hold in the queue.
 *     - One might argument that it is not absolutely necessary to add this attribute,
 *       since we could obtain the capacity from the queue attribute.
 *     - It is true.
 *     
 *     - Yet, since we would frequently use this capacity in out algorithm,
 *       we choose to keep it as an attribute, instead of invoking function len(queue) in Python at every occasion.
 *       
 *     - Though in other programming languages such as Java, it might be more efficient to 
 *       omit this attribute, since it is part of the attributes(queue.length) in Java array.
 *       - Note: for the sack of consistency, we keep this attribute for all implementations.       
 *     
 * 
 * 
 */
public class DesignCircilarQueue622_1a {
	
	private int[] queue;
	private int headIndex;
	private int count;
	private int capacity;
	
	/**
	 * Initialize your data structure here.
	 * Set the size of the queue to be k.
	 */
	public DesignCircilarQueue622_1a(int k) {
		this.capacity = k;
		this.queue = new int[k];
		this.headIndex = 0;
		this.count = 0;
	}
	
	/**
	 * Insert an element into the circular queue.
	 * Return true if the operation is successful.
	 */
	public boolean enQueue(int value) {
		if(this.count == this.capacity)
			return false;
		this.queue[(this.headIndex + this.count) % this.capacity] = value;
		this.count += 1;
		return true;
	}
	
	/**
	 * Delete an element from the circular queue.
	 * Return true if the operation is successful.
	 */
	public boolean deQueue() {
		if(this.count == 0)
			return false;
		this.headIndex = (this.headIndex + 1) % this.capacity;
		this.count -= 1;
		return true;
	}
	
	/**
	 * Get the front item from the queue.
	 */
	public int Front() {
		if(this.count == 0)
			return -1;
		return this.queue[this.headIndex];
	}
	
	/**
	 * Get the last item from the queue.
	 */
	public int Rear() {
		if(this.count == 0)
			return -1;
		int tailIndex = (this.headIndex + this.count - 1) % this.capacity;
		return this.queue[tailIndex];
	}
	
	/**
	 * Check whether the circular queue is empty or not.
	 */
	public boolean isEmpty() {
		return (this.count == 0);
	}
	
	public boolean isFull() {
		return (this.count == this.capacity);
	}

}

/**
 * Complexity:
 * 
 * - Time complexity:
 *   - O(1)
 *   - All of the methods in our circular data structure is of constant time complexity.
 *   
 * - Space complexity:
 *   - O(N)
 *   - The overall space complexity of the data structure is linear,
 *     where N is the pre-assigned capacity of the queue.
 *   - However, it is worth mentioning that the memory consumption of the data structure
 *     remains as its pre-assigned capacity during its entire life cycle.
 */




