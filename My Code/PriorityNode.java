
public class PriorityNode<T> {

	private T dataItem;						//Reference to the data item stored in this node
	private PriorityNode<T>next;				//reference to the next node in the list
	private PriorityNode<T>previous;			//reference to the previous node in the linked list
	private double priority;					//priority of the data item stored in this node
	
	/*
	 * creates a node storing the given data and priority.
	 */
	public PriorityNode(T data, double prio) {
		dataItem = data;
		priority = prio; 
	}

	/*
	 * Creates an empty node, with null data and priority zero.
	 */
	public PriorityNode() {
		dataItem = null;
		priority = 0;
	}
	
	/*
	 * Returns priority
	 */
	public double getPriority(){
		return priority;
	}
	
	/*
	 * Returns reference to the data item stored in this node 
	 */
	public T getDataItem() {
		return dataItem;
	}
	
	/*
	 * Returns the reference to the next node in the list
	 */
	public PriorityNode<T> getNext(){
		return next;
	}
	
	/*
	 * Returns the reference to the previous node in the list
	 */
	public PriorityNode<T> getPrevious(){
		return previous;
	}
	
	/*
	 * Sets the data Item.
	 */
	public void setDataItem(T dataItem) {
		this.dataItem = dataItem;
	}
	
	/*
	 * Sets as the next data Item
	 */
	public void setNext(PriorityNode<T> next) {
		this.next = next;
	}
	
	/*
	 * Sets the previous data item
	 */
	public void setPrevious(PriorityNode<T> prev) {
		previous = prev;
	}
	
	/*
	 * Sets the priority.
	 */
	public void setPriority(double priority) {
		this.priority = priority;
	}
}
