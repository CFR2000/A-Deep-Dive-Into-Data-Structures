public class ArrayStack<E> implements Stack<E> {
	public static final int CAPACITY = 1000; // default array capacity
	private E[] data; // generic array used for storage.
	private int t = -1; // index of the top element in stack


	public static void main(String[] args) {
			}

	@Override
	public int size() {
		return (t+1);
	}

	@Override
	public boolean isEmpty() {
		return (t == -1);
	}

	@Override
	public void push(E e) {
		if (size() > CAPACITY){
			new IllegalArgumentException("Stack Overflow"); // Max capacity
			return;
		}
		data[++t] = e; // pushing to top of stack
		return;
	}

	@Override
	public E top() {
		if(t == -1){ // empty condition
			return null;
		}
		return data[size()]; // returns top
	}

	@Override
	public E pop() {
		if(t == -1){
			new IllegalArgumentException("Stack Underflow");
			return null;
		}
		return(data[t--]); // decrementing stack and removing top
	}

}
