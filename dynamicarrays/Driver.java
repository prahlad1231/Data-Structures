import java.util.Iterator;

class Driver {
	public static void main(String[] args) {
		DynamicArray<String> arr = new DynamicArray<>();
		String str = "Demo String ";
		for(int i = 1; i <= 10; ++i)
			arr.add(str + i);
		System.out.println(arr.toString());	
		System.out.println(arr.indexOf("Demo String 8"));
		System.out.println(arr.contains("Demo String 11"));
		Iterator i = arr.iterator();
		while (i.hasNext()) {
			System.out.println(i.next());
		}
		arr.removeAt(0);
		System.out.println(arr.toString());
		arr.removeAt(20);
		System.out.println(arr.toString());
	}
}