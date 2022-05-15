public class ArrayQueue<E> implements Queue<E> {
	public static final int DEFAULT_SIZE = 100;
	private E data[];
	private int index;

	public static void main(String[] args) {

	}

	@Override
	public int size() {
		return index;
	}

	@Override
	public boolean isEmpty() {
		return index == 0;
	}

	@Override
	public void enqueue(E e) throws Exception{

		if(index == data.length -1){
			throw new Exception("Queue is FUll\n");
		}
		this.data[index] = e;
		this.index++;
	}

	@Override
	public E first() {
		if (isEmpty()) {
			return null;
		}
		return this.data[0];
	}

	@Override
	public E dequeue(){
		if (isEmpty()){
			return null;

		}
		E removed = this.data[0];
		for (int i = 0; i < this.index - 1; i++){
			data[i] = data[i + 1];
		}
		this.index--;
		return removed;
	}

}
