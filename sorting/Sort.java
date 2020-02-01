/*
	January 23, 2020
	@ Prahlad Panthi

	NOTE: only use insertion sort for better performance in small number of elements
*/

class Sort {
	public static void bubblesort(int[] arr) {
		int len = arr.length;
		for (int i = 0; i < len; ++i) {
			for (int j = 0; j < len-i-1; ++j) {
				if (arr[j] > arr[j+1]) {
					int temp = arr[j];
					arr[j] = arr[j+1];
					arr[j+1] = temp;
				}
			}
		}
	}

	public static void insertionsort(int[] arr) {
		int len = arr.length;
		/*
			i = index of rightmost element
			j = index of iterator for leftmost elements
		*/
		int i, j, temp;
		i = 1;
		while (i < len) {
			j = i;
			while (j > 0 && arr[j-1] > arr[j]) {
				temp = arr[j-1];
				arr[j-1] = arr[j];
				arr[j] = temp;
				--j;
			}
			++i;
		}
	}

	// select minimum element and then swap it with element at ith index
	public static void selectionsort(int[] arr) {
		int len = arr.length;
		int i, j, min, temp;
		for (i = 0; i < len; ++i) {
			min = i;
			for (j = i+1; j < len; ++j) {
				if (arr[j] < arr[min]) min = j;
			}
			temp = arr[i];
			arr[i] = arr[min];
			arr[min] = temp;
		}
	}
}