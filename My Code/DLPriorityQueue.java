
public class DLPriorityQueue<T> implements PriorityQueueADT<T>{
	private PriorityNode<T> front; 				//reference to first node of the doubly linked list
	private PriorityNode<T> rear;				//reference to the last node of the doubly linked list
	private int count;							//number of data items in the priority queue
	
	/*
	 * Constructor that creates an empty priority queue.
	 */
	public DLPriorityQueue() {
		count = 0;
		front = rear = null;
	}

	/*
	 * Adds to the priority queue the given data item associated its priority
	 * New data item is added to the rear of the doubly linked list
	 * @param dataItem is the data item to be added
	 * @param priority is the priority associated with the data item
	 */
	public void enqueue (T dataItem, double priority) {
		PriorityNode<T> node = new PriorityNode<T>(dataItem, priority);
		if (isEmpty()) {
			front = node;
		}else {
			rear.setNext(node);
			node.setPrevious(rear);
		}
		rear = node;
		count++;
	}
	
	/**
	 * Removes the element from the front of the priority queue
	 * @return element removed from the front of the queue
	 */
	public T dequeue() throws EmptyPriorityQueueException{
		if (isEmpty()) {throw new EmptyPriorityQueueException ("Priority is empty, unable to dequeue.");}
		
		T dequeued = front.getDataItem();
	    front = front.getNext();
	    count--;

	     if (isEmpty()) {rear = null;}

	     return dequeued;
		

	}
	
	/*
	 * Returns the priority of the specified dataItem
	 * InvalidDataItemException is thrown if the given data item is not in the priority queue
	 */
	public double getPriority (T dataItem) throws InvalidDataItemException{
		PriorityNode <T> current = front;
		if(isEmpty()) {
			throw new InvalidDataItemException ("The priority of the requested data item could not be found because the queue is empty.");
		}
		double priority = 0;
		while(current != null){
			if(current.getDataItem().equals(dataItem)) {
				priority = current.getPriority();
				break;
			}	
			if(current.getNext() == null) {
				throw new InvalidDataItemException ("The priority of the requested data item could not be found because the requested data item is not in the priority queue.");
			}
			current = current.getNext();
		}	

		return priority;
	}
	
	/*
	 * Changes the priority of the specified data item to a new value. 
	 * Invalid data item exception is thrown if the given dataItem is not in the priority queue. 
	 * @param dataitem 
	 * 		whose priority is to be changed
	 * @param newPriority 
	 * 		value of the new priority of this element
	 */
	public void changePriority(T dataItem, double newPriority) throws InvalidDataItemException{
		PriorityNode <T> current = front;
		if(isEmpty()) {
			throw new InvalidDataItemException ("Unable to change the priority of the requested data item because the queue is empty.");
		}
		while(current != null) {
			if(current.getDataItem().equals(dataItem)) {
				current.setPriority(newPriority);
				break;
			}
			if(current.getNext() == null) {
				throw new InvalidDataItemException ("Unable to change the priority of the requested data item because the data item is not in the priority queue.");
			}
			current = current.getNext();
			
		}
	}
	/**
	 * Removes and returns the data item from the priority queue with smallest priority.
	 * 
	 * @return T smallest priority element removed from the priority queue
	 */
	public T getSmallest() throws EmptyPriorityQueueException{
		if (isEmpty()) {throw new EmptyPriorityQueueException ("Priority is empty, unable to get the smallest data item.");}
		PriorityNode <T> current = front;
		PriorityNode <T> smallestPriorityNode = front; 

		while(!current.equals(null)) {
			if(current.getPriority() < smallestPriorityNode.getPriority()) {
				smallestPriorityNode = current; 
			}
			if(current.getNext() == null) {break;}
			current = current.getNext();
		}
		
		if(count == 1) {
			front = null;
			rear = null;
		}
		
		else if(smallestPriorityNode.equals(front)) {
			front = smallestPriorityNode.getNext();
			front.setPrevious(null);
		}
		else if(smallestPriorityNode.equals(rear)) {
			rear = smallestPriorityNode.getPrevious();
			rear.setNext(null);
		}
		else {
		smallestPriorityNode.getPrevious().setNext(smallestPriorityNode.getNext());
		smallestPriorityNode.getNext().setPrevious(smallestPriorityNode.getPrevious());
		}
		count--; 
		return smallestPriorityNode.getDataItem();
	
	}



	/**
	 * Returns true if this priority queue contains no elements.
	 * @return boolean whether or not this priority queue is empty
	 */
	public boolean isEmpty() {
		return count == 0;
	}

	/**
	 * Returns the number of elements in this priority queue.
	 * @return int number of elements in this priority queue
	 */
	public int numItems() {
		 return count;
	}

	/**
	 * Returns a string representation of this priority queue.
	 * @return String representation of this priority queue
	 */
	public String toString() {
		PriorityNode<T> current = front;
		String representation = "";
		representation = front.getDataItem().toString();
		while(!current.equals(rear)) {
			representation = representation + ", " + current.getNext().getDataItem().toString() + ", " + current.getNext().getPriority();
			current = current.getNext();

		}
	    return representation;
	}
}
