// applies only to integer numbers 

class Search {

	public int linearSearch(int[] arr,int element) {
		for (int i = 0; i < arr.length; ++i) {
			if (i == element) return i;
		}
		return -1;
	}

	public int binarySearch(int[] arr, int low, int high, int element) {
		if (low > high) return -1;
		int mid = (low + high) / 2;
		if (arr[mid] == element) return mid;
		else if (arr[mid] < element) 
			return binarySearch(arr, mid+1, high, element);
		else if (arr[mid] > element) 
			return binarySearch(arr, low, mid-1, element);
		return -1;
	}
}