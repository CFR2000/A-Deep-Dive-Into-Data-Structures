import org.w3c.dom.Node;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedQueue<E> implements Queue<E> {
	private int n;
	private Node<E> first;
	private Node<E> last;

	private class Node<E>{
		private E e;
		private Node next;
	}

	public LinkedQueue(){
		first = null;
		last = null;
		n = 0;
	}

	public static void main(String[] args) {

	}

	@Override
	public int size() {
		return n;
	}

	@Override
	public boolean isEmpty() {
		return first == null;
	}

	@Override
	public void enqueue(E e) {
		Node oldLast = last;
		last = new Node();
		last.e = e;
		last.next = null;
		if (isEmpty()) first = last;
		else oldLast.next = last;
		n++;
	}

	@Override
	public E first() {
		if (isEmpty()) throw new NoSuchElementException("Queue underflow");
		return first.e;
	}

	@Override
	public E dequeue() {
		if (isEmpty()) throw new NoSuchElementException("Queue underflow");
		E e = first.e;
		first = first.next;
		n--;
		if (isEmpty()) last = null;
		return e;
	}

	public Iterator<E> iterator()  {
		return new LinkedIterator();
	}

	// an iterator, doesn't implement remove() since it's optional
	private class LinkedIterator implements Iterator<E> {
		private LinkedQueue.Node current = first;

		public boolean hasNext()  { return current != null;                     }
		public void remove()      { throw new UnsupportedOperationException();  }

		public E next() {
			if (!hasNext()) throw new NoSuchElementException();
			E e = (E) current.e;
			current = current.next;
			return e;
		}
	}

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

}
