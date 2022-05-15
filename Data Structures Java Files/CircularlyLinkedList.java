import java.util.Iterator;

public class CircularlyLinkedList<E> implements List<E> {
    //---------------- nested Node class ----------------
    /**
     * Singly linked node, which stores a reference to its element and
     * to the subsequent node in the list.
     */
    private static class Node<E> {
        private E element;
        private CircularlyLinkedList.Node<E> next;

        public Node(E e, CircularlyLinkedList.Node<E> n) {
            element = e;
            next = n;
        }
        //Getter for next
        public CircularlyLinkedList.Node<E> getNext() {
            return next;
        }

        //Setter for next
        public void setNext(CircularlyLinkedList.Node<E> next) {
            this.next = next;
        }

        //Getter for element
        public E getElement() {
            return element;
        }

        //Setter for element
        public void setElement(E element) {
            this.element = element;
        }
    } //----------- end of nested Node class -----------

    // instance variables of the CircularlyLinkedList
    /** The designated cursor of the list */
    private Node<E> tail = null;                  // we store tail (but not head)

    /** Number of nodes in the list */
    private int size = 0;                         // number of nodes in the list

    /** Constructs an initially empty list. */
    public CircularlyLinkedList() { }             // constructs an initially empty list

    // access methods
    /**
     * Returns the number of elements in the linked list.
     * @return number of elements in the linked list
     */
    public int size() { return size; }

    /**
     * Tests whether the linked list is empty.
     * @return true if the linked list is empty, false otherwise
     */
    public boolean isEmpty() { return size == 0; }

    @Override
    public E get(int i) throws IndexOutOfBoundsException {
        Node<E> search = tail;

        //Brings us to i
        for(int j=0;j<i;j++){
            search = search.getNext();
        }
        return search.getElement();
    }

    @Override
    public E set(int i, E e) throws IndexOutOfBoundsException {
        Node<E> search = tail;

        //Brings us to Node before i
        for(int j=0;j<i-1;j++){
            search = search.getNext();
        }
        E temp = search.getNext().getElement(); //element of i
        Node<E> newNode = new Node<>(e, search.getNext().getNext());    //Sets next to node after node i
        search.setNext(newNode);  //Node before i - next is set to new node
        return temp;
    }

    @Override
    public void add(int i, E e) throws IndexOutOfBoundsException {
        Node<E> search = tail;

        //Brings us to node before i
        for(int j=0;j<i-1;j++){
            search = search.getNext();
        }

        Node<E> newNode = new Node<E>(e, search.getNext().getNext());
        search.setNext(newNode);
    }

    @Override
    public E remove(int i) throws IndexOutOfBoundsException {
        Node<E> search = tail;

        //Brings us to node before i
        for(int j=0;j<i-1;j++){
            search = search.getNext();
        }
        E temp = search.getNext().getElement();
        search.setNext(search.getNext().getNext()); //Skips node i
        return temp;
    }

    @Override
    public Iterator<E> iterator() {
        return null;
    }

    /**
     * Returns (but does not remove) the first element of the list
     * @return element at the front of the list (or null if empty)
     */
    public E first() {             // returns (but does not remove) the first element
        if(isEmpty()){
            return null;
        }else{
            return tail.getNext().getElement();
        }
    }

    /**
     * Returns (but does not remove) the last element of the list
     * @return element at the back of the list (or null if empty)
     */
    public E last() {              // returns (but does not remove) the last element
        if(isEmpty()){
            return null;
        }else{
            return tail.getElement();
        }
    }

    // update methods
    /**
     * Rotate the first element to the back of the list.
     */
    public void rotate() {         // rotate the first element to the back of the list
        if(tail != null){
            tail = tail.getNext();
        }
    }

    /**
     * Adds an element to the front of the list.
     * @param e  the new element to add
     */
    public void addFirst(E e) {                // adds element e to the front of the list
        if(size() == 0){
            tail = new Node<>(e, null);
            tail.setNext(tail);
        }else{
            Node<E> newest = new Node<>(e, tail.getNext());
            tail.setNext(newest);
        }
    }

    /**
     * Adds an element to the end of the list.
     * @param e  the new element to add
     */
    public void addLast(E e) {                 // adds element e to the end of the list
        addFirst(e);    //Adds it first as tail, then moves tail back to original tail
        tail = tail.getNext();
    }

    /**
     * Removes and returns the first element of the list.
     * @return the removed element (or null if empty)
     */
    public E removeFirst() {                   // removes and returns the first element
        if(isEmpty()){
            return null;
        }else{
            Node<E> head = tail.getNext();
            if(head == tail){   //If only length 1
                tail = null;
            }else{
                tail.setNext(head.getNext());
                size--;
            }
            return head.getElement();
        }
    }

    /**
     * Produces a string representation of the contents of the list.
     * This exists for debugging purposes only.
     */
    public String toString() {
        Iterator<E> it = iterator();
        if (! it.hasNext())
            return "[]";

        StringBuilder sb = new StringBuilder();
        sb.append('[');
        for (;;) {
            E e = it.next();
            sb.append(e);
            if (! it.hasNext())
                return sb.append(']').toString();
            sb.append(", ");
        }
    }


    public static void main(String [] args) {
        //ArrayList<String> all;
        //LinkedList<String> ll;
        CircularlyLinkedList<String> ll = new CircularlyLinkedList<>();

        String[] alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".split("");

        for (String s : alphabet) {
            ll.addFirst(s);
            ll.addLast(s);
        }
        System.out.println(ll.toString());

        ll.rotate();
        ll.rotate();

        for (String s : ll) {
            System.out.print(s + ", ");
        }

    }
}
