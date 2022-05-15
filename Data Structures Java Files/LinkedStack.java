import org.w3c.dom.Node;

import java.util.EmptyStackException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;


public class LinkedStack<E> implements Stack<E> {
	private int n;
	private Node<E> first;

	private class Node<E> {
		private E e;
		private Node<E> next;
	}

	public LinkedStack() {
		first = null;
		n = 0;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub


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
	public void push(E e) {
		Node<E> oldFIrst = first;
		first = new Node<>();
		first.e = e;
		first.next = oldFIrst;
		n++;
	}

	@Override
	public E top() {
		if (isEmpty()) throw new NoSuchElementException("Stack underflow");
		return first.e;
	}

	@Override
	public E pop() {
		if (isEmpty()) throw new NoSuchElementException("Stack underflow");
		E e = first.e;
		first = first.next;
		n--;
		return e;
	}


	public Iterator<E> iterator() {
		return new LinkedIterator();
	}

	// an iterator, doesn't implement remove() since it's optional
	private class LinkedIterator implements Iterator<E> {
		private LinkedStack.Node current = first;

		public boolean hasNext() {
			return current != null;
		}

		public void remove() {
			throw new UnsupportedOperationException();
		}

		public E next() {
			if (!hasNext()) throw new NoSuchElementException();
			E e = (E) current.e;
			current = current.next;
			return e;
		}
	}

	public String toString() {
		Iterator<E> it = iterator();
		if (!it.hasNext())
			return "[]";

		StringBuilder sb = new StringBuilder();
		sb.append('[');
		for (; ; ) {
			E e = it.next();
			sb.append(e);
			if (!it.hasNext())
				return sb.append(']').toString();
			sb.append(", ");
		}

	}
}
