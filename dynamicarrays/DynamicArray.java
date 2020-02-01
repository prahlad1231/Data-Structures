/*
	January 20, 2020
	@ Prahlad Panthi
*/
@SuppressWarnings("unchecked")

public class DynamicArray<T> implements Iterable<T> {
	private int len = 0;
	private int capacity = 0;
	private T[] arr;

	public DynamicArray() {
		this(10);
	}

	// default capacity will be 10
	public DynamicArray(int capacity) {
		if (capacity < 0)
			throw new IllegalArgumentException("ERROR: Invalid Argument");
		this.capacity = capacity;
		arr = (T[]) new Object[capacity];
	}

	public int size() {
		return len;
	}

	public boolean isEmpty() {
		return size() == 0;
	}

	public T get(int index) {
		if (index < 0 || index >= size()) 
			throw new IndexOutOfBoundsException("ERROR: Invalid index!");
		return arr[index];
	}

	public void set(int index, T element) {
		if(index < 0 || index >= size())
			throw new IndexOutOfBoundsException("ERROR: Invalid index!");
		arr[index] = element;
	}

	// takes O(n)
	public void clear() {
		for(int i = 0; i < size(); ++i)
			arr[i] = null;
	}

	// resize the array if len+1 is greater or equal to capacity
	public void add(T element){
		if (len + 1 >= capacity) 
			if(capacity == 0) capacity = 1;
			else resize();
		arr[len++] = element;
	}

	// takes O(n) as it should copy contents of previous array
	public void resize() {
		// System.out.println("Resizing....\n");
		T[] temp = (T[]) new Object[capacity *= 2];
		for(int i = 0; i < len; ++i)
			temp[i] = arr[i];
		arr = temp;
	}

	// takes O(n)
	public boolean contains(T obj) {
		return indexOf(obj) != -1;
	}

	// takes O(n)
	public void removeAt(int index) {
		if (index < 0 || index >= size())
			throw new IndexOutOfBoundsException("ERROR: Invalid index!");
		for(int i = index; i < size(); ++i) {
			arr[index] = arr[index+1];
		}
		arr[index] = null;
	}

	// takes O(n)
	public <T> void remove(T obj) {
		if(obj == null) 
			System.out.println("ERROR: Invalid input!\n");
		for(int i = 0; i < size(); ++i) {
			if(obj.equals(arr[i]))
				removeAt(i);
		}
	}

	// takes O(n)
	public int indexOf(T obj) {
		if (obj == null) return -1;
		for (int i = 0; i < len; ++i)
			if(obj.equals(arr[i]))
				return i;
		return -1;
	}

	@Override
	public java.util.Iterator<T> iterator() {
		return new java.util.Iterator<T> () {
			int index = 0;

			@Override
			public boolean hasNext() {
				return index < len;
			}

			@Override
			public T next() {
				return arr[index++];
			}

			@Override
			public void remove() {
				throw new UnsupportedOperationException();
			}
		};
	}

	@Override
	public String toString() {
		if (len == 0) return "[]";
		StringBuilder sb = new StringBuilder().append("[");
		for(int i = 0; i < len - 1; ++i) {
			sb.append(arr[i] + ", ");
		}
		sb.append(arr[len-1] + "]");
		return sb.toString();
	}
}