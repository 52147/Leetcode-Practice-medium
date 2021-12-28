import java.util.concurrent.locks.ReentrantLock;

/**
 * 622. Design Circular Queue
 * 
 * Improvement: Thread-Safe
 * 
 * - One might be happy with the above implementation, after all it meets all the requirements of the problem.
 * 
 * 
 * - As a followup question though, an interviewer might ask one to point out a potential defect 
 *   about the implementation and the solution.
 * 
 * - This time, it is not about space or time complexity, but concurrency.
 * - Our circular queue is NOT thread-safe.
 * - One could end up with corrupting our data structure in a multi-threaded environment.
 * 
 * 
 * - For example, here is an execution sequence where we exceed the designed capacity of the queue
 *   and overwrite the tail element undesirably.
 *   
 *                      Race conditions:                capacity = 5
 *   
 *   Timeline    Thread #1             Thread #2   
 *   1           enQueue()                                   count = 4
 *   2                                 enQueue()             count = 4
 *   3           if(count<capacity)                          count = 4
 *   4                                 if(count<capacity)    count = 4
 *   5           ...                                         count = 4
 *   6           count += 1                                  count = 5
 *   7                                 ...                   count = 5
 *   8                                 count += 1            count = 6
 *   
 *   Race conditions:
 *    - is an undesirable situation that occurs when a device or system
 *      attempts to perform 2 or more operations at the same time,
 *      but because of the nature of the device or system,
 *      the operations must be done in the proper sequence to be done correctly.
 *      
 *   Class ReentrantLock:
 *   - A reentrant mutual exclusion Lock with the same basic behavior and semantics
 *     as the implicit monitor lock accessed using synchronized methods and statements, 
 *     but with extended capabilities.
 *   - A ReentrantLock is owned by the thread last successfully locking,
 *     but not yet unlocking it.
 *   - A thread invoking lock will return, successfully acquiring the lock,
 *     when the lock is not owned by another thread.
 *   - The method will return immediately if the current thread already owns the lock.
 *     - This can be checked using methods isHeldByCurrentThead(), and getHoldCount().
 *   
 *   
 *   
 * - The above scenario is called race conditions as described in the problem of Print in Order.
 * - One can find more concurrency problems to practice on LeetCode.
 * 
 * - There are several ways to mitigate the above concurrency problem.
 * - Take the function enQueu(int value) as an example,
 *   we show how can we can make the function thread-safe in the following implementation.
 *   
 * 
 * - With the protection of locks, we now feel more confident to apply our circular queue
 *   in critical scenario.
 * 
 * - The improvement does not alter the time and space complexity of the original data structure,
 *   though overall the thread-safe measures do incur some extra costs.  
 *
 */
public class DesignCircularQueue622_1b {
	
	private Node head, tail;
	private int count;
	private int capacity;
	
	// Additional variable to secure the access of our queue
	private ReentrantLock queueLock = new ReentrantLock();
	
	/**
	 * Initialize your data structure here.
	 * Set the size of the queue to be k. 
	 */
	public DesignCircularQueue622_1b(int k) {
		this.capacity = k;
	}
	
	/**
	 * Insert an element into the circular queue.
	 * Return true if the operation is successful.
	 */
	public boolean enQueue(int value) {
		// ensure the exclusive access for the following block.
		queueLock.lock();
		
		try {
			if(this.count == this.capacity)
				return false;
			
			Node newNode = new Node(value);
			
			if(this.count == 0) {
				head = tail = newNode;
			}else {
				tail.nextNode = newNode;
				tail = newNode;
			}
			this.count += 1;
				
		}finally {
			queueLock.unlock();
		}
		return true;
		}
		
	}



