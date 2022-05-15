
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * A basic singly linked list implementation.
 */
public class SinglyLinkedList<E> implements Cloneable, Iterable<E>, List<E> {
    public static class Node<E> {
        private Node<E> next;
        private E val;

        public Node(E val, Node<E> n) {
            this.val = val;
            this.next = n;
        }


        public E getVal() {
            return this.val;
        }

        public void setVal(E val) {
            this.val = val;
        }

        public Node<E> getNext() {
            return this.next;
        }

        public void setNext(Node<E> n) {
            this.next = n;
        }
    } //----------- end of nested Node class -----------

    // instance variables of the SinglyLinkedList
    private Node<E> head = null; // head node of the list (or null if empty)

    public int size = 0; // number of nodes in the list

    public SinglyLinkedList() {
    }              // constructs an initially empty list

    // access methods

    /**
     * Returns the number of elements in the linked list.
     *
     * @return number of elements in the linked list
     */
    public int size() {
        return this.size;
    }

    /**
     * Tests whether the linked list is empty.
     *
     * @return true if the linked list is empty, false otherwise
     */
    public boolean isEmpty() {
        if (this.head == null || this.size == 0) {
            return true;
        }
        return false;
    }

    @Override
    public E get(int i) throws IndexOutOfBoundsException {
        if (i < 0 || i >= size())
            throw new IndexOutOfBoundsException("index cannot be a negative number or larger than the size of the linked list.");
        else if (size == 0) {
            System.out.println("List is empty");
            return null;
        } else {
            Node<E> temp = head;
            for (int count = 0; count < i; count++) {
                temp = temp.next;
            }
            return temp.getVal();
        }
    }


    @Override
    public E set(int i, E e) throws IndexOutOfBoundsException {
        E replaced;
        if (i < 0 || i <= size())
            throw new IndexOutOfBoundsException("index cannot be a negative number or larger than the size of the linked list.");
        else {
            int counter = 0;
            Node<E> curr = this.head;
            while (counter != (i - 1)) {
                curr.setNext(curr.getNext());
                counter++;
            }
            replaced = curr.getVal();
            curr.setVal(e);
        }
        return replaced;
    }

    @Override
    public void add(int i, E e) throws IndexOutOfBoundsException {
        if (i < 0 || i >= size())
            throw new IndexOutOfBoundsException("index cannot be a negative number or larger than size.");

        if(i == 0){ // if index is first position
            addFirst(e);
        }
        else {
            Node<E> newNode = new Node<>(e, null);
            Node<E> curr = head;
            int count = 0;
            while (count != i - 1) {
                curr = curr.next;

                count++;
            }
            newNode.setNext(curr.getNext());
            curr.setNext(newNode);
            size++;
        }
    }

    @Override
    public E remove(int i) throws IndexOutOfBoundsException {
        if (i < 0 || i >= size())
            throw new IndexOutOfBoundsException("index cannot be a negative number or larger than size.");
        Node<E> curr = head;
        if (i == 0) {
            head.setNext(curr.getNext());
            return curr.getVal();
        } else {
            Node<E> current = head;
            Node<E> prev = null;
            int counter = 0;
            while (counter != i) {
                prev = current;
                current = current.getNext();
                counter++;
            }
            Node<E> temp = prev.getNext(); // saving to-be deleted node in temp
            prev.setNext(current.getNext()); // deleting node by removing from list
            size--;
            return temp.getVal();
        }

    }

    public E removeLast(){
        if(isEmpty()){
            return null;
        }
        return remove(size() - 1);
    }

    /**
     * Returns (but does not remove) the first element of the list
     *
     * @return element at the front of the list (or null if empty)
     */
    public E first() {
        if (isEmpty()) return null;
        return this.head.getVal();
    }

    /**
     * Returns the last node of the list
     *
     * @return last node of the list (or null if empty)
     */
    public Node<E> getLast() {
        if (size() == 1) {
            return this.head;
        } else {
            Node<E> curr = this.head;
            int counter = 0;
            while (curr.getNext() != null) {
                curr.setNext(curr.getNext());
            }
            return curr;
        }
    }

    /**
     * Returns (but does not remove) the last element of the list.
     *
     * @return element at the end of the list (or null if empty)
     */
    public E last() {

        if (isEmpty()) return null;

        else if (size() == 1){
            return this.head.getVal();
        }
        else{
            Node<E> curr = this.head;
            while(curr.getNext() != null){
                curr = curr.next;
            }
            return curr.getVal();
        }
    }

    // update methods

    /**
     * Adds an element to the front of the list.
     *
     * @param e the new element to add
     */
    public void addFirst(E e) {
        head = new Node<E>(e, head);
        size++;
    }

    /**
     * Adds an element to the end of the list.
     *
     * @param e the new element to add
     */
    public void addLast(E e) {
        Node<E> newest = new Node<E>(e, null); // eventually be the tail
        Node<E> last = head;
        if (last == null) {
            head = newest;
        } else {
            while (last.getNext() != null) {
                last = last.getNext();
            }
            last.setNext(newest);
        }
        size++;
    }

    /**
     * Removes and returns the first element of the list.
     *
     * @return the removed element (or null if empty)
     */
    public E removeFirst() {
        // TODO
        if (isEmpty()) {
            return null;
        }
        Node<E> temp = this.head;
        this.head = null;
        return temp.getVal();
    }

    @SuppressWarnings({"unchecked"})
    public boolean equals(Object o) {
        // TODO
        return false;   // if we reach this, everything matched successfully
    }

    @SuppressWarnings({"unchecked"})
    public SinglyLinkedList<E> clone() throws CloneNotSupportedException {
        SinglyLinkedList<E> twin = new SinglyLinkedList<E>();
        Node<E> tmp = head;
        while (tmp != null) {
            twin.addLast(tmp.getVal());
            tmp = tmp.next;
        }
        return twin;
    }


    /**
     * Produces a string representation of the contents of the list.
     * This exists for debugging purposes only.
     */
    @Override
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

    // LinkedList iterations
    private class SinglyLinkedListIterator<E> implements Iterator<E> {
        Node curr;
        public SinglyLinkedListIterator() {
            curr = head;
        }
        @Override
        public boolean hasNext() {
            if (curr == null) return false;
            else return true;
        }
        @Override
        public E next() {
            E res = (E) curr.getVal();
            curr = curr.getNext();
            return res;
        }
    }


    public Iterator<E> iterator() {
        return new SinglyLinkedListIterator<E>();
    }

    public static void main(String[] args) {
        ArrayList<String> all;
        LinkedList<String> ll;
        SinglyLinkedList<String> sll = new SinglyLinkedList<String>();

        String[] alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".split("");

        for (String s : alphabet) {
            sll.addFirst(s);
            sll.addLast(s);
        }
        System.out.println(sll.toString());

        for (String s : sll) {
            System.out.print(s + ", ");
        }
    }
}

