/*
	January 22, 2020
	@ Prahlad Panthi
*/

@SuppressWarnings("unchecked")
class Stack<T> {
	private T[] stack;
	private int size = 0;
	private int capacity = 0;
	private int top = -1;

	public Stack() {
		this(99); // default capacity is 99
	}

	public Stack(int capacity) {
		this.capacity = capacity;
		stack = (T[]) new Object[capacity];
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public void push(T element) {
		if (size >= capacity || top > size-1)
			throw new RuntimeException("ERROR: Stack Overflow!");
		stack[++top] = element;
		++size; 
	}

	public T pop() {
		if (isEmpty())
			throw new RuntimeException("ERROR: Empty stack!");
		--size;
		return stack[top--];
	}

	public T peek() {
		if (isEmpty())
			throw new RuntimeException("ERROR: Empty Stack!");
		return stack[top];
	}

	public int size() {
		return size;
	}

	public void clear() {
		if (!isEmpty()){
			for (int i = 0; i < size; ++i) 
				stack[i] = null;
			size = 0;
			top = -1;
		}

	}

	@Override
	public String toString() {
		if (isEmpty()) return "[]";
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		for (int i = 0; i < size-1; ++i)
			sb.append(stack[i].toString() + ", ");
		sb.append(stack[size-1].toString() + "]");
		return sb.toString();
	}
}