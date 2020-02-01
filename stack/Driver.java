class Driver {
	public static void main(String[] args) {
		Stack<String> s = new Stack<>(20);
		String str = "Element ";
		for (int i = 0; i < 20; ++i) 
			s.push(str + i);
		System.out.println(s.toString());
		System.out.println(s.pop());
		s.push("New Element");
		System.out.println(s.toString());
		System.out.println(s.size());
		System.out.println(s.isEmpty());
		s.clear();
		System.out.println(s.toString());
		System.out.println(s.size());
		System.out.println(s.isEmpty());
		s.push("apple");
		s.push("ball");
		System.out.println(s.toString());
		System.out.println(s.size());
	}
}