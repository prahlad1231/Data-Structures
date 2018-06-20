/*
	Linked List class
*/

class LinkedList {
	private Node start;
	private Node end;
	public int size;
	
	public LinkedList() {
		size = 0;
		start = null;
		end = null;
	}
	
	public boolean isEmpty() {
		return (start == null)?true:false;
	}
	
	public int getSize() {
		return size;
	}
	
	public void insert(int data) {
		size += 1;
		if(start == null) {
			Node first = new Node(data);
			start = first;
			end = first;
		}
		else {
			Node node = new Node(data);
			end.setNext(node);
			end = node;		
		}
	}
	
	public void insert(int data, int pos) {
		Node node = new Node(data);
		pos = pos -1;
		if(pos == 0) {
			if(start == null) {
				start = node;
				end = start;
			}
			else {
				node.setNext(start);
				start = node;
			}
			size += 1;
		}
		else if(pos == size) {
			insert(data);
		}
		else {
			if(pos > size) {
				System.out.println("Error: Cannot insert element");
				return;
			}
			Node itr = start;
			for(int i = 1; i < size; i++) {
				if(pos == i) {
					Node temp = itr.getNext();
					itr.setNext(node);
					node.setNext(temp);
					break;
				}
				itr = itr.getNext();
			}
			size += 1;
		}
	}
	
	public void delete() {
		if(size == 0) {
			System.out.println("No element to delete");
			return;
		}
		Node toDelete = start;
		Node tempEnd = start;
		while(toDelete != end) {
			tempEnd = toDelete;
			toDelete = toDelete.getNext();
		}
		end = tempEnd;
		end.setNext(null);
		size -= 1;
		System.out.println("Element deleted from list");
	}
	
	public void delete(int pos) {
		pos = pos - 1;
		if(pos == 0) {
			start = start.getNext();
		}
		else if(pos == size-1) {
			delete();
			return;
		}
		else {
			if(pos > size) {
				System.out.println("Error: Position out of range");
				return;
			}
			Node itr = start;
			for(int i = 1; i < size; i++) {
				if(pos == i) {
					Node temp = itr.getNext();
					temp = temp.getNext();
					itr.setNext(temp);
				}
				itr = itr.getNext();
			}
		}
		size -= 1;
		System.out.println("Element deleted from list");
	}
	
	public void displayList() {
		if(size == 0) {
			System.out.println("EMPTY\n");
			return;
		}
		else if(start.getNext() == null) {
			System.out.println(start.getData());
			return;
		}
		else {
			Node node = start;
			while(node.getNext() != null) {
				System.out.print(node.getData()+"->");
				node = node.getNext();
			}
			System.out.println(node.getData());
		}
	}
}