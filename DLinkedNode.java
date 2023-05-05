
public class DLinkedNode <T> {
	
	private T dataItem;
	private double priority;
	private DLinkedNode <T> next;
	private DLinkedNode <T> prev;


	// ----- CONSTRUCTORS ----- \\
	public DLinkedNode(T data, double prio) {
        this.dataItem = data;
        this.priority = prio;
        this.next = null;
        this.prev = null;
    } // method DLinkedNode
	

    public DLinkedNode() {
        this(null, 0);
    } // method DLinkedNode
    
    
    
    
    // ----- SETTERS ----- \\
    public void setDataItem(T data) {
        this.dataItem = data;
    } // method setDataItem
    

    public void setNext(DLinkedNode<T> next) {
        this.next = next;
    } // method setNext
    

    public void setPrev(DLinkedNode<T> prev) {
        this.prev = prev;
    } // method setPrev
    

    public void setPriority(double priority) {
        this.priority = priority;
    } // method setPriority
    
    
    
    
    // ----- GETTERS ----- \\
    public double getPriority() {
        return priority;
    } // method getPriority
    

    public T getDataItem() {
        return dataItem;
    } // method getDataItem
    

    public DLinkedNode<T> getNext() {
        return next;
    } // method getNext

    public DLinkedNode<T> getPrev() {
        return prev;
    } // method getPrev
	
} // class DlinkedNode
