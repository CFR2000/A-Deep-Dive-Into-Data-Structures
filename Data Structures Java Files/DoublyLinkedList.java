import java.util.Iterator;
// passing 5/10
public class DoublyLinkedList<E> implements List<E> {

    //---------------- nested Node class ----------------
    /**
     * Node of a doubly linked list, which stores a reference to its
     * element and to both the previous and next node in the list.
     */
    private static class Node<E> {
        private E element;
        private Node<E> prev;
        private Node<E> next;

        public Node(E e, Node<E> p, Node<E> n){
            element = e;
            prev = p;
            next = n;
        }

        public E getElement(){
            return element;
        }

        public Node<E> getPrev(){
            return prev;
        }

        public Node<E> getNext() {
            return next;
        }

        public void setPrev(Node<E> p){
            prev = p;
        }

        public void setNext(Node<E> n){
            next = n;
        }

        public void setElement(E element) {
            this.element = element;
        }
    } //----------- end of nested Node class -----------

    // instance variables of the DoublyLinkedList
    /** Sentinel node at the beginning of the list */
    private Node<E> header;                    // header sentinel

    /** Sentinel node at the end of the list */
    private Node<E> trailer;                   // trailer sentinel

    /** Number of elements in the list (not including sentinels) */
    private int size = 0;                      // number of elements in the list

    /** Constructs a new empty list. */
    public DoublyLinkedList() {
        header = new Node<>(null, null, null);
        trailer = new Node<>(null, null, null);
        header.setNext(trailer);
    }

    // public accessor methods
    /**
     * Returns the number of elements in the linked list.
     * @return number of elements in the linked list
     */
    public int size() {
        return size;
    }

    /**
     * Tests whether the linked list is empty.
     * @return true if the linked list is empty, false otherwise
     */
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public E get(int i) throws IndexOutOfBoundsException {
        Node<E> search = header;

        //Will bring us to i
        for(int j=0; j<i; j++){
            search = search.getNext();
        }
        return search.getElement();
    }

    @Override
    public E set(int i, E e) throws IndexOutOfBoundsException {
        Node<E> search = header;

        //Will bring us to i
        for(int j=0; j<i; j++){
            search = search.getNext();
        }
        Node<E> prev = search.getPrev();
        Node<E> next = search.getNext();
        E temp = search.getElement();

        Node<E> newNode = new Node<E>(e , search.getPrev(), search.getNext());  //Puts in the new node
        prev.setNext(next); //Sets new node as next of previous
        next.setPrev(newNode);  //Sets new node as previous of next
        return temp;
    }

    @Override
    public void add(int i, E e) throws IndexOutOfBoundsException {
        Node<E> search = header;

        //Will bring us to i
        for(int j=0; j<i; j++){
            search = search.getNext();
        }
        Node<E> prev = search.getPrev();
        Node<E> next = search.getNext();
        E temp = search.getElement();

        //Add in and connect to prev/next
        Node<E> newNode = new Node<>(e, prev, next);
        prev.setNext(newNode);
        next.setPrev(newNode);
    }

    @Override
    public E remove(int i) throws IndexOutOfBoundsException {
        Node<E> search = header;

        //Get to i
        for(int j=0;j<i;j++){
            search = search.getNext();
        }
        Node<E> prev = search.getPrev();
        Node<E> next = search.getNext();
        E temp = search.getElement();

        //Remove and connect prev and next
        prev.setNext(next);
        next.setPrev(prev);
        return temp;
    }

    @Override
    public Iterator<E> iterator() {
        return null;
    }

    /**
     * Returns (but does not remove) the first element of the list.
     * @return element at the front of the list (or null if empty)
     */
    public E first() {
        if(isEmpty()){
            return null;
        }else{
            return header.getNext().getElement(); //First element is after header
        }
    }

    /**
     * Returns (but does not remove) the last element of the list.
     * @return element at the end of the list (or null if empty)
     */
    public E last() {
        if(isEmpty()){
            return null;
        }else{
            return trailer.getPrev().getElement();  //Last element is before trailer
        }
    }

    // public update methods
    /**
     * Adds an element to the front of the list.
     * @param e   the new element to add
     */
    public void addFirst(E e) {
        addBetween(e, header, header.getNext());
    }

    /**
     * Adds an element to the end of the list.
     * @param e   the new element to add
     */
    public void addLast(E e) {
        addBetween(e, trailer.getPrev(), trailer);
    }

    /**
     * Removes and returns the first element of the list.
     * @return the removed element (or null if empty)
     */
    public E removeFirst() {
        if(isEmpty()){
            return null;
        }else{
            return remove(header.getNext());
        }
    }

    /**
     * Removes and returns the last element of the list.
     * @return the removed element (or null if empty)
     */
    public E removeLast() {
        if(isEmpty()){
            return null;
        }else{
            return remove(trailer.getPrev());
        }
    }

    // private update methods
    /**
     * Adds an element to the linked list in between the given nodes.
     * The given predecessor and successor should be neighboring each
     * other prior to the call.
     *
     * @param predecessor   node just before the location where the new element is inserted
     * @param successor     node just after the location where the new element is inserted
     */
    private void addBetween(E e, Node<E> predecessor, Node<E> successor) {
        Node<E> newest = new Node<>(e, predecessor, successor);
        predecessor.setNext(newest);
        successor.setPrev(newest);
        size++;
    }

    /**
     * Removes the given node from the list and returns its element.
     * @param node    the node to be removed (must not be a sentinel)
     */
    private E remove(Node<E> node) {
        Node<E> prev = node.getPrev();
        Node<E> next = node.getNext();
        prev.setNext(next);
        next.setPrev(prev);
        size--;
        return node.getElement();
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
        DoublyLinkedList<String> ll = new DoublyLinkedList<String>();

        String[] alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".split("");

        for (String s : alphabet) {
            ll.addFirst(s);
            ll.addLast(s);
        }
        System.out.println(ll.toString());

        for (String s : ll) {
            System.out.print(s + ", ");
        }
    }
} //----------- end of DoublyLinkedList class -----------
