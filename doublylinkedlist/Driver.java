class Driver {
	public static void main(String[] args) {
		DoublyLinkedList<String> list = new DoublyLinkedList<> ();
		String str = "Node ";
		for (int i = 1; i < 11; ++i) {
			list.add(str+i);
		}
		System.out.println(list.toString());
		System.out.println(list.removeLast());
		System.out.println(list.toString());
	}
} 