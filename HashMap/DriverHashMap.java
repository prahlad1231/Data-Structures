package datastructures;

public class DriverHashMap {
	public static void main(String[] args) {
		HashMap<Integer, String> hashMap = new HashMap<Integer, String>();
		
		hashMap.insert(1, "Ram");
		hashMap.printMap();

		hashMap.insert(2, "Hari");
		hashMap.printMap();

		hashMap.insert(3, "Hemraj");
		hashMap.printMap();

		hashMap.insert(4, "Roshan");
		hashMap.printMap();

		hashMap.insert(5, "Raju");
		hashMap.printMap();

		hashMap.insert(6, "Binod");
		hashMap.printMap();

		hashMap.insert(6, "Shyam");
		hashMap.printMap();
	}
}