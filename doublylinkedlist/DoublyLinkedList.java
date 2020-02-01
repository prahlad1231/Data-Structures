/*
	January 21, 2020
	@ Prahlad Panthi
*/
@SuppressWarnings("unchecked")
public class DoublyLinkedList<T> implements Iterable{
	private int size = 0;
	private Node<T> head = null;
	private Node<T> tail = null;

	// internal class for each node of the linked list
	private static class Node<T> {
		private T data;
		private Node<T> prev, next;

		public Node(T data, Node<T> prev, Node<T> next) {
			this.data = data;
			this.prev = null;
			this.next = null;
		}

		@Override
		public String toString() {
			return data.toString();
		}
	}

	// empty the list, takes O(n) time
	public void clear() {
		Node<T> temp = head;
		while (temp != null) {
			Node<T> next = temp.next;
			temp.prev = null;
			temp.next = null;
			temp.data = null;
			temp = next;
		}
		head = tail = temp = null;
		size = 0;
	}

	// returns size of linked list, takes O(1) time
	public int size() {
		return size;
	}

	// returns true if linked list is empty, else returns false, takes O(1) time
	public boolean isEmpty() {
		return size == 0;
	}

	public void add(T data) {
		addLast(data);
	}

	// takes O(1)
	public void addLast(T data) {
		if(isEmpty()) {
			head = tail = new Node<T> (data, null, null);
		} else {
			tail.next = new Node<T> (data, tail, null);
			tail = tail.next;
		}
		size++;
	}

	// takes O(1)
	public void addFirst(T data) {
		if(isEmpty()) {
			head = tail = new Node<T> (data, null, null);
		} else {
			head.prev = new Node<T> (data, null, head);
			head = head.prev;
		}
		size++;
	}

	// takes O(1)
	public T peekFirst() {
		if (head == null)
			throw new RuntimeException("ERROR: empty list");
		return head.data;
	}

	// takes O(1)
	public T peekLast() {
		if (tail == null)
			throw new RuntimeException("ERROR: empty list");
		return tail.data;
	}

	// takes O(n)
	public void add(T data, int index) {
		if (index < 0 || index >= size)
			throw new IndexOutOfBoundsException("ERROR: Invalid Index!");
		if (index == 0) addFirst(data);
		else if (index == size) addLast(data);
		else {
			Node<T> node = new Node<T> (data, null, null);
			int itr = 0;
			Node<T> temp = head;
			while(itr < index) {
				temp = temp.next;
			}
			node.next = temp.next;
			node.next.prev = node;
			temp.next = node;
			node.prev = temp;
			size++;
		}
	}

	// takes O(1)
	public T removeFirst() {
		if(head == null) 
			throw new RuntimeException("ERROR: No element found!");
		T data = head.data;
		head = head.next;
		--size;

		// if the list become empty
		if (isEmpty()) tail = null;
		// cleaning memory
		else head.prev = null;

		return data;
	}

	// takes O(1)
	public T removeLast() {
		if(tail == null)
			throw new RuntimeException("ERROR: No element found!");
		T data = tail.data;
		tail = tail.prev;
		--size;

		// if the list become empty
		if (isEmpty()) head = null;
		// cleaning memory
		else tail.next = null; // why is NullPointerException coming here?

		return data;
	}

	// remove arbritary node from linked list, takes O(1)
	public T remove(Node<T> node) {
		if (node == null)
			throw new IllegalArgumentException("ERROR: cannot delete null!");
		
		if (node.prev == null) removeFirst();
		if (node.next == null) removeLast();

		T data = node.data;
		node.prev.next = node.next;
		node.next.prev = node.prev;
		node = node.next = node.prev = null;
		node.data = null;
		--size;

		return data;
	}

	// takes O(n)
	public T removeAt(int index) {
		if (index < 0 || index >= size)
			throw new IndexOutOfBoundsException("ERROR: Invalid index!");
		
		Node<T> temp;
		int i;

		if (index < (size/2)) 
			for (i = 0, temp = head; i != index; ++i) 
				temp = temp.next;
		else
			for (i = size-1, temp = tail; i != index; --i) 
				temp = temp.prev;

		return remove(temp);
	}

	// return index of particular node in the list, takes O(n)
	public int indexOf(T o) {
		if (o.equals(head.data)) return 0;
		else if (o.equals(tail.data)) return size-1;
		else {
			int i;
			Node<T> temp = head.next;
			if (o == null){
				for (i = 1; i < size-1; ++i) {
					if (temp == null) return i;
					temp = temp.next;
				}
			} else {
				for (i = 1; i < size-1; ++i){
					if (o.equals(temp.data)) return i;
					temp = temp.next;
				}
			}
		}
		return -1;
	}

	public boolean contains(T o) {
		return indexOf(o) != -1;
	}

	@Override
	public java.util.Iterator<T> iterator() {
		return new java.util.Iterator<T>() {
			private Node<T> itr = head;

			@Override
			public boolean hasNext() {
				return itr != null;
			}

			@Override
			public T next() {
				T data = itr.data;
				itr = itr.next;
				return data;
			}

			@Override
			public void remove() {
				throw new UnsupportedOperationException("ERROR: this operation is not supported!");
			}
		};
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		Node<T> temp = head;
		while(temp.next != null) {
			sb.append(temp.data + ", ");
			temp = temp.next;
		}
		sb.append(temp.data + "]");
		return sb.toString();
	}

}