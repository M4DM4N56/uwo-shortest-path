
public class DLPriorityQueue <T>{

	private DLinkedNode<T> front;
	private DLinkedNode<T> rear;
	private int count;
	
	
    public DLPriorityQueue() {
    	front = null;
        rear = null;
        count = 0;
    } // method DLPriorityQueue - constructor
    
    
    public void add(T dataItem, double priority) {
    	DLinkedNode<T> newNode = new DLinkedNode<>(dataItem, priority);
        
    	// if queue is empty, newNode is front and last
        if (isEmpty()) {
        	front = newNode;
        	rear = newNode;
        } // if
        
        // if newNode's priority is less than priority, newNode is now the front
        else if (priority < front.getPriority()) {
        	newNode.setNext(front);
        	front.setPrev(newNode);
        	front = newNode;
        } // elif

        else {
            DLinkedNode<T> current = front;
            
            // cycles to the end of the list, or until the priority is less than the node's priority
            while (current.getNext() !=  null && priority >=  current.getNext().getPriority()) {
                current = current.getNext();
            } // while
            
            newNode.setNext(current.getNext());
            
            // places node in the appropriately placed position in terms of priority
            if (current.getNext() !=  null) {
                current.getNext().setPrev(newNode);
            } // if      
            
            // node is lowest priority
            else {
                rear = newNode;
            } // else
            
            current.setNext(newNode);
            newNode.setPrev(current);
        } // else
        
        count++;
    } // method add
    
    
    public void updatePriority(T dataItem, double newPriority) throws InvalidElementException {
        DLinkedNode<T> current = front;
        boolean hasNode = false;
        
        // cycles through whole queue searching for dataItem
        while (current !=  null && !hasNode) {
        	
            if (current.getDataItem().equals(dataItem)) {
            	hasNode = true;
            } // if
            
            else {
                current = current.getNext();
            } // else
            
        } // while
        
        // exceotion if node not found in queue
        if (!hasNode) {
            throw new InvalidElementException("Priority Queue missing desired element.");
        } // if
        
        
        // removing the node from the priority queue
        // if in front
        if (current ==  front) {
            front = current.getNext();
        } // if
        else {
        	current.getPrev().setNext(current.getNext());
        } // else
        
        // if at end
        if(current ==  rear) {
        	rear = current.getPrev();
        } // if
        else {
        	current.getNext().setPrev(current.getPrev());
        } // else
        

        // eliminated element, so count must shrink
        count--;

        // add the node back with new priority
        add(current.getDataItem(), newPriority);
    } // method updatePriority
    
    
    public T removeMin() throws EmptyPriorityQueueException{
    	// checks if empty
    	if (isEmpty()) {
    		throw new EmptyPriorityQueueException("Empty queue, no element can be removed.");
    	} // if
    	
    	T minData = front.getDataItem();
    	
    	// removing minData from the queue
    	front = front.getNext();
    	
    	if (front ==  null) {
    		rear = null;
    	} // if
    	else {
    		front.setPrev(null);
    	} // else
    	
    	count --;
    	
    	return minData;
    } // method removeMin
    
    
    public boolean isEmpty() {
        return count ==  0;
    } // method isEmpty    
    
   
    public int size() {
    	return count;
    } // method size
    
    
    public String toString() {;
    	String queueString = "";
    	DLinkedNode<T> current = front;
    	
    	// cycling and concatonating each node to the string
    	while (current !=  null) {
    		queueString += current.getDataItem().toString();
    		current = current.getNext();
    	} // while
    	
    	return queueString;
    } // method toString
    
    
    public  DLinkedNode<T> getRear(){
    	return rear;
    } // method getRear
 
    
    
} // class DLPriorityQueue
